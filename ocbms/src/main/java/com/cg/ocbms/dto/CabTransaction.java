package com.cg.ocbms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="cab_transaction")
public class CabTransaction {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "transaction_id")
		private Long transactionId;
		@DateTimeFormat(pattern="dd-MM-yyyy")
		@Column(name = "journey_date")
		private LocalDateTime date;
		@OneToOne(cascade = CascadeType.ALL)
		private Cab cab;
		@Column(name = "delete_flag")
		private boolean isDeleted;
		
		public CabTransaction() {
			// TODO Auto-generated constructor stub
		}

		public CabTransaction(Long transactionId, LocalDateTime date, Cab cab, boolean isDeleted) {
			super();
			this.transactionId = transactionId;
			this.date = date;
			this.cab = cab;
			this.isDeleted = isDeleted;
		}

		public Long getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(Long transactionId) {
			this.transactionId = transactionId;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public void setDate(LocalDateTime date) {
			this.date = date;
		}

		public Cab getCab() {
			return cab;
		}

		public void setCab(Cab cab) {
			this.cab = cab;
		}

		public boolean isDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		@Override
		public String toString() {
			return "CabTransaction [transactionId=" + transactionId + ", date=" + date + ", cab=" + cab + ", isDeleted="
					+ isDeleted + "]";
		}
		
		
		
}
