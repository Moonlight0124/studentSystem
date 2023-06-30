package cn.edu.hit.action;

import cn.edu.hit.dao.UserDao;
import cn.edu.hit.dao.impl.UserDaoImpl;
import cn.edu.hit.entity.User;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        UserDao userDao = new UserDaoImpl();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (action.equals("getAllStudent")) {
            List<User> userList = userDao.getAll("select uid ,uname,gender,age,birthday,pwd,isTeacher,users.mid,major.mname from users,major where isteacher=0 and users.mid=major.mid");
            out.println(JSON.toJSONString(userList));
        } else if (action.equals("modify")) {
            String uid = request.getParameter("uid");
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("pwd");
            String isTeacher = request.getParameter("isTeacher");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            String mid = request.getParameter("mid");
            String age = request.getParameter("age");
            String aid = request.getParameter("aid");
            String modifyUser = "";
            modifyUser = request.getParameter("modifyUser");
            userDao.modify(new User(uid, uname, Integer.valueOf(isTeacher), pwd, gender, mid, birthday, Integer.valueOf(age)));
            if (modifyUser.equals("student")) {
                response.sendRedirect("student.jsp?uid=" + uid);
            } else if (modifyUser.equals("teacher")) {
                response.sendRedirect("teacher.jsp?uid=" + uid);
            } else {
                response.sendRedirect("admin.jsp?aid=" + aid);
            }
        } else if (action.equals("add")) {
            String uid = request.getParameter("uid");
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("pwd");
            String isTeacher = request.getParameter("isTeacher");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            String mid = request.getParameter("mid");
            String age = request.getParameter("age");
            String aid = request.getParameter("aid");
            userDao.add(new User(uid, uname, Integer.valueOf(isTeacher), pwd, gender, mid, birthday, Integer.valueOf(age)));
            response.sendRedirect("admin.jsp?aid=" + aid);
        } else if (action.equals("remove")) {
            String uid = request.getParameter("uid");
            String aid=request.getParameter("aid");
            userDao.remove(uid);
            response.sendRedirect("admin.jsp?aid=" + aid);
        }else if(action.equals("getAll")){
            out = response.getWriter();
            List<User> userList = userDao.getAll("select uid ,uname,gender,age,birthday,pwd,isTeacher,users.mid,major.mname from users,major where  users.mid=major.mid");
            System.out.println(JSON.toJSONString(userList));
            out.print(JSON.toJSONString(userList));
        }
        else if (action.equals("getAllTeacher")) {
        } else if (action.equals("getByUid")) {
            String uid = request.getParameter("uid");
            User user = userDao.getByUid(uid);
            out.println(JSON.toJSONString(user));
        }
    }
}
