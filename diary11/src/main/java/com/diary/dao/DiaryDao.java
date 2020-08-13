package com.diary.dao;

import org.apache.ibatis.annotations.Param;

import com.diary.vo.Diary;

public interface DiaryDao {

	void write(Diary diary);

	String readFilePath(@Param(value = "page") int page,@Param(value = "userId") int userId);

	int findCount(int userId); 
	
}
