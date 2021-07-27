/*
 * This file is part of the MedRecord Serious Gaming Service which is commercial software
 * created by MedVision360 B.V. The source code is proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * Copyright (c) by MedVision360 B.V. All rights reserved.
 */

package com.invitations.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntityWithLongId implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
