package com.example.ecommerce.dto;

import java.util.Set;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;


public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Long phone;

    private String refreshToken;

    private Set<Role> roles;




    public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public Long getPhone() {
		return phone;
	}




	public void setPhone(Long phone) {
		this.phone = phone;
	}




	public String getRefreshToken() {
		return refreshToken;
	}




	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}




	public Set<Role> getRoles() {
		return roles;
	}




	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}




	public UserDto(User entity) {

        this.id = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.roles = entity.getRoles();
        this.refreshToken = entity.getRefreshToken();


    }
}
