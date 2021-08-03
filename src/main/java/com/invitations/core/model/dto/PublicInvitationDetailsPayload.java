package com.invitations.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicInvitationDetailsPayload {

  private Long id;
  private String uuid;
  private String subject;
  private String parameters;
  private boolean plural;

  private String templateText;

  private InvitationResponsePayload response;

}
