package com.invitations.core.controller;

import com.invitations.core.model.AuthenticationRequestPayload;
import com.invitations.core.model.AuthenticationResponsePayload;
import com.invitations.core.model.User;
import com.invitations.core.service.InvitationsUserDetailsService;
import com.invitations.core.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final InvitationsUserDetailsService userDetailsService;
  private final JwtUtil jwtTokenUtil;

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello worldy");
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponsePayload> createAuthenticationToken(
      @RequestBody AuthenticationRequestPayload payload
  ) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword())
      );
    } catch (AuthenticationException e) {
      e.printStackTrace();
      throw new RuntimeException("nema");
    }

    final User user = userDetailsService.getUserByUsername(payload.getUsername());

    final String jwt = jwtTokenUtil.generateToken(user);

    return ResponseEntity.ok(new AuthenticationResponsePayload(jwt));
  }

}
