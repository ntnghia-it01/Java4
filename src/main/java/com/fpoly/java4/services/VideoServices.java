package com.fpoly.java4.services;

import java.sql.SQLDataException;
import java.util.Date;

import com.fpoly.java4.beans.VideoFormBean;
import com.fpoly.java4.dao.CategoryDAO;
import com.fpoly.java4.dao.UserDAO;
import com.fpoly.java4.dao.VideoDAO;
import com.fpoly.java4.entities.CategoryEntity;
import com.fpoly.java4.entities.UserEntity;
import com.fpoly.java4.entities.VideoEntity;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class VideoServices {
	
	public boolean createVideo(VideoFormBean bean, HttpServletRequest request) {
		try {
//			Lưu video => Lấy tên video
			String extVideo = bean.getVideoForm().getContentType().split("/")[1]; 
			String fileNameVideo = String.valueOf(new Date().getTime()); 
			String pathVideo =  "/assets/videos/" + fileNameVideo + "." + extVideo; // Lưu DB 
			String pathContextVideo = request.getServletContext().getRealPath(pathVideo);
			bean.getVideoForm().write(pathContextVideo);
//			Lưu ảnh => Lấy tên ảnh
			String extImage = bean.getImageForm().getContentType().split("/")[1]; 
			String fileNameImage = String.valueOf(new Date().getTime()); 
			String pathImage =  "/assets/images/" + fileNameImage + "." + extImage; // Lưu DB
			String pathContextImage = request.getServletContext().getRealPath(pathImage);
			bean.getImageForm().write(pathContextImage);
			
//			Chuyển dữ liệu từ bean => Entity
			VideoEntity videoEntity = new VideoEntity();
			videoEntity.setTitle(bean.getTitle());
			videoEntity.setDescription(bean.getDesc());
			videoEntity.setVideoURL(pathVideo);
			videoEntity.setThumnailURL(pathImage);
			videoEntity.setStatus(bean.getStatus());
			
//			Lấy được đối tượng user đang đăng nhập từ db
//			=> Thêm vào userEntity
			
//			Lấy userID từ cookie => Dùng DAO để lấy được UserEntity
			
			Cookie[] cookies = request.getCookies();
			
			String userID = "";
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userId")) {
					userID = cookie.getValue();
					break;
				}
			}
			
			UserDAO userDAO = new UserDAO();
//			Integer.parseInt(userID) => Có thể có lỗi xảy ra không?
			UserEntity userEntity = userDAO.getUserById(Integer.parseInt(userID));
			videoEntity.setUserEntity(userEntity);
			
//			Lấy CategoryEntity từ categoryID => Để thêm vào VideoEntity
//			Có categoryID => Form => Bean 
//			bean.getCategory(); => ID
			CategoryDAO categoryDAO = new CategoryDAO();
			CategoryEntity categoryEntity = categoryDAO.getCategoryById(bean.getCategory());
			videoEntity.setCategoryEnitity(categoryEntity);
			
//			Tương tác vào DB => DAO
			VideoDAO videoDAO = new VideoDAO();
			return videoDAO.create(videoEntity);
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return false;
	}
}
// 1 - User
// 2 - Channel (Có thể CRUD video)
// 3 - Admin (Có thể CRUD video)
