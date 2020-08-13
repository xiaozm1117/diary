package com.diary.controller;

import java.security.PublicKey;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diary.service.UserService;
import com.diary.vo.User;
import com.diary.vo.Result;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String to1(){
		
		return "login";
	}
	
	@RequestMapping("/register")
	public String to2(){
		
		return "register";
	}
	
	 
	@RequestMapping(value="/doLogin")
	public String loginSubmit(User user,HttpServletRequest request,HttpServletResponse response) {
		System.out.print("login");
		try {
			 userService.loginSubmit(user,request,response);
			 return "home";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "login";
		 
	}
	
	@RequestMapping("/doRegister")

	public String registerSubmit(User user) {
		System.out.print("register");
		try {
			userService.registerSubmit(user);
			return "login";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "register";
	}
	
	@RequestMapping("/doCheckname")
	@ResponseBody
	public Result doCheckname( String username) {
		try {
			userService.doCheckname(username);
			return new Result("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result("noOk");
		
	}
}
