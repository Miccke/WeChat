package com.cn.car.util;

import com.alibaba.fastjson.JSONObject;

public class JsonResult {
	private boolean success;

	private String message;

	private JSONObject data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public JsonResult(boolean success, String message, JSONObject data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public JsonResult() {
		super();
	}

}
