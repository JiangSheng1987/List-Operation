package cn.ytmj.findlist.service.impl;

import cn.ytmj.findlist.dao.UserDao;
import cn.ytmj.findlist.dao.impl.UserDaoImpl;
import cn.ytmj.findlist.domain.PageBean;
import cn.ytmj.findlist.domain.User;
import cn.ytmj.findlist.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @author rui
 * @create 2019-08-15 23:37
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //注入dao完成查询
        List<User> users = userDao.findAll();
        return users;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void deleteById(String id) {
        userDao.deleteById(Integer.parseInt(id));
    }

    @Override
    public User findUser(String id) {
        return userDao.findUser(Integer.parseInt(id));
    }

    @Override
    public void update(String id, User user) {

        userDao.update(Integer.parseInt(id), user);
    }

    @Override
    public void deleteSelected(String[] list) {
        for (String str : list
        ) {
            userDao.deleteById(Integer.parseInt(str));
        }
    }
    @Override
    public PageBean<User> findUserByPage(int currentPage, int rows,Map<String, String[]> map) {
        PageBean<User> pageBean = new PageBean<>();
        if (currentPage <= 0) {
            currentPage = 1;
        }

        int totalCount = userDao.findTotalCount(map);
        //计算总页数
        int totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1;
        pageBean.setTotalPage(totalPage);
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        List<User> list = userDao.findUserByPage(currentPage, rows,map);

        pageBean.setTotalCount(totalCount);
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        pageBean.setList(list);


        return pageBean;
    }
}
