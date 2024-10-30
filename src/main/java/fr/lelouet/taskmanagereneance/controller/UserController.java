package fr.lelouet.taskmanagereneance.controller;

import fr.lelouet.taskmanagereneance.model.User;
import fr.lelouet.taskmanagereneance.service.UserService;
import fr.lelouet.taskmanagereneance.webservices.enums.WsError;
import fr.lelouet.taskmanagereneance.webservices.user.bean.UserRegisterRequest;
import fr.lelouet.taskmanagereneance.webservices.utils.error_handler.EneanceException;
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

    public User registerUser(UserRegisterRequest userRegisterRequest) throws RuntimeException {
        if (userService.existsByEmail(userRegisterRequest.getEmail())) {
            throw new EneanceException(WsError.EMAIL_ALREADY_EXISTS);
        }
        return userService.registerUser(userRegisterRequest);

    }

    public Optional<User> findById(Long id) {
        return userService.findById(id);
    }
}
