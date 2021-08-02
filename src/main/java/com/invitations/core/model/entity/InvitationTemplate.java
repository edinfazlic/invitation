package com.invitations.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
    name = "invitation_template"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationTemplate extends EntityWithLongId {

  @Column(name = "name")
  private String name;

  @Column(name = "text", length = 1500)
  private String text;

}
