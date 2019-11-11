package com.cg.ocbms.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners({AuditingEntityListener.class})
@Table(name="cab")
public class Cab {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cab_id")
	private Long cabId;
	@Column(name="cab_number")
	private String cabNumber;
	@Column(name="cab_name")
	private String cabName;
	@Column(name="cab_model")
	private String cabModel;
	@Column(name="cab_type")
	private String cabType;
	

	@CreatedBy
	protected String createdBy;
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;
	@LastModifiedBy
	protected String lastModifiedBy;
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedDate;
	
	
	
	public Cab() {
		// TODO Auto-generated constructor stub
	}

	public Cab(Long cabId, String cabNumber, String cabName, String cabModel, String cabType) {
		super();
		this.cabId = cabId;
		this.cabNumber = cabNumber;
		this.cabName = cabName;
		this.cabModel = cabModel;
		this.cabType = cabType;
	}

	public Long getCabId() {
		return cabId;
	}

	public void setCabId(Long cabId) {
		this.cabId = cabId;
	}

	public String getCabNumber() {
		return cabNumber;
	}

	public void setCabNumber(String cabNumber) {
		this.cabNumber = cabNumber;
	}

	public String getCabName() {
		return cabName;
	}

	public void setCabName(String cabName) {
		this.cabName = cabName;
	}

	public String getCabModel() {
		return cabModel;
	}

	public void setCabModel(String cabModel) {
		this.cabModel = cabModel;
	}

	public String getCabType() {
		return cabType;
	}

	public void setCabType(String cabType) {
		this.cabType = cabType;
	}

	@Override
	public String toString() {
		return "Cab [cabId=" + cabId + ", cabNumber=" + cabNumber + ", cabName=" + cabName + ", cabModel=" + cabModel
				+ ", cabType=" + cabType + "]";
	}
	
	

}
