package seang.spring.testingmvc.exceptionHandler;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import seang.spring.testingmvc.model.entity.Users;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");

        String errorMessage = ex.getLocalizedMessage();
        if (ex.getCause() != null && ex.getCause().getMessage() != null) {
            String causeMessage = ex.getCause().getMessage();
            if (causeMessage.contains("users_email_key") && causeMessage.contains("email")) {

                String email = "";
                int startIndex = causeMessage.indexOf("Key (email)=(") + "Key (email)=(".length();
                int endIndex = causeMessage.indexOf(")", startIndex);
                if (endIndex != -1) {
                    email = causeMessage.substring(startIndex, endIndex); //get string between startIndex and endIndex
                }
                errorMessage = "The email " + email + " is already registered in user.";
            } else if (causeMessage.contains("users_uuid_key") && causeMessage.contains("uuid")) {
                String uuid = "";
                int startIndex = causeMessage.indexOf("Key (uuid)=(") + "Key (uuid)=(".length();
                int endIndex = causeMessage.indexOf(")", startIndex);
                if (endIndex != -1) {
                    uuid = causeMessage.substring(startIndex, endIndex);
                }
                errorMessage = "The uuid " + uuid + " is already registered in user.";
            }else if(causeMessage.contains("users_phone_number_key") && causeMessage.contains("phone_number")) {
                String phone = "";
                int startIndex = causeMessage.indexOf("Key (phone_number)=(") + "Key (phone_number)=(".length();
                int endIndex = causeMessage.indexOf(")", startIndex);
                if (endIndex != -1) {
                    phone = causeMessage.substring(startIndex, endIndex);
                }
                errorMessage = "The phone " + phone + " is already registered in user.";
            }else if(causeMessage.contains("roles_pk") && causeMessage.contains("name")) {
                String roleName = "";
                int startIndex = causeMessage.indexOf("Key (name)=(") + "Key (name)=(".length();
                int endIndex = causeMessage.indexOf(")", startIndex);
                if (endIndex != -1) {
                    roleName = causeMessage.substring(startIndex, endIndex);
                }
                errorMessage = "The role " + roleName + " is already registered in user.";
            }
        }
        body.put("message", errorMessage);
        body.put("path", request.getDescription(false).replace("uri=", "")); // Clean up the path
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("path", request.getDescription(false).replace("uri=", ""));
        body.put("message", ex.getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
