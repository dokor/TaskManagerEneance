package fr.lelouet.taskmanagereneance.repository;

import fr.lelouet.taskmanagereneance.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface fonctionelle de gestion des taches
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}