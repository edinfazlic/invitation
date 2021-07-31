package com.invitations.core.util;

import com.invitations.core.model.dto.InvitationResponsePayload;
import com.invitations.core.model.entity.InvitationResponse;

public class InvitationResponseUtil {

  private InvitationResponseUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InvitationResponsePayload toResponsePayload(InvitationResponse entity) {
    return InvitationResponsePayload.builder()
        .comment(entity.getComment())
        .status(entity.getStatus())
        .build();
  }

}
