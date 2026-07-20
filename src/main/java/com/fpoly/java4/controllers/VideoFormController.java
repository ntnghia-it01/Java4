package com.fpoly.java4.controllers;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java4.beans.VideoFormBean;

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

		req.getRequestDispatcher("/channel/video-form.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			VideoFormBean bean = new VideoFormBean();
//			Convert dữ liệu nguyên thuỷ (số, chuỗi)
			BeanUtils.populate(bean, req.getParameterMap());
			
			Part video = req.getPart("video");
			Part image = req.getPart("image");
			bean.setVideo(video);
			bean.setImage(image);
			
			
			req.setAttribute("bean", bean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/channel/video-form.jsp").forward(req, resp);
	}
}
