package com.cg.ocbms.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ocbms.dto.Booking;
import com.cg.ocbms.dto.Cab;
import com.cg.ocbms.dto.CabTransaction;
import com.cg.ocbms.dto.Driver;
import com.cg.ocbms.dto.TravelDetails;
import com.cg.ocbms.dto.User;
import com.cg.ocbms.exception.CBMSException;
import com.cg.ocbms.exception.ExceptionMessage;
import com.cg.ocbms.repository.BookingRepository;
import com.cg.ocbms.repository.CabRepository;
import com.cg.ocbms.repository.CabTransactionRepository;
import com.cg.ocbms.repository.DriverRepository;
import com.cg.ocbms.repository.TravelDetailsRepository;
import com.cg.ocbms.repository.UserRepository;
@Service("cabBookingService")
@Transactional
public class CabBookingServiceImpl implements CabBookingService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	CabRepository cabRepository;
	
	@Autowired
	TravelDetailsRepository travelDetailsRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	CabTransactionRepository cabTransactionRepository;
	
	
	@Override
	public User addUser(User user) throws CBMSException {
		// TODO Auto-generated method stub
		User userOne= userRepository.save(user);
		if(userOne==null) {
			throw new CBMSException(ExceptionMessage.ADDUSERMESSAGE);
		}
		
			return userOne;
		
	}
	@Override
	public User searchUser(Long userId) throws CBMSException {
		User returnedUser=userRepository.findByUserId(userId);
		if(returnedUser!=null) {
			return returnedUser;
		}
		else {
			throw new CBMSException(ExceptionMessage.USERMESSAGE);
		}
	}


//	@Override
//	public User updateUser(User user) throws CBMSException {
//		// TODO Auto-generated method stub
//		User userOne=new User();
//		userOne.setUserName(user.getUserName());
//		userOne.setUserPassword(user.getUserPassword());
//		userOne.setUserContact(user.getUserContact());
//		userOne.setUserEmail(user.getUserEmail());
//		User userUpdated  =userRepository.save(userOne);
//		return userUpdated;
//		
//	}

	@Override
	public User updateUser(Long userId,User user) throws CBMSException {
		// TODO Auto-generated method stub
		User userOne=userRepository.findByUserId(userId);
		if(userOne!=null) {
			user.setUserId(userId);
			userOne.setUserName(user.getUserName());
			userOne.setUserPassword(user.getUserPassword());
			userOne.setUserContact(user.getUserContact());
			userOne.setUserEmail(user.getUserEmail());
			userOne.setBookingList(user.getBookingList());
			userRepository.save(userOne);
			return user;
		}else {
			throw new CBMSException(ExceptionMessage.USERMESSAGE);
		}
		
		
	}

	
	@Override
	public List<User> getAllUsers() throws CBMSException {
		// TODO Auto-generated method stub
		
		ArrayList<User> userList=new ArrayList<User>();
		List<User> user= userRepository.findAll();
		for (User user2 : user) {
			if(user2.getBookingList()!=null) {
				User newUser=new User();
				newUser.setUserId(user2.getUserId());
				newUser.setUserName(user2.getUserName());
				newUser.setUserEmail(user2.getUserEmail());
				newUser.setUserContact(user2.getUserContact());
				userList.add(newUser);
			}
			
		}
				  

		
			// TODO Auto-generated method stub
//			ArrayList<User> userList = new ArrayList<User>( );
//			
//			 List<User> users= userRepository.findAll();
//			 for (User user : users) {
//				 if(user.getBookingList()!=null) {
//					 User newUser=new User();
//					 newUser.setUserId(user.getUserId());
//					 newUser.setUserName(user.getUserName());
//					 newUser.setUserContact(user.getUserContact());
//					 newUser.setUserEmail(user.getUserEmail());
//					 userList.add(newUser);
//				 }
//				 
//			 } 
//				 
//		return userList;
			
//		//return userRepository.findAll();
//		List<User> user=userRepository.findAll();
//		List<User> userList=new ArrayList<User>();
//		Iterator<User> iterator=user.iterator();
//		while(iterator.hasNext()) {
//			User newUser=iterator.next();
//			if(newUser.getIsDeleted()==false && newUser.getIsAdmin()==false && newUser.getBookingList()!=null) {
//				 User newUser1=new User();
//				 newUser1.setUserId(newUser.getUserId());
//				 newUser1.setUserName(newUser.getUserName());
//				 newUser1.setUserContact(newUser.getUserContact());
//				 newUser1.setUserEmail(newUser.getUserEmail());
//				// userList.add(newUser);
//				userList.add(newUser1);
//				
//				
//			}
//		}
		return userList;
	}

	@Override
	public Driver addDriver(Driver driver) throws CBMSException {
		
		Driver driverOne= driverRepository.save(driver);
		if(driverOne==null) {
			throw new CBMSException(ExceptionMessage.ADDDRIVERMESSAGE);
		}
		return driverOne;
		
	}
	
	@Override
	public Driver searchDriver(Long driverId) throws CBMSException {
		// TODO Auto-generated method stub
		Driver returnedDriver=driverRepository.findByDriverId(driverId);
		if(returnedDriver!=null) {
			return returnedDriver;
		}
		else {
			throw new CBMSException(ExceptionMessage.DRIVERMESSAGE);
		}
		
	}

	@Override
	public Driver updateDriver(Long driverId,Driver driver) throws CBMSException {
		// TODO Auto-generated method stub
		Driver driverOne =driverRepository.findByDriverId(driverId);
		if(driverOne!=null) {
		driverOne.setDriverId(driverId);
		driverOne.setDriverName(driver.getDriverName());
		
		driverOne.setDriverEmail(driver.getDriverEmail());
		driverOne.setDriverContact(driver.getDriverContact());
		driverOne.setDrivingLicence(driver.getDrivingLicence());
		driverRepository.save(driverOne);
		
		return driver;
		}else {
			throw new CBMSException(ExceptionMessage.DRIVERMESSAGE);
		}
	}
	

	@Override
	public List<Driver> getAllDrivers() throws CBMSException {
		// TODO Auto-generated method stub
		return driverRepository.findAll();
	}

	@Override
	public Cab addCab(Long driverId,Cab cab) throws CBMSException {
		// TODO Auto-generated method stub
		Driver driver=driverRepository.findByDriverId(driverId);
		Cab newCab=new Cab();
		newCab.setCabName(cab.getCabName());
		newCab.setCabModel(cab.getCabModel());
		newCab.setCabNumber(cab.getCabNumber());
		newCab.setCabType(cab.getCabType());
		
	//	driver.getCab().setCabId(cab.getCabId());
		Cab cabOne= cabRepository.save(newCab);
		driver.setCab(newCab);
		driverRepository.save(driver);
		if(cabOne==null) {
			throw new CBMSException(ExceptionMessage.ADDCABMESSAGE);
		}
		return cabOne;
		
	}

	@Override
	public Cab updateCab(Cab cab) throws CBMSException {
		// TODO Auto-generated method stub
		Cab cabOne= new Cab();
		cabOne.setCabName(cab.getCabName());
		cabOne.setCabNumber(cab.getCabNumber());
		cabOne.setCabModel(cab.getCabModel());
		cabOne.setCabType(cab.getCabType());
		Cab cabUpdated=cabRepository.save(cabOne);
		return cabUpdated;
	}

	@Override
	public List<Cab> getAllCabs() throws CBMSException {
		// TODO Auto-generated method stub
		return cabRepository.findAll();
	}
	
	@Override
	public Cab searchCab(Long cabId) throws CBMSException {
		// TODO Auto-generated method stub
		Cab returnedCab=cabRepository.findByCabId(cabId);
		if(returnedCab!=null) {
			return returnedCab;
		}
		else {
			throw new CBMSException(ExceptionMessage.CABMESSAGE);
		}
		
	}

	@Override
	public Booking createBooking(Booking booking,String source,String destination,Long id,Long userId) throws CBMSException {
		
		
		Booking newBooking=new Booking();
		List<TravelDetails> details = travelDetailsRepository.findAll();
		for(int i=0;i<details.size();i++) {
			if(details.get(i).getSource().equals(source)&&details.get(i).getDestination().equals(destination)) {
				newBooking.setTravelDetails(details.get(i));
				break;
			}
		}
		if(newBooking.getTravelDetails()==null) {
			throw new CBMSException("Sorry no cabs are available for this route");
		}
		
		newBooking.setUser(userRepository.findByUserId(userId));
		newBooking.setCab(cabRepository.findByCabId(id));
	//	newBooking.setCab(booking.getCab());
	//	newBooking.setSource(booking.getSource());
	//	newBooking.setDestination(booking.getDestination());
		newBooking.setStartTime(booking.getStartTime());
		newBooking.setEndTime(booking.getEndTime());
	//.setEstimatedFare(booking.getEstimatedFare());
		newBooking.setModeOfPayment(booking.getModeOfPayment());
		newBooking.setStatus(null);
		
//		newBooking.setCab(booking.getCab());
//		newBooking.setUser(booking.getUser());
		
		Booking bookingOne=bookingRepository.save(newBooking);
		if(bookingOne==null) {
			throw new CBMSException("Booking cannot be made");
		}
		return bookingOne;
	
		
	}

	



	@Override
	public Booking cancelBooking(Long bookingId) throws CBMSException{
		// TODO Auto-generated method stub
		Booking booking = bookingRepository.findById(bookingId).get();
		booking.setStatus("CANCELLED");
		return booking;
	}
	
	@Override
	public List<Booking> getAllBookings(Long userId) throws CBMSException{
		// TODO Auto-generated method stub
		User user=userRepository.findByUserId(userId);
		List<Booking>bookings= bookingRepository.findByUser(user);
		if(bookings==null) {
			throw new CBMSException("No bookings has been made by the user");
		}
		return bookings;
	}
	
	@Override
	public Booking findBookingById(Long bookingId) throws CBMSException{
		// TODO Auto-generated method stub
		return bookingRepository.findById(bookingId).get();
	}
	@Override
	public CabTransaction addTransaction(CabTransaction transaction) {
		// TODO Auto-generated method stub
		return cabTransactionRepository.save(transaction);
	}
	@Override
	public CabTransaction searchTransactionById(Long transactionId) {
		// TODO Auto-generated method stub
		return cabTransactionRepository.findById(transactionId).get();
	}

	@Override
	public List<String> findSources() throws CBMSException {
		// TODO Auto-generated method stub
		return travelDetailsRepository.findBySource();
		
		
	}
	@Override
	public TravelDetails addDetails(TravelDetails detail) throws CBMSException {
		// TODO Auto-generated method stub
		return travelDetailsRepository.save(detail);
	}





	
}
