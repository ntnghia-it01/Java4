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
@Table(name = "channel_follow")
public class ChannelFollowEntity {
	@Id
	@Column(name = "id") 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
//	Đối tượng đang đăng nhập
//	Người dùng follow 
	
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private UserEntity channelEntity;
//	Đối tượng kênh video
//	Được follow
}


//CREATE TABLE [dbo].[channel_follow] (
//	    [id]         INT IDENTITY (1, 1) NOT NULL,
//	    [user_id]    INT NOT NULL,
//	    [channel_id] INT NOT NULL,
//	    PRIMARY KEY CLUSTERED ([id] ASC),
//	    CONSTRAINT [ck_channel_follow_not_self] CHECK ([user_id]<>[channel_id]),
//	    CONSTRAINT [fk_channel_follow_channel] FOREIGN KEY ([channel_id]) REFERENCES [dbo].[users] ([id]),
//	    CONSTRAINT [fk_channel_follow_user] FOREIGN KEY ([user_id]) REFERENCES [dbo].[users] ([id]),
//	    CONSTRAINT [uq_channel_follow_user_channel] UNIQUE NONCLUSTERED ([user_id] ASC, [channel_id] ASC)
//	);
//
