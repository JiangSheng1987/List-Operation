package cn.ytmj.findlist.service;

/**
 * @author rui
 * @create 2019-08-15 23:35
 */

import cn.ytmj.findlist.domain.PageBean;
import cn.ytmj.findlist.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有
     * @return
     */
    public List<User> findAll();

    public void add(User user);

    void deleteById(String id);

    User findUser(String id);

    void update(String id, User user);

    void deleteSelected(String[] list);

    PageBean<User> findUserByPage(int currentPage, int rows,Map<String, String[]> map);
}
