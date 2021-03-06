package cn.ytmj.findlist.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @author rui
 * @create 2019-08-19 19:35
 */

/**
 * 敏感词汇过滤器
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.创建代理对象，增强getParameter方法
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否是getParameter方法
                if (method.getName().equals("getParameter")) {
                    //增强返回值
                    //获取返回值
                    String value = (String) method.invoke(req, args);
                    if (value != null) {
                        for (String str : list) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }

                    return value;
                }

                //判断方法名是否是 getParameterMap
                if (method.getName().equals("getParameterMap")) {
                    //增强返回值
                    //获取返回值
                    Map<String, String[]> map = (Map<String, String[]>) method.invoke(req, args);
                    Set<Map.Entry<String, String[]>> entries = map.entrySet();
                    Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
                    Map map1 = new HashMap(req.getParameterMap());
                    while (iterator.hasNext()) {
                        Map.Entry<String, String[]> next = iterator.next();
                        //获得value
                        String value = next.getValue()[0];
                        if (value != null) {
                            for (String str : list) {
                                if (value.contains(str)) {
                                    value = value.replaceAll(str, "***");
                                    String[] strings = next.getValue();
                                    strings[0] = value;
                                    map1.put(next.getKey(), strings);
                                } else {
                                    map1.put(next.getKey(), next.getValue());
                                }

                            }
                        }
                    }
                    return map1;
                }
                //判断方法名是否是 getParameterValue

                return method.invoke(req, args);
            }
        });

        //2.放行
        chain.doFilter(proxy_req, resp);
    }

    private List<String> list = new ArrayList<String>();

    public void init(FilterConfig config) throws ServletException {
        try {
            //获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
