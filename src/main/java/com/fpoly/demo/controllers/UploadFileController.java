package com.fpoly.demo.controllers;

import java.io.File;
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
//		Lấy folder lưu trữ ứng dụng bên trong server tomcat 
		String contextPath = req.getServletContext().getRealPath("");

//		Nơi lưu trữ hình ảnh trong project 
//		File.separator => / -> MAC, \ -> Windows
		String assetsPath = contextPath + File.separator + "assets" + File.separator + "images";
//				String.format("%s%sassets%simages", contextPath, File.separator, File.separator);
//		Gọi đến folder
		File file = new File(assetsPath);
//		true => đã tồn tại, false => ngược lại 
		if (!file.exists()) {
//			Tạo folder 
			file.mkdir();
		}

//		Danh sách file từ input 
//		req.getParts();

		for (Part part : req.getParts()) {
			if (part.getContentType().startsWith("image/")) {
//				Lưu ảnh 
				String fileName = System.currentTimeMillis() + ".jpg";
				part.write(assetsPath + File.separator + fileName);
				System.out.println(assetsPath + File.separator + fileName);
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
		}

//		Part part = req.getPart("image");
//
////		Lấy tên file upload từ user 
////		String fileName = part.getSubmittedFileName();
//
////		Thay vì lấy tên ảnh từ phía user upload lên
////		Phải tạo 1 tên ảnh mới "...." + ".jpg"
////		Đảm bảo mỗi lần upload tên ảnh không bị trùng
//
////		Lấy thời gian hiện tại khi thực hiện lưu ảnh 
////		ms 
//		String fileName = System.currentTimeMillis() + ".jpg";
//
////		sẽ là nội dung lưu vào db 
//
////		Lưu hình ảnh vào folder assets/images với tên file là tên từ user upload 
//		part.write(assetsPath + File.separator + fileName);
//
////		In ra nơi lưu trữ của file 
//		System.out.println(assetsPath + File.separator + fileName);

//		/Users\trongnghia\eclipse-workspace-v2\

//		part.getContentType();
//
//		part.getName();
//
//		part.getSize(); // byte

		req.getRequestDispatcher("/views/upload-file.jsp").forward(req, resp);

		// foreach

//		Kiểm tra nếu có bất kỳ file upload lên
//		có kích thước lớn hơn 50kb thì thông báo lỗi 
//		long maxSize = 1024 * 50;
//		for (Part part : req.getParts()) {
//			System.out.println(part.getContentType());
////			Kiểm content type có bắt đầu bằng chuỗi "image/"
//			if (part.getContentType().startsWith("image/")) {
////				Xử lý ảnh ....
//
//				if (part.getSize() > maxSize) {
//					req.setAttribute("errImage", "ERROR");
//					break;
//				}
//			}
//		}

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
	}
}
