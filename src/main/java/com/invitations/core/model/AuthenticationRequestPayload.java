package com.invitations.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestPayload {
  private String username;
  private String password;
}
