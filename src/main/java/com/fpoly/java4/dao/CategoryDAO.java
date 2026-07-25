package com.fpoly.java4.dao;

import java.util.ArrayList;
import java.util.List;

import com.fpoly.java4.entities.CategoryEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class CategoryDAO {
	
	public List<CategoryEntity> getList(){
		List<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();
		
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
			EntityManager manager = factory.createEntityManager();
			
			String sql = "SELECT * FROM category"; 
			
//			manager.createNativeQuery => Trả về Object 
			Query query = manager.createNativeQuery(sql, CategoryEntity.class);
			
			categoryEntities = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categoryEntities;
	}
	
//	Lấy chi tiết danh mục từ id 
	public CategoryEntity getCategoryById(int id) {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
			EntityManager manager = factory.createEntityManager();
			
			CategoryEntity categoryEntity = manager.find(CategoryEntity.class, id);
			
			return categoryEntity;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
