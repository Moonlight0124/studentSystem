<%@ page import="cn.edu.hit.entity.Course" %>
<%@ page import="cn.edu.hit.entity.Major" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.hit.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="checkLogin.jsp" %>
<jsp:useBean id="CourseDao" class="cn.edu.hit.dao.impl.CourseDaoImpl" scope="session"/>
<jsp:useBean id="UserDao" class="cn.edu.hit.dao.impl.UserDaoImpl" scope="session"/>
<html>
<head>
    <title>课程修改</title>
    <link rel="stylesheet" type="text/css" href="submitPage.css">
    <script src="js/jquery-3.6.3.js"></script>
    <script>
        //表单非空验证
        function validateForm() {
            var cnameObj = document.getElementById("cnameId");
            var teacherObj = document.getElementById("tid")

            if (cnameObj.value == "") {
                alert("课程名不能为空!");
                return false;
            }
            if (cnameObj.value.length > 30) {
                alert("课程名不能超过30位！");
                return false;
            }
            if (teacherObj.value == "Null") {
                alert("请选择授课教师!");
                return false;
            }
            return true;
        }
    </script>
    <script>
        //修改选课信息中课程对应的教师
        function modify_selectionCourse() {
            let tid = $("#tid").val();
            let cid = $("#cid").val();
            console.log(tid);
            console.log(cid);
            console.log("executed");
            $.ajax({
                url: "CourseSelectionServlet",
                data: {
                    action: "modifyTeacher",
                    tid: tid,
                    cid: cid,
                },
                type: "post",
                dataType: "json",
            });
        }
    </script>

</head>
<body>
<%
    String cid = request.getParameter("cid");
    Course course = CourseDao.getByCid(cid);
    String cname = course.getCname();
    String tid = course.getTid();
    String aid = request.getParameter("aid");
%>
<form method="post" action="CourseServlet" onsubmit="return validateForm()">
    <input type="hidden" name="action" value="modify">
    <input type="hidden" name="aid" value=<%=aid%>>
    <table>
        <tr>
            <td>课程编号</td>
            <td><input type="text" name="cid" id="cid" readonly="true" value=<%=cid%>></td>
        </tr>
        <tr>
            <td>课程名称</td>
            <td><input type="text" name="cname" id="cnameId" value=<%=cname%>></td>
        </tr>
        <tr>
            <td>任课教师</td>
            <td>
                <select name="tid" id="tid" >
                    <option name="tid" value="Null">-请选择教师-</option>
                    <%
                        List<User> userList = UserDao.getAll("select uid ,uname,gender,age,birthday,pwd,isTeacher,users.mid,major.mname from users,major where isTeacher=1 and users.mid=major.mid");
                        String Tid, Tname;
                        for (User user : userList) {
                            Tid = user.getUid();
                            Tname = user.getUname();

                    %>
                    <option name="tid" value="<%=Tid%>" <%if (tid.equals(Tid)) out.print("selected");%>><%=Tname%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="提交" onclick="modify_selectionCourse()"></td>
        </tr>
    </table>
</form>
</body>
</html>
