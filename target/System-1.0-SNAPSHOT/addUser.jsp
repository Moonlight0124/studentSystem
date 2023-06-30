<%@ page import="cn.edu.hit.entity.Major" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="checkLogin.jsp" %>
<jsp:useBean id="studentDao" class="cn.edu.hit.dao.impl.UserDaoImpl" scope="session"/>
<jsp:useBean id="majorDao" class="cn.edu.hit.dao.impl.MajorDaoImpl" scope="session"/>
<html>
<head>
    <title>新增信息</title>
    <link rel="stylesheet" type="text/css" href="submitPage.css">
    <script src="js/jquery-3.6.3.js"></script>
    <script>
        //判断uid是否已经存在于数据库，若存在进行提示
        function checkUid() {
            $.ajax({
                url: "UserServlet",
                type: "post",
                data: {
                    action: 'getAll'
                },
                dataType: "json",
                success: function (data) {
                    console.log("checkUid success");
                    console.log(data);
                    let find = false;
                    let formSid = $("#uid").val();
                    console.log(formSid);
                    for (let i = 0; i < data.length; ++i) {
                        if (formSid == data[i].uid) {
                            find = true;
                            break;
                        }
                    }
                    if (find == true) {
                        $("#uid_div").html("该工号已存在,请重新输入!");
                    } else {
                        $("#uid_div").html("");
                    }
                },
                error: function (error) {
                    console.log("checkUid error");
                    console.log(error);
                }
            })

        }
    </script>

    <script>
        //表单非空验证
        function validateForm() {
            var uidObj = document.getElementById("uid");
            var unameObj = document.getElementById("unameId");
            var birthdayObj = document.getElementById("birthdayId");
            var ageObj = document.getElementById("ageId");
            var majorObj = document.getElementById("mid")

            if (uidObj.value == "") {
                alert("工号不能为空!");
                return false;
            }
            if (uidObj.value.length > 10) {
                alert("学号不能超过10位");
                return false;
            }

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
    String group = request.getParameter("group");
    String aid = request.getParameter("aid");
%>
<h1>新增<%=group%>信息</h1>
<form id="formId" method="post" action="UserServlet" onsubmit="return validateForm()">
    <input type="hidden" name="pwd" id="pwdId" value="123456"/>
    <input type="hidden" name="isTeacher" id="isTeacherId"
           value="<%if(group.equals("学生")) out.print("0");
           else out.print("1");%>"/>
    <input type="hidden" name="action" id="actionId" value="add"/>
    <input type="hidden" name="aid" id="aId" value=<%=aid%> >
    <table>
        <tr>
            <td><%
                if (group.equals("学生")) out.print("学号");
                else out.print("教工号");
            %></td>
            <td><input type="text" name="uid" id="uid" onblur="checkUid()"></td>
            <td>
                <div id="uid_div" style="color: red"></div>
            </td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="text" name="uname" id="unameId"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td><label><input type="radio" name="gender" value="m" checked>男</label>
                <label><input type="radio" name="gender" value="f">女</label></td>
        </tr>
        <tr>
            <td>生日</td>
            <td><input type="date" name="birthday" id="birthdayId"></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input type="number" name="age" id="ageId"></td>
        </tr>
        <tr>
            <td>专业</td>
            <td>
                <select name="mid" id="mid">
                    <option name="mid" value="Null" selected>-请选择专业-</option>
                    <%
                        List<Major> majorList = majorDao.getAll("select * from major");
                        String mid, mname;
                        for (Major major : majorList) {
                            mid = major.getMid();
                            mname = major.getMname();

                    %>
                    <option name="mid" value="<%=mid%>"><%=mname%>
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
