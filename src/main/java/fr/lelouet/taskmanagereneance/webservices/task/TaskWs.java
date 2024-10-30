package fr.lelouet.taskmanagereneance.webservices.task;

import fr.lelouet.taskmanagereneance.controller.TaskController;
import fr.lelouet.taskmanagereneance.model.Task;
import fr.lelouet.taskmanagereneance.webservices.task.bean.TaskRegisterRequest;
import fr.lelouet.taskmanagereneance.webservices.utils.error_handler.WsError;
import fr.lelouet.taskmanagereneance.webservices.utils.error_handler.EneanceException;
import fr.lelouet.taskmanagereneance.webservices.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PreAuthorize("permitAll()")
    public ResponseEntity<Task> addTask(@RequestBody TaskRegisterRequest taskRegisterRequest) {
        try {
            Task newTask = taskController.register(taskRegisterRequest);
            return ResponseEntity.ok(newTask);
        } catch (Exception e) {
            // On ne donne volontairement pas d'informations sur l'erreur au front
            throw new EneanceException(WsError.TASK_CANT_BE_CREATED);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getUserTasks() {
        // Récupérer l'utilisateur authentifié
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        // Utilisez le nom d'utilisateur ou d'autres détails pour récupérer les tâches de l'utilisateur
        // Exemple :
        List<Task> tasks = taskController.getTasks();
        return ResponseEntity.ok(tasks);
    }

    // DELETE
    // UPDATE
    // GET ALL
    // GET ID
}
