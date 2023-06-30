package cn.edu.hit.dao;

import cn.edu.hit.entity.Admin;

public interface AdminDao {
    void modifyPwd(Admin admin);
    void add(Admin admin);
    void remove(String aid);
}
