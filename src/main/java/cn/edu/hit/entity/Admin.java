package cn.edu.hit.entity;

public class Admin {
    private String aid;
    private String pwd;


    public Admin() {
    }

    public Admin(String aid, String pwd) {
        this.aid = aid;
        this.pwd = pwd;
    }

    /**
     * 获取
     * @return aid
     */
    public String getAid() {
        return aid;
    }

    /**
     * 设置
     * @param aid
     */
    public void setAid(String aid) {
        this.aid = aid;
    }

    /**
     * 获取
     * @return pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置
     * @param pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String toString() {
        return "Admin{aid = " + aid + ", pwd = " + pwd + "}";
    }
}
