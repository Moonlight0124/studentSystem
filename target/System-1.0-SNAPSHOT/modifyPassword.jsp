<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="checkLogin.jsp" %>
<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" type="text/css" href="submitPage.css">

    <script src="js/jquery-3.6.3.js"></script>
    <script>
        function validateForm() {
            let pwd = document.getElementById("pwdId").value;
            let pwdReapeat = document.getElementById("pwdRepeat").value;
            if (pwd == "") {
                alert("新密码不能为空!");
                return false;
            }
            if (pwdReapeat == "") {
                alert("重复密码不能为空!");
                return false;
            }
            if (pwd != pwdReapeat) {
                return false;
            }
            return true;
        }

        function checkRepeat() {
            let pwd = document.getElementById("pwdId").value;
            let pwdReapeat = document.getElementById("pwdRepeat").value;
            if (pwd != pwdReapeat) {
                $("#pwdReapeat_div").html("<font color='red'>两次输入密码不一致!</font>");
            } else {
                $("#pwdReapeat_div").html("");
            }

        }
    </script>
</head>
<body>
<%
    String aid = request.getParameter("aid");
%>
<form method="post" action="AdminServlet" onsubmit="return validateForm()">
    <input type="hidden" name="action" value="modify">
    <table>
        <tr>
            <td>管理员ID</td>
            <td><input type="text" readonly="ture" id="aid" name="aid" value=<%=aid%>></td>
        </tr>
        <tr>
            <td>新密码</td>
            <td><input type="password" id="pwdId" name="pwd"></td>
        </tr>
        <tr>
            <td>重新输入密码</td>
            <td><input type="password" id="pwdRepeat" onblur="checkRepeat()"></td>
            <td>
                <div id="pwdReapeat_div"></div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit"></td>
        </tr>
    </table>
</form>
</body>
</html>
