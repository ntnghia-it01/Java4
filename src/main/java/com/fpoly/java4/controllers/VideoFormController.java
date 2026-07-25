package com.fpoly.java4.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.tomcat.util.json.JSONParser;

import com.fpoly.java4.beans.VideoFormBean;
import com.fpoly.java4.dao.CategoryDAO;
import com.fpoly.java4.entities.CategoryEntity;
import com.fpoly.java4.services.VideoServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/channel/video-form")
@MultipartConfig
public class VideoFormController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<CategoryEntity> categoryEntities = categoryDAO.getList();
		
		req.setAttribute("categories", categoryEntities);
		
		
		req.getRequestDispatcher("/channel/video-form.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			CategoryDAO categoryDAO = new CategoryDAO();
			List<CategoryEntity> categoryEntities = categoryDAO.getList();
			
			req.setAttribute("categories", categoryEntities);
			
			VideoFormBean bean = new VideoFormBean();
//			Convert dữ liệu nguyên thuỷ (số, chuỗi)
			BeanUtils.populate(bean, req.getParameterMap());
			
			Part video = req.getPart("video");
			Part image = req.getPart("image");
			bean.setVideoForm(video);
			bean.setImageForm(image);
			
			req.setAttribute("bean", bean);
			
//			Xử lý lỗi ở phía UI => Bean 
			
			if(bean.getErrors().isEmpty()) {
				VideoServices videoServices = new VideoServices();
//				Kiểm tra các lỗi logic => Services 
				boolean insertVideo = videoServices.createVideo(bean, req);
				if(insertVideo) {
					resp.sendRedirect(req.getContextPath() + "/channel/videos");
					return;
				}
				req.setAttribute("error", "Thêm video thất bại");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/channel/video-form.jsp").forward(req, resp);
	}
}
