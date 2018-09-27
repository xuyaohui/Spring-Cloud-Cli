package com.teradata.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntity {
	
	private Integer code;//http状态码 代码正常时返回的状态 200代表操作成功 400代表操作失败 500代表服务器出现程序上的bug
	
	private String msg;//提示消息
	
	private Map<String, Object> data=new HashMap<>();//返回数据
	
	/**
	 * 操作成功
	 * @return
	 */
	public static ResponseEntity success() {
		ResponseEntity result=new ResponseEntity();
		result.setCode(200);
		result.setMsg("操作成功！");
		return result;
	}
	/**
	 * 操作成功
	 * @param code 状态码
	 * @return
	 */
	public static ResponseEntity success(Integer code) {
		ResponseEntity result=new ResponseEntity();
		result.setCode(code);
		result.setMsg("操作成功！");
		return result;
	}
	/**
	 * 操作成功
	 * @param msg 提示信息
	 * @return
	 */
	public static ResponseEntity success(String msg) {
		ResponseEntity result=new ResponseEntity();
		result.setCode(200);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 服务器异常
	 * @return
	 */
	public static ResponseEntity error() {
		ResponseEntity result=new ResponseEntity();
		result.setCode(500);
		result.setMsg("服务器出错！");
		return result;
	}
	/**
	 * 服务器异常
	 * @param msg 服务器异常提示信息
	 * @return
	 */
	public static ResponseEntity error(String msg) {
		ResponseEntity result=new ResponseEntity();
		result.setCode(500);
		result.setMsg(msg);
		return result;
	}
	
	public  ResponseEntity add(String key,Object value) {
	   this.getData().put(key, value);
	   return this;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
