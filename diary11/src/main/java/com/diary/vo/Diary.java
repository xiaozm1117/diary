package com.diary.vo;

import java.io.Serializable;
import java.util.Date;


public class Diary implements Serializable{

	private static final long serialVersionUID = -8675738867992275478L;
	
	private Integer id;
	private Integer userId;
	private Date createTime;
	private String content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
