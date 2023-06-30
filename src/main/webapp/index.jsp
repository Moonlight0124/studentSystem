<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        a {
            text-align: center;
            font-size: 20px;
            font-family: Boldface;
            color: black;
        }
    </style>

</head>
<body>
<div style="text-align: center;">
    <h1 >请选择登录方式</h1>
    <h2>
        <div><a href="Login.jsp?group=admin">管理员登录</a></div>
        <div><a href="Login.jsp?group=student">学生登录</a></div>
        <div><a href="Login.jsp?group=teacher">教师登录</a></div>
    </h2>


</div>

</body>
</html>