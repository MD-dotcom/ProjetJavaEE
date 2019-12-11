package com.master.ips.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true)
		//prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) //activer la sécurité globale des méthodes
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    protected void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder encoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	
    	//pour ajouter des users dans la memoire et non pas dans la base
        auth.inMemoryAuthentication()
        	.withUser("malika")
        	.password(encoder.encode("123"))
        	.roles("ADMIN")
        	.and();
        auth.inMemoryAuthentication()
    		.withUser("nadir")
    		.password(encoder.encode("123"))
    		.roles("TOURISTE")
    		.and();
 
    }
    	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.csrf().disable()
      		.authorizeRequests()
      			.antMatchers("/css/**", "/js/**","/bootstrap/**").permitAll()
    				// .authenticated()
    					.and()
    					
            .formLogin()
            	//.loginPage("/login")
            .defaultSuccessUrl("/home")
            	.failureUrl("/login")
            	.and()
       .logout()
       		.invalidateHttpSession(true)
       		//.logoutUrl("/logout")
       		.permitAll()
    		.and();
    	//donner les droits	
    //	http.authorizeRequests().antMatchers("/gererMonument").hasRole("TOURISTE");
  
    			}
  }