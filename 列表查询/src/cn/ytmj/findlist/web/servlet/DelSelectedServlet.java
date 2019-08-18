package cn.ytmj.findlist.web.servlet;

import cn.ytmj.findlist.service.UserService;
import cn.ytmj.findlist.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rui
 * @create 2019-08-17 21:21
 */
@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        String[] list = request.getParameterValues("u_id");

        //调用service
        UserService userService = new UserServiceImpl();
        try {
            userService.deleteSelected(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //跳转至userListServlet
        response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
