package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.MajorDao;
import cn.edu.hit.db.DBUtil;
import cn.edu.hit.entity.Major;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MajorDaoImpl implements MajorDao {

    @Override
    public void add(Major major) {
        DBUtil dbUtil = new DBUtil();
        String mid = major.getMid();
        String mname = major.getMname();
        dbUtil.executeUpdate("insert into major(mid,mname) values('" + mid + "' , '" + mname + "')");
        dbUtil.close();
    }

    @Override
    public void modify(Major major) {
        DBUtil dbUtil = new DBUtil();
        String mid = major.getMid();
        String mname = major.getMname();
        dbUtil.executeUpdate("update major set mname = '" + mname + "' where mid = '" + mid + "'");
        dbUtil.close();
    }

    @Override
    public void remove(String mid) {
        DBUtil dbUtil = new DBUtil();
        dbUtil.executeUpdate("delete from major where mid = '" + mid + "'");
        dbUtil.close();
    }

    @Override
    public Major searchByMid(String mid) {
        DBUtil dbUtil = new DBUtil();
        Major major = null;
        ResultSet rs = dbUtil.executeQuery("select * from major where mid = '" + mid + "'");
        String mname;
        try {
            if (rs.next()) {
                mname = rs.getString(2);
                 major = new Major(mid, mname);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbUtil.close();
        }
        return major;
    }

    @Override
    public List<Major> getAll(String sql) {
        DBUtil dbUtil = new DBUtil();
        ResultSet rs = dbUtil.executeQuery(sql);
        String mid, mname;
        List<Major> majorList = new ArrayList<>();
        try {
            while (rs.next()) {
                mid = rs.getString(1);
                mname = rs.getString(2);
                majorList.add(new Major(mid, mname));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbUtil.close();
            return majorList;
        }
    }
}
