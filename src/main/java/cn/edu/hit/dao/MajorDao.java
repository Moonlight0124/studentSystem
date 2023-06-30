package cn.edu.hit.dao;

import cn.edu.hit.entity.Major;

import java.util.List;

public interface MajorDao {
    void add(Major major);
    void modify(Major major);
    void remove(String mid);
    Major searchByMid(String mid);
    List<Major> getAll(String sql);
}
