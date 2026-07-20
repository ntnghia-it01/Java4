package com.fpoly.java4.beans;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoFormBean {
	private String title;
	private String desc;
	private int category;
	private int status;
	
	private Part video;
	private Part image;
	
	public Map<String, String> getErrors(){
		Map<String, String> errors = new HashMap<String, String>();
		
//		Tiêu đề không rỗng, có ít nhất 3 từ
//		Mô tả không rỗng, có ít nhất 10 từ
//		Danh mục bắt buộc chọn
//		Trạng thái bắt buộc chọn
		
//		Video
//		- Phải đúng định dạng video 
//		- Dung lượng không quá 1GB
		
//		Image
//		- Phải đúng định dạng ảnh
//		- Dung lượng ảnh không quá 5MB
		
		return errors;
	}
}
