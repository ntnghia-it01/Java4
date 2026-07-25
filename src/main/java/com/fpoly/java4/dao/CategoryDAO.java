package com.fpoly.java4.dao;

import java.util.ArrayList;
import java.util.List;

import com.fpoly.java4.entities.CategoryEnitity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class CategoryDAO {
	
	public List<CategoryEnitity> getList(){
		List<CategoryEnitity> categoryEnitities = new ArrayList<CategoryEnitity>();
		
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
			EntityManager manager = factory.createEntityManager();
			
			String sql = "SELECT * FROM category"; 
			
//			manager.createNativeQuery => Trả về Object 
			Query query = manager.createNativeQuery(sql, CategoryEnitity.class);
			
			categoryEnitities = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categoryEnitities;
	}
	
//	Lấy chi tiết danh mục từ id 
	public CategoryEnitity getCategoryById(int id) {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
			EntityManager manager = factory.createEntityManager();
			
			CategoryEnitity categoryEnitity = manager.find(CategoryEnitity.class, id);
			
			return categoryEnitity;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
