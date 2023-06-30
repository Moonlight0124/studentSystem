package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.UserDao;
import cn.edu.hit.db.DBUtil;
import cn.edu.hit.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void add(User user) {
        DBUtil dbUtil = new DBUtil();
        String uid = user.getUid();
        String uname = user.getUname();
        String pwd = user.getPwd();
        Integer isTeacher = user.getIsTeacher();
        String gender = user.getGender();
        String birthday = user.getBirthday();
        String mid = user.getMid();
        Integer age = user.getAge();
        String sql = "insert into users(uid,uname,pwd,isTeacher,gender,birthday,mid,age) values('" + uid + "','" + uname + "','" + pwd + "'," + isTeacher + ",'" + gender + "','" + birthday + "','" + mid + "'," + "" + age + ")";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }

    @Override

    public void modify(User user) {
        DBUtil dbUtil = new DBUtil();
        String uid = user.getUid();
        String uname = user.getUname();
        String pwd = user.getPwd();
        Integer isTeacher = user.getIsTeacher();
        String gender = user.getGender();
        String birthday = user.getBirthday();
        String mid = user.getMid();
        Integer age = user.getAge();
        String sql = "update users set uname='" + uname + "',pwd='" + pwd + "',isTeacher=" + isTeacher + ",gender='" + gender + "',birthday='" + birthday + "',mid='" + mid + "',age=" + age + " where uid='" + uid + "'";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }

    @Override
    public void remove(String uid) {
        DBUtil dbUtil = new DBUtil();
        String sql = "delete from users where uid='" + uid + "'";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }

    @Override
    public List<User> getAll(String sql) {
        DBUtil dbUtil = new DBUtil();
        List<User> userList = new ArrayList<>();
        ResultSet rs = dbUtil.executeQuery(sql);
        String uid, uname, pwd, birthday, gender, mid, mname;
        Integer isTeacher, age;
        try {
            while (rs.next()) {
                uid = rs.getString("uid");
                uname = rs.getString("uname");
                pwd = rs.getString("pwd");
                isTeacher = rs.getInt("isTeacher");
                birthday = rs.getString("birthday");
                gender = rs.getString("gender");
                mid = rs.getString("mid");
                age = rs.getInt("age");
                mname = rs.getString("mname");
                userList.add(new User(uid, uname, isTeacher, pwd, gender, mid,birthday, mname, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dbUtil.close();
            return userList;
        }
    }



    @Override
    public User getByUid(String uid) {
        DBUtil dbUtil = new DBUtil();
        String sql = "select * from users where uid= '" + uid + "'";
        ResultSet rs = dbUtil.executeQuery(sql);
        String uname, pwd, birthday, gender, mid;
        Integer isTeacher, age;
        User user = null;
        try {
            while (rs.next()) {
                uname = rs.getString("uname");
                pwd = rs.getString("pwd");
                isTeacher = rs.getInt("isTeacher");
                birthday = rs.getString("birthday");
                gender = rs.getString("gender");
                mid = rs.getString("mid");
                age = rs.getInt("age");
                user = new User(uid, uname, isTeacher, pwd, gender, mid, birthday, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dbUtil.close();
            return user;
        }
    }
}
