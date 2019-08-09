package dao;

import exception.LogicException;
import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private Connection connection;
    
    public UserDaoImpl(){
        connection = DBHelper.getConnection();
    }

    @Override
    public List<User> getAllUsers()  {
        List<User> list = new ArrayList<>();

        final String sql = "SELECT id, name, password, login FROM users_task1";

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
        final String sql = "SELECT id, name, password, login FROM users_task1 WHERE id=?";
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
        String sql = "INSERT INTO users_task1 (name, password, login) VALUES (?,?,?)";

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
        final String sql = "UPDATE users_task1 SET name=?, password=?, login=? WHERE id=?";
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
        final String sql = "DELETE FROM users_task1 WHERE id=?";
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
