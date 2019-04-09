package com.beyondsoft.itsm.modules.user.domain;

import com.beyondsoft.itsm.core.exception.IResponseStatus;

public enum UserResponseStatus implements IResponseStatus{
	OK("200","成功"), 
	ERROR("400","失败");

	private String code; // 错误代码
	private String msg; // 错误原因

	UserResponseStatus(String code, String msg) {
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
