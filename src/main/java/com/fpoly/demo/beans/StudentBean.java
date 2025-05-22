package com.fpoly.demo.beans;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentBean {
	private String code;
	private String name;
	private String phone;
	private String gender;
	private int major;

	public Map<String, String> getErrors() {
		Map<String, String> errors = new HashMap<String, String>();

		if (code.length() < 7 && (!code.startsWith("T") || !code.startsWith("P"))) {
			errors.put("errCode", "Sai MSSV");
		}

		if (name.isBlank()) {
			errors.put("errName", "Ten khong duoc bo trong");
		}

		return errors;
	}
}

//Map => {"errCode": "Sai MSSV", "errName": "Sai Ten"}
