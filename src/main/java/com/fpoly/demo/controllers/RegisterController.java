package com.fpoly.demo.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.demo.beans.UserBean;
import com.fpoly.demo.dao.UserDAO;
import com.fpoly.demo.entities.UserEntity;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			UserBean userBean = new UserBean();
			BeanUtils.populate(userBean, req.getParameterMap());

			req.setAttribute("user", userBean);

			if (userBean.getErrors().isEmpty()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setUsername(userBean.getUsername());
				userEntity.setPassword(userBean.getPassword());
				userEntity.setName(userBean.getName());
				userEntity.setEmail(userBean.getEmail());
//				0 == Admin, 1 == User
				userEntity.setRole(1);
//				0 == inactive, 1 == active
				userEntity.setStatus(1);

				UserDAO.insert(userEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}
}
