package fr.lelouet.taskmanagereneance.webservices.user;

import fr.lelouet.taskmanagereneance.controller.UserController;
import fr.lelouet.taskmanagereneance.model.User;
import fr.lelouet.taskmanagereneance.webservices.enums.WsError;
import fr.lelouet.taskmanagereneance.webservices.user.bean.LoginRequest;
import fr.lelouet.taskmanagereneance.webservices.utils.error_handler.EneanceException;
import fr.lelouet.taskmanagereneance.webservices.utils.jwt.JwtUtil;
import fr.lelouet.taskmanagereneance.webservices.utils.jwt.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // Défini comme sans authentification dans SecurityConfig
    @PostMapping("/register")
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

    // Défini comme sans authentification dans SecurityConfig
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
            String jwt = jwtUtil.generateToken(loginRequest.getLogin());
            return ResponseEntity.ok(new UserSession(jwt));
        } catch (AuthenticationException e) {
            throw new EneanceException(WsError.USER_CANT_LOGIN);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userController.findById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new EneanceException(WsError.USER_NOT_FOUND, "User with ID " + id + " not found"));
    }
}
