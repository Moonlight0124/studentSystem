package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.AdminDao;
import cn.edu.hit.db.DBUtil;
import cn.edu.hit.entity.Admin;

public class AdminDaoImpl implements AdminDao {
    @Override
    public void modifyPwd(Admin admin) {
        DBUtil dbUtil = new DBUtil();
        String aid = admin.getAid();
        String pwd = admin.getPwd();
        String sql = "update admin set pwd='" + pwd + "' where aid='" + aid + "'";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }

    @Override
    public void add(Admin admin) {
        DBUtil dbUtil = new DBUtil();
        String aid = admin.getAid();
        String pwd = admin.getPwd();
        String sql = "insert into admin(aid,pwd) values('" + aid + "','" + pwd + "') ";
        System.out.println(sql);
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }

    @Override
    public void remove(String aid) {
        DBUtil dbUtil = new DBUtil();
        String sql = "delete from admin where aid='" + aid + "' ";
        dbUtil.executeUpdate(sql);
        dbUtil.close();
    }
}
