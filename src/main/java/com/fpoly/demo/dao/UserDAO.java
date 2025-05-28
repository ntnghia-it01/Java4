package com.fpoly.demo.dao;

import java.util.List;

import com.fpoly.demo.entities.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class UserDAO {
	public List<UserEntity> findAll() {
//		Nạp dữ liệu config từ file persistence vào EntityManager 
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("dbConnect");
//		Thực hiện kết nối đến sql server 
		EntityManager entityManager = managerFactory.createEntityManager();

//		Thực thi câu lệnh sql được gọi theo thông tin của entity 
//		String sqlQuery = "SELECT * FROM UserEntity";
//		entityManager.createQuery("sqlQuery");

//		Thực thi câu lệnh sql được gọi theo thông tin của db 
//		Copy trong sql ra dung được 
		String sqlQueryNative = "SELECT * FROM users";
		Query query = entityManager.createNativeQuery(sqlQueryNative);

//		Ngắt kết nối 
		entityManager.close();

		return query.getResultList(); // danh sách
	}

	public UserEntity findByUsername(String username) {
//		Nạp dữ liệu config từ file persistence vào EntityManager 
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("dbConnect");
//		Thực hiện kết nối đến sql server 
		EntityManager entityManager = managerFactory.createEntityManager();

		String sqlQuery = "SELECT * FROM users WHERE username='" + username + "'";

		Query query = entityManager.createNativeQuery(sqlQuery);

//		Ngắt kết nối 
		entityManager.close();
		return (UserEntity) query.getSingleResult(); // 1 đối tượng
	}

	public UserEntity findById(int id) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager entityManager = managerFactory.createEntityManager();

//		Truyền vào cấu trúc entity mà muốn chuyển đổi dữ liệu 
		UserEntity userEntity = entityManager.find(UserEntity.class, id);
//		Trả về 2 giá
//		Nếu tìm thấy thông tin entity => trả về 1 đối tượng entity 
//		Nếu không tim thấy => null;

		entityManager.close();

		return userEntity;
	}

	public boolean insert(UserEntity userEntity) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager entityManager = managerFactory.createEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.persist(userEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			entityManager.close();
			return false;
		}
		entityManager.close();
		return true;
	}

	public boolean update(UserEntity userEntity) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager entityManager = managerFactory.createEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.merge(userEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			entityManager.close();
			return false;
		}
		entityManager.close();
		return true;
	}

	public boolean delete(UserEntity userEntity) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("dbConnect");
		EntityManager entityManager = managerFactory.createEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.remove(userEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			entityManager.close();
			return false;
		}
		entityManager.close();
		return true;
	}
}
