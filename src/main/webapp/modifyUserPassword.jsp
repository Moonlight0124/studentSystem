<%@ page import="cn.edu.hit.entity.User" %>
<jsp:useBean id="UserDao" class="cn.edu.hit.dao.impl.UserDaoImpl" scope="session"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    String uid = request.getParameter("uid");
    User user = UserDao.getByUid(uid);
    String uname = user.getUname();
    Integer isTeacher = user.getIsTeacher();
    String gender = user.getGender();
    String mid = user.getMid();
    String birthday = user.getBirthday();
    Integer age = user.getAge();
%>
<h1>修改<%=uname%><%
    if (isTeacher == 0) out.print("同学");
    else out.print("老师");
%>的密码</h1>
<form method="post" action="UserServlet" onsubmit="return validateForm()">
    <input type="hidden" name="uname" value=<%=uname%>>
    <input type="hidden" name="isTeacher" value=<%=isTeacher%>>
    <input type="hidden" name="gender" value=<%=gender%>>
    <input type="hidden" name="mid" value=<%=mid%>>
    <input type="hidden" name="birthday" value=<%=birthday%>>
    <input type="hidden" name="age" value=<%=age%>>
    <input type="hidden" name="modifyUser" value=<%if(isTeacher==0) out.print("student"); else out.print("teacher");%>>
    <input type="hidden" name="action" value="modify">
    <table>
        <tr>
            <td><%
                if (isTeacher == 0) out.print("学号");
                else out.print("教工号");
            %></td>
            <td><input type="text" readonly="ture" name="uid" value=<%=uid%>></td>
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
