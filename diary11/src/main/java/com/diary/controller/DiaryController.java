package com.diary.controller;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.diary.service.DiaryService;
import com.diary.vo.Result;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/diary")
public class DiaryController {
	@Autowired
	private DiaryService diaryService;
	
	@RequestMapping("/{moduleName}")
	public String toModule(@PathVariable String moduleName){
		
		return moduleName;
	}
	
	
	@ResponseBody
	@RequestMapping("/submit")
	public Result write(String content, HttpServletRequest request) {
		try {
			diaryService.write(content,request.getSession().getServletContext().getRealPath("/"));
			return new Result("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result("noOk");
	}
	
	
	@RequestMapping("/load")
	public ModelAndView read(int page,ModelAndView mv) {
		
		String content=diaryService.read(page);
		int total=diaryService.findCount();
		mv.addObject("total",total);
		mv.addObject("content", content);
		mv.addObject("currentPage",page);
		mv.setViewName("diaries");
		return mv;
	}
	
	/*
	 * public Result read() { try { diaryService.read(); return new Result("ok"); }
	 * catch (Exception e) { e.printStackTrace(); } return new Result("noOk"); }
	 */
	
	
}
