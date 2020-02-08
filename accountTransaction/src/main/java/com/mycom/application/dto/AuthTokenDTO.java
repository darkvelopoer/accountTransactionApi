package com.mycom.application.dto;

import java.io.Serializable;

public class AuthTokenDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 4381634290900615273L;
	private String memNo;
    private String clientId;
    private String accessToken;
    private String refreshToken;
    private String accessTokenExpiry;
    private String refreshTokenExpiry;
    private String tokenInvalid;
    
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getAccessTokenExpiry() {
		return accessTokenExpiry;
	}
	public void setAccessTokenExpiry(String accessTokenExpiry) {
		this.accessTokenExpiry = accessTokenExpiry;
	}
	public String getRefreshTokenExpiry() {
		return refreshTokenExpiry;
	}
	public void setRefreshTokenExpiry(String refreshTokenExpiry) {
		this.refreshTokenExpiry = refreshTokenExpiry;
	}
	public String getTokenInvalid() {
		return tokenInvalid;
	}
	public void setTokenInvalid(String tokenInvalid) {
		this.tokenInvalid = tokenInvalid;
	}
}
