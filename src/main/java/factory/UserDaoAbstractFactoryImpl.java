package factory;

import util.DbHelper;

public class UserDaoAbstractFactoryImpl {
    private UserDaoFactory userDaoFactory = null;

    public UserDaoAbstractFactoryImpl(){
        String daoProperty = DbHelper.getInstance().getProperties().getProperty("dao");
        if(daoProperty.equals("hibernate")){
            this.userDaoFactory = new HibernateUserDaoFactory();
        }else{
            this.userDaoFactory = new JdbcUserDaoFactory();
        }
    }
    public UserDaoFactory getDaoFactory(){
        return this.userDaoFactory;
    }
}
