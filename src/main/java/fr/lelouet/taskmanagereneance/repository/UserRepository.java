package fr.lelouet.taskmanagereneance.repository;

import fr.lelouet.taskmanagereneance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface JPA de gestion des utilisateurs
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}