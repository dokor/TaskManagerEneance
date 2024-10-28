package fr.lelouet.taskmanagereneance.webservices.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * Listing des erreurs WS disponibles pour le projet dans le cas de réponses API "invalides".
 */
public enum WsError {
    INVALID_INPUT("INVALID_INPUT", "Invalid input provided"),
    UNAUTHORIZED_ACCESS("UNAUTHORIZED_ACCESS", "Unauthorized access"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal server error"),
    // USER Errors
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found"),
    USER_CANT_BE_CREATED("USER_CANT_BE_CREATED", "User can't be created")
    // TASK Errors
    ;

    private final String code;
    private final String defaultMessage;

    public String formatMessage(String specificMessage) {
        return String.format("%s: %s", code, specificMessage != null ? specificMessage : defaultMessage);
    }
}