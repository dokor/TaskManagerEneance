package fr.lelouet.taskmanagereneance.webservices;

import fr.lelouet.taskmanagereneance.controller.UserController;
import fr.lelouet.taskmanagereneance.model.User;
import fr.lelouet.taskmanagereneance.webservices.enums.WsError;
import fr.lelouet.taskmanagereneance.webservices.utils.EneanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * WS non identifiés des utilisateurs.
 */
@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()") // Par défault, le WS demande une authentification
public class UserWs {

    @Autowired
    private UserController userController;

    @PostMapping("/register")
    @PreAuthorize("permitAll()") // Accessible sans authentification
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User newUser = userController.registerUser(user);
            // TODO alelouet : Ne pas renvoyer le password, renvoyer un bean réduit car WS non authenth
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            // On ne donne volontairement pas d'informations sur l'erreur au front
            throw new EneanceException(WsError.USER_CANT_BE_CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userController.findById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new EneanceException(WsError.USER_NOT_FOUND, "User with ID " + id + " not found"));
    }
}
