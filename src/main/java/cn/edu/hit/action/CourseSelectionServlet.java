package cn.edu.hit.action;

import cn.edu.hit.dao.CourseSelectionDao;
import cn.edu.hit.dao.impl.CourseSelectionDaoImpl;
import cn.edu.hit.entity.CourseSelection;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CourseSelectionServlet", value = "/CourseSelectionServlet")
public class CourseSelectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        CourseSelectionDao csDao = new CourseSelectionDaoImpl();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        if (action.equals("getAll")) {
            String cid = request.getParameter("cid");
            String sql = "select * from courseSelection where cid='" + cid + "'";
            System.out.println(sql);
            List<CourseSelection> courseSelectionList = csDao.getAll(sql);
            out.println(JSON.toJSONString(courseSelectionList));
        } else if (action.equals("add")) {
            String cid = request.getParameter("cid");
            String sid = request.getParameter("sid");
            String tid = request.getParameter("tid");
            String score = request.getParameter("score");
            csDao.add(new CourseSelection(sid, cid, Integer.valueOf(score), tid));
            out.println("<h1>选课成功！<a href='student.jsp?uid=" + sid + "'>请返回</a>");
        } else if (action.equals("remove")) {
            String cid = request.getParameter("cid");
            String sid = request.getParameter("sid");
            csDao.remove(sid, cid);
            response.sendRedirect("student.jsp?uid=" + sid);
        } else if (action.equals("modify")) {
            String cid = request.getParameter("cid");
            String sid = request.getParameter("sid");
            String tid = request.getParameter("tid");
            String score = request.getParameter("score");
            csDao.modify(new CourseSelection(sid, cid, Integer.valueOf(score), tid));
            response.sendRedirect("teacher.jsp?uid=" + tid);
        }
        else if(action.equals("modifyTeacher")){
            String cid=request.getParameter("cid");
            String tid=request.getParameter("tid");
            csDao.modifyTeacher(new CourseSelection(cid,tid));
        }
    }
}
