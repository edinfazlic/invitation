package com.invitations.core.controller;

import com.invitations.core.model.dto.InvitationDetailsPayload;
import com.invitations.core.model.dto.InvitationListItemPayload;
import com.invitations.core.model.dto.InvitationRequestPayload;
import com.invitations.core.model.dto.InvitationResponsePayload;
import com.invitations.core.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/invitation")
@RequiredArgsConstructor
public class InvitationController {

  private final InvitationService invitationService;

  @PostMapping
  public ResponseEntity<Object> createInvitation(
      @RequestBody InvitationRequestPayload payload
  ) {
    invitationService.save(payload);

    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<InvitationListItemPayload>> getInvitations() {
    List<InvitationListItemPayload> result = invitationService.getList();

    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InvitationDetailsPayload> getInvitation(
      @PathVariable Long id
  ) {
    InvitationDetailsPayload result = invitationService.get(id);

    return ResponseEntity.ok(result);
  }

  @GetMapping("/public/{uuid}")
  public ResponseEntity<InvitationResponsePayload> getInvitationByUuid(
      @PathVariable("uuid") String invitationUuid
  ) {
    InvitationResponsePayload result = invitationService.get(invitationUuid);

    return ResponseEntity.ok(result);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateInvitation(
      @PathVariable Long id,
      @RequestBody InvitationRequestPayload payload
  ) {
    invitationService.update(id, payload);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteInvitation(
      @PathVariable Long id
  ) {
    invitationService.delete(id);

    return ResponseEntity.noContent().build();
  }

}
