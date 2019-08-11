package dao;

public class JdbcUserDaoFactory implements UserDaoFactory {
    @Override
    public UserDao getUserDao() {
        return new UserDaoJdbcImpl();
    }
}
