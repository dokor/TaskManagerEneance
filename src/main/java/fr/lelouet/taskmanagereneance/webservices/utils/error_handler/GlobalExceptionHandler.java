package fr.lelouet.taskmanagereneance.webservices.utils.error_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handler d'erreurs WS afin de traiter les cas d'erreurs metiers plus précisement
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // TODO : Modfier le handler pour avoir un générique métier et avoir un handler par Ws si necessaire
    @ExceptionHandler(EneanceException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(EneanceException ex) {
        WsError error = ex.getError();
        return new ResponseEntity<>(new ErrorResponse(error.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        WsError error = WsError.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ErrorResponse(error.getCode(), error.getDefaultMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}