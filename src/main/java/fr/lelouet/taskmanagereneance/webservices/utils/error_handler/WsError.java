package fr.lelouet.taskmanagereneance.webservices.utils.error_handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Listing des erreurs WS disponibles pour le projet dans le cas de réponses API "invalides".
 */
@Getter
@AllArgsConstructor
public enum WsError {
    INVALID_INPUT("INVALID_INPUT", "Invalid input provided"),
    UNAUTHORIZED_ACCESS("UNAUTHORIZED_ACCESS", "Unauthorized access"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal server error"),
    // USER Errors
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found"),
    USER_CANT_BE_CREATED("USER_CANT_BE_CREATED", "User can't be created"),
    USER_CANT_LOGIN("USER_CANT_LOGIN", "User can't be logged"),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "Email already in use"),
    // TASK Errors
    TASK_CANT_BE_CREATED("TASK_CANT_BE_CREATED", "Task can't be created")
    ;

    private final String code;
    private final String defaultMessage;

    public String formatMessage(String specificMessage) {
        return String.format("%s", specificMessage != null ? specificMessage : defaultMessage);
    }
}
