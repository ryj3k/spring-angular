package com.example.security.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.example.security.config.WebSecurityConfig;
import com.example.security.model.RawAccessJwtToken;

public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter{
	 private final AuthenticationFailureHandler failureHandler;
	 private final TokenExtractor tokenExtractor;
	 private static final String ORIGIN = "Origin";
	 @Autowired
	 public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler, 
	            TokenExtractor tokenExtractor, RequestMatcher matcher) {
	        super(matcher);
	        this.failureHandler = failureHandler;
	        this.tokenExtractor = tokenExtractor;
	    }
	
	 	@Override
	    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	            throws AuthenticationException, IOException, ServletException {
	 		if (request.getHeader(ORIGIN) != null) {            
	            response.addHeader("Access-Control-Allow-Origin", request.getHeader(ORIGIN));
	            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	            response.addHeader("Access-Control-Allow-Credentials", "true");
	            response.addHeader("Access-Control-Allow-Headers",
	            request.getHeader("Access-Control-Request-Headers"));
	        }
	 		
	 		
	 		String tokenPayload = request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM);
	        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
	        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
	    }

	    @Override
	    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	            Authentication authResult) throws IOException, ServletException {
	        SecurityContext context = SecurityContextHolder.createEmptyContext();
	        context.setAuthentication(authResult);
	        SecurityContextHolder.setContext(context);
	        chain.doFilter(request, response);
	    }

	    @Override
	    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	            AuthenticationException failed) throws IOException, ServletException {
	        SecurityContextHolder.clearContext();
	        failureHandler.onAuthenticationFailure(request, response, failed);
	    }
}
