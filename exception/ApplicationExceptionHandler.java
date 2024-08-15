package com.society.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(UserNotFoundException notFoundException){
      return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
	
	}
	 @ExceptionHandler(InvalidUserException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public ResponseEntity<?> handleInvalidUserException(InvalidUserException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ResponseBody
	    public  ResponseEntity<?> handleException(Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    @ExceptionHandler(PaymentNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ResponseBody
	    public ResponseEntity<?>  handlePaymentNotFoundException(PaymentNotFoundException ex) {
	        return new ResponseEntity<>( ex.getMessage(),HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(InvalidPaymentException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public ResponseEntity<?>  handleInvalidPaymentException(InvalidPaymentException ex) {
	        return new ResponseEntity<> ( ex.getMessage(),HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(ComplaintNotFoundException.class)
	    public ResponseEntity<String> handleComplaintNotFoundException(ComplaintNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }

	    @ExceptionHandler(InvalidComplaintException.class)
	    public ResponseEntity<String> handleInvalidComplaintException(InvalidComplaintException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	    @ExceptionHandler(BillNotFoundException.class)
	    public ResponseEntity<String> handleBillNotFoundException(BillNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }

	    @ExceptionHandler(InvalidBillException.class)
	    public ResponseEntity<String> handleInvalidBillException(InvalidBillException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	    
	
}
