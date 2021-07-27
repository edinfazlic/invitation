package com.invitations.core.model.dto;

import com.invitations.core.model.constant.InvitationResponseStatus;
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
public class InvitationListItemPayload {

  private Long invitationId;
  private String subject;
  private int peopleAmount;
  private int childrenAmount;
  private InvitationResponseStatus responseStatus;
  private String comment;
  private String note;
  private String uuid;
}
