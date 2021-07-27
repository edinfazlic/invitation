package com.invitations.core.repository;

import com.invitations.core.model.entity.InvitationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationTemplateRepository extends JpaRepository<InvitationTemplate, Long> {

}
