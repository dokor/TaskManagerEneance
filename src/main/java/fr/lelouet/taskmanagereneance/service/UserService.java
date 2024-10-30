package fr.lelouet.taskmanagereneance.service;

import fr.lelouet.taskmanagereneance.model.User;
import fr.lelouet.taskmanagereneance.repository.UserRepository;
import fr.lelouet.taskmanagereneance.webservices.user.bean.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegisterRequest request) {
        // Conversion UserRegisterRequest en User
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash du mot de passe

        // Sauvegarde en base de données
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}