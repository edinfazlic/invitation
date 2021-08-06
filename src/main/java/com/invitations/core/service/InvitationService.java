package com.invitations.core.service;

import com.invitations.core.model.constant.ChangeType;
import com.invitations.core.model.dto.InvitationDetailsPayload;
import com.invitations.core.model.dto.InvitationListItemPayload;
import com.invitations.core.model.dto.InvitationRequestPayload;
import com.invitations.core.model.dto.PublicInvitationDetailsPayload;
import com.invitations.core.model.entity.Invitation;
import com.invitations.core.model.entity.InvitationChange;
import com.invitations.core.model.entity.InvitationChangeItem;
import com.invitations.core.model.entity.InvitationTemplate;
import com.invitations.core.model.entity.InvitationVisit;
import com.invitations.core.repository.InvitationChangeItemRepository;
import com.invitations.core.repository.InvitationChangeRepository;
import com.invitations.core.repository.InvitationRepository;
import com.invitations.core.repository.InvitationVisitRepository;
import com.invitations.core.util.InvitationResponseUtil;
import com.invitations.core.util.InvitationTemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.invitations.core.util.InvitationChangeItemUtil.getChangeItems;
import static com.invitations.core.util.InvitationLogUtil.getInvitationLogs;

@Service
@RequiredArgsConstructor
public class InvitationService {

  private final InvitationRepository invitationRepository;
  private final InvitationChangeRepository invitationChangeRepository;
  private final InvitationChangeItemRepository invitationChangeItemRepository;
  private final InvitationVisitRepository invitationVisitRepository;

  public void save(InvitationRequestPayload payload) {
    Invitation invitation = invitationRepository.save(fromPayload(payload));

    saveInvitationCreationInformation(invitation);
  }

  private Invitation fromPayload(InvitationRequestPayload payload) {
    Invitation entity = Invitation.builder()
        .subject(payload.getSubject())
        .parameters(payload.getParameters())
        .peopleAmount(payload.getPeopleAmount())
        .childrenAmount(payload.getChildrenAmount())
        .note(payload.getNote())
        .build();

    InvitationTemplate invitationTemplate = new InvitationTemplate();
    invitationTemplate.setId(payload.getInvitationTemplateId());
    entity.setInvitationTemplate(invitationTemplate);

    return entity;
  }

  private void saveInvitationCreationInformation(Invitation invitation) {
    InvitationChange invitationChange = InvitationChange.builder()
        .invitation(invitation)
        .changeType(ChangeType.CREATED)
        .build();
    invitationChangeRepository.save(invitationChange);
  }

  public List<InvitationListItemPayload> getList() {
    return invitationRepository.findAllByDeletedIsNullOrderById().stream()
        .map(this::toListPayload)
        .collect(Collectors.toList());
  }

  private InvitationListItemPayload toListPayload(Invitation entity) {
    InvitationListItemPayload payload = InvitationListItemPayload.builder()
        .invitationId(entity.getId())
        .subject(entity.getSubject())
        .peopleAmount(entity.getPeopleAmount())
        .childrenAmount(entity.getChildrenAmount())
        .note(entity.getNote())
        .uuid(entity.getUuid())
        .build();

    if (entity.getInvitationResponse() != null) {
      payload.setResponseStatus(entity.getInvitationResponse().getStatus());
      payload.setComment(entity.getInvitationResponse().getComment());
    }
    return payload;
  }

  @Transactional(readOnly = true, noRollbackFor = Exception.class)
  public InvitationDetailsPayload get(Long id) {
    return toDetailsPayload(getEntity(id));
  }

  private Invitation getEntity(Long id) {
    Optional<Invitation> invitationOptional = invitationRepository.findById(id);
    if (invitationOptional.isPresent()) {
      return invitationOptional.get();
    }
    throw new RuntimeException("Invitation with id:" + id + " doesn't exist!");
  }

  private InvitationDetailsPayload toDetailsPayload(Invitation entity) {
    InvitationDetailsPayload payload = InvitationDetailsPayload.builder()
        .id(entity.getId())
        .uuid(entity.getUuid())
        .subject(entity.getSubject())
        .parameters(entity.getParameters())
        .peopleAmount(entity.getPeopleAmount())
        .childrenAmount(entity.getChildrenAmount())
        .note(entity.getNote())
        .template(InvitationTemplateUtil.toResponsePayload(entity.getInvitationTemplate()))
        .logs(getInvitationLogs(entity))
        .build();

    if (entity.getInvitationResponse() != null) {
      payload.setResponse(InvitationResponseUtil.toResponsePayload(entity.getInvitationResponse()));
    }

    return payload;
  }

  public PublicInvitationDetailsPayload get(String invitationUuid) {
    Invitation invitation = invitationRepository.findByUuidAndDeletedIsNull(invitationUuid);
    if (invitation == null) {
      return null;
    }

    invitationVisitRepository.save(InvitationVisit.builder()
        .date(new Date())
        .invitation(invitation)
        .build());

    return toPublicInvitationPayload(invitation);
  }

  private PublicInvitationDetailsPayload toPublicInvitationPayload(Invitation entity) {
    PublicInvitationDetailsPayload payload = PublicInvitationDetailsPayload.builder()
        .id(entity.getId())
        .uuid(entity.getUuid())
        .subject(entity.getSubject())
        .parameters(entity.getParameters())
        .plural(entity.getPeopleAmount() + entity.getChildrenAmount() > 1)
        .templateText(entity.getInvitationTemplate().getText())
        .build();

    if (entity.getInvitationResponse() != null) {
      payload.setResponse(InvitationResponseUtil.toResponsePayload(entity.getInvitationResponse()));
    }

    return payload;
  }

  public void update(Long id, InvitationRequestPayload payload) {
    Invitation current = getEntity(id);
    Invitation invitation = fromPayload(payload);

    List<InvitationChangeItem> changeItems = getChangeItems(current, invitation);

    if (!changeItems.isEmpty()) {
      invitation.setId(current.getId());
      invitation.setUuid(current.getUuid());
      invitation = invitationRepository.save(invitation);

      saveInvitationChangeInformation(invitation, changeItems);
    }
  }

  private void saveInvitationChangeInformation(Invitation invitation,
                                               List<InvitationChangeItem> changeItems) {
    InvitationChange invitationChange = InvitationChange.builder()
        .invitation(invitation)
        .changeType(ChangeType.UPDATED)
        .build();
    invitationChange = invitationChangeRepository.save(invitationChange);
    for (InvitationChangeItem invitationChangeItem : changeItems) {
      invitationChangeItem.setInvitationChange(invitationChange);
      invitationChangeItemRepository.save(invitationChangeItem);
    }
  }

  public void delete(Long id) {
    Invitation invitation = getEntity(id);
    invitation.setDeleted(true);

    invitation = invitationRepository.save(invitation);

    saveInvitationDeletionInformation(invitation);
  }

  private void saveInvitationDeletionInformation(Invitation invitation) {
    InvitationChange invitationChange = InvitationChange.builder()
        .invitation(invitation)
        .changeType(ChangeType.DELETED)
        .build();
    invitationChangeRepository.save(invitationChange);
  }

}
