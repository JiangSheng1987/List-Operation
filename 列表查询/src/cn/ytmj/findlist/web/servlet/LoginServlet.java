package cn.ytmj.findlist.web.servlet;

import cn.ytmj.findlist.domain.Login;
import cn.ytmj.findlist.domain.User;
import cn.ytmj.findlist.service.LoginService;
import cn.ytmj.findlist.service.impl.LoginServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author rui
 * @create 2019-08-16 21:22
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取验证码数据
        String verifycode = request.getParameter("verifycode");

        //验证码校验
        HttpSession session = request.getSession();
        Object checkcode_server = session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.toString().equalsIgnoreCase(verifycode)) {
            //不正确
            //提示信息
            request.setAttribute("login_msg", "验正码错误");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        //封装对象
        Map<String, String[]> map = request.getParameterMap();
        Login login = new Login();
        try {
            BeanUtils.populate(login, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service查询
        LoginService service = new LoginServiceImpl();
        Login loginUser = service.login(login);
        //判断是否登录成功
        if (loginUser != null) {
            //成功
            //将用户存入session

            session.setAttribute("user", loginUser);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            //失败
            //提示信息
            request.setAttribute("login_msg", "用户名或密码错误");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
