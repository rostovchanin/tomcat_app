package dao;

import util.DbHelper;

public class UserDaoAbstractFactoryImpl {
    private UserDaoFactory userDaoFactory = null;

    public UserDaoAbstractFactoryImpl(){
        String daoProperty = DbHelper.getInstance().getProperties().getProperty("dao");
        if(daoProperty.equals("hibernate")){
            this.userDaoFactory = new HibernateUserDaoFactory();
        }
        if(daoProperty.equals("jdbc")){
            this.userDaoFactory = new JdbcUserDaoFactory();
        }
        if(this.userDaoFactory == null){
            throw new RuntimeException("Dao property error. Can't create DaoFactory");
        }
    }
    public UserDaoFactory getDaoFactory(){
        return this.userDaoFactory;
    }
}
