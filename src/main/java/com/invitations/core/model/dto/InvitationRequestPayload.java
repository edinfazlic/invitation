package com.invitations.core.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationRequestPayload {

  private long invitationTemplateId;
  private String subject;
  private String parameters;
  private int peopleAmount;
  private int childrenAmount;
  private String note;

}
