<%@ page import="cn.edu.hit.entity.User" %>
<%@ page import="cn.edu.hit.entity.CourseSelection" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.hit.entity.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="UserDao" class="cn.edu.hit.dao.impl.UserDaoImpl" scope="session"/>
<jsp:useBean id="CourseDao" class="cn.edu.hit.dao.impl.CourseDaoImpl" scope="session"/>
<jsp:useBean id="CourseSelectionDao" class="cn.edu.hit.dao.impl.CourseSelectionDaoImpl" scope="session"/>

<html>
<head>
    <title>教师平台</title>
    <script src="js/jquery-3.6.3.js"></script>
    <link rel="stylesheet" type="text/css" href="managementPage.css">

</head>
<body>
<%
    String tid = request.getParameter("uid");
    User teacher = UserDao.getByUid(tid);
    String tname = teacher.getUname();
%>
<div style="text-align: center">
    <h1>教师信息管理平台</h1>
    <p>您好，<%=tname%>老师！<a href="modifyUserPassword.jsp?uid=<%=tid%>">修改密码请点击此处
    </a></p>
</div>


<h1>成绩录入</h1>
<table>
    <tr>
        <th>课程编号</th>
        <th>课程名称</th>
        <th>学号</th>
        <th>学生姓名</th>
        <th>分数</th>
        <th></th>
    </tr>
    <%
        List<CourseSelection> courseSelectionList = CourseSelectionDao.getAll("select * from courseSelection where tid='" + tid + "'");
        for (CourseSelection courseSelection : courseSelectionList) {
            String cid = courseSelection.getCid();
            Course course = CourseDao.getByCid(cid);
            String cname = course.getCname();
            String sid = courseSelection.getSid();
            User student = UserDao.getByUid(sid);
            String sname = student.getUname();
            Integer score = courseSelection.getScore();
    %>
    <form method="post" action="CourseSelectionServlet">
        <input type="hidden" name="cid" value=<%=cid%>>
        <input type="hidden" name="sid" value=<%=sid%>>
        <input type="hidden" name="tid" value=<%=tid%>>
        <input type="hidden" name="action" value="modify">
        <tr>
            <td><%=cid%>
            </td>
            <td><%=cname%>
            </td>
            <td><%=sid%>
            </td>
            <td><%=sname%>
            </td>
            <td><span style="width:20%"><input type="number" name="score" id="scoreId" value=<%=score%>></span>
            </td>
            <td><input type="submit" value="提交"></td>
        </tr>
    </form>
    <%
        }
    %>
</table>
<h1>成绩查询</h1>
<table>
    <tr>
        <th>课程编号</th>
        <th>课程名称</th>
        <th>学号</th>
        <th>学生姓名</th>
        <th>分数</th>

    </tr>
    <%
        courseSelectionList = CourseSelectionDao.getAll("select * from courseSelection where tid='" + tid + "'");
        for (CourseSelection courseSelection : courseSelectionList) {
            String cid = courseSelection.getCid();
            Course course = CourseDao.getByCid(cid);
            String cname = course.getCname();
            String sid = courseSelection.getSid();
            User student = UserDao.getByUid(sid);
            String sname = student.getUname();
            Integer score = courseSelection.getScore();
    %>
    <tr>
        <td><%=cid%>
        </td>
        <td><%=cname%>
        </td>
        <td><%=sid%>
        </td>
        <td><%=sname%>
        </td>
        <td><%=score%>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
