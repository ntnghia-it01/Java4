package com.fpoly.java4.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fpoly.java4.beans.VideoFormBean;
import com.fpoly.java4.dao.CategoryDAO;
import com.fpoly.java4.dao.ChannelFollowDAO;
import com.fpoly.java4.dao.UserDAO;
import com.fpoly.java4.dao.VideoDAO;
import com.fpoly.java4.entities.CategoryEntity;
import com.fpoly.java4.entities.ChannelFollowEntity;
import com.fpoly.java4.entities.UserEntity;
import com.fpoly.java4.entities.VideoEntity;
import com.fpoly.java4.utils.Utils;

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
			
//			Cookie[] cookies = request.getCookies();
			
			String userID = Utils.getCookieByName("userId", request);
//			for(Cookie cookie : cookies) {
//				if(cookie.getName().equals("userId")) {
//					userID = cookie.getValue();
//					break;
//				}
//			}
			
			UserDAO userDAO = new UserDAO();
//			Integer.parseInt(userID) => Có thể có lỗi xảy ra không?
			UserEntity userEntity = userDAO.getUserById(Integer.parseInt(userID));
			videoEntity.setUserEntity(userEntity);
			
//			Lấy CategoryEntity từ categoryID => Để thêm vào VideoEntity
//			Có categoryID => Form => Bean 
//			bean.getCategory(); => ID
			CategoryDAO categoryDAO = new CategoryDAO();
			CategoryEntity categoryEntity = categoryDAO.getCategoryById(bean.getCategory());
			videoEntity.setCategoryEntity(categoryEntity);
			
//			Tương tác vào DB => DAO
			VideoDAO videoDAO = new VideoDAO();
			return videoDAO.create(videoEntity);
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return false;
	}
	
//	Thực hiện CV của chức năng sửa     
//	- Ở doGet sẽ lấy thông tin video từ id ở url và userId
//    - Nếu TH có dữ liệu => Chuyển VideoEntity qua VideoBeans 
//	=> Gửi qua ip để hiển thị lên các ô input tương ứng
//    - Nếu TH không có dữ liệu => Quay về trang danh sách
	
	public VideoFormBean getBeansByIdAndChannelId(String videoId, HttpServletRequest request) {
		try {	
			int userId = Integer.parseInt(Utils.getCookieByName("userId", request));
			VideoDAO videoDAO = new VideoDAO();
			VideoEntity videoEntity = videoDAO
					.getVideoByChannelAndId(userId, Integer.parseInt(videoId));
			if(videoEntity != null) {
//				Convert dữ liệu từ Entity qua Beans 
				VideoFormBean bean = new VideoFormBean();
				bean.setId(videoEntity.getId());
				bean.setTitle(videoEntity.getTitle());
				bean.setDesc(videoEntity.getDescription());
				bean.setCategory(videoEntity.getCategoryEntity().getId());
				bean.setStatus(videoEntity.getStatus());
				
				return bean;
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateVideo(VideoFormBean bean, HttpServletRequest request) {
		try {	
			int userId = Integer.parseInt(Utils.getCookieByName("userId", request));
			VideoDAO videoDAO = new VideoDAO();
			VideoEntity videoEntity = videoDAO
					.getVideoByChannelAndId(userId, bean.getId());
			if(videoEntity != null) {
//				Kiểm tra user có cập nhật lại video hay không?
//				Nếu có thực hiện thêm video vào project
//				Và xoá video cũ trong project 
				String pathVideo = videoEntity.getVideoURL();
				if(bean.getVideoForm() != null) {
//					Thêm video vào project 
					String extVideo = bean.getVideoForm().getContentType().split("/")[1]; 
					String fileNameVideo = String.valueOf(new Date().getTime()); 
					pathVideo =  "/assets/videos/" + fileNameVideo + "." + extVideo; // Lưu DB 
					String pathContextVideo = request.getServletContext().getRealPath(pathVideo);
					bean.getVideoForm().write(pathContextVideo);
					
//					Xoá video cũ khỏi project 
					String videoFile = request.getServletContext().getRealPath(videoEntity.getVideoURL());
					Path filePathVideo = Paths.get(videoFile);
					Files.delete(filePathVideo);
				}
				String pathImage = videoEntity.getThumnailURL();
				if(bean.getImageForm() != null) {
//					Thêm ảnh vào project
					String extImage = bean.getImageForm().getContentType().split("/")[1]; 
					String fileNameImage = String.valueOf(new Date().getTime()); 
					pathImage =  "/assets/images/" + fileNameImage + "." + extImage; // Lưu DB
					String pathContextImage = request.getServletContext().getRealPath(pathImage);
					bean.getImageForm().write(pathContextImage);
//					Xoá ảnh vào project
					String imageFile = request.getServletContext().getRealPath(videoEntity.getThumnailURL());
					Path filePathImage = Paths.get(imageFile);
					Files.delete(filePathImage);
					
//					image\abc.png => Windows 
//					image/abc.png => MacOS
				}
//				Convert dữ liệu ở bean vào entity
				VideoEntity videoEntitySaveDB = new VideoEntity();
				videoEntitySaveDB.setId(bean.getId());
				videoEntitySaveDB.setTitle(bean.getTitle());
				videoEntitySaveDB.setDescription(bean.getDesc());
				videoEntitySaveDB.setThumnailURL(pathImage);
				videoEntitySaveDB.setVideoURL(pathVideo);
				videoEntitySaveDB.setStatus(bean.getStatus());
				
//				videoEntitySaveDB.setCategoryEntity(null)
				CategoryDAO categoryDAO = new CategoryDAO();
				CategoryEntity categoryEntity = categoryDAO.getCategoryById(bean.getCategory());
				videoEntitySaveDB.setCategoryEntity(categoryEntity);
				
				videoEntitySaveDB.setUserEntity(videoEntity.getUserEntity());
				
				return videoDAO.update(videoEntitySaveDB);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return false;
	}
	
	public List<VideoEntity> getVideoHomeAfterLogin(HttpServletRequest request){
		List<VideoEntity> videoEntities = new ArrayList<VideoEntity>();
		try {
			int userId = Integer.parseInt(Utils.getCookieByName("userId", request));
			
			ChannelFollowDAO channelFollowDAO = new ChannelFollowDAO();
			
			List<ChannelFollowEntity> channelFollowEntities = channelFollowDAO
					.getList(userId);
			
			
			
			List<VideoEntity> videoFollowEntities = new ArrayList<VideoEntity>();
			
			for(ChannelFollowEntity channelFollowEntity : channelFollowEntities) {
				
				List<VideoEntity> videoEntitiesChannel = channelFollowEntity
						.getChannelEntity() 
						.getVideoEntities();
				
				videoFollowEntities.addAll(videoEntitiesChannel);
			}
//			videoFollowEntities => danh sách tất cả video mà user đã follow channel
			
//			Danh sách hiện tại đang sắp xếp theo channel 
			
//			Sắp xếp danh sách videoFollowEntities video theo id giảm dần
			
			Collections.sort(videoFollowEntities, new Comparator<VideoEntity>() {
				@Override
				public int compare(VideoEntity videoEntity1, VideoEntity videoEntity2) {
					// TODO Auto-generated method stub
					return videoEntity2.getId() > videoEntity1.getId() ? 1 : 0;
				}
			});
			
//			Lọc video công khai của channel đã follow
			for(VideoEntity videoEntity : videoFollowEntities) {
				if(videoEntity.getStatus() == 3) videoEntities.add(videoEntity); 
			}
			
			VideoDAO videoDAO = new VideoDAO();
//			Lấy danh sách video còn lại mà user chưa follow 
			videoEntities.addAll(videoDAO.getVideoNotFollowList(userId));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoEntities;
	}
}
// 1 - User
// 2 - Channel (Có thể CRUD video)
// 3 - Admin (Có thể CRUD video)
