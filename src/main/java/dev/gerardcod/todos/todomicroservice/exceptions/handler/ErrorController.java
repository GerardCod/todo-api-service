package dev.gerardcod.todos.todomicroservice.exceptions.handler;

import dev.gerardcod.todos.commons.exceptions.BadRequestBodyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(BadRequestBodyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(BadRequestBodyException e) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", e.getMessage());
        response.put("errors", e.getErrors());
        return response;
    }

}
