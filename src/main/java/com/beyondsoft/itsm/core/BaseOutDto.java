package com.beyondsoft.itsm.core;

import com.beyondsoft.itsm.core.exception.ResponseStatus;

/**
 * 基本输出dto
 * 
 * @author jeff
 *
 */
public class BaseOutDto<T> {
	private String code; // 错误代码
	private String msg; // 错误消息

	private boolean success; // 是否成功

	private T data; // 返回�?

	public BaseOutDto() {

	}

	public BaseOutDto(final String code, final String msg, final boolean success, final T data) {
		this.code = code;
		this.msg = msg;
		this.success = success;
		this.data = data;
	}

	public static BaseOutDto success() {
		return new BaseOutDto(ResponseStatus.OK.getCode(), ResponseStatus.OK.getMsg(), true, null);
	}

	public static <T>  BaseOutDto<T> success(final T data) {
		return new BaseOutDto<T>(ResponseStatus.OK.getCode(), ResponseStatus.OK.getMsg(), true,data);
	}

	public static  <T> BaseOutDto<T> success(final String msg, final T data) {
		return new BaseOutDto<T>(ResponseStatus.OK.getCode(), msg, true, data);
	}

	public static BaseOutDto fail(final String code, final String msg) {
		return new BaseOutDto(code, msg, false, null);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
