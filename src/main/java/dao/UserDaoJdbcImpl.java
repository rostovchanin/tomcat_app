package dao;

import exception.LogicException;
import model.User;
import util.DbHelper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao{
    private final String DB_TABLE = "users_task1";
    private Connection connection;
    
    public UserDaoJdbcImpl(){
        connection = DbHelper.getInstance().getConnection();
    }

    @Override
    public List<User> getAllUsers()  {
        List<User> list = new ArrayList<>();

        final String sql = "SELECT id, name, password, login FROM "+ DB_TABLE;

        try(PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet res = statement.executeQuery();

            while(res.next()){
                list.add(new User(res.getInt("id"), res.getString("name"),
                res.getString("password"), res.getString("login")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public User getUserById(Integer id) {
        User user = null;
        final String sql = "SELECT id, name, password, login FROM "+ DB_TABLE +" WHERE id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                user = new User(res.getInt("id"), res.getString("name"),
                        res.getString("password"), res.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO "+ DB_TABLE +" (name, password, login) VALUES (?,?,?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getLogin());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user){
        final String sql = "UPDATE "+ DB_TABLE +" SET name=?, password=?, login=? WHERE id=?";
        boolean success = false;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getLogin());
            statement.setInt(4, user.getId());
            int affected = statement.executeUpdate();
            success = affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!success){
            throw new LogicException("Пользователь не обновлен!");
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        final String sql = "DELETE FROM "+ DB_TABLE +" WHERE id=?";
        boolean success = false;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affected = statement.executeUpdate();
            success = affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!success){
            throw new LogicException("Пользователь не удален!");
        }
    }
}