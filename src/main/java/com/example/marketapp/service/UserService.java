package com.example.marketapp.service;

import com.example.marketapp.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    void deleteUserById(Long id);

    User buyProduct(Long userId, Long productId);

    List<User> getAllUsersByProductId(Long id);
}
