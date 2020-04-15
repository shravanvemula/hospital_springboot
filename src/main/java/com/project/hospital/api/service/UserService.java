package com.project.hospital.api.service;

import com.project.hospital.api.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<User> findAll();

    public User findById(String userName);

    public void save(User theUser);

    public void deleteById(String userName);


}
