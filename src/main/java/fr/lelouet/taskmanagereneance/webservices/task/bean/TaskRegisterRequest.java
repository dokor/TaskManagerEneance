package fr.lelouet.taskmanagereneance.webservices.task.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Bean d'entrée utilisé pour lors de la création d'une tache
 */
@AllArgsConstructor
@Getter
@Setter
public class TaskRegisterRequest {
    private String label;
    private String category;
    private LocalDate dueDate;
    private boolean done;
}
