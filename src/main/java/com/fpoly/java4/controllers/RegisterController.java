package com.fpoly.java4.controllers;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java4.beans.RegisterBean;
import com.fpoly.java4.services.UserServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegisterController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
//			Chuyển dữ liệu từ submit form qua beans để kiểm tra lỗi
			RegisterBean bean = new RegisterBean();
			BeanUtils.populate(bean, req.getParameterMap());
			
//			Gửi dữ liệu beans lại qua jsp 
//			Để hiển thị thông tin đã nhập và lỗi ở form nếu có
			req.setAttribute("bean", bean);
//			Hiển thị giá trị ở các value của input (Tự thêm)
//			Hiển thị lỗi của từng input nếu có (Tự thêm)
			
//			Kiểm tra form có lỗi hay không để xử lý tiếp db??
			if(bean.getErrors().isEmpty()) {
//				Không có lỗi ở form
//				Kiểm tra các giá trị bị trùng theo quy ước của DB
//				Kiểm tra email trùng => Services 
//				Services kiểm tra ở db hoặc là logic 
				
				UserServices services = new UserServices();
				String errorService = services.register(bean);
				
				if(errorService != null) {
//					Có lỗi xảy ra 
					req.setAttribute("errServices", errorService);
				}else {
//					TH còn lại errorService == null => Đăng ký thành công
//					Chuyển về trang đăng nhập 
					resp.sendRedirect(req.getContextPath() + "/login");
					return;
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
}
