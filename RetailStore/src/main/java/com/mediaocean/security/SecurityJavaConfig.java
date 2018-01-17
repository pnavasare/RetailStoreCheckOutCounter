package com.mediaocean.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

 
@Configuration
@EnableWebSecurity
@ComponentScan("com.mediaocean")
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
 
 private static String REALM = "EXAMPLE_REALM";
 
 @Autowired
 public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
 auth.inMemoryAuthentication().withUser("priya").password("smile").roles("USER");
 auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
 }
 
 @Override
 protected void configure(HttpSecurity http) throws Exception {
 
 http.csrf().disable().authorizeRequests().antMatchers("/**").hasRole("USER").and().httpBasic()
 .realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
 // No need session.
 .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 }
 
 @Bean
 public RestAuthenticationEntryPoint getBasicAuthEntryPoint() {
 return new RestAuthenticationEntryPoint();
 }
 
 /* To allow Pre-flight [OPTIONS] request from browser */
 @Override
 public void configure(WebSecurity web) throws Exception {
 web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
 }
}
