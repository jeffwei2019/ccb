package com.beyondsoft.itsm.core;

import com.beyondsoft.itsm.core.exception.IResponseStatus;
import com.beyondsoft.itsm.core.exception.ResponseStatus;

/**
 * 基本输出dto
 * 
 * @author jeff
 *
 */
public class BaseOutVO<T> {
	private String code; // 错误代码
	private String msg; // 错误消息

	private boolean success; // 是否成功

	private T data; // 返回数据

	public BaseOutVO() {
		//转json需要的空构造函数
	}

	public BaseOutVO(final String code, final String msg, final boolean success, final T data) {
		this.code = code;
		this.msg = msg;
		this.success = success;
		this.data = data;
	}

	public static BaseOutVO success() {
		return new BaseOutVO(ResponseStatus.OK.getCode(), ResponseStatus.OK.getMsg(), true, null);
	}

	public static <T> BaseOutVO<T> success(final T data) {
		return new BaseOutVO<T>(ResponseStatus.OK.getCode(), ResponseStatus.OK.getMsg(), true, data);
	}

	public static <T> BaseOutVO<T> success(final String msg, final T data) {
		return new BaseOutVO<T>(ResponseStatus.OK.getCode(), msg, true, data);
	}

	public static BaseOutVO fail(IResponseStatus iResponseStatus) {
		return new BaseOutVO(iResponseStatus.getCode(), iResponseStatus.getMsg(), false, null);
	}
	
	public static BaseOutVO fail(final String code,final String message) {
		return new BaseOutVO(code, message, false, null);
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
