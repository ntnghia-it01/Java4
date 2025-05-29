package com.fpoly.demo.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.demo.dao.UserDAO;
import com.fpoly.demo.entities.UserEntity;

@WebServlet("/users")
public class UsersController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		tạo 1 biến để nhận danh sách user từ phương thức findAll ở DAO 

//		lấy danh sách user từ db 
		List<UserEntity> userEntities = UserDAO.findAll();

		req.setAttribute("users", userEntities);

		req.getRequestDispatcher("/views/users.jsp").forward(req, resp);
	}

}
