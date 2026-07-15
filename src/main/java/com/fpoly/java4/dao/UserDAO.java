package com.fpoly.java4.dao;

import java.util.ArrayList;
import java.util.List;

import com.fpoly.java4.entities.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class UserDAO {

	public List<UserEntity> getList(){
		List<UserEntity> userEntities = new ArrayList<UserEntity>();
		
		try {
//			Kết nối database 
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
//			Tạo transaction đồng bộ với database
			EntityManager manager = factory.createEntityManager();
			
//			SQL Script 
			String sql = "SELECT * FROM users";
			
//			JPQL
//			String sqlJPQL = "SELECT user FROM UserEntity user";
//			manager.createQuery(sqlJPQL, null); 
			
			Query query = manager.createNativeQuery(sql, UserEntity.class);
			
//			lấy ra được danh sách user 
			userEntities = query.getResultList();
			
//			Đóng transaction 
			manager.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return userEntities;
	}
	
	public boolean create(UserEntity userEntity) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
//		Tạo transaction đồng bộ với database
		EntityManager manager = factory.createEntityManager();
		try {
			
//			Kiểm tra transaction đã kích hoạt chưa?
			if(!manager.getTransaction().isActive()) {
//				Kích hoạt transaction
				manager.getTransaction().begin();
			}
			
//			Thực thi lệnh insert ở transaction
			manager.persist(userEntity);
			
//			Đẩy transaction vào db 
			manager.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
//			Loại bỏ các lệnh thêm sửa xoá ở transaction đã thực thi trước đó
//			Và không đồng bộ vào db
			manager.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean update(UserEntity userEntity) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager manager = factory.createEntityManager();
		try {
			if(!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}
			manager.merge(userEntity);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean delete(UserEntity userEntity) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager manager = factory.createEntityManager();
		try {
			if(!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}
			manager.remove(userEntity);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean emailExist(String email) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager manager = factory.createEntityManager();
		try {
			String sql = "SELECT * FROM users WHERE email=?";
			
//			JPQL = "SELECT u FROM UserEntity u WHERE u.email=?";
			
			Query query = manager.createNativeQuery(sql, UserEntity.class);
			query.setParameter(1, email);
			
			UserEntity userEntity = (UserEntity) query.getSingleResult();
			
			return userEntity != null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
//	Overload => Nạp chồng 
	public UserEntity getUserByEmail(String email) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager manager = factory.createEntityManager();
		try {
			String sql = "SELECT * FROM users WHERE email=?";
			
//			JPQL = "SELECT u FROM UserEntity u WHERE u.email=?";
			
			Query query = manager.createNativeQuery(sql, UserEntity.class);
			query.setParameter(1, email);
			
			UserEntity userEntity = (UserEntity) query.getSingleResult();
			
			return userEntity;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
