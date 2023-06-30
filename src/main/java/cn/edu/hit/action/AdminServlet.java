package cn.edu.hit.action;

import cn.edu.hit.dao.AdminDao;
import cn.edu.hit.dao.impl.AdminDaoImpl;
import cn.edu.hit.entity.Admin;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        AdminDao adminDao = new AdminDaoImpl();
        response.setContentType("text/html;charset=utf-8");
        if (action.equals("modify")) {
            String aid = request.getParameter("aid");
            String pwd = request.getParameter("pwd");
            adminDao.modifyPwd(new Admin(aid, pwd));
            response.sendRedirect("admin.jsp?aid=" + aid);
        } else if (action.equals("add")) {
            String aid = request.getParameter("aid");
            String pwd = request.getParameter("pwd");
            adminDao.add(new Admin(aid, pwd));
        } else if (action.equals("remove")) {
            String aid = request.getParameter("aid");
            adminDao.remove(aid);
        }
    }
}

