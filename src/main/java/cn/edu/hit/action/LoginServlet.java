package cn.edu.hit.action;

import cn.edu.hit.dao.LoginDao;
import cn.edu.hit.dao.impl.LoginDaoImpl;
import cn.edu.hit.entity.Admin;
import cn.edu.hit.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String group = request.getParameter("group");
        String sql = "";
        PrintWriter out = response.getWriter();
        LoginDao loginDao = new LoginDaoImpl();
        if (group.equals("admin")) {
            String aid = request.getParameter("aid");
            String pwd = request.getParameter("pwd");
            sql = "select count(*) from admin where aid= '" + aid + "' and pwd = '" + pwd + "'";
            System.out.println(sql);
            if (!loginDao.Login(aid, pwd, sql)) {
                out.println("<h1>非法管理员，请<a href=\"Login.jsp?group=admin\">登录</a>!</h1>");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("admin", new Admin(aid, pwd));
                response.sendRedirect("admin.jsp?aid=" + aid);
            }
        }
        else if (group.equals("student")) {
            String uid = request.getParameter("uid");
            String pwd = request.getParameter("pwd");
            sql = "select count(*) from users where uid= '" + uid + "' and pwd = '" + pwd + "' and isTeacher = 0";
            System.out.println(sql);
            if (!loginDao.Login(uid, pwd, sql)) {
                out = response.getWriter();
                out.println("<h1>非法学生，请<a href=\"Login.jsp?group=student\">登录</a>!</h1>");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("student01",new User(uid,pwd));
                response.sendRedirect("student.jsp?uid=" + uid + "");
            }
        }
        else if (group.equals("teacher")) {
            String uid = request.getParameter("uid");
            String pwd = request.getParameter("pwd");
            sql = "select count(*) from users where uid= '" + uid + "' and pwd = '" + pwd + "' and isTeacher = 1";
            System.out.println(sql);
            if (!loginDao.Login(uid, pwd, sql)) {
                out = response.getWriter();
                out.println("<h1>非法教师，请<a href=\"Login.jsp?group=teacher\">登录</a>!</h1>");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("teacher01",new User(uid,pwd));
                response.sendRedirect("teacher.jsp?uid=" + uid);
            }
        }
    }
}
