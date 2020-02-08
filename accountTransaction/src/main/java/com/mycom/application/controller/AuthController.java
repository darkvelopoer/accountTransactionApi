package com.mycom.application.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.application.constant.AppConstant;
import com.mycom.application.dto.BaseDTO;
import com.mycom.application.dto.ErrorAuthDTO;
import com.mycom.application.dto.SuccessAuthDTO;
import com.mycom.application.service.AuthService;

@RestController
public class AuthController {
	@Autowired
	private AuthService authService;
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	@PostMapping(value="/access_token")
	public ResponseEntity<BaseDTO> authorize(@RequestParam String grant_type, @RequestParam(required = false) String custId, @RequestParam(required = false) String password,
			@RequestParam String client_id, @RequestParam String client_secret, @RequestParam(required = false) String refresh_token){
		
		SuccessAuthDTO successAuth = new SuccessAuthDTO();
		try {
			
			Map<String, Object> memberMsgs = authService.validateAuthRequest(grant_type, custId, password, client_id, client_secret, refresh_token);
			if(memberMsgs.get("message")!=null) {
				ErrorAuthDTO errorAuth = new ErrorAuthDTO();
				errorAuth.setError(AppConstant.INVALID_REQUEST);
				errorAuth.setErrorDescription((String)memberMsgs.get("message"));
				
				if(AppConstant.TOKEN_EXPIRED_MSG.equals(memberMsgs.get("message"))) {
					return new ResponseEntity<>(errorAuth, HttpStatus.UNAUTHORIZED);
				}

				return new ResponseEntity<>(errorAuth, HttpStatus.METHOD_NOT_ALLOWED); 
			}
			log.info("PASSS");
			successAuth = authService.generateAccessToken(client_id, custId, (String)memberMsgs.get("memNo"));
			
		} catch (SQLException | NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		} 
		return new ResponseEntity<>(successAuth, HttpStatus.OK);
		
	}
}
