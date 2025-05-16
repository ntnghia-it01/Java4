package com.fpoly.demo.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username"); // => ""
		String password = req.getParameter("password");

		System.out.println(String.format("Username: %s", username));
		System.out.println("Password: " + password);

//		Tên tài khoản và mật khẩu bắt buộc nhập 

//		"                        "

//		isEmpty => Kiểm tra chuỗi rỗng hoàn toàn == "" => true <> "    " => false
//		isBlank => trim() => isEmpty 

//		Thực hiện kiểm tra điều kiện 
//		Nếu lỗi truyền 1 attr qua view bằng key errUsername 
//		Hiển thị lỗi này lên view

		if (username.isBlank()) {
			req.setAttribute("errUsername", "Tên tài khoản bắt buộc nhập ");
		}

//		Kiểm tra lỗi ở password 
//		Hiển thị lại nội dung người dùng đã nhập ở ô input 
//		Truyền attr qua sau đó để vào value của ô input 
//		VD: key == "username" => <input value="${username}"/>

//		Tượng xử lý trang đăng ký 

		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}
}

//http://localhost:8080/Java4/login?username=abc&password=123456