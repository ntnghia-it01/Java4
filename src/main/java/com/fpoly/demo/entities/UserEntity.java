package com.fpoly.demo.entities;

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
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username", nullable = false, length = 50)
	private String username;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "name", nullable = false, columnDefinition = "nvarchar(50)")
	private String name;

	@Column(name = "role", nullable = false)
	private int role;

	@Column(name = "status", nullable = false)
	private int status;

	@OneToMany(mappedBy = "userEntity")
	List<FavouritesEntity> favouritesEntities;

	public UserEntity(int id, String username, String password, String email, String name, int role, int status) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.role = role;
		this.status = status;
	}

}
