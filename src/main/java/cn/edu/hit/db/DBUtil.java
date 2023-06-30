package cn.edu.hit.db;
import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private Connection con;
    private Statement stmt;

//    连接数据库
    public DBUtil(){
        try {
            ResourceBundle resource = ResourceBundle.getBundle("jdbc");
            String driver =resource.getString("jdbc.driver");
            String url =resource.getString("jdbc.url");
            String username=resource.getString("jdbc.username");
            String password=resource.getString("jdbc.password");
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    断开数据库
    public void close(){
        try {
            if(!stmt.isClosed()){
                stmt.close();
            }
            if(!con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet executeQuery(String sql){
        try {
            ResultSet rs  = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(String sql){
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
