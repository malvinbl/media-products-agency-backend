package es.mbl_cu.mpa.infrastructure.http.cotroller.exception;

import es.mbl_cu.mpa.domain.exception.BadRequestException;
import es.mbl_cu.mpa.domain.exception.CategoryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {

	private static final String ERROR = "error";

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Map<String, Object>> handle(BadRequestException e) {
		log.error("Exception it has happened: ", e);
		var error = e.getMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(Map.of(ERROR, error));
	}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        log.error("Exception it has happened: ", e);
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handle(CategoryNotFoundException e) {
        log.error("Exception it has happened: ", e);
        var error = "Category not exists";
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(Map.of(ERROR, error));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handle(Exception e) {
        log.error("Exception it has happened: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(Map.of(ERROR, e.getMessage()));
    }

}
