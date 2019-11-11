package com.cg.ocbms.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ocbms.exception.CBMSException;
import com.cg.ocbms.dto.Booking;
import com.cg.ocbms.dto.Cab;
import com.cg.ocbms.dto.CabTransaction;
import com.cg.ocbms.dto.Driver;
import com.cg.ocbms.dto.TravelDetails;
import com.cg.ocbms.dto.User;
import com.cg.ocbms.service.CabBookingService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CabBookingController {
	
	private static final Logger logger = LoggerFactory.getLogger(CabBookingController.class);
	
	
	@Autowired
	CabBookingService cabBookingService;
	
	
	@PostMapping(value="/registeruser")
	public ResponseEntity<?> addUser(@RequestBody User user){
		logger.info("Entered register user method");
		User newUser=new User();
		try {
			List<Booking> bookingList=new ArrayList<Booking>();
			newUser.setUserName(user.getUserName());
			newUser.setUserPassword(user.getUserPassword());
			newUser.setUserEmail(user.getUserEmail());
			newUser.setUserContact(user.getUserContact());
			newUser.setBookingList(null);
			newUser.setIsAdmin(false);
			newUser.setIsDeleted(false);
			cabBookingService.addUser(newUser);
		}catch(CBMSException e) {
			return new ResponseEntity<String>("Data not added!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Data added successfully!", HttpStatus.OK);	

	}

	@GetMapping(value="showuserinput")
	public ResponseEntity<?> updateUser(@RequestParam("userid")Long userId) throws CBMSException{
		User user;
		try {
			user=cabBookingService.searchUser(userId);
			User userOne=new User();
			userOne.setUserId(user.getUserId());
			userOne.setUserName(user.getUserName());
			userOne.setUserPassword(user.getUserPassword());
			userOne.setUserContact(user.getUserContact());
			userOne.setUserEmail(user.getUserEmail());
			userOne.setBookingList(user.getBookingList());
			return new ResponseEntity<User>(userOne,HttpStatus.OK);
		}catch(CBMSException e) {
			return new ResponseEntity<String>("User not found",HttpStatus.NO_CONTENT);
		}
	}
	@PutMapping(value="/updateuser")
	public ResponseEntity<?> updateUserProfile(@RequestBody User user) throws CBMSException{
		User userOne;
		try {
			userOne=cabBookingService.searchUser(user.getUserId());
			userOne.setUserId(user.getUserId());
			userOne.setUserName(user.getUserName());
			userOne.setUserPassword(user.getUserPassword());
			userOne.setUserContact(user.getUserContact());
			userOne.setUserEmail(user.getUserEmail());
			cabBookingService.updateUser(user.getUserId(), userOne);
			return new ResponseEntity<String>("User Updated Successfully",HttpStatus.OK);
		}catch(CBMSException e) {
			return new ResponseEntity<String>("User Not Updated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		

	@GetMapping(value="/showusers")
	public ResponseEntity<?> getAllUsers() throws CBMSException{
		
		List<User> userone=  cabBookingService.getAllUsers();
		if(userone.isEmpty()) {
			
			return new ResponseEntity<String>(JSONObject.quote("No user details present"),HttpStatus.BAD_REQUEST);
			
		}
			
			
			return new ResponseEntity<List<User>>(userone,HttpStatus.OK);
		}
		
	
//	@GetMapping(value="showusers")
//	public ResponseEntity<?> getAllUsers() throws CBMSException{
//		List<User> users=  cabBookingService.getAllUsers();
//		if(users.isEmpty()) {
//			return new ResponseEntity<String>("No user details exists",HttpStatus.INTERNAL_SERVER_ERROR);
//			
//		}else
//			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
//	}
//	
	@PostMapping(value="/adddriver")
	public ResponseEntity<?> addDriver(@RequestBody Driver driver){
		Driver newDriver=new Driver();
		try {
			
			newDriver.setDriverName(driver.getDriverName()); 
			
			newDriver.setDriverEmail(driver.getDriverEmail());
			newDriver.setDriverContact(driver.getDriverContact());
			newDriver.setDrivingLicence(driver.getDrivingLicence());
	
			cabBookingService.addDriver(newDriver);
		}catch(CBMSException e) {
			return new ResponseEntity<String>(JSONObject.quote("Data not added!"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(JSONObject.quote("Data added successfully!"), HttpStatus.OK);	

	}

	@GetMapping(value="showdriverinput")
	public ResponseEntity<?> updateDriver(@RequestParam("driverid")Long driverId) throws CBMSException{
		Driver driver;
		
		try {
			driver=cabBookingService.searchDriver(driverId);
			Driver driverOne=new Driver();
			driverOne.setDriverId(driver.getDriverId());
			driverOne.setDriverName(driver.getDriverName());
			driverOne.setDriverContact(driver.getDriverContact());
			driverOne.setDriverEmail(driver.getDriverEmail());
			driverOne.setDrivingLicence(driver.getDrivingLicence());
			
			return new ResponseEntity<Driver>(driverOne,HttpStatus.OK);
		}catch(CBMSException e) {
			return new ResponseEntity<String>(JSONObject.quote("Driver not found."),HttpStatus.NO_CONTENT);
		}
			

	}
	

	@PutMapping(value="/updatedriver")
	public ResponseEntity<?> updateDriverProfile(@RequestBody Driver driver) throws CBMSException {
		Driver driverOne;
		
		try {
			driverOne=cabBookingService.searchDriver(driver.getDriverId());
			driverOne.setDriverId(driver.getDriverId());
			driverOne.setDriverName(driver.getDriverName());
			
			driverOne.setDriverContact(driver.getDriverContact());
			driverOne.setDriverEmail(driver.getDriverEmail());
			driverOne.setDrivingLicence(driver.getDrivingLicence());
			cabBookingService.updateDriver(driver.getDriverId(), driverOne);
			return new ResponseEntity<String>(JSONObject.quote("Driver Updated Successfully"),HttpStatus.OK);
		}catch(CBMSException e) {
			return new ResponseEntity<String>(JSONObject.quote("Driver Not Updated"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	

	@GetMapping(value="showdrivers")
	public ResponseEntity<?> getAllDrivers() throws CBMSException{
		List<Driver> drivers=  cabBookingService.getAllDrivers();
		if(drivers.isEmpty()) {
			return new ResponseEntity<String>("No driver details exists",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}else
			return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
	}
	
	@PostMapping(value="/addcab")
	public ResponseEntity<?> addCab(@RequestParam("driverid") Long driverId,@RequestBody Cab cab) throws CBMSException {
	Driver driver=cabBookingService.searchDriver(driverId);
	if(driver==null) {
		return new ResponseEntity<String>(JSONObject.quote("Driver does not exist"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
		try {
		
			cabBookingService.addCab(driverId, cab);
		}catch(CBMSException e) {
			return new ResponseEntity<String>("Driver does not exist!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Data added successfully!", HttpStatus.OK);	

	}
	@GetMapping(value="showcab")
	public ResponseEntity<?> showCab(@RequestParam("cabid")Long cabId) throws CBMSException{
     Cab returnedCab=cabBookingService.searchCab(cabId);
		
		if(returnedCab==null) {
			return new ResponseEntity<String>("Cab does not exist!",HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		return new ResponseEntity<Cab>(returnedCab, HttpStatus.OK);
		
	}
	
	@GetMapping(value="showcabs")
	public ResponseEntity<?> getAllCabs() throws CBMSException{
		List<Cab> cabs=  cabBookingService.getAllCabs();
		if(cabs.isEmpty()) {
			return new ResponseEntity<String>("No cab details exists",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}else
			return new ResponseEntity<List<Cab>>(cabs, HttpStatus.OK);
	}
	
	@GetMapping(value="/showbookings")
	public ResponseEntity<?> getAllBookings(@RequestParam("userid") Long userId) throws CBMSException{
		List<Booking> bookings=cabBookingService.getAllBookings(userId);
		if(bookings.isEmpty()) {
			return new ResponseEntity<String>("No booking is made by the user yet",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new  ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
	}
	

	@PostMapping(value="/createbooking")
	public ResponseEntity<?> addBooking(@RequestBody Booking booking,@RequestParam("source") String source,@RequestParam("destination") String destination,@RequestParam("cabid") Long id,@RequestParam("userid") Long userid) throws CBMSException {
		
		
		Booking bookingOne=cabBookingService.createBooking(booking, source, destination,id,userid);
		
//		booking.setCab(cabTransaction.getCab());
//		booking.setStatus("BOOKED");
//		booking.setModeOfPayment(booking.getModeOfPayment());
//		booking.setDeleted(false);
//		booking.setStartTime(cabTransaction.getDate());
//		booking.setSource(booking.getSource());
//		booking.setDestination(booking.getDestination());
//		booking.setDeleted(false);
	//	Booking bookingOne=cabBookingService.createBooking(booking);
		if(bookingOne==null) {
			return new ResponseEntity<String>("No bookings made",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Booking>(bookingOne,HttpStatus.OK);
		
		
		
	}
	@PostMapping(value="/cancelbooking")
	public ResponseEntity<?> cancelBooking(@RequestParam("bookingid") Long bookingId) throws CBMSException{
		Booking bookingStatus=cabBookingService.cancelBooking(bookingId);
		if(bookingStatus==null) {
			return new ResponseEntity<String>("Booking cannot be cancelled ",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Booking cancelled",HttpStatus.OK);
	}
	
	@GetMapping(value="bookingbyid")
	public ResponseEntity<?> getBookingByBookingId(@RequestParam("id")Long bookingId) throws CBMSException{
		Booking booking=cabBookingService.findBookingById(bookingId);
		if(booking==null) {
			return new ResponseEntity<String>("No bookings with the given id has been made",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Booking>(booking,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/getbookings")
	public ResponseEntity<?> getBookings(@RequestParam("userid") Long userId) throws CBMSException {
		List<Booking> bookings=cabBookingService.getAllBookings(userId);
		if(bookings==null) {
			return new ResponseEntity<String>("No bookings has been made by the user",HttpStatus.NO_CONTENT);
		}
		
		
		return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
	}
	
	@PostMapping("/adddetail")
	public ResponseEntity<?> addDetail(@RequestBody TravelDetails detail) throws CBMSException{
		TravelDetails travelDetails=new TravelDetails();
		try {
			travelDetails.setSource(detail.getSource());
			travelDetails.setDestination(detail.getDestination());
			travelDetails.setPrice(detail.getPrice());
			cabBookingService.addDetails(travelDetails);
		}catch(CBMSException e) {
			return new ResponseEntity<String>(JSONObject.quote("Details cannot be added"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(JSONObject.quote("Travel details added successfully"),HttpStatus.OK);
		
//		TravelDetails travelDetails=cabBookingService.addDetails(detail);
//		if(travelDetails==null) {
//			return new ResponseEntity<String>("Data not added",HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity<TravelDetails>(travelDetails,HttpStatus.OK);
//		
	}
	
	

	
	@GetMapping("/getsources")
	public ResponseEntity<?> getSourceList() throws CBMSException{
		List<String> sources=cabBookingService.findSources();
		if(sources==null) {
			return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
		}
	return new ResponseEntity<List<String>>(sources,HttpStatus.OK);
}
//	
//
//	@GetMapping(value = "/getdestinations")
//	public ResponseEntity<?> getDestinationList() throws CBMSException {
//		List<String> destinations= cabBookingService.findDestination();
//		if(destinations==null) {
//			return   new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<List<String>>(destinations,HttpStatus.OK);
//	}
}

	
	

	
		
	


