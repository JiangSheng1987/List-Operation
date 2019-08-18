package cn.ytmj.findlist.web.servlet;

import cn.ytmj.findlist.domain.User;
import cn.ytmj.findlist.service.UserService;
import cn.ytmj.findlist.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author rui
 * @create 2019-08-17 18:47
 */
@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("up_id");
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("up_id" + id + user.getId() + user.getName());

        //调用service
        UserService userService = new UserServiceImpl();
        userService.update(id, user);
        //跳转至userListServlet
        response.sendRedirect(request.getContextPath() + "/userListServlet");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
