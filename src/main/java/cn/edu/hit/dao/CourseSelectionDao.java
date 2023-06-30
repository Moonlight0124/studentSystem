package cn.edu.hit.dao;

import cn.edu.hit.entity.CourseSelection;

import java.util.List;

public interface CourseSelectionDao {
    void add(CourseSelection selection);
    void remove(String sid,String cid);
    List<CourseSelection> getAll(String sql);
    void modify(CourseSelection selection);
    public void modifyTeacher(CourseSelection selection);
}
