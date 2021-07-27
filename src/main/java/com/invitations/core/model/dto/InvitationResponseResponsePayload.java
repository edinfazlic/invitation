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
public class InvitationResponseResponsePayload {

  private InvitationResponseStatus status;
  private String comment;

}
