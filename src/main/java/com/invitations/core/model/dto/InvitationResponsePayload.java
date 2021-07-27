package com.invitations.core.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationResponsePayload {

  private Long id;
  private String uuid;
  private String subject;
  private String parameters;
  private int peopleAmount;
  private int childrenAmount;
  private String note;

  InvitationTemplateResponsePayload template;
  InvitationResponseResponsePayload response;

}
