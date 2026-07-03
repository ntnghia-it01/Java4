package com.fpoly.java4.beans;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterBean {
	private String email;
	private String password;
	private String name;
	
	public Map<String, String> getErrors(){
		Map<String, String> errors = new HashMap<String, String>();
		
		if(!this.email.matches("")) {
			errors.put("errEmail", "Email không đúng định dạng");
		}
		
		return errors;
	}
}
