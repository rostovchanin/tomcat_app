package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User getUserByLoginAndPassword(String login, String password);
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(Integer id);
    boolean isCorrectedId(String strId);
}
