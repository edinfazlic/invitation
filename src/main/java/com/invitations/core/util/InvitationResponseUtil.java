package com.invitations.core.util;

import com.invitations.core.model.dto.InvitationResponseResponsePayload;
import com.invitations.core.model.entity.InvitationResponse;

public class InvitationResponseUtil {

  private InvitationResponseUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InvitationResponseResponsePayload toResponsePayload(InvitationResponse entity) {
    return InvitationResponseResponsePayload.builder()
        .comment(entity.getComment())
        .status(entity.getStatus())
        .build();
  }

}
