<%@ page import="cn.edu.hit.entity.User" %>
<%@ page import="cn.edu.hit.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.hit.entity.CourseSelection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="UserDao" class="cn.edu.hit.dao.impl.UserDaoImpl" scope="session"/>
<jsp:useBean id="CourseDao" class="cn.edu.hit.dao.impl.CourseDaoImpl" scope="session"/>
<jsp:useBean id="CourseSelectionDao" class="cn.edu.hit.dao.impl.CourseSelectionDaoImpl" scope="session"/>

<html>
<head>
    <title>学生操作平台</title>
    <link rel="stylesheet" type="text/css" href="managementPage.css">

</head>
<body>
<%
    String sid = request.getParameter("uid");
    User student = UserDao.getByUid(sid);
    String sname = student.getUname();

%>
<div style="text-align: center">
    <h1>学生信息管理平台</h1>
    <p>您好,<%=sname%>同学!<a href="modifyUserPassword.jsp?uid=<%=sid%>">修改密码请点击此处</a>
    </p>
</div>

<h1>选课界面</h1>
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
        for (Course course : courseList) {
            String cid = course.getCid();
            String cname = course.getCname();
            String tname = course.getTname();
            String tid = course.getTid();

    %>
    <tr>
        <td><%=cid%>
        </td>
        <td><%=cname%>
        </td>
        <td><%=tname%>
        </td>
        <td><a href="CourseSelectionServlet?action=add&cid=<%=cid%>&tid=<%=tid%>&score=0&sid=<%=sid%>">选课</a></td>
        <td><a onclick="return confirm('是否要退选<%=cname%>课程?')"
               href="CourseSelectionServlet?action=remove&sid=<%=sid%>&cid=<%=cid%>">退选</a></td>
    </tr>
    <%
        }
    %>
</table>
<h1>成绩查询</h1>
<table>
    <tr>
        <th>课程编号</th>
        <th>课程名称</th>
        <th>任课教师</th>
        <th>分数</th>
    </tr>
    <%
        List<CourseSelection> courseSelectionList = CourseSelectionDao.getAll("select * from courseSelection where sid='" + sid + "'");
        for (CourseSelection courseSelection : courseSelectionList) {
            String cid = courseSelection.getCid();
            Course course = CourseDao.getByCid(cid);
            String cname = course.getCname();
            User teacher = UserDao.getByUid(course.getTid());
            String tname = teacher.getUname();
            Integer score = courseSelection.getScore();
    %>
    <tr>
        <td><%=cid%>
        </td>
        <td><%=cname%>
        </td>
        <td><%=tname%>
        </td>
        <td><%=score%>
        </td>
    </tr>
    </td>
    <%
        }
    %>
</table>

</body>
</html>
