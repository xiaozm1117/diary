package com.diary.service;

public interface DiaryService {

	void write(String content, String realPath);


	String read(int page);


	int findCount();

}
