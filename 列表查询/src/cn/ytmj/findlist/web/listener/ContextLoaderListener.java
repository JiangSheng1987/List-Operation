package cn.ytmj.findlist.web.listener; /**
 * @author rui
 * @create 2019-08-19 23:41
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@WebListener()
public class ContextLoaderListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public ContextLoaderListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------

    /**
     * 服务器启动后自动调用
     *
     * @param sce
     */
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
      //加载资源文件
        //获取ServletContex对象
        ServletContext servletContext = sce.getServletContext();
        //加载资源文件

        String initParameter = servletContext.getInitParameter("contextConfigLocation");
        //获取真实路径
        String realPath = servletContext.getRealPath(initParameter);
        //加载进内存
        try {
            FileInputStream fis=new FileInputStream(new File(realPath));
            System.out.println(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("没有找到");
        }
        System.out.println("ServletContext初始化");
    }

    /**
     * 服务器关闭后，ServletContext对象被销毁，服务器正常关闭后该方法被调用
     *
     * @param sce
     */
    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        System.out.println("ServletContext销毁");
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
