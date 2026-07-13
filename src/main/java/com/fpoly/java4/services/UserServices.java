package com.fpoly.java4.services;

import com.fpoly.java4.beans.RegisterBean;
import com.fpoly.java4.dao.UserDAO;
import com.fpoly.java4.entities.UserEntity;

public class UserServices {

//	return kiểu gì? boolean || String
//	Trùng email
//	Insert thất bại
//	Insert thành công 
	public String register(RegisterBean bean) {
		try {
//			Kiểm tra email có tồn tại không? 
			
			UserDAO userDAO = new UserDAO();
			boolean checkEmailExist = userDAO.emailExist(bean.getEmail());
			if(checkEmailExist) {
				return "Email đã tồn tại";
			}
			
//			Có thể thực hiện đăng ký tài khoản
//			Convert beans to entity
			UserEntity userEntity = new UserEntity();
			userEntity.setEmail(bean.getEmail());
			userEntity.setPassword(bean.getPassword());
			userEntity.setName(bean.getName());
			userEntity.setRole(1);
			
			boolean insert = userDAO.create(userEntity);
			
			if(!insert) {
				return "Đăng ký thất bại!";
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "Đăng ký thất bại!";
		}
	}
}
