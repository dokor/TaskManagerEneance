package fr.lelouet.taskmanagereneance.webservices.user;

import fr.lelouet.taskmanagereneance.controller.UserController;
import fr.lelouet.taskmanagereneance.model.User;
import fr.lelouet.taskmanagereneance.service.CustomUserDetailsService;
import fr.lelouet.taskmanagereneance.webservices.utils.error_handler.WsError;
import fr.lelouet.taskmanagereneance.webservices.user.bean.LoginRequest;
import fr.lelouet.taskmanagereneance.webservices.user.bean.UserRegisterRequest;
import fr.lelouet.taskmanagereneance.webservices.user.bean.UserResponse;
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
 * WS des utilisateurs
 */
@RestController
@RequestMapping("/users")
public class UserWs {

    @Autowired
    private UserController userController;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Défini comme sans authentification dans SecurityConfig
    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        try {
            User newUser = userController.registerUser(userRegisterRequest);
            // TODO alelouet : ajouter validation des données
            UserResponse userResponse = new UserResponse(
                newUser.getId(),
                newUser.getEmail(),
                newUser.getFirstName(),
                newUser.getLastName()
            );
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            // On ne donne volontairement pas d'informations sur l'erreur au front
            throw new EneanceException(WsError.USER_CANT_BE_CREATED);
        }
    }

    // Défini comme sans authentification dans SecurityConfig
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
            // Si l'authentification réussit, charger les vraies informations utilisateur
            User user = (User) userDetailsService.loadUserByUsername(loginRequest.getLogin());
            String jwt = jwtUtil.generateToken(user.getUsername(), user.getId(), user.getFirstName(), user.getLastName());
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
