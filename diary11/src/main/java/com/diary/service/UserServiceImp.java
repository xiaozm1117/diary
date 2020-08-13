package com.diary.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.diary.dao.UserDao;
import com.diary.thread.UserThreadLocal;
import com.diary.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class UserServiceImp implements UserService {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private UserDao userDao;
	@Override
	public void loginSubmit(User user,HttpServletRequest request,HttpServletResponse response) {
		
		
			String token="";
			String password=user.getPassword();
			user.setPassword(DigestUtils.md5Hex(password));
			List<User> userList=userDao.loginSubmit(user);
			
			if(userList==null || userList.isEmpty()) throw new RuntimeException();
			
			user=userList.get(0);
			
			try {
				String userJSON = objectMapper.writeValueAsString(user);
				//md5（“JT_TICKET_” + System.currentTime + username）
				token = DigestUtils.md5Hex("TICTOT_"+System.currentTimeMillis() + user.getUsername());
				request.getSession().getServletContext().setAttribute(token,userJSON);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			
			
			 Cookie cookie = new Cookie("TICTOK", token);
				cookie.setPath("/"); //表示在浏览器根目录生效
				cookie.setMaxAge(3600 * 24 * 7); //设定生命周期 单位秒
				response.addCookie(cookie);
			
	}

	@Override
	public void registerSubmit(User user) {
		String password=user.getPassword();
		user.setPassword(DigestUtils.md5Hex(password));
		int a=userDao.registerSubmit(user);
		if(a==0) throw new RuntimeException();
		
	}

	@Override
	public void doCheckname(String name) {
		int a=userDao.doCheckname(name);
		if(a==0)throw new RuntimeException();
	}

	
}
