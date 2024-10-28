package fr.lelouet.taskmanagereneance.webservices.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Réponse type d'une erreur WS
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
}