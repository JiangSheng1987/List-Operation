package cn.ytmj.findlist.web.servlet;

import cn.ytmj.findlist.domain.User;
import cn.ytmj.findlist.service.UserService;
import cn.ytmj.findlist.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author rui
 * @create 2019-08-15 23:34
 */
@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用userService查询
        UserService service=new UserServiceImpl();
        List<User> list = service.findAll();
        //将list存入requist域
        request.setAttribute("List",list);
        //转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
