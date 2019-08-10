package dao;

import util.DbHelper;

public class UserDaoFactory {
    public static UserDao getUserDao(){
        UserDao dao = null;
        switch(DbHelper.getInstance().getProperties().getProperty("dao")){
            case "hibernate":{
                dao = new UserDaoHibernateImpl();
                break;
            }
            case "jdbc":{
                dao = new UserDaoJdbcImpl();
                break;
            }
        }
        return dao;
    }
}
