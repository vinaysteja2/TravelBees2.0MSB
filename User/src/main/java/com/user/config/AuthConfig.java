package com.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.user.entity.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class AuthConfig {
	
	@Bean
	public UserDetailsService userDetailsService(){
		return new CustomUserDetailsService();
	}
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.csrf().disable()
	        		.authorizeRequests()
	        		.requestMatchers("/auth/register","/auth/token","/auth/validate,"
	        		+ "/auth/adminRegister","/auth/send-otp","/auth/validate-otp").permitAll().and().build();
	    }
//	 ,"/tourist-guides/**","/tours/**"

	
	@Bean
	public PasswordEncoder getPassword() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(getPassword());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager newAuthenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
}
