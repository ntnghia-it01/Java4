package com.fpoly.demo.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig()
@WebServlet("/upload-file")
public class UploadFileController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/views/upload-file.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// foreach

//		Kiểm tra nếu có bất kỳ file upload lên
//		có kích thước lớn hơn 50kb thì thông báo lỗi 
		long maxSize = 1024 * 50;
		for (Part part : req.getParts()) {
			System.out.println(part.getContentType());
//			Kiểm content type có bắt đầu bằng chuỗi "image/"
			if (part.getContentType().startsWith("image/")) {
//				Xử lý ảnh ....

				if (part.getSize() > maxSize) {
					req.setAttribute("errImage", "ERROR");
					break;
				}
			}
		}

//		Part part = req.getPart("image");
//
////		Kiểu dữ liệu của file 
//		System.out.println(part.getContentType());
////		Tên file trên máy của user 
//		System.out.println(part.getName());
////		Kích thước của file => byte 
//		System.out.println(part.getSize());
//
////		15MB => 5646546545 byte
//
//		long maxSize = 1024 * 15;
//
//		if (part.getSize() > maxSize) {
//			req.setAttribute("errImage", "Errors");
//		}

//		kiểm tra nếu kích thước của file lớn hơn
//		giá trị của maxSize thì hiển thị thông báo lỗi
//		
////		10h => ms
//		
//		long maxHour = 1000 * 60 * 60 * 10;

		req.getRequestDispatcher("/views/upload-file.jsp").forward(req, resp);
	}
}
