package cn.ytmj.findlist.dao.impl;

import cn.ytmj.findlist.dao.UserDao;
import cn.ytmj.findlist.domain.User;

import cn.ytmj.findlist.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * @author rui
 * @create 2019-08-15 23:40
 */
public class UserDaoImpl implements UserDao {


    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用jdbc操作数据库
        String sql = "select * from user";

        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return query;
    }

    @Override
    public void add(User user) {
        try {
            String sql = "insert into user(name,gender,age,address,qq,email) values (?,?,?,?,?,?)";
            jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete  from user where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public User findUser(int id) {
        String sql = "select * from user where id=?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    @Override
    public void update(int id, User user) {
        String sql = "update user set gender=?,age=?,address=?,qq=?,email=? where id=?";
        jdbcTemplate.update(sql, user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), id);
    }

    @Override
    public List<User> findUserByPage(int currentPage, int rows, Map<String, String[]> map) {
        String sql = "select * from user where 1=1";
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
        StringBuilder sb = new StringBuilder(sql);
        List arr = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> next = iterator.next();
            String key = next.getKey();

            String value = next.getValue()[0];
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like  '%" + value + "%'");
            }
        }
        sb.append(" limit ? , ?");
        List<User> list = jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), (currentPage - 1) * rows, rows);
        return list;
    }

    @Override
    public int findTotalCount(Map<String, String[]> map) {
        String sql = "select count(*) from user where 1=1";
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
        StringBuilder sb = new StringBuilder(sql);
        List arr = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> next = iterator.next();
            String key = next.getKey();

            String value = next.getValue()[0];
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like  '%" + value + "%'");
            }
        }

        int count = jdbcTemplate.queryForObject(sb.toString(), Integer.class);

        return count;
    }
}
