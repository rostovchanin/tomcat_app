package dao;

import util.DbHelper;

public class UserDaoFactoryImpl implements UserDaoFactory{
    public UserDao getUserDao(){
        String daoProperty = DbHelper.getInstance().getProperties().getProperty("dao");
        if(daoProperty.equals("hibernate")){
                return new UserDaoHibernateImpl();
        }
        if(daoProperty.equals("jdbc")){
                return new UserDaoJdbcImpl();
        }
        return null;
    }
}
