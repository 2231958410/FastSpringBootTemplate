package com.springtemplate.util;

import com.springtemplate.common.CommonConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author Qiu Ping
 * @description 响应信息主体
 * @date 2019/8/11
 */



@Builder
@ToString
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "响应信息主体")
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "返回标记：成功标记=0，失败标记=1")
	private int code;

	@ApiModelProperty(value = "返回信息")
	private String msg;

	@ApiModelProperty(value = "数据")
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public R() {
		super();
	}

	public R(T data) {
		super();
		this.data = data;
	}

	public R(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}

	public R(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = CommonConstants.FAIL;
	}

	public static <T> R<T> ok() {
		return restResult(null, CommonConstants.SUCCESS, null);
	}

	public static <T> R<T> ok(T data) {
		return restResult(data, CommonConstants.SUCCESS, null);
	}

	public static <T> R<T> ok(T data, String msg) {
		return restResult(data, CommonConstants.SUCCESS, msg);
	}

	public static <T> R<T> failed() {
		return restResult(null, CommonConstants.FAIL, null);
	}

	public static <T> R<T> failed(String msg) {
		return restResult(null, CommonConstants.FAIL, msg);
	}

	public static <T> R<T> failed(T data) {
		return restResult(data, CommonConstants.FAIL, null);
	}

	public static <T> R<T> failed(T data, String msg) {
		return restResult(data, CommonConstants.FAIL, msg);
	}

	private static <T> R<T> restResult(T data, int code, String msg) {
		R<T> apiResult = new R<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}
}
