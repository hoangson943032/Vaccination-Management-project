package qld.mock.vaccination.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserExceptionHandler {
	@ExceptionHandler(NotFountException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String HandlerNotFountException(NotFountException notFountException, WebRequest webRequest) {
		
		return "exceptionPage/failure";
	}
	
	
//	@ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handleAllException(Exception ex, WebRequest request) {
//		
//		return "exceptionPage/failure";
//    }
//	


}
