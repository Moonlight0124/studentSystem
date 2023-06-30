package cn.edu.hit.entity;

public class User {
    private String uid;
    private String uname;
    private int isTeacher;
    private String pwd;
    private String gender;
    private String mid;
    private String birthday;
    private String cname;
    private String mname;
    private Integer age;


    public User() {
    }

    public User(String uid, String pwd){
        this.uid = uid;
        this.pwd = pwd;
    }

    public User(String uid, String uname, int isTeacher, String pwd, String gender, String mid, String birthday, Integer age) {
        this.uid = uid;
        this.uname = uname;
        this.isTeacher = isTeacher;
        this.pwd = pwd;
        this.gender = gender;
        this.mid = mid;
        this.birthday = birthday;
        this.age = age;
    }

    public User(String uid, String uname, int isTeacher, String pwd, String gender, String mid, String birthday, String mname,Integer age) {
        this.uid = uid;
        this.uname = uname;
        this.isTeacher = isTeacher;
        this.pwd = pwd;
        this.gender = gender;
        this.mid = mid;
        this.birthday = birthday;
        this.age = age;
        this.mname = mname;
    }


    public String getMname() {
        return mname;
    }

    public String getCname() {
        return cname;
    }

    /**
     * 获取
     *
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置
     *
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取
     *
     * @return uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * 设置
     *
     * @param uname
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * 获取
     *
     * @return isTeacher
     */
    public int getIsTeacher() {
        return isTeacher;
    }

    /**
     * 设置
     *
     * @param isTeacher
     */
    public void setIsTeacher(int isTeacher) {
        this.isTeacher = isTeacher;
    }

    /**
     * 获取
     *
     * @return pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置
     *
     * @param pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取
     *
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     *
     * @return mid
     */
    public String getMid() {
        return mid;
    }

    /**
     * 设置
     *
     * @param mid
     */
    public void setMid(String mid) {
        this.mid = mid;
    }

    /**
     * 获取
     *
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置
     *
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取
     *
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     *
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "User{uid = " + uid + ", uname = " + uname + ", isTeacher = " + isTeacher + ", pwd = " + pwd + ", gender = " + gender + ", mid = " + mid + ", birthday = " + birthday + ", age = " + age + "}";
    }
}