package fr.lelouet.taskmanagereneance.webservices.utils.error_handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Surcouche d'exceptions pour ajouter le contexte de l'application dans certaines erreurs "metiers"
 */
@Getter
@RequiredArgsConstructor
public class EneanceException extends RuntimeException {
    private final WsError error;

    public EneanceException(WsError error, String specificMessage) {
        super(error.formatMessage(specificMessage));
        this.error = error;
    }
}