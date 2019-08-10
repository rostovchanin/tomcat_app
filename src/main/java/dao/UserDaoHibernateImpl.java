package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DbHelper;



import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(){ this.sessionFactory = getSessionFactory();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            users =  session.createQuery("FROM User").list();
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        User user = new User();
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.load(user, id);
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public void createUser(User user) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            session.save(user);
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.delete(user);
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }



    public  SessionFactory getSessionFactory() {
        Configuration configuration = DbHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}
