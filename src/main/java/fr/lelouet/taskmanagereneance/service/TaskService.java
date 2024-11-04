package fr.lelouet.taskmanagereneance.service;

import fr.lelouet.taskmanagereneance.model.Task;
import fr.lelouet.taskmanagereneance.model.User;
import fr.lelouet.taskmanagereneance.repository.TaskRepository;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskAssociateRequest;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Task associateUserToTask(TaskAssociateRequest taskAssociateRequest, User user) {
        Task taskFound = taskRepository.findById(taskAssociateRequest.getId())
            .orElseThrow(RuntimeException::new);
        taskFound.setUser(user);
        return taskRepository.save(taskFound);
    }

    public Task getTaskById(TaskAssociateRequest taskAssociateRequest) {
        return taskRepository.findById(taskAssociateRequest.getId())
            .orElseThrow(RuntimeException::new);
    }

    public Task updateTask(Long taskId, Task newTask) {
        Task oldTask = taskRepository.findById(taskId)
            .orElseThrow(RuntimeException::new);
        oldTask.setLabel(newTask.getLabel());
        oldTask.setCategory(newTask.getCategory());
        oldTask.setDueDate(newTask.getDueDate());
        oldTask.setLastModificationDate(LocalDate.now());
        return taskRepository.save(oldTask);
    }
}