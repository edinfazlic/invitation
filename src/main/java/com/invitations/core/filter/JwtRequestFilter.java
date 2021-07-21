package com.invitations.core.filter;

import com.invitations.core.model.User;
import com.invitations.core.service.InvitationsUserDetailsService;
import com.invitations.core.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_STRING = "Authorization";
  private static final String BEARER_STRING = "Bearer ";
  private static final int BEARER_STRING_LENGTH = 7;
  private final JwtUtil jwtUtil;
  private final InvitationsUserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws ServletException, IOException {
    final String authorizationHeader = request.getHeader(AUTHORIZATION_STRING);

    if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_STRING)) {
      String jwt = authorizationHeader.substring(BEARER_STRING_LENGTH);
      String username = jwtUtil.extractUsername(jwt);

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        User user = userDetailsService.getUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(user, null, null);
        usernamePasswordAuthenticationToken
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }

    chain.doFilter(request, response);
  }
}
