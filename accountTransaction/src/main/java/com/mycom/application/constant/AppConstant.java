package com.mycom.application.constant;

public class AppConstant {
	public static final int REFRESH_TOKEN_LENGTH = 40;
	public static final String REFRESH_TOKEN_SOURCE = "0123456789"; //to use for refresh token
	public static final String BEARER = "bearer";
	public static final String CREATE = "create";
	public static final String READ = "read";
	public static final String WRITE = "write";
	public static final String GRANT_PASSWORD = "password";
	public static final String GRANT_REFRESH_TOKEN = "refresh_token";
	public static final long EXPIRY_DURATION_3600 = 3600; //60min
	public static final long EXPIRY_DURATION_1800 = 1800; //30min
	public static final String API_CLIENT_ID = "trxapp";
	public static final String API_CLIENT_SECRET = "123456";
	public static final String SELLER_API_TOKEN_DURATION_HOUR = "SELLER_API_TOKEN_DURATION_HOUR";
	public static final String SELLER_API_TOKEN_DURATION_DAY = "SELLER_API_TOKEN_DURATION_DAY";
	
	public static final String NOT_SELLER_MEM_ID_MSG = "Not a seller member Id";
	public static final String STATUS_NO_LONGER_ACTIVE_MSG = "Status is no longer active";
	public static final String ACCOUNT_LOCKED_MSG = "Your account is locked due to more than 10 failed login attempts. Please reset your password to unlock your account";
	public static final String TOKEN_EXPIRED_MSG = "Token expired";
	public static final String GRANT_TYPE_CANNOT_EMPTY_MSG = "grant_type can not be empty";
	public static final String INVALID_GRANT_TYPE_MSG = "Invalid grant_type";
	public static final String INCORRECT_EMAIL_PASSWORD_MSG = "Incorrect email or password";
	public static final String PASSWORD_CHANGED_MSG = "Password changed";
	public static final String TOKEN_INVALID_MSG = "Token invalid";
	
	public static final String INVALID_CLIENT_SECRET_MSG = "Invalid client_secret";
	public static final String INVALID_CLIENT_ID_MSG = "Invalid client_id";
	public static final String CLIENT_SECRET_CANNOT_EMPTY = "client_secret can not be empty";
	public static final String CLIENT_ID_CANNOT_EMPTY = "client_id can not be empty";
	public static final String REFRESH_TOKEN_CANNOT_EMPTY = "refresh_token can not be empty";
	public static final String INVALID_REQUEST = "invalid_request";
	public static final String TOKEN_CANNOT_EMPTY = "token can not be empty";
	
	public static final String ACCESS_TOKEN = "access_token";
	public static final String REFRESH_TOKEN = "refresh_token";
	
	public static final int TOKEN_OK = 0;
	public static final int TOKEN_INVALIDATED = 1;
	public static final int TOKEN_EXPIRED = 2;
	public static final int TOKEN_INVALID = 3;
}
