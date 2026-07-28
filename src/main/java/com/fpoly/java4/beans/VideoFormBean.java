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
	private int id = 0;
	private String title;
	private String desc;
	private int category;
	private int status;
	
	private Part videoForm;
	private Part imageForm;
	
	public Map<String, String> getErrors(){
		Map<String, String> errors = new HashMap<String, String>();
		
//		Tiêu đề không rỗng, có ít nhất 3 từ
		if(this.title.trim().split(" ").length < 3) {
			errors.put("errTitle", "Tiêu đề phải có ít nhất 3 từ");
		}
//		Mô tả không rỗng, có ít nhất 10 từ
		if(this.desc.trim().split(" ").length < 10) {
			errors.put("errDesc", "Mô tả phải có ít nhất 10 từ");
		}
//		Danh mục bắt buộc chọn
		if(this.category < 1) {
			errors.put("errCat", "Danh mục bắt buộc chọn");
		}

//		Trạng thái bắt buộc chọn
		if(this.status != 1 && this.status != 2) {
			errors.put("errStatus", "Trạng thái bắt buộc chọn");
		}
		
//		Video
//		- Phải đúng định dạng video 
//		- Dung lượng không quá 1GB
		if(id == 0) {
			if(!this.videoForm.getContentType().startsWith("video/")) {
				errors.put("errVideo", "Nội dung tải lên phải là video");
			}else if(this.videoForm.getSize() > 1024 * 1024 * 1024) {
				errors.put("errVideo", "Nội dung tải lên không được quá 10GB");
			}
		}
		
//		Image
//		- Phải đúng định dạng ảnh
//		- Dung lượng ảnh không quá 5MB
		if(id == 0) {
			if(!this.imageForm.getContentType().startsWith("image/")) {
				errors.put("errImage", "Nội dung tải lên phải là ảnh");
			}else if(this.imageForm.getSize() > 1024 * 1024 * 5) {
				errors.put("errImage", "Nội dung tải lên không được quá 5MB");
			}
		}
		
		return errors;
	}
}
