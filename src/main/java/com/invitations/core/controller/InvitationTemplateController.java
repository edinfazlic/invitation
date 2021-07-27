package com.invitations.core.controller;

import com.invitations.core.model.dto.InvitationTemplateRequestPayload;
import com.invitations.core.model.dto.InvitationTemplateResponsePayload;
import com.invitations.core.service.InvitationTemplateService;
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
@RequestMapping("/invitation-template")
@RequiredArgsConstructor
public class InvitationTemplateController {

  private final InvitationTemplateService invitationTemplateService;

  @PostMapping
  public ResponseEntity<Object> createInvitationTemplate(
      @RequestBody InvitationTemplateRequestPayload payload
  ) {
    invitationTemplateService.save(payload);

    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<InvitationTemplateResponsePayload>> getInvitationTemplates() {
    List<InvitationTemplateResponsePayload> result = invitationTemplateService.getList();

    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InvitationTemplateResponsePayload> getInvitationTemplate(
      @PathVariable Long id
  ) {
    InvitationTemplateResponsePayload result = invitationTemplateService.get(id);

    return ResponseEntity.ok(result);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateInvitationTemplate(
      @PathVariable Long id,
      @RequestBody InvitationTemplateRequestPayload payload
  ) {
    invitationTemplateService.update(id, payload);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteInvitationTemplate(
      @PathVariable Long id
  ) {
    invitationTemplateService.delete(id);

    return ResponseEntity.noContent().build();
  }

}
