package com.wecp.progressive.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.wecp.progressive.dto.UserRegistrationDTO;
import com.wecp.progressive.entity.User;
import com.wecp.progressive.repository.UserRepository;

@Service
public class UserLoginServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        if(userRepository.findByUsername(userRegistrationDTO.getUsername())!=null)
        {
            if(userRegistrationDTO.getRole().equals("STUDENT") || userRegistrationDTO.getRole().equals("TEACHER"))
            {
                throw new RuntimeException("Invalid role. Only 'STUDENT' or 'TEACHER' allowed.");
            }
        }

        User user=new User();

        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setRole(userRegistrationDTO.getRole());

        userRepository.save(user);
    }

    public User getUserDetails(int userId) {
        return userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found with ID: "+userId));
    }

    public UserDetails loadUserByUsername(String username)
    {
        User user=userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
    }
}
