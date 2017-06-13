package com.example.security.login;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.example.dto.CustomerDTO;
import com.example.service.CustomerService;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
	
	private final CustomerService userService;
	private final BCryptPasswordEncoder encoder;
	private final Logger logger = LoggerFactory.getLogger(AjaxAuthenticationProvider.class);
	
	@Autowired
    public AjaxAuthenticationProvider(final CustomerService userService, final BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		    Assert.notNull(authentication, "No authentication data provided");

	        String username = (String) authentication.getPrincipal();
	        String password = (String) authentication.getCredentials();

	        CustomerDTO user =  userService.findByName(username);
	        		
	        if(user == null){
	        	logger.warn("User not found - authentication failed");
	        	throw new UsernameNotFoundException("User not found: " + username);
	        }

	        if (!encoder.matches(password, user.getPassword())) {
	            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
	        }

	        if (user.getRoles() == null){
	        	throw new InsufficientAuthenticationException("User has no roles assigned");
	        }

	        List<GrantedAuthority> authorities = user.getRoles().stream()
	                .map(authority -> new SimpleGrantedAuthority(authority))
	                .collect(Collectors.toList());

	        UserContext userContext = UserContext.create(user.getName(), authorities);

	        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}