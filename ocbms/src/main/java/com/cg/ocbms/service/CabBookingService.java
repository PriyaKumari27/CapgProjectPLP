package com.cg.ocbms.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.cg.ocbms.dto.Booking;
import com.cg.ocbms.dto.Cab;
import com.cg.ocbms.dto.CabTransaction;
import com.cg.ocbms.dto.Driver;
import com.cg.ocbms.dto.TravelDetails;
import com.cg.ocbms.dto.User;
import com.cg.ocbms.exception.CBMSException;

public interface CabBookingService {
	
	public User addUser(User user) throws CBMSException;
	public User searchUser(Long userId) throws CBMSException;
	public User updateUser(Long userId,User user) throws CBMSException;
	public List<User> getAllUsers() throws CBMSException;
	
	public Driver addDriver(Driver driver) throws CBMSException;
	public Driver searchDriver(Long driverId) throws CBMSException;
	public Driver updateDriver(Long driverId,Driver driver) throws CBMSException;
	
	public TravelDetails addDetails(TravelDetails detail) throws CBMSException;
	
	public List<Driver> getAllDrivers() throws CBMSException;
	public List<String> findSources() throws CBMSException;;
//	public List<String> findDestination() throws CBMSException;
	
	public Cab addCab(Long driverId,Cab cab) throws CBMSException;
	public Cab searchCab(Long cabId) throws CBMSException;
	public Cab updateCab(Cab cab) throws CBMSException;
	public List<Cab> getAllCabs() throws CBMSException;

	
	public Booking createBooking(Booking booking,String source,String destination,Long id,Long userId) throws CBMSException;
	public Booking cancelBooking(Long bookingId) throws CBMSException;
	public List<Booking> getAllBookings(Long userId) throws CBMSException;
	public Booking findBookingById(Long bookingId) throws CBMSException;
	
	public CabTransaction addTransaction(CabTransaction transaction);
	public CabTransaction searchTransactionById(Long transactionId);
}
