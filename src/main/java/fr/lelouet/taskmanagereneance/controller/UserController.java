package fr.lelouet.taskmanagereneance.controller;

import fr.lelouet.taskmanagereneance.model.User;
import fr.lelouet.taskmanagereneance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Controller du JPA User
 */
@Service
public class UserController {

    @Autowired
    private UserService userService;

    public User registerUser(User user) {
        return userService.registerUser(user);
    }

    public Optional<User> findById(Long id) {
        return userService.findById(id);
    }
}
