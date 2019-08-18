import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author rui
 * @create 2019-08-08 23:38
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        Object checkCode_session = session.getAttribute("checkCode_session");
        if(checkCode_session.toString().equalsIgnoreCase(check)){
            if("张三".equals(username)&&"123".equals(password)){//需要查询数据库
                request.setAttribute("login_success","用户名密码错误");
            }else{//失败
                //存储提示信息到request
                request.setAttribute("login_error","用户名密码错误");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
                response.sendRedirect(request.getContextPath()+"");
            }
        }else{
            //存储提示信息到request
            request.setAttribute("check_error","验证码错误");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        System.out.println();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
