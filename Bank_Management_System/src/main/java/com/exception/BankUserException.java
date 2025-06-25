package com.exception;

public class BankUserException extends RuntimeException {
	String msg;
	public BankUserException(String msg) {
		this.msg=msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
