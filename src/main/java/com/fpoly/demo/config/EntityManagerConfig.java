package com.fpoly.demo.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Instance class 
public class EntityManagerConfig {
//	Lưu vào 1 ô nhớ cố định 
	private static EntityManager manager;

	public static EntityManager getEntityManager() {
//		EntityManager chưa được khởi tạo 
		if (manager == null) {
			System.out.println("Connect");
			EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("dbConnect");
			manager = managerFactory.createEntityManager();
		}

		System.out.println("Connected");
		return manager;
	}
}
