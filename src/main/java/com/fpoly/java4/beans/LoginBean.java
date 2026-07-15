package com.fpoly.java4.beans;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginBean {
	private String email;
	private String password;
	
	public Map<String, String> getErrors(){
		Map<String, String> errors = new HashMap<String, String>();
		
//		!= Khác
//		! phủ định
//		!true == false
//		!!true == !false == true 
		
		if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("errEmail", "Email không đúng định dạng");
		}
		
		if(password.length() < 6) {
			errors.put("errPassword", "Mật khẩu có ít nhất 6 ký tự");
		}
		
		return errors;
	}
}
