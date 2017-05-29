package com.example.security.login;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	
	 public static boolean isAjax(HttpServletRequest request) {
		    return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		  }
		  
		  public static boolean isGET(HttpServletRequest request) {
		    return "GET".equals(request.getMethod());
		  }
		  
		  public static boolean isPOST(HttpServletRequest request) {
		    return "POST".equals(request.getMethod());
		  }
}
