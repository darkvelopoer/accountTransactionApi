package com.mycom.application.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import com.mycom.application.dto.SuccessAuthDTO;

public interface AuthService {
	public Map<String, Object> validateAuthRequest(String grantType, String memId, String password,
			String clientId, String clientSecret, String refreshToken) throws SQLException, NoSuchAlgorithmException;
	
	public SuccessAuthDTO generateAccessToken(String clientId,String memberId, String memNo) throws SQLException;
	
	public int validateAccessToken(String accessToken) throws SQLException;
}
