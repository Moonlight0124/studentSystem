package cn.edu.hit.dao;

import cn.edu.hit.entity.Course;

import java.util.List;

public interface CourseDao {
    void modify(Course course);
    void add(Course course);
    void remove(String cid);
    List<Course> getAll(String sql);
    Course getByCid(String cid);
}
