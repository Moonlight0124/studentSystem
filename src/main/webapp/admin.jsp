<%@ page import="cn.edu.hit.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.hit.entity.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="checkLogin.jsp" %>
<jsp:useBean id="UserDao" class="cn.edu.hit.dao.impl.UserDaoImpl" scope="session"/>
<jsp:useBean id="CourseDao" class="cn.edu.hit.dao.impl.CourseDaoImpl" scope="session"/>
<html>
<head>
    <title>管理平台</title>
    <script src="js/jquery-3.6.3.js"></script>
    <link rel="stylesheet" type="text/css" href="managementPage.css">
    <style>

    </style>
</head>
<body>
<%
    String aid = request.getParameter("aid");
%>
<div style="text-align: center">
    <h1>最高权限数据管理平台
    </h1>
    <div>您好！<%=aid%> <a href="modifyPassword.jsp?aid=<%=aid%>">修改密码请点击此处</a></div>

</div>

<h1>学生管理</h1>
<div style="text-align: center;font-size: 30px"><a href="addUser.jsp?group=学生&aid=<%=aid%>">增加学生</a></div>
<table>
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>生日</th>
        <th>专业</th>
        <th></th>
        <th></th>
    </tr>
    <%
        List<User> studentList = UserDao.getAll("select uid ,uname,gender,age,birthday,pwd,isTeacher,users.mid,major.mname from users,major where isTeacher=0 and users.mid=major.mid");
        String sid, sname, gender, birthday, mname;
        Integer age;
        for (User student : studentList) {
            sid = student.getUid();
            sname = student.getUname();
            gender = student.getGender();
            age = student.getAge();
            birthday = student.getBirthday();
            mname = student.getMname();
    %>
    <tr>
        <td><%=sid%>
        </td>
        <td><%=sname%>
        </td>
        <td><%
            if (gender.equals("m"))
                out.print("男");
            else
                out.print("女");
        %></td>
        <td><%=age%>
        </td>
        <td><%=birthday%>
        </td>
        <td><%=mname%>
        </td>
        <td><a href="modifyUser.jsp?aid=<%=aid%>&id=<%=sid%>">修改学生</a></td>
        <td><a onclick="return confirm('是否要删除<%=sname%>同学?')"
               href="UserServlet?action=remove&uid=<%=sid%>&aid=<%=aid%>">删除学生</a>
        </td>
    </tr>

    <%
        }
    %>
</table>
<h1>教师管理</h1>
<div style="text-align: center;font-size: 30px"><a href="addUser.jsp?group=教师&aid=<%=aid%>">增加教师</a></div>
<table>
    <tr>
        <th>教工号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>生日</th>
        <th>专业</th>
        <th></th>
        <th></th>
    </tr>
    <%
        List<User> teacherList = UserDao.getAll("select uid ,uname,gender,age,birthday,pwd,isTeacher,users.mid,major.mname from users,major where isTeacher=1 and users.mid=major.mid");
        String tid, tname;
        for (User teacher : teacherList) {
            tid = teacher.getUid();
            tname = teacher.getUname();
            gender = teacher.getGender();
            age = teacher.getAge();
            birthday = teacher.getBirthday();
            mname = teacher.getMname();
    %>
    <tr>
        <td><%=tid%>
        </td>
        <td><%=tname%>
        </td>
        <td><%
            if (gender.equals("m"))
                out.print("男");
            else
                out.print("女");
        %></td>
        <td><%=age%>
        </td>
        <td><%=birthday%>
        </td>
        <td><%=mname%>
        </td>
        <td><a href="modifyUser.jsp?aid=<%=aid%>&id=<%=tid%>">修改老师</a></td>
        <td><a onclick="return confirm('是否要删除<%=tname%>老师?')"
               href="UserServlet?action=remove&uid=<%=tid%>&aid=<%=aid%>">删除老师</a>
        </td>
    </tr>

    <%
        }
    %>
</table>
<h1>课程管理</h1>
<div style="text-align: center;font-size: 30px"><a href="addCourse.jsp?aid=<%=aid%>">新增课程</a></div>
<table>
    <tr>
        <th>课程编号</th>
        <th>课程名称</th>
        <th>任课教师</th>
        <th></th>
        <th></th>
    </tr>
    <%
        List<Course> courseList = CourseDao.getAll("select cid,cname,course.tid,users.uname from users,course where users.isTeacher=1 and users.uid=course.tid");
        String cid, cname;
        for (Course course : courseList) {
            cid = course.getCid();
            cname = course.getCname();
            tname = course.getTname();
    %>
    <tr>
        <td><%=cid%>
        </td>
        <td><%=cname%>
        </td>
        <td><%=tname%>
        </td>
        <td><a href="modifyCourse.jsp?cid=<%=cid%>&aid=<%=aid%>">修改课程</a></td>
        <td><a onclick="return confirm('是否要删除<%=cname%>课程?')"
               href="CourseServlet?action=remove&cid=<%=cid%>&aid=<%=aid%>">删除课程</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<h1>成绩查询</h1>
<td>
    <div style="text-align: center">
        <%--       下拉获取课程 --%>
        <select name="cid" id="cid" onclick="selectCourse()">
            <option name="cid" value="Null" selected>-请选择课程-</option>
            <%
                courseList = CourseDao.getAll("select cid,cname,course.tid,users.uname from users,course where users.isTeacher=1 and users.uid=course.tid");
                for (Course course : courseList) {
                    cid = course.getCid();
                    cname = course.getCname();

            %>
            <option name="cid" value="<%=cid%>"><%=cname%>
            </option>
            <%
                }
            %>
        </select>
    </div>
    <script>
        function selectCourse() {
            let cid = $("option[name='cid']:selected").val();
            let sname, cname, result = "<tr> <th>课程编号</th> <th>课程名称</th> <th>学生姓名</th> <th>成绩</th> </tr>";
            $.ajax({
                url: "CourseSelectionServlet",
                type: "post",
                async: false,
                data: {
                    cid: cid,
                    action: "getAll",
                },
                dataType: "json",
                success: function (data) {
                    for (let i = 0; i < data.length; i++) {
                        let sid = data[i].sid;

                        //获取学生名字
                        $.ajax({
                            url: "UserServlet",
                            type: "post",
                            async: false,
                            data: {
                                uid: sid,
                                action: "getByUid",
                            },
                            dataType: "json",
                            success: function (dataUser) {
                                sname = dataUser.uname;
                            }
                        });

                        //获取课程名字
                        $.ajax({
                            url: "CourseServlet",
                            type: "post",
                            async: false,
                            data: {
                                cid: cid,
                                action: "getByCid",
                            },
                            dataType: "json",
                            success: function (dataCourse) {
                                cname = dataCourse.cname;
                            }
                        });

                        let score = data[i].score;
                        result += "<tr><td>" + cid + "</td><td>" + cname + "</td><td>" + sname + "</td><td>" + score + "</td></tr>";
                    }
                    $("#courseId").html(result);
                    console.log(result);
                },
            });

        }
    </script>
    <table id="courseId">
    </table>
</body>
</html>
