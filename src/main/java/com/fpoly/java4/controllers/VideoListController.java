package com.fpoly.java4.controllers;

import java.io.IOException;
import java.util.List;

import com.fpoly.java4.dao.VideoDAO;
import com.fpoly.java4.entities.VideoEntity;
import com.fpoly.java4.utils.Utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/channel/videos")
public class VideoListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Channel ID lấy ở đâu ra???
		
//		Đoạn code lấy userid từ cookie ra sẽ được dùng rất nhiều 
		
		int userId = Integer.parseInt(Utils.getCookieByName("userId", req));

		VideoDAO videoDAO = new VideoDAO();
		List<VideoEntity> videoEntities = videoDAO.getList(userId);
		
		req.setAttribute("videos", videoEntities);
		
		req.getRequestDispatcher("/channel/videos.jsp").forward(req, resp);
	}
}
