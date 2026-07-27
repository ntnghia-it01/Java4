package com.fpoly.java4.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fpoly.java4.dao.VideoDAO;
import com.fpoly.java4.entities.VideoEntity;
import com.fpoly.java4.utils.Utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/channel/video-delete")
public class VideoDeleteController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String videoId = req.getParameter("id");
			String userId = Utils.getCookieByName("userId", req);
			
			VideoDAO videoDAO = new VideoDAO();
			
			VideoEntity videoEntity = videoDAO
					.getVideoByChannelAndId(Integer.parseInt(userId), Integer.parseInt(videoId));
			
//			Xoá luôn file video
			String videoFile = getServletContext().getRealPath(videoEntity.getVideoURL());
//			Xoá luôn file ảnh
			String imageFile = getServletContext().getRealPath(videoEntity.getThumnailURL());
			
			if(videoEntity != null) {
				System.out.println(videoEntity.getId());
				videoDAO.delete(videoEntity);
				
				Path filePathVideo = Paths.get(videoFile);
				Path filePathImage = Paths.get(imageFile);

				Files.delete(filePathVideo);
				Files.delete(filePathImage);
		            
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect(req.getContextPath() + "/channel/videos");
	}
	
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		String method = req.getMethod();
//		
//		super.service(req, resp);
//	}
}
