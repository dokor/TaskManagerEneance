package fr.lelouet.taskmanagereneance.service;

import fr.lelouet.taskmanagereneance.model.User;
import fr.lelouet.taskmanagereneance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service fonctionelle de gestion des utilisateurs
 * // TODO alelouet : a diviser en service fonctionelle si besoin
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}