package com.invitations.core.util;

import com.invitations.core.model.constant.InvitationResponseAttribute;
import com.invitations.core.model.constant.InvitationResponseStatus;
import com.invitations.core.model.entity.InvitationResponse;
import com.invitations.core.model.entity.InvitationResponseChangeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InvitationResponseChangeItemUtil {

  private InvitationResponseChangeItemUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static List<InvitationResponseChangeItem> getChangeItems(InvitationResponse oldValue,
                                                                  InvitationResponse newValue) {
    List<InvitationResponseChangeItem> changeItems = new ArrayList<>();

    checkForChange(oldValue.getStatus(), newValue.getStatus(),
        InvitationResponseAttribute.STATUS, changeItems);
    checkForChange(oldValue.getComment(), newValue.getComment(),
        InvitationResponseAttribute.COMMENT, changeItems);

    return changeItems;
  }

  private static void checkForChange(InvitationResponseStatus oldValue,
                                     InvitationResponseStatus newValue,
                                     InvitationResponseAttribute attribute,
                                     List<InvitationResponseChangeItem> changes) {
    if (Objects.equals(oldValue, newValue)) {
      return;
    }
    InvitationResponseChangeItem changeItem = InvitationResponseChangeItem.builder()
        .invitationResponseAttribute(attribute)
        .oldValue(oldValue.name())
        .newValue(newValue.name())
        .build();
    changes.add(changeItem);
  }

  private static void checkForChange(String oldValue, String newValue,
                                     InvitationResponseAttribute attribute,
                                     List<InvitationResponseChangeItem> changes) {
    if (Objects.equals(oldValue, newValue)) {
      return;
    }
    InvitationResponseChangeItem changeItem = InvitationResponseChangeItem.builder()
        .invitationResponseAttribute(attribute)
        .oldValue(oldValue)
        .newValue(newValue)
        .build();
    changes.add(changeItem);
  }

}
