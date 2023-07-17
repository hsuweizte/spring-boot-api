package com.hsuweizte.handler;

import com.hsuweizte.exception.InvalidRequestException;
import com.hsuweizte.exception.NotFoundException;
import com.hsuweizte.resource.ErrorResource;
import com.hsuweizte.resource.FieldResource;
import com.hsuweizte.resource.InvalidErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /* 處理資源找不到異常*/
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleNofFound(RuntimeException e) {
        ErrorResource errorResource = new ErrorResource(e.getMessage());
        ResponseEntity result = new ResponseEntity<Object>(errorResource, HttpStatus.NOT_FOUND);
        logger.warn("Return ------ {}", result);
        return result;
    }

    /*
     * 處理參數驗證失敗異常
     */
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    public ResponseEntity<?> handleInvalidRequest(InvalidRequestException e) {
        Errors errors = e.getErrors();
        List<FieldResource> fieldResources = new ArrayList<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            FieldResource fieldResource = new FieldResource(fieldError.getObjectName(),
                    fieldError.getField(),
                    fieldError.getCode(),
                    fieldError.getDefaultMessage());

            fieldResources.add(fieldResource);
        }

        InvalidErrorResource ier = new InvalidErrorResource(e.getMessage(), fieldResources);
        ResponseEntity result = new ResponseEntity<Object>(ier, HttpStatus.BAD_REQUEST);
        logger.warn("Return ------ {}", result);
        return result;
    }

    /**
     * 處理全域異常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleException(Exception e) {
        logger.error("Error ---- {}", e);
        return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

