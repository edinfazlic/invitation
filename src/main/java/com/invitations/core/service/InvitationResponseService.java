package com.invitations.core.service;

import com.invitations.core.model.constant.ChangeType;
import com.invitations.core.model.dto.InvitationResponseRequestPayload;
import com.invitations.core.model.entity.Invitation;
import com.invitations.core.model.entity.InvitationResponse;
import com.invitations.core.model.entity.InvitationResponseChange;
import com.invitations.core.model.entity.InvitationResponseChangeItem;
import com.invitations.core.repository.InvitationResponseChangeItemRepository;
import com.invitations.core.repository.InvitationResponseChangeRepository;
import com.invitations.core.repository.InvitationResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.invitations.core.util.InvitationResponseChangeItemUtil.getChangeItems;

@Service
@RequiredArgsConstructor
public class InvitationResponseService {

  private final InvitationResponseRepository invitationResponseRepository;
  private final InvitationResponseChangeRepository invitationResponseChangeRepository;
  private final InvitationResponseChangeItemRepository invitationResponseChangeItemRepository;

  public void saveOrUpdate(InvitationResponseRequestPayload payload) {
    InvitationResponse invitationResponse = fromPayload(payload);

    InvitationResponse current =
        invitationResponseRepository.findByInvitationId(payload.getInvitationId());
    if (current != null) {
      updateInvitationResponse(current, invitationResponse);
    } else {
      createInvitationResponse(invitationResponse);
    }
  }

  private InvitationResponse fromPayload(InvitationResponseRequestPayload payload) {
    InvitationResponse entity = InvitationResponse.builder()
        .status(payload.getStatus())
        .comment(payload.getComment())
        .build();

    Invitation invitation = new Invitation();
    invitation.setId(payload.getInvitationId());
    entity.setInvitation(invitation);

    return entity;
  }

  private void updateInvitationResponse(InvitationResponse current,
                                        InvitationResponse invitationResponse) {
    List<InvitationResponseChangeItem> changeItems = getChangeItems(current, invitationResponse);

    if (!changeItems.isEmpty()) {
      invitationResponse.setId(current.getId());
      invitationResponseRepository.save(invitationResponse);

      saveInvitationResponseChangeInformation(invitationResponse, changeItems);
    }
  }

  private void saveInvitationResponseChangeInformation(InvitationResponse invitationResponse,
                                                       List<InvitationResponseChangeItem> changeItems) {
    InvitationResponseChange responseChange = InvitationResponseChange.builder()
        .invitationResponse(invitationResponse)
        .changeType(ChangeType.UPDATED)
        .build();
    responseChange = invitationResponseChangeRepository.save(responseChange);
    for (InvitationResponseChangeItem responseChangeItem : changeItems) {
      responseChangeItem.setInvitationResponseChange(responseChange);
      invitationResponseChangeItemRepository.save(responseChangeItem);
    }
  }

  private void createInvitationResponse(InvitationResponse invitationResponse) {
    invitationResponse = invitationResponseRepository.save(invitationResponse);

    saveInvitationResponseCreationInformation(invitationResponse);
  }

  private void saveInvitationResponseCreationInformation(InvitationResponse response) {
    InvitationResponseChange responseChange = InvitationResponseChange.builder()
        .invitationResponse(response)
        .changeType(ChangeType.CREATED)
        .build();
    invitationResponseChangeRepository.save(responseChange);
  }

}
