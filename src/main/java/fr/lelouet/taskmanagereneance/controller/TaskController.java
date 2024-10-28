package fr.lelouet.taskmanagereneance.controller;

import fr.lelouet.taskmanagereneance.model.Task;
import fr.lelouet.taskmanagereneance.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, Principal principal) {
        Task newTask = taskService.createTask(task, principal.getName());
        return ResponseEntity.ok(newTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(Principal principal) {
        return ResponseEntity.ok(taskService.getTasks(principal.getName()));
    }
}