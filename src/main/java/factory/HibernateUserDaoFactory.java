package factory;

import dao.UserDao;
import dao.UserDaoHibernateImpl;

public class HibernateUserDaoFactory implements UserDaoFactory {
    @Override
    public UserDao getUserDao() {
        return new UserDaoHibernateImpl();
    }
}
