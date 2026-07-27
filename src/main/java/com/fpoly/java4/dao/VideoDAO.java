package com.fpoly.java4.dao;

import java.util.ArrayList;
import java.util.List;

import com.fpoly.java4.entities.CategoryEntity;
import com.fpoly.java4.entities.UserEntity;
import com.fpoly.java4.entities.VideoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class VideoDAO {
//    - Hàm lấy danh sách tất cả video
//    - Hàm lấy danh sách video theo channel_id (user_id)
//    - Thêm video vào db
	
	public List<VideoEntity> getList(){
		List<VideoEntity> videoEnitities = new ArrayList<VideoEntity>();
		
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
			EntityManager manager = factory.createEntityManager();
			
			String sql = "SELECT * FROM video"; 
			
			Query query = manager.createNativeQuery(sql, VideoEntity.class);
			
			videoEnitities = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return videoEnitities;
	}
	
	public List<VideoEntity> getList(int channelID){
		List<VideoEntity> videoEnitities = new ArrayList<VideoEntity>();
		
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
			EntityManager manager = factory.createEntityManager();
			
			String sql = "SELECT * FROM video WHERE channel_id=?"; 
			
			Query query = manager.createNativeQuery(sql, VideoEntity.class);
			query.setParameter(1, channelID);
			
			videoEnitities = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return videoEnitities;
	}
	
	public boolean create(VideoEntity videoEntity) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager manager = factory.createEntityManager();
		try {
			
			if(!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}
			
			manager.persist(videoEntity);
			manager.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}
	
//	Viết thêm DAO sửa và xoá 
//	- Sửa video
//	- Xoá video 
//	- Kiểm tra video có thuộc sở hữu của user không (int userId, int videoId)
	
	public VideoEntity getVideoByChannelAndId(int userId, int videoId) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager manager = factory.createEntityManager();
		try {
//			Có 2 cách:
//			- C1: Viết lệnh sql
			String sql = "SELECT * FROM video WHERE channel_id=? AND id=?";
			Query query = manager.createNativeQuery(sql, VideoEntity.class);
			query.setParameter(1, userId);
			query.setParameter(2, videoId);
			
			return (VideoEntity) query.getSingleResult();
			
//			- C2: Dùng hàm find để lấy video sau đó so sánh với userID 
//			VideoEntity videoEntity = manager.find(VideoEntity.class, videoId);
//			Câu if này đủ điều kiện chưa? 
//			Lệnh if này có khả năng sẽ lỗi???
//			if(videoEntity.getUserEntity().getId() == userId) {
//				return videoEntity;
//			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean update(VideoEntity videoEntity) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager manager = factory.createEntityManager();
		try {
			if(!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}
			manager.merge(videoEntity);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean delete(VideoEntity videoEntity) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager manager = factory.createEntityManager();
		try {
			if(!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}
			VideoEntity managed = manager.merge(videoEntity);
			manager.remove(managed);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}
}
