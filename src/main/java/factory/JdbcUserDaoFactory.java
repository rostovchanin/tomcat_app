package factory;

import dao.UserDao;
import dao.UserDaoJdbcImpl;

public class JdbcUserDaoFactory implements UserDaoFactory {
    @Override
    public UserDao getUserDao() {
        return new UserDaoJdbcImpl();
    }
}
