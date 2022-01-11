package br.com.jfcardoso.tqi_evolution_avaliacao.config.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(RuntimeException exception) {
        Map<String, String> retornoErro = new HashMap<>();
        retornoErro.put("mensagem", exception.getMessage());
        return ResponseEntity.unprocessableEntity().body(retornoErro);
    }

}
