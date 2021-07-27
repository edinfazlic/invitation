package com.invitations.core.model.dto;

import com.invitations.core.model.constant.InvitationLogType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.xml.datatype.DatatypeConstants.EQUAL;
import static javax.xml.datatype.DatatypeConstants.GREATER;
import static javax.xml.datatype.DatatypeConstants.LESSER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationLogItemPayload implements Comparable<InvitationLogItemPayload> {

  private InvitationLogType logType;
  private Date date;
  private List<ChangeItemPayload> changes = new ArrayList<>();

  @Override
  public int compareTo(InvitationLogItemPayload o) {
    if (date == null && o.getDate() == null) {
      return EQUAL;
    }
    if (date == null) {
      return GREATER;
    }
    if (o.getDate() == null) {
      return LESSER;
    }

    return date.compareTo(o.getDate());
  }
}
