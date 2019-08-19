package cn.ytmj.findlist.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author rui
 * @create 2019-08-19 14:36
 */

/**
 * 登录验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强转
        HttpServletRequest req1 = (HttpServletRequest) req;
        //获取资源的请求路径
        String uri = req1.getRequestURI();
        //判断是否包含登录相关资源路径,要注意排除 css/js/图片/验证码等
        if (uri.contains("login.jsp") || uri.contains("/loginServlet")||uri.contains("/checkCode")||uri.contains("/js/")||uri.contains("/css/")||uri.contains("/fonts/")) {
            //要登录，放行
            chain.doFilter(req, resp);
        } else {
            //不包含，需要验证用户是否登录
            //获取session的user
            Object user = req1.getSession().getAttribute("user");
            if (user != null) {
                //已经登录,放行
                chain.doFilter(req, resp);
            } else {
                //没有登录需要跳转登录页面
                req1.setAttribute("login_msg", "您还没登录，请登录后查看");
                req1.getRequestDispatcher("/login.jsp").forward(req1, resp);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
