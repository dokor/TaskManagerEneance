package fr.lelouet.taskmanagereneance.webservices.task;

import fr.lelouet.taskmanagereneance.controller.TaskController;
import fr.lelouet.taskmanagereneance.model.Task;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskAssociateRequest;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskRegisterRequest;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskUpdateRequest;
import fr.lelouet.taskmanagereneance.webservices.utils.error_handler.WsError;
import fr.lelouet.taskmanagereneance.webservices.utils.error_handler.EneanceException;
import fr.lelouet.taskmanagereneance.webservices.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * WS des tasks.
 */
@RestController
@RequestMapping("/tasks")
public class TaskWs {

    @Autowired
    private TaskController taskController;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // Défini comme sans authentification dans SecurityConfig
    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody TaskRegisterRequest taskRegisterRequest) {
        try {
            Task newTask = taskController.register(taskRegisterRequest);
            return ResponseEntity.ok(newTask);
        } catch (Exception e) {
            // On ne donne volontairement pas d'informations sur l'erreur au front
            throw new EneanceException(WsError.TASK_CANT_BE_CREATED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTask() {
        // TODO
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getUserTasks() {
        List<Task> tasks = taskController.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping
    public ResponseEntity<Task> associateCurentUserToTask(@RequestBody TaskAssociateRequest taskAssociateRequest) {
        // TODO
        // Recupt l'utilisateur courant
        getUserDetails();
        // Vérifier que le bean est bien rempli
        return ResponseEntity.ok(taskController.associateUserToTask(taskAssociateRequest));
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody TaskUpdateRequest taskUpdateRequest) {
        // TODO
        // Recupt l'utilisateur courant
        getUserDetails();
        // Vérifier que le bean est bien rempli
        return ResponseEntity.ok(taskController.updateTask(taskUpdateRequest));
    }

    @GetMapping
    public ResponseEntity<Task> getTaskById(@RequestBody TaskAssociateRequest taskAssociateRequest) {
        return ResponseEntity.ok(taskController.getTaskById(taskAssociateRequest));
    }

    private UserDetails getUserDetails() {
        // Récupérer l'utilisateur authentifié
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
