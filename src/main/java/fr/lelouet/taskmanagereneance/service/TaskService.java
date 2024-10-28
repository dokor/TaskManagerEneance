package fr.lelouet.taskmanagereneance.service;

import fr.lelouet.taskmanagereneance.model.Task;
import fr.lelouet.taskmanagereneance.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task, String name) {
        return new Task();
    }

    public List<Task> getTasks(String name) {
        return List.of();
    }
}