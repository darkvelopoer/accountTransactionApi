package com.mycom.mybatis.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.mycom.application.dto.AuthMemberDTO;
import com.mycom.application.dto.AuthTokenDTO;

public interface AuthMapper {
	@Insert("Insert into APP_API_AUTH_INFO(MEM_NO, CLIENT_ID, ACCESS_TOKEN, REFRESH_TOKEN, "
			+ "CREATE_DATE, CREATE_NO, UPDATE_DATE, UPDATE_NO, ACCESS_TOKEN_EXPIRY, REFRESH_TOKEN_EXPIRY) "
			+ "values (#{memNo}, #{clientId}, #{accessToken}, #{refreshToken}, CURRENT_TIMESTAMP(), #{memNo}, "
			+ "CURRENT_TIMESTAMP(), #{memNo}, to_date(#{accessTokenExpiry}, 'yyyymmddhh24miss'), to_date(#{refreshTokenExpiry}, 'yyyymmddhh24miss'))")
	public Integer saveAuthInfo(AuthTokenDTO authTokenDTO);
	
	@Select("select customer_no memNo from CUSTOMERS where customer_id = #{custId} and password = #{password}")
	public AuthMemberDTO getAuthMember(Map<String, String> params);
	
	@Select("SELECT TO_CHAR(ACCESS_TOKEN_EXPIRY, 'yyyymmddhh24miss') accessTokenExpiry " 
			+ "FROM APP_API_AUTH_INFO " 
			+ "WHERE ACCESS_TOKEN = #{accessToken} " 
			+ " AND ROWNUM = 1 "  
			+ "ORDER BY CREATE_DATE DESC")
	public AuthTokenDTO getAccessToken(Map<String, String> params);
}
