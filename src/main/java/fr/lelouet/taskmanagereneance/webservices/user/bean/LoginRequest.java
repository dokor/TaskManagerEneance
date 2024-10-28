package fr.lelouet.taskmanagereneance.webservices.user.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Bean d'entrée utilisé pour lors du login d'un utilisateur
 */
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    private String login;
    private String password;
}
