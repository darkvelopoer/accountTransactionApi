package com.mycom.application.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class AuthServiceInterceptorAppConfig extends WebMvcConfigurationSupport { 
   @Autowired
   AuthServiceInterceptor authServiceInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(authServiceInterceptor).addPathPatterns("/rest/**");//.excludePathPatterns("/admin/**");;
   }
}