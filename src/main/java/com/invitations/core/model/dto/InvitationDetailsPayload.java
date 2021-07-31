package com.invitations.core.model.dto;

import com.invitations.core.model.dto.invitationtemplate.InvitationTemplateResponsePayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationDetailsPayload {

  private Long id;
  private String uuid;
  private String subject;
  private String parameters;
  private int peopleAmount;
  private int childrenAmount;
  private String note;

  private InvitationTemplateResponsePayload template;
  private InvitationResponsePayload response;
  private List<InvitationLogItemPayload> logs;

}
