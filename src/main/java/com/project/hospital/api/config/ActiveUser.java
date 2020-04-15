package com.project.hospital.api.config;

import java.util.List;

import com.project.hospital.api.entity.Role;
import org.springframework.stereotype.Component;



import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class ActiveUser {

	private String userName;
	private List<Role> roles;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public List<Role> getRoles() {
		return roles;
	}
}
