package com.shubham.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
		
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
	   //	super.configure(auth);
		
		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups")
		.contextSource(contextsource()).passwordCompare().passwordEncoder(new LdapShaPasswordEncoder()).passwordAttribute("userPassword");
	}
	
	@Bean
	public  DefaultSpringSecurityContextSource contextsource() {
		
		return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389/"),
				"dc=springframework,dc=org");
	}
}
