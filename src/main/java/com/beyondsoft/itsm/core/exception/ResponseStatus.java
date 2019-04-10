package com.beyondsoft.itsm.core.exception;

public enum ResponseStatus implements IResponseStatus {
	OK("200","成功"), 
	ERROR("400","失败"),
	CA_ERROR("9999","ca webservice访问失败"),
	;

	private String code; // 错误代码
	private String msg; // 错误原因

	ResponseStatus(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	
	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMsg() {
		return this.msg;
	}
}
