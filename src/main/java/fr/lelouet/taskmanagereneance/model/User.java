package fr.lelouet.taskmanagereneance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users") // User est un mot-clé réservé en SQL
/**
 * Bean BDD représentant un utilisateur
 */
public class User {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; // Définit l'unicité d'un point de vue utilisateur.
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate createDate;

}
