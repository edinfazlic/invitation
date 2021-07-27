package com.invitations.core.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
    name = "invitation_user"
)
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends EntityWithLongId {

  @Column(name = "name")
  private String username;

  @Column(name = "password")
  private String password;

}
