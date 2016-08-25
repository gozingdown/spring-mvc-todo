package com.zheng.springmvc.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.zheng.springmvc.security.CustomAuthenticationFailureHandler;
import com.zheng.springmvc.security.CustomAuthenticationProvider;
import com.zheng.springmvc.security.CustomAuthenticationSuccessHandler;

@Configuration
//http://docs.spring.io/spring-security/site/docs/3.2.7.RELEASE/reference/htmlsingle/#csrf-include-csrf-token
/*
 * If you are using Spring MVC <form:form> tag or Thymeleaf 2.1+, and you replace @EnableWebSecurity 
 * with @EnableWebMvcSecurity, the CsrfToken is automatically included for you (using the CsrfRequestDataValueProcessor).
 */
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
//	@Autowired
//	DaoAuthenticationProvider daoAuthenticationProvider;
	
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
    	//auth.authenticationProvider(daoAuthenticationProvider)...
    	//add a customized userDetailService
    	
    	/*
    	 * Option 1: add a custom authentication provider + UserDetailsService + salt + encoder
    	 * Option 2: user existing DaoAuthenticationProvider with custom UserDetailsService
    	 *  - create user entity
    	 *  - create user dao
    	 *  - create user daoimpl
    	 *  - create user service
    	 *  - create user serviceimpl
    	 *  - create custom UserDetailsService
    	 *  - create MSYQL table and insert a row for testing
    	 *  - change security configuration
    	 */
    	auth.authenticationProvider(customAuthenticationProvider);
   
//        auth.inMemoryAuthentication()
//        	.withUser("jack").password("pswd")
//        	.roles("USER")
//        	.and()
//        	.withUser("zheng").password("pswd")
//            .roles("USER", "ADMIN");
    	
    	//auth.authenticationProvider(new DaoAuthenticationProvider());
    	
    	
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//    	http.csrf().disable();
        http.authorizeRequests()
        		.antMatchers("/team").permitAll()
        		.antMatchers("/login","/register").permitAll()
                .antMatchers("/", "/*todo*/**", "/welcome","/api/**").access("hasRole('USER')")
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                //.defaultSuccessUrl("/welcome")
                //.failureUrl("/login")
                .failureHandler(authenticationFailureHandler);
    }
}
