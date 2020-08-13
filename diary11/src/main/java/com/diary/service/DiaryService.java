package com.diary.service;

public interface DiaryService {

	void write(String content);


	String read(int page);


	int findCount();

}
