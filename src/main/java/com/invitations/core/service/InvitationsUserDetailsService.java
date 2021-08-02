package com.invitations.core.service;

import com.invitations.core.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class InvitationsUserDetailsService implements UserDetailsService {

  @Value("${security.admin.user}")
  private String user;

  @Value("${security.admin.password}")
  private String password;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return new org.springframework.security.core.userdetails.User(this.user,
        this.password, Collections.emptyList());
  }

  public User getUserByUsername(String userName) {
    return new User(this.user);
  }
}
