package com.invitations.core.repository;

import com.invitations.core.model.entity.InvitationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationResponseRepository extends JpaRepository<InvitationResponse, Long> {

  InvitationResponse findByInvitationId(Long invitationId);
}
