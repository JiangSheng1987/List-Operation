<%--
  Created by IntelliJ IDEA.
  cn.ytmj.el.User: ada
  Date: 2019/8/8
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <script>
        window.onload=function () {

            var check=document.getElementById("check");
            check.onclick=function () {
                var date=new Date().getTime();
                check.src="/session/checkCodeServlet?time="+date;
            }
        }

    </script>
</head>
<body>

<form action="/session/loginServlet" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td>
                <input type="text" name="username" placeholder="请输入用户名">
            </td>
        </tr>
        <tr>
            <td>密&ensp;&ensp;码：</td>
            <td><input type="password" name="password" placeholder="请输入密码"></td>
        </tr>
        <tr>
            <td><img src="/session/checkCodeServlet" id="check"></td>
            <td><input type="text" name="check" placeholder="请输入验证码"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="登录"></td>
        </tr>
    </table>
</form>


</body>
</html>
