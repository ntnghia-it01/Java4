package com.fpoly.java4.dao;

import java.util.ArrayList;
import java.util.List;

import com.fpoly.java4.entities.ChannelFollowEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class ChannelFollowDAO {
//	Danh sách follow theo userID 
	public List<ChannelFollowEntity> getList(int userId){
		List<ChannelFollowEntity> channelFollowEntities = new ArrayList<ChannelFollowEntity>();
		try {
			String sql = "SELECT * FROM channel_follow WHERE user_id=?";
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbConnect");
			EntityManager manager = factory.createEntityManager();
			Query query = manager.createNativeQuery(sql, ChannelFollowEntity.class);
			query.setParameter(1, userId);
			
			channelFollowEntities = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channelFollowEntities;
	}
}
