package com.invitations.core.repository;

import com.invitations.core.model.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

  Invitation findByUuidAndDeletedIsNull(String uuid);

  List<Invitation> findAllByDeletedIsNull();
}
