<%--
  Created by IntelliJ IDEA.
  cn.ytmj.el.User: ada
  Date: 2019/8/14
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@page import="cn.ytmj.el.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>sx</title>
    </head>
    <body>
        <%!
            int num=1;

        %>
        <%
            List<String> list = new ArrayList<String>();
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            list.add("5");
            list.add("6");
            request.setAttribute("list",list);
            request.setAttribute("num",num);
        %>

        <h2>sd</h2>
        ${requestScope.list[2]}
        <c:if test="${!empty list}">
            ${num}
        </c:if>
        <C:forEach begin="2" end="10" step="2" var="i" varStatus="s">
            ${i}
            ${s.index}
        </C:forEach>
<hr>
        <c:forEach items="${list}" var="i" varStatus="s">

            ${s.index} ${s.count} ${i}<br>
        </c:forEach>

    </body>
</html>
