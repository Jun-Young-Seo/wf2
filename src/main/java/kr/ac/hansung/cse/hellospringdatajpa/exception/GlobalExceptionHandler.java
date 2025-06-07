package kr.ac.hansung.cse.hellospringdatajpa.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void userAlreadyExistException(UserAlreadyExistException e) {
        logger.error(e.getMessage());
    }

    @ExceptionHandler(NoRoleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void noRoleException(NoRoleException e) {
        logger.error(e.getMessage());
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void userNameNotFoundException(UserNameNotFoundException e) {
        logger.error(e.getMessage());
    }
}
