<%@ page import="cn.edu.hit.entity.Major" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.hit.dao.MajorDao" %>
<%@ page import="cn.edu.hit.dao.impl.MajorDaoImpl" %>
<%@ page import="cn.edu.hit.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="checkLogin.jsp" %>
<jsp:useBean id="userDao" class="cn.edu.hit.dao.impl.UserDaoImpl" scope="session"/>
<jsp:useBean id="majorDao" class="cn.edu.hit.dao.impl.MajorDaoImpl" scope="session"/>
<html>
<head>
    <title>修改信息</title>
    <link rel="stylesheet" type="text/css" href="submitPage.css">

    <script src="js/jquery-3.6.3.js"></script>
    <script>
        //表单非空验证
        function validateForm() {
            var unameObj = document.getElementById("snameId");
            var birthdayObj = document.getElementById("birthdayId");
            var ageObj = document.getElementById("ageId");
            var majorObj = document.getElementById("mid")

            if (unameObj.value == "") {
                alert("姓名不能为空!");
                return false;
            }
            if (unameObj.value.length > 20) {
                alert("姓名不能超过20位");
                return false;
            }

            if (ageObj.value == "") {
                alert("年龄不能为空!");
                return false;
            }
            if (ageObj.value < 0) {
                alert("年龄不能为负数，请重新输入年龄");
                return false;
            }
            if (birthdayObj.value == "") {
                alert("生日不能为空!");
                return false;
            }
            if (majorObj.value == "Null") {
                alert("请选择专业!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<%
    String id = request.getParameter("id");
    User user = userDao.getByUid(id);
    String uname = user.getUname();
    String gender = user.getGender();
    Integer age = user.getAge();
    String birthday = user.getBirthday();
    String mid = user.getMid();
    String pwd = user.getPwd();
    Integer isTeacher = user.getIsTeacher();
    String aid = request.getParameter("aid");
%>
<h1>修改<%
    if (isTeacher == 0) out.print("学生");
    else out.print("教师");
%>信息</h1>
<form id="formId" method="post" action="UserServlet" onsubmit="return validateForm()">
    <input type="hidden" name="pwd" id="pwdId" value=<%=pwd%>>
    <input type="hidden" name="isTeacher" id="isTeacherId" value=<%=isTeacher%>>
    <input type="hidden" name="action" id="actionId" value="modify"/>
    <input type="hidden" name="modifyUser" value="NULL">
    <input type="hidden" name="aid" value=<%=aid%>>
    <table>
        <tr>
            <td>
                <%
                    if (isTeacher == 0) out.print("学号");
                    else out.print("教工号");
                %>
            </td>
            <td><input type="text" name="uid" id="stuId" readonly="true" value=<%=id%>></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="text" name="uname" id="snameId" value=<%=uname%>></td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <label><input type="radio" name="gender"
                              value="m" <%if(gender.equals("m")) out.print("checked");%>>男</label>
                <label><input type="radio" name="gender"
                              value="f" <%if(gender.equals("f")) out.print("checked");%>>女</label></td>
        </tr>
        <tr>
            <td>生日</td>
            <td><input type="date" name="birthday" id="birthdayId" value=<%=birthday%>></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input type="number" name="age" id="ageId" value=<%=age%>></td>
        </tr>
        <tr>
            <td>专业</td>
            <td>
                <select name="mid" id="mid">
                    <option name="mid" value="Null">-请选择专业-</option>
                    <%
                        List<Major> majorList = majorDao.getAll("select * from major");
                        String majorMid, mname;
                        for (Major major : majorList) {
                            majorMid = major.getMid();
                            mname = major.getMname();

                    %>
                    <option name="mid" value="<%=majorMid%>" <%
                        if (mid.equals(majorMid)) out.print("selected");%>><%=mname%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>
