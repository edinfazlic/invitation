package com.invitations.core.util;

import com.invitations.core.model.constant.InvitationAttribute;
import com.invitations.core.model.entity.Invitation;
import com.invitations.core.model.entity.InvitationChangeItem;
import com.invitations.core.model.entity.InvitationTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InvitationChangeItemUtil {

  private InvitationChangeItemUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static List<InvitationChangeItem> getChangeItems(Invitation oldValue,
                                                          Invitation newValue) {
    List<InvitationChangeItem> changeItems = new ArrayList<>();

    checkForChange(oldValue.getInvitationTemplate(), newValue.getInvitationTemplate(),
        InvitationAttribute.TEMPLATE, changeItems);
    checkForChange(oldValue.getSubject(), newValue.getSubject(),
        InvitationAttribute.SUBJECT, changeItems);
    checkForChange(oldValue.getParameters(), newValue.getParameters(),
        InvitationAttribute.PARAMETERS, changeItems);
    checkForChange(oldValue.getPeopleAmount(), newValue.getPeopleAmount(),
        InvitationAttribute.PEOPLE_AMOUNT, changeItems);
    checkForChange(oldValue.getChildrenAmount(), newValue.getChildrenAmount(),
        InvitationAttribute.CHILDREN_AMOUNT, changeItems);
    checkForChange(oldValue.getNote(), newValue.getNote(),
        InvitationAttribute.NOTE, changeItems);

    return changeItems;
  }

  private static void checkForChange(InvitationTemplate oldValue, InvitationTemplate newValue,
                                     InvitationAttribute attribute,
                                     List<InvitationChangeItem> changes) {
    if (oldValue.getId().equals(newValue.getId())) {
      return;
    }
    InvitationChangeItem changeItem = InvitationChangeItem.builder()
        .invitationAttribute(attribute)
        .oldValue(String.valueOf(oldValue.getId()))
        .newValue(String.valueOf(newValue.getId()))
        .build();
    changes.add(changeItem);
  }

  private static void checkForChange(String oldValue, String newValue,
                                     InvitationAttribute attribute,
                                     List<InvitationChangeItem> changes) {
    if (Objects.equals(oldValue, newValue)) {
      return;
    }
    InvitationChangeItem changeItem = InvitationChangeItem.builder()
        .invitationAttribute(attribute)
        .oldValue(oldValue)
        .newValue(newValue)
        .build();
    changes.add(changeItem);
  }

  private static void checkForChange(int oldValue, int newValue, InvitationAttribute attribute,
                                     List<InvitationChangeItem> changes) {
    if (oldValue == newValue) {
      return;
    }
    InvitationChangeItem changeItem = InvitationChangeItem.builder()
        .invitationAttribute(attribute)
        .oldValue(String.valueOf(oldValue))
        .newValue(String.valueOf(newValue))
        .build();
    changes.add(changeItem);
  }

}
