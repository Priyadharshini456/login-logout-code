package com.bms.authserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.authserver.models.PasswordAudit;

public interface PasswordAuditRepository extends JpaRepository<PasswordAudit, Long> {

}
