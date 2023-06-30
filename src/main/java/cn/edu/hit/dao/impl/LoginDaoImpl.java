package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.LoginDao;
import cn.edu.hit.db.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    @Override
    public boolean Login(String id, String pwd,String sql) {
        DBUtil dbUtil =new DBUtil();
        ResultSet rs = dbUtil.executeQuery(sql);
        System.out.println(rs);
        int count=0;
        try {
            if(rs.next()){
                count=rs.getInt(1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            dbUtil.close();
            return count ==1 ? true : false;
        }
    }
}
