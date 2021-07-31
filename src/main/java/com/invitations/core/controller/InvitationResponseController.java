package com.invitations.core.controller;

import com.invitations.core.model.dto.InvitationResponsePayload;
import com.invitations.core.service.InvitationResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invitation/public/{id}/response")
@RequiredArgsConstructor
public class InvitationResponseController {

  private final InvitationResponseService invitationResponseService;

  @PostMapping
  public ResponseEntity<Object> createInvitationResponse(
      @PathVariable("id") Long invitationId,
      @RequestBody InvitationResponsePayload payload
  ) {
    invitationResponseService.saveOrUpdate(invitationId, payload);

    return ResponseEntity.noContent().build();
  }

}
