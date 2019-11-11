package com.cg.ocbms.dto;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="booking_id")
	private Long bookingId;
	@Column(name="status")
	private String status;
	@Column(name="booking_starttime")
	private LocalDateTime startTime;
	@Column(name="booking_endtime")
	private LocalDateTime endTime;
	@Column(name = "mode_of_payment")
	private String modeOfPayment;
	@Column(name = "delete_flag")
	private boolean isDeleted;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cab_id")
	private Cab cab;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
	private TravelDetails travelDetails;
	
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}


	


	public Booking(Long bookingId, String status, LocalDateTime startTime, LocalDateTime endTime, String modeOfPayment,
			boolean isDeleted, Cab cab, User user, TravelDetails travelDetails) {
		super();
		this.bookingId = bookingId;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.modeOfPayment = modeOfPayment;
		this.isDeleted = isDeleted;
		this.cab = cab;
		this.user = user;
		this.travelDetails = travelDetails;
	}





	public Long getBookingId() {
		return bookingId;
	}


	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	public LocalDateTime getEndTime() {
		return endTime;
	}


	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}


	



	public String getModeOfPayment() {
		return modeOfPayment;
	}


	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}


	public boolean isDeleted() {
		return isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public Cab getCab() {
		return cab;
	}


	public void setCab(Cab cab) {
		this.cab = cab;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	


	public TravelDetails getTravelDetails() {
		return travelDetails;
	}


	public void setTravelDetails(TravelDetails travelDetails) {
		this.travelDetails = travelDetails;
	}





	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", status=" + status + ", startTime=" + startTime + ", endTime="
				+ endTime + ", modeOfPayment=" + modeOfPayment + ", isDeleted=" + isDeleted + ", cab=" + cab + ", user="
				+ user + ", travelDetails=" + travelDetails + "]";
	}


	

	
}
