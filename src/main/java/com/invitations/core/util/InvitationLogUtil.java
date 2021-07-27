package com.invitations.core.util;

import com.invitations.core.model.constant.ChangeType;
import com.invitations.core.model.constant.InvitationLogType;
import com.invitations.core.model.dto.ChangeItemPayload;
import com.invitations.core.model.dto.InvitationLogItemPayload;
import com.invitations.core.model.entity.Invitation;
import com.invitations.core.model.entity.InvitationChange;
import com.invitations.core.model.entity.InvitationChangeItem;
import com.invitations.core.model.entity.InvitationResponseChange;
import com.invitations.core.model.entity.InvitationResponseChangeItem;
import com.invitations.core.model.entity.InvitationVisit;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InvitationLogUtil {

  private InvitationLogUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static List<InvitationLogItemPayload> getInvitationLogs(Invitation invitation) {
    List<InvitationLogItemPayload> logs = invitation.getInvitationVisits().stream()
        .map(InvitationLogUtil::toInvitationLogItem)
        .collect(Collectors.toList());

    List<InvitationLogItemPayload> invitationChanges = invitation.getInvitationChanges().stream()
        .map(InvitationLogUtil::toInvitationLogItem)
        .collect(Collectors.toList());
    logs.addAll(invitationChanges);

    if (invitation.getInvitationResponse() != null) {
      List<InvitationLogItemPayload> responseChanges =
          invitation.getInvitationResponse().getInvitationResponseChanges().stream()
              .map(InvitationLogUtil::toInvitationLogItem)
              .collect(Collectors.toList());

      logs.addAll(responseChanges);
    }

    Collections.sort(logs);
    return logs;
  }

  private static InvitationLogItemPayload toInvitationLogItem(InvitationVisit invitationVisit) {
    return InvitationLogItemPayload.builder()
        .logType(InvitationLogType.VISITED)
        .date(invitationVisit.getDate())
        .build();
  }

  private static InvitationLogItemPayload toInvitationLogItem(InvitationChange invitationChange) {
    return InvitationLogItemPayload.builder()
        .logType(invitationChangeToLogType(invitationChange.getChangeType()))
        .date(invitationChange.getDate())
        .changes(invitationChange.getInvitationChangeItems().stream()
            .map(InvitationLogUtil::toChangeItemPayload)
            .collect(Collectors.toList()))
        .build();
  }

  private static InvitationLogType invitationChangeToLogType(ChangeType invitationChange) {
    switch (invitationChange) {
      case CREATED:
        return InvitationLogType.INVITATION_CREATED;
      case UPDATED:
        return InvitationLogType.INVITATION_UPDATED;
      case DELETED:
        return InvitationLogType.INVITATION_DELETED;
    }
    throw new RuntimeException("Cannot map ChangeType: " + invitationChange + " to LogType!");
  }

  private static ChangeItemPayload toChangeItemPayload(InvitationChangeItem invitationChangeItem) {
    return ChangeItemPayload.builder()
        .attribute(invitationChangeItem.getInvitationAttribute().name())
        .oldValue(invitationChangeItem.getOldValue())
        .newValue(invitationChangeItem.getNewValue())
        .build();
  }

  private static InvitationLogItemPayload toInvitationLogItem(InvitationResponseChange responseChange) {
    return InvitationLogItemPayload.builder()
        .logType(responseChangeToLogType(responseChange.getChangeType()))
        .date(responseChange.getDate())
        .changes(responseChange.getInvitationResponseChangeItems().stream()
            .map(InvitationLogUtil::toChangeItemPayload)
            .collect(Collectors.toList()))
        .build();
  }

  private static InvitationLogType responseChangeToLogType(ChangeType responseChange) {
    switch (responseChange) {
      case CREATED:
        return InvitationLogType.RESPONSE_CREATED;
      case UPDATED:
        return InvitationLogType.RESPONSE_UPDATED;
      case DELETED:
        return InvitationLogType.RESPONSE_DELETED;
    }
    throw new RuntimeException("Cannot map ChangeType: " + responseChange + " to LogType!");
  }

  private static ChangeItemPayload toChangeItemPayload(InvitationResponseChangeItem responseChangeItem) {
    return ChangeItemPayload.builder()
        .attribute(responseChangeItem.getInvitationResponseAttribute().name())
        .oldValue(responseChangeItem.getOldValue())
        .newValue(responseChangeItem.getNewValue())
        .build();
  }

}
