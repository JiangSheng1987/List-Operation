<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.ytmj.el.User" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: ada
  Date: 2019/8/15
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>显示数据到表格中</title>
        <style type="text/css">
            .bor{
                background-color: darkgray;
            }
        </style>
    </head>
    <body>
        <%
            Date date = new Date();
            List list = new ArrayList();
            list.add(new User("张三", 21, date));
            list.add(new User("张三", 21, date));
            list.add(new User("李四", 22, date));
            list.add(new User("王五", 23, date));
            list.add(new User("小名", 24, date));
            list.add(new User("李华", 25, date));
            request.setAttribute("list", list);
        %>
        <table border="1" align="center">
            <tr>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>日期</th>
            </tr>
            <c:forEach items="${list}" var="i" varStatus="s">
                <c:if test="${s.count%2==0}">
                <tr class="bor">
                    <td>
                            ${s.count}
                    </td>
                    <td>
                            ${i.name}
                    </td>
                    <td>
                            ${i.age}
                    </td>
                    <td>
                            ${i.simDate}
                    </td>
                </tr>
                </c:if>
                <c:if test="${s.count%2==1}">
                    <tr >
                        <td>
                                ${s.count}
                        </td>
                        <td>
                                ${i.name}
                        </td>
                        <td>
                                ${i.age}
                        </td>
                        <td>
                                ${i.simDate}
                        </td>
                    </tr>
                </c:if>
            </c:forEach>


        </table>

    </body>
</html>
