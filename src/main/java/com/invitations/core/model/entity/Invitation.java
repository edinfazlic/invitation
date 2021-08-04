package com.invitations.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
    name = "invitation"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invitation extends EntityWithLongId {

  @ManyToOne
  @JoinColumn(name = "invitation_template", referencedColumnName = "id")
  private InvitationTemplate invitationTemplate;

  @Column(name = "uuid")
  private String uuid;

  @Column(name = "subject")
  private String subject;

  @Column(name = "parameters")
  private String parameters;

  @Column(name = "people_amount", nullable = false)
  private int peopleAmount;

  @Column(name = "children_amount", nullable = false)
  private int childrenAmount;

  @Column(name = "note")
  private String note;

  @Column(name = "deleted")
  private Boolean deleted;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "invitation")
  private InvitationResponse invitationResponse;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "invitation")
  private List<InvitationAccess> invitationAccesses;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "invitation")
  private List<InvitationVisit> invitationVisits;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "invitation")
  private List<InvitationChange> invitationChanges;

  @PrePersist
  public void generateUuid() {
    uuid = UUID.randomUUID().toString();
  }
}
