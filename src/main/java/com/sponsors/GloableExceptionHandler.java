package com.sponsors;

import com.sponsors.dto.ErrorDto;
import com.sponsors.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.InternalServerErrorException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GloableExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GloableExceptionHandler.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	@ExceptionHandler(BaseException.class)
	@ResponseBody
	public ErrorDto webApplicationException(BaseException baseException, HttpServletResponse response) {
		response.setStatus(baseException.getStatus());
		return baseException.createErrorDto();
	}


	@ExceptionHandler(value = { InternalServerErrorException.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public Map<String, Object> internalServerErrorException(InternalServerErrorException exception) {
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("error", exception.getMessage());
		exception.printStackTrace();
		return responseData;
	}



}
