package com.invitations.core.service;

import com.invitations.core.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class InvitationsUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return new org.springframework.security.core.userdetails.User("kanta",
        "$2a$10$nR9Eu0vQqQZHg822dxF6JO3Dc2We6KcmmAnpbD7gp86MkaybYaxcW", Collections.emptyList());
  }

  public User getUserByUsername(String userName) {
    return new User("kanta");
  }
}
