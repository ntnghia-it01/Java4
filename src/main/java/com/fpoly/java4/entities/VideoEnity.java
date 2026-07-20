package com.fpoly.java4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "video")
public class VideoEnity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title", nullable = false, columnDefinition = "NVARCHAR(255)")
	private String title;
	
	@Column(name = "description", nullable = false, columnDefinition = "NVARCHAR(MAX)")
	private String description;
	
	@Column(name = "video_url", nullable = false, length = 1000)
	private String videoURL;
	
	@Column(name = "thumbnail_url", nullable = false, length = 1000)
	private String thumnailURL;
	
	@Column(name = "approval_status", nullable = false)
	private int status = 1;
	
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private UserEntity userEntity;
//	Chỉ đến khoá ngoại trong db
// 	Luôn đi cùng với joincolumn 
//	Giá trị bên trong joincolumn là tên cột khoá ngoại
//	Khi gặp khoá ngoại thì bắt buộc sử dụng 
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEnitity categoryEnitity;
}
//create table video (
//	    id int identity(1,1) primary key,
//	    channel_id int not null,
//	    category_id int not null,
//	    title nvarchar(255) not null,
//	    description nvarchar(max) null,
//	    video_url varchar(1000) not null,
//	    thumbnail_url varchar(1000) null,
//	    approval_status int not null default 1,
//
//	    constraint fk_video_channel foreign key (channel_id) references [user](id),
//	    constraint fk_video_category foreign key (category_id) references category(id),
//	    constraint ck_video_approval_status check (approval_status in (1, 2, 3)),
//	);