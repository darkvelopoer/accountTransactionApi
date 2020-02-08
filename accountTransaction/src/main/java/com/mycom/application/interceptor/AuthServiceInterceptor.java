package com.mycom.application.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.mycom.application.constant.AppConstant;
import com.mycom.application.dto.ErrorAuthDTO;
import com.mycom.application.service.AuthService;


@Component
public class AuthServiceInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private AuthService authService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("Pre Handle method is Calling");
		//String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		String accessToken = request.getHeader("Authorization");

		boolean result;
		if (accessToken == null || !accessToken.startsWith("Bearer ")) {
			rejectRequest(response, AppConstant.TOKEN_INVALID_MSG);
			result = false;
		}else {
			accessToken = accessToken.substring(7); 
			int accessTokenStatus = authService.validateAccessToken(accessToken);
			
			if(AppConstant.TOKEN_OK == accessTokenStatus){
				result = true;
			}
			else if(AppConstant.TOKEN_INVALIDATED == accessTokenStatus){
				rejectRequest(response, AppConstant.PASSWORD_CHANGED_MSG);
				result = false;
			}
			else if(AppConstant.TOKEN_EXPIRED == accessTokenStatus){
				rejectRequest(response, AppConstant.TOKEN_EXPIRED_MSG);
				result = false;
			}
			else {
				rejectRequest(response, AppConstant.TOKEN_INVALID_MSG);
				result = false;
			}

		}
		return result;
	}
	
    private void rejectRequest(HttpServletResponse response, String errorMsg) throws IOException {
    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter out = response.getWriter();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        
        ErrorAuthDTO errorAuth = new ErrorAuthDTO();
		errorAuth.setError(AppConstant.INVALID_REQUEST);
		errorAuth.setErrorDescription(errorMsg);
    
        Gson gson = new Gson();
        out.print(gson.toJson(errorAuth));
        out.flush();
       
    }
}
