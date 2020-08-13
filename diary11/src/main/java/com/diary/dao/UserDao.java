package com.diary.dao;


import java.security.PublicKey;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.diary.vo.User;

import sun.security.jgss.LoginConfigImpl;

public interface UserDao {
	
	public int registerSubmit(User user);
	public List<User> loginSubmit(User user);
	public int doCheckname(String name);
	
}
