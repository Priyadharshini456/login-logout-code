package com.bms.authserver.models;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


@Entity
@Table(name="password_audit")
public class PasswordAudit {
	
	@Id
	@Column(name="p_id")
	@GenericGenerator(strategy = "native",name = "native")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name="c_id")
	private CustomerCredentials customerCredentials;
	
	@Column(name="old_password")
	private String oldPassword;
	
	@Column(name="insert_ts")
	@Temporal(TemporalType.TIMESTAMP)
	private Date insertTs;

	@Column(name="insert_by",length = 25)
	private String insertBy;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long pId) {
		this.id = pId;
	}

	public CustomerCredentials getCustomerCredentials() {
		return customerCredentials;
	}

	public void setCustomerCredentials(CustomerCredentials customerCredentials) {
		this.customerCredentials = customerCredentials;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Date getInsertTs() {
		return insertTs;
	}

	public void setInsertTs(Date insertTs) {
		this.insertTs = insertTs;
	}

	public String getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(String insertBy) {
		this.insertBy = insertBy;
	}

	public PasswordAudit() {}

	public PasswordAudit(CustomerCredentials customerCredentials,  String oldPassword, Date insertTs,
			String insertBy) {
		this.customerCredentials = customerCredentials;
		this.oldPassword = oldPassword;
		this.insertTs = insertTs;
		this.insertBy = insertBy;
	}

	
}
