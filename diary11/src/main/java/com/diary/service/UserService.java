package com.diary.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diary.vo.User;

public interface UserService {


	void registerSubmit(User user);

	void loginSubmit(User user, HttpServletRequest request, HttpServletResponse response);

	void doCheckname(String name);

}
