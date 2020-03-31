package com.moviereview.moviereview.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;

import com.moviereview.moviereview.dao.UserDAO;
import com.moviereview.moviereview.model.User;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
  
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    	
    	userDAO = UserDAO.getInstance();
    	
        User user = userDAO.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
              "No user found with username: " + username);
        }
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user.getRole()));
    }
     
    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
