package cn.ytmj.findlist.web.servlet;

import cn.ytmj.findlist.domain.PageBean;
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
import java.util.Map;

/**
 * @author rui
 * @create 2019-08-18 9:38
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if (null == currentPage || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (null == rows || "".equals(rows)) {
            rows = "5";
        }
        Map<String, String[]> map = request.getParameterMap();
        //调用service
        UserService userService = new UserServiceImpl();
        PageBean<User> pageBean = userService.findUserByPage(Integer.parseInt(currentPage), Integer.parseInt(rows), map);
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("condition",map);
        //转发
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
