package com.invitations.core.model.dto;

import com.invitations.core.model.constant.InvitationResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationResponseRequestPayload {

  private InvitationResponseStatus status;
  private String comment;
  private long invitationId;

}
