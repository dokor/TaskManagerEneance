package fr.lelouet.taskmanagereneance.controller;

import fr.lelouet.taskmanagereneance.model.Task;
import fr.lelouet.taskmanagereneance.model.User;
import fr.lelouet.taskmanagereneance.service.TaskService;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskAssociateRequest;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskRegisterRequest;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskUpdateRequest;
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

    public Task associateUserToTask(TaskAssociateRequest taskAssociateRequest) {
        // TODO : Vérifier que l'utilisateur a bien le droit de modifier la task
        // TODO : virer cette ligne une fois que l'utilisateur a été correctement récupéré
        User user = new User();
        return taskService.associateUserToTask(taskAssociateRequest, user);
    }

    public Task getTaskById(TaskAssociateRequest taskAssociateRequest) {
        return taskService.getTaskById(taskAssociateRequest);
    }

    public Task updateTask(TaskUpdateRequest taskUpdateRequest) {
        return taskService.updateTask(taskUpdateRequest.getTaskId(), taskUpdateRequest.getNewTask());
    }
}