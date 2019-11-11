package com.cg.ocbms.exception;

public class ExceptionMessage {
	
	public static final String IDMESSAGE = "Id cannot be negative or null";
	public static final String ADDUSERMESSAGE = "User not added";
	public static final String ADDDRIVERMESSAGE = "Driver not added";
	public static final String ADDCABMESSAGE = "Cab not added";
	public static final String DATABASEMESSAGE = "Database is full! Contact your database manager for further queries!";
	public static final String USERMESSAGE = "The user does not exist";
	public static final String DRIVERMESSAGE = "The driver does not exist";
	public static final String CABMESSAGE = "Cab with given input does not exist";
	public static final String ASSOCIATEMESSAGE = "Car associated succesfully";
	public static final String ASSOCIATE_FAIL_CAR = "Car is Allready Associated with other driver";
	public static final String ASSOCIATE_FAIL_DRIVER = "Driver is Allready Associated with other car";

	

	
	public ExceptionMessage() {
		super();
	}

}

