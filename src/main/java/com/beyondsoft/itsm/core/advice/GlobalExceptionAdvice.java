package com.beyondsoft.itsm.core.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.beyondsoft.itsm.core.BaseOutVO;
import com.beyondsoft.itsm.core.exception.BussinessException;
import com.beyondsoft.itsm.core.exception.ResponseStatus;

/**
 * 全局exception切面类 捕获controller控制抛出的所有异常
 * 
 * @author jeff
 *
 */
@ControllerAdvice
public class GlobalExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

	@ExceptionHandler
	@ResponseBody
	public BaseOutVO exception(Exception exception, WebRequest request) {
		logger.error(exception.getMessage());
		BaseOutVO baseOutVO;
		if (exception instanceof BussinessException) {
			BussinessException bussinessException = (BussinessException) exception;
			baseOutVO = BaseOutVO.fail(bussinessException.getErrorCode(), bussinessException.getMessage());
		} else {
			baseOutVO = BaseOutVO.fail(ResponseStatus.ERROR.getCode(), exception.getMessage());
		}
		return baseOutVO;
	}
}
