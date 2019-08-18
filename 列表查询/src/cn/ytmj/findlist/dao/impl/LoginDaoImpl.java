package cn.ytmj.findlist.dao.impl;

import cn.ytmj.findlist.dao.LoginDao;
import cn.ytmj.findlist.domain.Login;
import cn.ytmj.findlist.domain.User;
import cn.ytmj.findlist.service.LoginService;
import cn.ytmj.findlist.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author rui
 * @create 2019-08-16 21:59
 */
public class LoginDaoImpl implements LoginDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Login login(Login login) {
        try {
            String sql = "select * from login where username=? and password=?";
            Login login1 = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Login>(Login.class), login.getUsername(), login.getPassword());
            return login1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
