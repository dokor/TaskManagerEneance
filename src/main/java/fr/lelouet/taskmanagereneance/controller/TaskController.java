package fr.lelouet.taskmanagereneance.controller;

import fr.lelouet.taskmanagereneance.model.Task;
import fr.lelouet.taskmanagereneance.service.TaskService;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskController {

    @Autowired
    private TaskService taskService;

    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    public Task register(TaskRegisterRequest taskRegisterRequest) {
        return taskService.createTask(taskRegisterRequest);
    }
}