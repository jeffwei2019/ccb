package com.beyondsoft.itsm.core.exception;

/**
 *  核心业务异常�?
 * @author jeff
 *
 */
public class BussinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String errorCode;

	public BussinessException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}

	public BussinessException(String errorCode, String errorMsg, Throwable throwable) {
		super(errorMsg, throwable);
		this.errorCode = errorCode;
	}

	public BussinessException(IResponseStatus responseStatus) {
		super(responseStatus.getMsg());
		this.errorCode = responseStatus.getCode();
	}

	public BussinessException(IResponseStatus responseStatus, Throwable throwable) {
		super(responseStatus.getMsg(), throwable);
		this.errorCode = responseStatus.getCode();
	}

	public String getErrorCode() {
		return errorCode;
	}
}
