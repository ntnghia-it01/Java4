package com.fpoly.java4.services;

import java.sql.SQLDataException;
import java.util.Date;

import com.fpoly.java4.beans.VideoFormBean;

import jakarta.servlet.http.HttpServletRequest;

public class VideoServices {
	
	public boolean createVideo(VideoFormBean bean, HttpServletRequest request) {
		try {
//			Lưu video => Lấy tên video
			String extVideo = bean.getVideo().getContentType().split("/")[1]; 
			String fileNameVideo = String.valueOf(new Date().getTime()); 
			String pathVideo =  "/videos/" + fileNameVideo + "." + extVideo; // Lưu DB 
			String pathContextVideo = request.getServletContext().getRealPath(pathVideo);
			bean.getVideo().write(pathContextVideo);
//			Lưu ảnh => Lấy tên ảnh
			String extImage = bean.getImage().getContentType().split("/")[1]; 
			String fileNameImage = String.valueOf(new Date().getTime()); 
			String pathImage =  "/images/" + fileNameImage + "." + extImage; // Lưu DB
			String pathContextImage = request.getServletContext().getRealPath(pathImage);
			bean.getImage().write(pathContextImage);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return false;
	}
}
