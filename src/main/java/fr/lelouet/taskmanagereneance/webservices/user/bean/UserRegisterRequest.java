package fr.lelouet.taskmanagereneance.webservices.user.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Bean d'entrée utilisé pour lors de l'inscription d'un utilisateur
 */
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
