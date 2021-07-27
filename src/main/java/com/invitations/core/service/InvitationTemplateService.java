package com.invitations.core.service;

import com.invitations.core.model.dto.InvitationTemplateRequestPayload;
import com.invitations.core.model.dto.InvitationTemplateResponsePayload;
import com.invitations.core.model.entity.InvitationTemplate;
import com.invitations.core.repository.InvitationTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvitationTemplateService {

  private final InvitationTemplateRepository invitationTemplateRepository;

  public void save(InvitationTemplateRequestPayload payload) {
    invitationTemplateRepository.save(fromPayload(payload));
  }

  public List<InvitationTemplateResponsePayload> getList() {
    return invitationTemplateRepository.findAll().stream()
        .map(this::toPayload)
        .collect(Collectors.toList());
  }

  public InvitationTemplateResponsePayload get(Long id) {
    Optional<InvitationTemplate> invitationTemplateOptional =
        invitationTemplateRepository.findById(id);
    if (invitationTemplateOptional.isPresent()) {
      return toPayload(invitationTemplateOptional.get());
    }
    throw new RuntimeException("InvitationTemplate with id:" + id + " doesn't exist!");
  }

  public void update(Long id, InvitationTemplateRequestPayload payload) {
    get(id);

    InvitationTemplate invitationTemplate = fromPayload(payload);
    invitationTemplate.setId(id);
    invitationTemplateRepository.save(invitationTemplate);
  }

  public void delete(Long id) {
    invitationTemplateRepository.deleteById(id);
  }

  private InvitationTemplate fromPayload(InvitationTemplateRequestPayload payload) {
    return InvitationTemplate.builder()
        .name(payload.getName())
        .text(payload.getText())
        .build();
  }

  private InvitationTemplateResponsePayload toPayload(InvitationTemplate entity) {
    return InvitationTemplateResponsePayload.builder()
        .id(entity.getId())
        .name(entity.getName())
        .text(entity.getText())
        .build();
  }
}
