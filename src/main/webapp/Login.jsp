<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆界面</title>
    <script type="text/javascript" src="js/jquery-3.6.3.js"></script>
    <link rel="stylesheet" type="text/css" href="submitPage.css">
    <script src="js/jquery-3.6.3.js"></script>
    <script>
        //表单非空验证
        function validateForm() {
            var uidObj = document.getElementById("uid");
            var pwdObj = document.getElementById("pwdId");

            if (uidObj.value == "") {
                alert("工号不能为空!");
                return false;
            }
            if (uidObj.value.length > 10) {
                alert("工号不能超过10位");
                return false;
            }

            if (pwdObj.value == "") {
                alert("密码不能为空!");
                return false;
            }
            if (pwdObj.value.length > 20) {
                alert("密码不能超过20位!");
                return false;
            }

            return true;
        }
    </script>

</head>
<body>
<%
    String user = request.getParameter("group");
%>
<h1><%
    if (user.equals("student")) out.print("学生");
    else if (user.equals("teacher")) out.print("教师");
    else if (user.equals("admin")) out.print("管理员");%>
    登录平台
</h1>
<form action="LoginServlet" method="post" onsubmit="return validateForm()">
    <input type="hidden" name="group" value=<%=user%>>
    <table>
        <tr>
            <td><%
                if (user.equals("student")) out.print("学生");
                else if (user.equals("teacher")) out.print("教师");
                else if (user.equals("admin")) out.print("管理员");
            %>ID
            </td>
            <td><input type="text" name=<%if(user.equals("admin")) out.print("aid"); else out.print("uid");%> id="uid">
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="pwd" id="pwdId"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>
