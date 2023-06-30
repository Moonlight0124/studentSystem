package cn.edu.hit.entity;

public class Course {
    private String cid;
    private String cname;
    private String tid;
    private String tname;

    public Course() {
    }

    public Course(String cid, String cname, String tid) {
        this.cid = cid;
        this.cname = cname;
        this.tid = tid;

    }
    public Course(String cid, String cname, String tid,String tname) {
        this.cid = cid;
        this.cname = cname;
        this.tid = tid;
        this.tname=tname;

    }
    public String getTname(){
        return tname;
    }

    /**
     * 获取
     * @return cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * 设置
     * @param cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * 获取
     * @return cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * 设置
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * 获取
     * @return tid
     */
    public String getTid() {
        return tid;
    }

    /**
     * 设置
     * @param tid
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    public String toString() {
        return "Course{cid = " + cid + ", cname = " + cname + ", tid = " + tid + "}";
    }
}
