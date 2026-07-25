package com.fpoly.java4.config;

import java.io.IOException;

import com.fpoly.java4.dao.UserDAO;
import com.fpoly.java4.entities.UserEntity;
import com.fpoly.java4.utils.Utils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Giả đinh website có 3 vai trò: user, channel, admin
// - user: /user/* => role: 1
// - channel: /channel/* => role: 2
//- admin: /admin/* => role: 3

@WebFilter({"/user/*", "/channel/*", "/admin/*"})
public class AuthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		Cookie[] cookies = req.getCookies();
		
		if(cookies == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		
		int userId = 0, role = 0;
		try {
			userId = Integer.parseInt(Utils.getCookieByName("userId", req));
			role = Integer.parseInt(Utils.getCookieByName("userRole", req));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(userId == 0 || role == 0) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
//		Kiểm tra trạng thái của tài khoản ở db 
//		Nếu trạng thái là khoá => Chuyển về login
//		Ngược lại cho đi tiếp
		
		UserDAO userDAO = new UserDAO();
		UserEntity userEntity = userDAO.getUserById(userId);
		
		if(userEntity.getStatus() == 0) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		String path = req.getServletPath();
		
		if(path.startsWith("/admin/") && userEntity.getRole() != 3) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		if(path.startsWith("/channel/") && userEntity.getRole() != 2) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		if(path.startsWith("/user/") && (userEntity.getRole() != 2 && userEntity.getRole() != 3)) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
//		Khi dòng này được thực thi thì luồng dữ liệu sẽ được xử lý tiếp ở Controller (Servlet)
		chain.doFilter(request, response);
	}

}
