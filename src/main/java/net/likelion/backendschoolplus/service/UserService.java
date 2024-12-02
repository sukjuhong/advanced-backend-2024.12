package net.likelion.backendschoolplus.service;

import net.likelion.backendschoolplus.entity.User;

import java.util.List;

public interface UserService {
    int CORRECT_LOGIN = 0;
    int WRONG_PASSWORD = 1;
    int USER_NOT_EXIST = 2;

    User findById(Long id);
    User findByUsername(String username);
    List<User> getUsers();
    void registerUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
