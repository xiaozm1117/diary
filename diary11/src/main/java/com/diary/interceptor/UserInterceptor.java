package com.diary.interceptor;


import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.diary.thread.UserThreadLocal;
import com.diary.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserInterceptor implements HandlerInterceptor{
	private ObjectMapper objectMapper=new ObjectMapper();
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ServletContext application = request.getSession().getServletContext();
		
Cookie[] cookies  = request.getCookies();
		if(cookies!=null) {
		String token = "";
		for (Cookie cookie : cookies) {
			
			if("TICTOK".equals(cookie.getName())){
				token = cookie.getValue();
				break;
			}
		}
		
		//判断token是否为null
		if(!StringUtils.isEmpty(token)){
			
			
			String userJSON = (String) request.getSession().getServletContext().getAttribute(token);
			
			if(!StringUtils.isEmpty(userJSON)){
			
				User user = objectMapper.readValue(userJSON, User.class);
				//可以将user信息保持到request域中/session域 但是该方法无法在业务层中动态获取userId
				//需要在业务接口中 添加userId等信息 
				//request.setAttribute("JT_USER", user);
				UserThreadLocal.set(user);
				
				 //放行请求
				return true;
			}
		}
		}
		
		//如果程序执行到这里说明用户登陆有误,则需要重新登陆
		response.sendRedirect("/user/login.html");
		return false; //表示拦截
	}

	//controller中的方法刚刚执行完
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//返回页面前
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//将threadLocal销毁
		UserThreadLocal.remove();
		
	}

}
