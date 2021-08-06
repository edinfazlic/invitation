package com.invitations.core.repository;

import com.invitations.core.model.entity.InvitationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationTemplateRepository extends JpaRepository<InvitationTemplate, Long> {

  List<InvitationTemplate> findAllByOrderByName();
}
