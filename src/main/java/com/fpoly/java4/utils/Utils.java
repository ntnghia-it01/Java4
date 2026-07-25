package com.fpoly.java4.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class Utils {

//	Lấy giá trị của cookie theo tên (key)
	public static String getCookieByName(String key, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		
		if(cookies == null) return null;
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(key)) {
				return cookie.getValue();
			}
		}
		
		return null;
	}
}
