package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.CourseDao;
import cn.edu.hit.db.DBUtil;
import cn.edu.hit.entity.Course;
import cn.edu.hit.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public void modify(Course course) {
        DBUtil dbUtil = new DBUtil();
        String cid = course.getCid();
        String cname = course.getCname();
        String tid = course.getTid();
        String sql = "update course set cname='" + cname + "',tid='" + tid + "' where cid='" + cid + "'";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }

    @Override
    public void add(Course course) {
        DBUtil dbUtil = new DBUtil();
        String cid = course.getCid();
        String cname = course.getCname();
        String tid = course.getTid();
        String sql = "insert into course(cid,cname,tid) values('" + cid + "','" + cname + "','" + tid + "')";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }

    @Override
    public void remove(String cid) {
        DBUtil dbUtil = new DBUtil();
        String sql = "delete from course where cid='" + cid + "'";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }

    @Override
    public List<Course> getAll(String sql) {
        DBUtil dbUtil = new DBUtil();
        List<Course> courseList = new ArrayList<>();
        ResultSet rs = dbUtil.executeQuery(sql);
        String cid, cname, tid,tname;
        try {
            while (rs.next()) {
                cid = rs.getString("cid");
                cname = rs.getString("cname");
                tid = rs.getString("tid");
                tname=rs.getString("uname");
                courseList.add(new Course(cid, cname, tid,tname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dbUtil.close();
            return courseList;
        }
    }

    @Override
    public Course getByCid(String cid) {
        DBUtil dbUtil = new DBUtil();
        String sql = "select * from course where cid= '" + cid + "'";
        ResultSet rs = dbUtil.executeQuery(sql);
        String cname,tid;
        Course course = null;
        try {
            while (rs.next()) {
                cname=rs.getString("cname");
                tid=rs.getString("tid");
                course=new Course(cid,cname,tid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dbUtil.close();
            return course;
        }
    }
}
