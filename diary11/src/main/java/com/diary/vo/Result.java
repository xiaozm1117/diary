package com.diary.vo;

import java.io.Serializable;

public class Result implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1548484541521521L;
	private int state=1;
    private Object data;
    private String  message="ok";
    public Result() {}
    public Result(int state) {this.state=state;}
    public Result(String message) {this.message=message;}
    public Result(Object data) {this.data=data;}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
