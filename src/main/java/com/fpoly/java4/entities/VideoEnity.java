package com.fpoly.java4.entities;

import jakarta.persistence.Entity;
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

	@ManyToOne
	@JoinColumn(name = "channel_id")
	private UserEntity userEntity;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}
// Bắt buộc phải có manytoone
// OneToMany có thể khai báo hay không cũng được

//Video n - 1 User

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