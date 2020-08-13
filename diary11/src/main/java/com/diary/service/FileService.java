package com.diary.service;

import org.springframework.web.multipart.MultipartFile;

import com.diary.vo.PicUploadResult;


public interface FileService {

	PicUploadResult upload(MultipartFile uploadFile);

}
