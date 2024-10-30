package fr.lelouet.taskmanagereneance.service;

import fr.lelouet.taskmanagereneance.model.Task;
import fr.lelouet.taskmanagereneance.repository.TaskRepository;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskRegisterRequest taskRegisterRequest) {
        Task task = new Task();
        task.setCategory(taskRegisterRequest.getCategory());
        task.setLabel(taskRegisterRequest.getLabel());
        task.setDueDate(taskRegisterRequest.getDueDate());
        task.setDone(false);
        return taskRepository.save(task);
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}