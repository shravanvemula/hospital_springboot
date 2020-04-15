package com.project.hospital.api.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.hospital.api.entity.Role;
import com.project.hospital.api.entity.User;
import com.project.hospital.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ActiveUser activeUser;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName();
		
		System.out.println("userName=" + userName);

		User theUser = userService.findById(userName);
		List<Role> roles = theUser.getRoles();
		
		// now place in the session
		HttpSession session = request.getSession(false);
		if(session != null) {
			LoggedUser user = new LoggedUser(authentication.getName(),roles,activeUser);
			session.setAttribute("user", user);
		}
		
		System.out.println(session.getAttribute("user").toString());
		
		// forward to home page
		
		response.sendRedirect(request.getContextPath() + "/api/home");
	}

}
