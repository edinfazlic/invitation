package com.invitations.core.model.entity;

import com.invitations.core.model.constant.InvitationResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(
    name = "invitation_response"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationResponse extends EntityWithLongId {

  @OneToOne
  @JoinColumn(name = "invitation", referencedColumnName = "id")
  private Invitation invitation;

  @Column(name = "accepted", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private InvitationResponseStatus status;

  @Column(name = "comment")
  private String comment;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "invitationResponse")
  private List<InvitationResponseChange> invitationResponseChanges;

}
