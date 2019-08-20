package cn.ytmj.findlist.test;

/**
 * @author rui
 * @create 2019-08-20 19:40
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@WebServlet("/testGetParamerMap")
public class TestGetParamerMap extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> next = iterator.next();
            //获得value
            String key = next.getKey();
            String value = next.getValue()[0];
            System.out.println(key + ":" + value);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}