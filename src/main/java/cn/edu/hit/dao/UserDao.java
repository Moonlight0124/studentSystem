package cn.edu.hit.dao;
import cn.edu.hit.entity.User;
import java.util.List;

public interface UserDao {
    void add(User user);
    void modify(User user);
    void remove(String uid);
    List<User> getAll(String sql);
    User getByUid(String uid);

}
