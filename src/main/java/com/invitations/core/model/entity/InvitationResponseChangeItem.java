package com.invitations.core.model.entity;

import com.invitations.core.model.constant.InvitationResponseAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(
    name = "invitation_response_change_item"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationResponseChangeItem extends EntityWithLongId {

  @ManyToOne
  @JoinColumn(name = "invitation_change", referencedColumnName = "id")
  private InvitationResponseChange invitationResponseChange;

  @Column(name = "invitation_response_attribute")
  @Enumerated(value = EnumType.STRING)
  private InvitationResponseAttribute invitationResponseAttribute;

  @Column(name = "old_value")
  private String oldValue;

  @Column(name = "new_value")
  private String newValue;

}
