package com.fpoly.java4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
	@Id //Khoá chính 
	@Column(name = "id") // Đăng ký tên cột tương ứng để nhận gía trị 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Giá trị tự tăng lên 1 đv 
	private int id;
	@Column(name = "name", nullable = false, columnDefinition = "nvarchar(255)")
	private String name;
	@Column(name = "status", nullable = false)
	private int status = 1;
}


//create table category (
//	    id int identity(1,1) primary key,
//	    name nvarchar(255) not null,
//	    status int not null default 1,
//
//	    constraint ck_category_status check (status in (1, 2, 3))
//	);