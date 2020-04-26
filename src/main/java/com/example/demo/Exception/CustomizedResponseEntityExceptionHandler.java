package com.example.demo.Exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//defining exception handling for all the exceptions   
@ControllerAdvice  
@RestController  
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {  
	
	@ExceptionHandler(Exception.class)  
	//override method of ResponseEntityExceptionHandler class  
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
        details.add(request.getDescription(false));
		//creating exception response structure  
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Internal Server Error", details);  
		//returning exception structure and Internal Server status   
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);  
	}  
	
	@ExceptionHandler(RecordNotFoundException.class)  
	//override method of ResponseEntityExceptionHandler class  
	public final ResponseEntity<Object> handleRecordNotFoundExceptions(RecordNotFoundException ex, WebRequest request) {  
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
        details.add(request.getDescription(false));
		//creating exception response structure  
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Record Not Found", details);  
		//returning exception structure and Not Found status   
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);  
	}     

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", details);
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}