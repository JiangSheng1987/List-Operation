package cn.ytmj.findlist.dao;

/**
 * @author rui
 * @create 2019-08-15 23:39
 */

import cn.ytmj.findlist.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的dao
 */
public interface UserDao {
    //查找所有用户
    public List<User> findAll();

    //添加用户
    public void add(User user);

    //删除用户
    void deleteById(int id);

    //查找用户
    User findUser(int id);

    //更新数据
    void update(int parseInt, User user);

    List<User> findUserByPage(int currentPage, int rows, Map<String, String[]> map);

    int findTotalCount(Map<String, String[]> map);
}
