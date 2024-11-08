package fr.lelouet.taskmanagereneance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@Entity
/**
 * Bean BDD représentant une tache
 */
public class Task {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String category; // TODO : relier la catégory avec les futures catégories dispo pour les users (BDD)
    private LocalDate dueDate;
    private boolean done;
    private LocalDate createDate;
    private LocalDate lastModificationDate;

    // TODO : ajouter utilisateur crea/modif

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // TODO : Faire un choix sur la possibilité d'avoir des taches sans user
    private User user;

}
