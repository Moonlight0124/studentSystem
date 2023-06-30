package cn.edu.hit.action;

import cn.edu.hit.dao.CourseDao;
import cn.edu.hit.dao.impl.CourseDaoImpl;
import cn.edu.hit.entity.Course;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CourseServlet", value = "/CourseServlet")
public class CourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        CourseDao courseDao = new CourseDaoImpl();
        if (action.equals("getAll")) {
            String sql = "select cid,cname,course.tid,users.uname from users,course where users.isTeacher=1 and users.uid=course.tid";
            System.out.println(sql);
            List<Course> courseList = courseDao.getAll(sql);
            out.println(JSON.toJSONString(courseList));
        } else if (action.equals("add")) {
            String cid = request.getParameter("cid");
            String cname = request.getParameter("cname");
            String tid = request.getParameter("tid");
            String aid = request.getParameter("aid");
            courseDao.add(new Course(cid, cname, tid));
            response.sendRedirect("admin.jsp?aid=" + aid);
        } else if (action.equals("modify")) {
            String cid = request.getParameter("cid");
            String cname = request.getParameter("cname");
            String tid = request.getParameter("tid");
            String aid = request.getParameter("aid");
            courseDao.modify(new Course(cid, cname, tid));
            response.sendRedirect("admin.jsp?aid=" + aid);
        } else if (action.equals("remove")) {
            String cid = request.getParameter("cid");
            String aid = request.getParameter("aid");
            courseDao.remove(cid);
            response.sendRedirect("admin.jsp?aid=" + aid);
        } else if (action.equals("getByCid")) {
            String cid = request.getParameter("cid");
            Course course = courseDao.getByCid(cid);
            out.println(JSON.toJSONString(course));
        }
    }
}
