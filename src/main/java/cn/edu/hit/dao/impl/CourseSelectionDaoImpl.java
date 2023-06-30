package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.CourseSelectionDao;
import cn.edu.hit.db.DBUtil;
import cn.edu.hit.entity.CourseSelection;
import cn.edu.hit.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseSelectionDaoImpl implements CourseSelectionDao {

    @Override
    public void add(CourseSelection selection) {
        DBUtil dbUtil = new DBUtil();
        String sid = selection.getSid();
        String tid = selection.getTid();
        String cid = selection.getCid();
        Integer score = selection.getScore();
        String sql = "insert into courseSelection(sid,cid,score,tid) values('" + sid + "','" + cid + "'," + score + ",'" + tid + "')";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }

    @Override
    public void remove(String sid, String cid) {
        DBUtil dbUtil = new DBUtil();
        String sql = "delete from courseSelection where sid='" + sid + "' and cid='" + cid + "'";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }

    @Override
    public List<CourseSelection> getAll(String sql) {
        DBUtil dbUtil = new DBUtil();
        List<CourseSelection> selectionList = new ArrayList<>();
        ResultSet rs = dbUtil.executeQuery(sql);
        String cid, sid, tid;
        Integer score;
        try {
            while (rs.next()) {
                sid = rs.getString("sid");
                cid = rs.getString("cid");
                tid = rs.getString("tid");
                score = rs.getInt("score");
                selectionList.add(new CourseSelection(sid, cid, score, tid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dbUtil.close();
            return selectionList;
        }
    }

    @Override
    public void modify(CourseSelection selection) {
        DBUtil dbUtil = new DBUtil();
        String sid = selection.getSid();
        String cid = selection.getCid();
        String tid = selection.getTid();
        Integer score = selection.getScore();
        String sql = "update courseSelection set score='" + score + "',tid='" + tid + "' where sid='" + sid + "' and cid='" + cid + "'";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }
    public void modifyTeacher(CourseSelection selection) {
        DBUtil dbUtil = new DBUtil();
        String cid = selection.getCid();
        String tid = selection.getTid();
        String sql = "update courseSelection set tid='" + tid + "' where cid='" + cid + "'";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }
}