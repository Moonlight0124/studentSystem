<%@ page import="cn.edu.hit.entity.Admin" %>
<%@ page import="cn.edu.hit.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    User student01 = (User) session.getAttribute("student");
    User teacher01 = (User) session.getAttribute("teacher");
    if (admin == null && student01 == null && teacher01 == null) {
        out.print("<div style=\"text-align: center;font-size: 20px\">" +
                "当前用户为非法用户，请选择一种身份登陆系统" + "<br>" +
                "<a href=\"Login.jsp?group=admin\">管理员登录</a>" + "<br>" +
                "<a href=\"Login.jsp?group=teacher\">老师登录</a>" + "<br>" +
                "<a href=\"Login.jsp?group=student\">学生登录</a>" + "<br>" +
                "</div>");
        return;
    }

%>