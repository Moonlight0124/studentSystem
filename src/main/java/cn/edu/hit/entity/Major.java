package cn.edu.hit.entity;

public class Major {
    private String mid;
    private String mname;

    public Major() {
    }

    public Major(String mid, String mname) {
        this.mid = mid;
        this.mname = mname;
    }

    /**
     * 获取
     * @return mid
     */
    public String getMid() {
        return mid;
    }

    /**
     * 设置
     * @param mid
     */
    public void setMid(String mid) {
        this.mid = mid;
    }

    /**
     * 获取
     * @return mname
     */
    public String getMname() {
        return mname;
    }

    /**
     * 设置
     * @param mname
     */
    public void setMname(String mname) {
        this.mname = mname;
    }

    public String toString() {
        return "Major{mid = " + mid + ", mname = " + mname + "}";
    }
}
