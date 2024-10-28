package fr.lelouet.taskmanagereneance.repository;

import fr.lelouet.taskmanagereneance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface fonctionelle de gestion des utilisateurs
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // TODO alelouet : ajouter les methodes CRUD fonctionel
}