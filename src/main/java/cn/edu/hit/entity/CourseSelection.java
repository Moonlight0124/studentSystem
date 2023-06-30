package cn.edu.hit.entity;

public class CourseSelection {
    private String sid;
    private String cid;
    private Integer score;
    private String tid;


    public CourseSelection() {
    }

    public CourseSelection(String sid, String cid, Integer score, String tid) {
        this.sid = sid;
        this.cid = cid;
        this.score = score;
        this.tid = tid;
    }

    public CourseSelection(String cid, String tid) {
        this.cid = cid;
        this.tid = tid;
    }

    /**
     * 获取
     *
     * @return sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * 设置
     *
     * @param sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 获取
     *
     * @return cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * 设置
     *
     * @param cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * 获取
     *
     * @return score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置
     *
     * @param score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取
     *
     * @return tid
     */
    public String getTid() {
        return tid;
    }

    /**
     * 设置
     *
     * @param tid
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    public String toString() {
        return "CourseSelection{sid = " + sid + ", cid = " + cid + ", score = " + score + ", tid = " + tid + "}";
    }
}
