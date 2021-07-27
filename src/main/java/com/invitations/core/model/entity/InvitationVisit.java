package com.invitations.core.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(
    name = "invitation_visit"
)
@Getter
@Setter
@NoArgsConstructor
public class InvitationVisit extends EntityWithLongId {

  @ManyToOne
  @JoinColumn(name = "invitation", referencedColumnName = "id")
  private Invitation invitation;

  @Column(name = "created_date")
  private Date date;

  @PrePersist
  public void generateDate() {
    date = new Date();
  }
}
