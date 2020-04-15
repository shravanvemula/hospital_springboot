package com.project.hospital.api.config;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.project.hospital.api.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUser implements HttpSessionBindingListener {

	private String userName;
	
	private List<Role> roles;
	
	@Autowired
	private ActiveUser activeUser;
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		
		String user = activeUser.getUserName();
		LoggedUser loggedUser = (LoggedUser) event.getValue();
		if(user == null) {
			activeUser.setUserName(loggedUser.getUserName());
			activeUser.setRoles(roles);
		}
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		String user = activeUser.getUserName();
		LoggedUser loggedUser = (LoggedUser) event.getValue();
		if(user != null && user.equals(loggedUser.getUserName())) {
			activeUser.setUserName(null);
			activeUser.setRoles(null);
		}
	}


	public String getUserName() {
		return userName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public ActiveUser getActiveUser() {
		return activeUser;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setActiveUser(ActiveUser activeUser) {
		this.activeUser = activeUser;
	}
}
