package com.mediaocean.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private ResponseEntity<Object> customizeErrorMessage(Exception ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ErrorMsg msg = new ErrorMsg();
		msg.setTimeStamp(new Date().getTime());
		msg.setErrorCode(status.value());
		msg.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, msg, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return customizeErrorMessage(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return customizeErrorMessage(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return customizeErrorMessage(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return customizeErrorMessage(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return customizeErrorMessage(ex, headers, status, request);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return customizeErrorMessage(manve, headers, status, request);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorMsg> handleResourceNotFoundException(Exception ex, HttpServletRequest request) {

		ErrorMsg msg = new ErrorMsg();
		msg.setTimeStamp(new Date().getTime());
		msg.setErrorCode(HttpStatus.NOT_FOUND.value());
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorMsg>(msg, null, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler({ InValidInputException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		ErrorMsg msg = new ErrorMsg();
		msg.setTimeStamp(new Date().getTime());
		msg.setErrorCode(HttpStatus.BAD_REQUEST.value());
		msg.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


}

