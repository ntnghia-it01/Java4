package com.fpoly.java4.controllers;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java4.beans.LoginBean;
import com.fpoly.java4.services.UserServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			LoginBean bean = new LoginBean();
			
			BeanUtils.populate(bean, req.getParameterMap());
			
			req.setAttribute("bean", bean);
			
//			Không có lỗi dữ liệu đầu vào
			if(bean.getErrors().isEmpty()) {
				UserServices userServices = new UserServices();
				boolean login = userServices.login(bean, resp);
				if(login) {
					resp.sendRedirect(req.getContextPath() + "/");
					return;
				}
				
				req.setAttribute("errLogin", "Đăng nhập thất bại");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}
