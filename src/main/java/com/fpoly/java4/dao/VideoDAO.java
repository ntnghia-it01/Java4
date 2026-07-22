package com.fpoly.java4.dao;

import java.util.ArrayList;
import java.util.List;

import com.fpoly.java4.entities.CategoryEnitity;
import com.fpoly.java4.entities.UserEntity;
import com.fpoly.java4.entities.VideoEnity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class VideoDAO {
//    - Hàm lấy danh sách tất cả video
//    - Hàm lấy danh sách video theo channel_id (user_id)
//    - Thêm video vào db
	
	public List<VideoEnity> getList(){
		List<VideoEnity> videoEnitities = new ArrayList<VideoEnity>();
		
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
			EntityManager manager = factory.createEntityManager();
			
			String sql = "SELECT * FROM video"; 
			
			Query query = manager.createNativeQuery(sql);
			
			videoEnitities = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return videoEnitities;
	}
	
	public List<VideoEnity> getList(int channelID){
		List<VideoEnity> videoEnitities = new ArrayList<VideoEnity>();
		
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
			EntityManager manager = factory.createEntityManager();
			
			String sql = "SELECT * FROM video WHERE channel_id=?"; 
			
			Query query = manager.createNativeQuery(sql);
			query.setParameter(1, channelID);
			
			videoEnitities = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return videoEnitities;
	}
	
	public boolean create(VideoEnity videoEnity) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager manager = factory.createEntityManager();
		try {
			
			if(!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}
			
			manager.persist(videoEnity);
			manager.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}
}
