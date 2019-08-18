<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <!-- 使用Edge最新的浏览器的渲染方式 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
        width: 默认宽度与设备的宽度相同
        initial-scale: 初始的缩放比，为1:1 -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>用户信息管理系统</title>

        <!-- 1. 导入CSS的全局样式 -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
        <script src="js/jquery-2.1.0.min.js"></script>
        <!-- 3. 导入bootstrap的js文件 -->
        <script src="js/bootstrap.min.js"></script>
        <style type="text/css">
            td, th {
                text-align: center;
            }
            .tb {
                float: right;
                margin: 5px;
            }
        </style>
        <script>
            function deleteUser(id) {
                if (confirm("确定删除吗?")) {
                    location.href = " ${pageContext.request.contextPath}/delUserServlet?id=" + id;
                }

            }

            window.onload = function delSelected() {

                document.getElementById("delSelected").onclick = function () {
                    if (window.confirm("确认删除？")) {
                        //判断是否有选中
                        var flag = false;
                        var elementById = document.getElementsByName("u_id");
                        for (var i = 0; i < elementById.length; i++) {
                            if (elementById[i].checked) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            document.getElementById("form").submit();
                        }
                    }
                }
                document.getElementById("firstCb").onclick = function () {
                    //获取所有选择框
                    var elementById = document.getElementsByName("u_id");
                    for (var i = 0; i < elementById.length; i++) {
                        elementById[i].checked = this.checked;
                    }
                }

            }
        </script>

    </head>
    <body>
        <div class="container">

            <h3 style="text-align: center">用户信息列表</h3>
            <div style="float: left">
                <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet">
                    <div class="form-group">
                        <label for="exampleInputName2">姓名</label>
                        <input type="text" class="form-control" id="exampleInputName2" name="name" value="${condition.name[0]}" placeholder="Jane Doe">
                    </div>
                    <div class="form-group">
                        <label for="address">籍贯</label>
                        <input type="text" class="form-control" id="address" name="address" value="${condition.address[0]}" placeholder="河南">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail2">邮箱</label>
                        <input type="email" class="form-control" id="exampleInputEmail2" value="${condition.email[0]}" name="email"
                               placeholder="jane.doe@example.com">
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
            </div>
            <div class="tb">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
                <a class="btn btn-primary" href="javascript:delSelected();" id="delSelected">删除选中</a>
            </div>

            <br>
            <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
                <table border="1" class="table table-bordered table-hover">
                    <tr class="success">
                        <td><input type="checkbox" id="firstCb"></td>
                        <th>编号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>籍贯</th>
                        <th>QQ</th>
                        <th>邮箱</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${pageBean.list}" var="i" varStatus="s">
                        <tr>
                            <td><input type="checkbox" name="u_id" value=${i.id}></td>
                            <td>${s.count}</td>
                            <td>${i.name}</td>
                            <td>${i.gender}</td>
                            <td>${i.age}</td>
                            <td>${i.address}</td>
                            <td>${i.qq}</td>
                            <td>${i.email}</td>
                            <td><a class="btn btn-default btn-sm"
                                   href="${pageContext.request.contextPath}/findUserServlet?up_id=${i.id}">修改</a>&nbsp;

                                <a class="btn btn-default btn-sm" href="javascript:deleteUser(${i.id});">删除</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
            <div style="float: left">
                <nav>
                    <ul class="pagination">
                        <%-- 判断是否是第一页--%>
                        <c:if test="${pageBean.currentPage==1}">
                        <li class="disabled">
                            </c:if>
                            <c:if test="${pageBean.currentPage!=1}">
                        <li>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pageBean.currentPage-1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach var="i" varStatus="s" step="1" begin="1" end="${pageBean.totalPage}">
                            <c:if test="${pageBean.currentPage == i}">
                                <li class="active">

                                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}"

                                       name="li">${i}</a></li>
                            </c:if>
                            <c:if test="${pageBean.currentPage != i}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}"
                                       name="li">${i}</a></li>
                            </c:if>
                        </c:forEach>
                        <%-- 判断是否是第一页--%>
                        <c:if test="${pageBean.currentPage >= pageBean.totalPage}">
                        <li class="disabled">
                            </c:if>
                            <c:if test="${pageBean.currentPage!=pageBean.totalPage}">
                        <li>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pageBean.currentPage+1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}"
                               aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <span style="font-size: 25px ;margin-left: 5px">共${pageBean.totalCount}条数据，共${pageBean.totalPage}页</span>
                    </ul>
                </nav>
            </div>
        </div>
    </body>
</html>
