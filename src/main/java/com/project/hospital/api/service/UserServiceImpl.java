package com.project.hospital.api.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.project.hospital.api.dao.UserRepository;
import com.project.hospital.api.entity.Role;
import com.project.hospital.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    // need to inject user dao
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findById(userName);
        User user = null;
        if (result.isPresent() == false) {
            throw new UsernameNotFoundException("Invalid username / password.");
        }
        user = result.get();
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String userName) {
        Optional<User> result = userRepository.findById(userName);
        User user = null;
        if(result.isPresent()) {
            user = result.get();
        }
        else {
            throw new RuntimeException("Did not find a user");
        }
        return user;
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }

    @Override
    public void deleteById(String userName) {
        userRepository.deleteById(userName);

    }
}