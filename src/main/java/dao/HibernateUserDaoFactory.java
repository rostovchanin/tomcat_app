package dao;

public class HibernateUserDaoFactory implements UserDaoFactory{
    @Override
    public UserDao getUserDao() {
        return new UserDaoHibernateImpl();
    }
}
