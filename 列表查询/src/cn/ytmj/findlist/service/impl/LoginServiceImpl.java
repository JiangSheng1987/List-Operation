package cn.ytmj.findlist.service.impl;

import cn.ytmj.findlist.dao.LoginDao;
import cn.ytmj.findlist.dao.impl.LoginDaoImpl;
import cn.ytmj.findlist.domain.Login;
import cn.ytmj.findlist.domain.User;
import cn.ytmj.findlist.service.LoginService;

/**
 * @author rui
 * @create 2019-08-16 21:58
 */
public class LoginServiceImpl implements LoginService {
    private LoginDao loginDao=new LoginDaoImpl();
    @Override
    public Login login(Login login) {

        return loginDao.login(login);
    }
}
