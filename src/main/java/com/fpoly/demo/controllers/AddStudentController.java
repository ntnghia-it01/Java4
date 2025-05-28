package com.fpoly.demo.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.demo.beans.StudentBean;
import com.fpoly.demo.config.EntityManagerConfig;

@WebServlet("/add-student")
public class AddStudentController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerConfig.getEntityManager();
		EntityManagerConfig.getEntityManager();
		EntityManagerConfig.getEntityManager();
		EntityManagerConfig.getEntityManager();

		req.getRequestDispatcher("/views/add-student.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			StudentBean studentBean = new StudentBean();
			BeanUtils.populate(studentBean, req.getParameterMap());

			req.setAttribute("student", studentBean);

		} catch (Exception e) {

		}

		req.getRequestDispatcher("/views/add-student.jsp").forward(req, resp);

//
////		lấy tất cả dữ liệu người dùng đã nhập
////		và chọn trong form 
//		String code = req.getParameter("code");
//		String name = req.getParameter("name");
//		String phone = req.getParameter("phone");
//		String gender = req.getParameter("gender");
//		String major = req.getParameter("major");
//
//		req.setAttribute("code", code);
//
////		Kiểm tra các thông tin này:
////		MSSV: Phải có 7 ký tự và bắt đầu bằng T hoặc P 
////		Tên bắt buộc phải nhập 
////		Số điện thoại có 10 ký tự, bắt đầu bằng 0
////		Giới tính và ngành học bắt buộc chọn 
//
////		Kiểm tra các thông tin trên và hiển thị ra lỗi tương ứng 
//
////		PC12 => true
////		false && false => false || true => true
////		false && true = > false
//		if (code.length() < 7 && (!code.startsWith("T") || !code.startsWith("P"))) {
//			req.setAttribute("errCode", "MSSV sai định dạng ");
//		}
//
//		if (name.isBlank()) {
//		}
//		if (phone.length() < 10 && !phone.startsWith("0")) {
//		}
//		if (gender == null) {
//		}
////		Chuyển ngành học về kiểu số
////		So sánh == -1 false
//
//		try {
//			if (Integer.parseInt(major) == -1) {
//
//			}
//		} catch (Exception e) {
//
//		}

	}
}
