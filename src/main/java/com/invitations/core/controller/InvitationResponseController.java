package com.invitations.core.controller;

import com.invitations.core.model.dto.InvitationResponseRequestPayload;
import com.invitations.core.service.InvitationResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invitation-response")
@RequiredArgsConstructor
public class InvitationResponseController {

  private final InvitationResponseService invitationResponseService;

  @PostMapping("/public")
  public ResponseEntity<Object> createInvitationResponse(
      @RequestBody InvitationResponseRequestPayload payload
  ) {
    invitationResponseService.saveOrUpdate(payload);

    return ResponseEntity.noContent().build();
  }

}
