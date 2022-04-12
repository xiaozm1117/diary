package com.diary.service;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diary.dao.DiaryDao;
import com.diary.thread.UserThreadLocal;
import com.diary.vo.Diary;
@Service
public class DiaryServiceImp implements DiaryService {
	@Autowired
	private DiaryDao diaryDao;
	
	@Override
	public void write(String content, String realPath) {
		Diary diary=new Diary();
		int userId=UserThreadLocal.get().getId();
		diary.setUserId(userId);
		
		int count=diaryDao.findCount(userId);
		//
		File picFile = new File(realPath+"/diary/userId="+userId);
		if(!picFile.exists()){
			picFile.mkdirs();
		}
		String DatePath =realPath+"/diary/userId="+userId+"/p"+ (count+1);
		diary.setContent(DatePath);
		diary.setCreateTime(new Date());
		
		diaryDao.write(diary);
		write11(DatePath,content);
	}
	
	public void write11(String DatePath,String content) {
		FileWriter fwriter = null;
		try {
			// true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
			fwriter = new FileWriter(DatePath, true);
			fwriter.write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fwriter.flush();
				fwriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	
	@Override
	public String read(int page) {
		int userId=UserThreadLocal.get().getId();
		String path=diaryDao.readFilePath(page,userId);
		if (path!=null) {
			return read(path);
		}
		return "";
	}
	
	public String read(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr);
			}
			reader.close();
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}

	@Override
	public int findCount() {
		int userId=UserThreadLocal.get().getId();
		return diaryDao.findCount(userId);
	}

}
