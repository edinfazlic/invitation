package com.invitations.core.util;

import com.invitations.core.model.dto.InvitationTemplateResponsePayload;
import com.invitations.core.model.entity.InvitationTemplate;

public class InvitationTemplateUtil {

  private InvitationTemplateUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InvitationTemplateResponsePayload toResponsePayload(InvitationTemplate entity) {
    return InvitationTemplateResponsePayload.builder()
        .id(entity.getId())
        .name(entity.getName())
        .text(entity.getText())
        .build();
  }

}
