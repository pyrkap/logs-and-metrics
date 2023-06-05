package pyrkap.logsandmetrics.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pyrkap.logsandmetrics.domain.exceptions.CyclistAlreadyExistsException;
import pyrkap.logsandmetrics.domain.exceptions.CyclistNotFoundException;
import pyrkap.logsandmetrics.domain.exceptions.InvalidIdException;
import pyrkap.logsandmetrics.infrastructure.api.dtos.MessageDto;

@ControllerAdvice
public class CustomExceptionHandler {
    
    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = {CyclistAlreadyExistsException.class})
    public ResponseEntity<MessageDto> handle(CyclistAlreadyExistsException e) {
        logger.warn(e.getMessage());
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {CyclistNotFoundException.class})
    public ResponseEntity<MessageDto> handle(CyclistNotFoundException e) {
        logger.warn(e.getMessage());
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidIdException.class})
    public ResponseEntity<MessageDto> handle(InvalidIdException e) {
        logger.warn(e.getMessage());
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<MessageDto> handle(Exception e) {
        logger.error("Unexpected error occurred", e);
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
