package com.fpoly.java4.services;

import com.fpoly.java4.beans.LoginBean;
import com.fpoly.java4.beans.RegisterBean;
import com.fpoly.java4.dao.UserDAO;
import com.fpoly.java4.entities.UserEntity;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

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
	
	public boolean login(LoginBean bean, HttpServletResponse response) {
//		Kiểm tra có đúng email trong db hay không
//		Nếu đúng => Có đối tượng user entity => Có password 
//		Lấy password trong entity so sánh với password ở bean
		try {
			UserDAO userDAO = new UserDAO();
			UserEntity userEntity = userDAO.getUserByEmail(bean.getEmail());
			if(userEntity == null) return false;

//			#1 => Có thể xảy ra lỗi
//			userEntity.getPassword() có thể null.equals()
//			if(!userEntity.getPassword().equals(bean.getPassword())) return false;
//			#2 => Luôn không có lỗi 
//			"".equals(null)
			if(!bean.getPassword().equals(userEntity.getPassword())) return false;
			
			
//			Lưu user id và role vào cookies 
			Cookie userIdCookie = new Cookie("userId", String.valueOf(userEntity.getId()));
			userIdCookie.setPath("/");
			userIdCookie.setMaxAge(60 * 60 * 3);
			
			Cookie userRoleCookie = new Cookie("userRole", String.valueOf(userEntity.getRole()));
			userRoleCookie.setPath("/");
			userRoleCookie.setMaxAge(60 * 60 * 3);
			
			response.addCookie(userIdCookie);
			response.addCookie(userRoleCookie);
			
			return true;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
