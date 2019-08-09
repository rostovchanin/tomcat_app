package service;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import dao.UserDaoJdbcImpl;
import model.User;


import java.util.List;

public class UserServiceImpl implements UserService{
    private static UserService instance;
    private UserDao dao;

    private UserServiceImpl(){

        //dao = new UserDaoJdbcImpl();
        dao = new UserDaoHibernateImpl();
    }

    public static UserService getInstance(){
        if(instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers(){
        return dao.getAllUsers();
    }

    @Override
    public void createUser(User user){
        dao.createUser(user);
    }

    @Override
    public void updateUser(User user){
        dao.updateUser(user);
    }

    @Override
    public User getUserById(Integer id){
        return dao.getUserById(id);
    }

    @Override
    public void deleteUserById(Integer id){
        dao.deleteUserById(id);
    }

    @Override
    public boolean isCorrectedId(String strId) {
        try {
            Integer.parseInt(strId);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
