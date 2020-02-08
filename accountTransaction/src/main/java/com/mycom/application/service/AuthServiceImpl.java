package com.mycom.application.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.application.constant.AppConstant;
import com.mycom.application.dto.AuthMemberDTO;
import com.mycom.application.dto.AuthTokenDTO;
import com.mycom.application.dto.SuccessAuthDTO;
import com.mycom.application.util.CommonUtil;
import com.mycom.mybatis.mapper.AuthMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class AuthServiceImpl implements AuthService {
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private AuthMapper authMapper;
	
	public Map<String, Object> validateAuthRequest(String grantType, String memId, String password,
			String clientId, String clientSecret, String refreshToken) throws SQLException, NoSuchAlgorithmException {
		Map<String, Object> memberMsgs = new HashMap<String, Object>();
		if (!(AppConstant.API_CLIENT_ID).equals(clientId)) {
			memberMsgs.put("message", AppConstant.INVALID_CLIENT_ID_MSG);
			return memberMsgs;
		} else if(!(AppConstant.API_CLIENT_SECRET).equals(clientSecret)) {
			memberMsgs.put("message", AppConstant.INVALID_CLIENT_SECRET_MSG);
			return memberMsgs;
		}
		if(AppConstant.GRANT_PASSWORD.equals(grantType)) {
			validateGrantTypePassword(memId, password, memberMsgs);
		}
		
		return memberMsgs;
	}
	
	private void validateGrantTypePassword(String memId, String password, Map<String, Object> memberMsgs)
			throws SQLException, NoSuchAlgorithmException {
		String encPassword = CommonUtil.toHexString(CommonUtil.getSHA(password));
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("custId", memId);
		params.put("password", encPassword);
		AuthMemberDTO authMember = authMapper.getAuthMember(params);

		if (authMember == null) {
			memberMsgs.put("message", AppConstant.INCORRECT_EMAIL_PASSWORD_MSG);
		}
		
		memberMsgs.put("memNo", authMember.getMemNo());
	}

	public SuccessAuthDTO generateAccessToken(String clientId,String memberId, String memNo) throws SQLException{
		SuccessAuthDTO successAuthDTO = new SuccessAuthDTO();
		if(clientId != null && memNo != null){	
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    int tokenDurationHour = 1; //1 hour
		    int tokenDurationDay = 30; //30 days
		    
			Calendar calSixtyMinLater = Calendar.getInstance();
			calSixtyMinLater.add(Calendar.HOUR, tokenDurationHour);
			
			Calendar calThirtyDaysLater = Calendar.getInstance();
			calThirtyDaysLater.add(Calendar.DATE, tokenDurationDay);
			
			//Generate JWT
		    HashMap<String,Object> map = new HashMap<String,Object>();
		    map.put("alg","none");//HS256
		    map.put("typ","JWT");
		    HashMap<String,Object> mapClaims = new HashMap<String,Object>();
		    map.put("clientId",clientId);
		    map.put("memNo",memNo);
		    map.put("createdAt", formatter.format(new Date()));
		    map.put("expiredAt", formatter.format(calSixtyMinLater.getTime()));
		    		    
		    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("myTestApplication123");
		    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		    
		    String jwtToken = Jwts.builder().setClaims(mapClaims)
					            .setHeader(map)
					            .setExpiration(new Date(System.currentTimeMillis() + (3600 * 1000))) //60 minutes
					            .signWith(signatureAlgorithm, signingKey)
					            .compact();
		    
		    successAuthDTO.setAccessToken(jwtToken);

		    String refreshToken = Base64.getEncoder().encodeToString(
		    		generateRefreshToken(AppConstant.REFRESH_TOKEN_LENGTH, clientId + AppConstant.REFRESH_TOKEN_SOURCE + memberId).getBytes());
		    successAuthDTO.setRefreshToken(refreshToken);
		    successAuthDTO.setTokenType(AppConstant.BEARER);
		    successAuthDTO.setExpiresIn(AppConstant.EXPIRY_DURATION_3600);
		    String tokenScope = AppConstant.READ + ", " + AppConstant.WRITE;
		    successAuthDTO.setScope(tokenScope);
		    
		    DateFormat formatterDb = new SimpleDateFormat("yyyyMMddHHmmss");
		    
		    AuthTokenDTO authTokenDTO = new AuthTokenDTO();
		    authTokenDTO.setMemNo(memNo);
		    authTokenDTO.setClientId(clientId);
		    authTokenDTO.setAccessToken(jwtToken);
		    authTokenDTO.setRefreshToken(refreshToken);
		    authTokenDTO.setAccessTokenExpiry(formatterDb.format(calSixtyMinLater.getTime()));
		    authTokenDTO.setRefreshTokenExpiry(formatterDb.format(calThirtyDaysLater.getTime()));
		    authMapper.saveAuthInfo(authTokenDTO);
		}
		return successAuthDTO;
	}
	
	private String generateRefreshToken(int n, String AlphaNumericString){ 
        StringBuilder sb = new StringBuilder(n); 
        for (int i = 0; i < n; i++) { 
            int index = (int)(AlphaNumericString.length()*Math.random());             
            sb.append(AlphaNumericString.charAt(index)); 
        } 
        return sb.toString(); 
    } 
	
	public int validateAccessToken(String accessToken) throws SQLException{
		int accessTokenStatus = AppConstant.TOKEN_INVALID; 
		if(accessToken != null){
			try {
				Map<String, String> params = new HashMap<>();
				params.put("accessToken", accessToken);
				AuthTokenDTO authToken = authMapper.getAccessToken(params);
				if (authToken == null) {
					accessTokenStatus = AppConstant.TOKEN_INVALID;
				}
				else {
					DateFormat dtFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					Date accesstokenExpiryDate = dtFormat.parse(authToken.getAccessTokenExpiry());
					Date currentDt = dtFormat.parse(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
					if (currentDt.before(accesstokenExpiryDate)) {
						accessTokenStatus = AppConstant.TOKEN_OK;
					} else {
						accessTokenStatus = AppConstant.TOKEN_EXPIRED;
					}
				}
				
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		return accessTokenStatus;
	}
}
