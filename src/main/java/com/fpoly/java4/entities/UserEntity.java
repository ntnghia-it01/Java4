package com.fpoly.java4.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {
	@Id //Khoá chính 
	@Column(name = "id") // Đăng ký tên cột tương ứng để nhận gía trị 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Giá trị tự tăng lên 1 đv 
	private int id;
	@Column(name = "email", length = 255, nullable = false, unique = true)
	private String email;
	@Column(name = "password_hash", length = 255, nullable = false)
	private String password;
	@Column(name = "full_name", nullable = true, columnDefinition = "nvarchar(255)")
	private String name;
	@Column(name = "role", nullable = false)
	private int role;
	@Column(name = "status", nullable = false)
	private int status = 1;
	
	@OneToMany(mappedBy = "userEntity")
	private List<VideoEntity> videoEntities;
//	Có thể khai báo hay không cũng được tuỳ vào yêu cầu dự án
//	Muốn khai báo thì ở đối con phải được khai báo manytoone rồi 
//	Giá trị bên trong ontomany là tên biến của đối tượng hiện tại
//	đang khai báo bên trong đối tượng con
}


//create table [user] (
//	    id int identity(1,1) primary key,
//	    email varchar(255) not null unique,
//	    password_hash varchar(255) not null,
//	    full_name nvarchar(255) null,
//	    role int not null,
//	    status int not null default 1,
//
//	    constraint ck_user_role check (role in (1, 2, 3)),
//	    constraint ck_user_status check (status in (1, 2, 3))
//	);