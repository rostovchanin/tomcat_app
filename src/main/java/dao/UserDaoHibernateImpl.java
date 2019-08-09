package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DbHibernateHelper;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(){
        this.sessionFactory = DbHibernateHelper.getSessionFactory();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Session session = this.sessionFactory.openSession()){
            users = execRes(session, "FROM User", hql -> session.createQuery(hql).list());
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        User user = new User();
        try(Session session = this.sessionFactory.openSession()){
            exec(session, user, u -> session.load(u, id));
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void createUser(User user) {
        try(Session session = this.sessionFactory.openSession()){
            exec(session, user, u -> session.save(u));
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try(Session session = this.sessionFactory.openSession()){
            exec(session, user, u -> session.update(u));
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        try(Session session = this.sessionFactory.openSession()){
            User user = new User();
            user.setId(id);
            exec(session, user, u -> session.delete(u));
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    private <T> void exec(Session session, T t, Consumer<T> cons){
        Transaction transaction = session.beginTransaction();
        cons.accept(t);
        transaction.commit();
        session.close();
    }

    private <T, V>  V execRes(Session session, T t, Function<T,V> fn){
        Transaction transaction = session.beginTransaction();
        V result = fn.apply(t);
        transaction.commit();
        session.close();
        return result;
    }
}
