package com.master.ips.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//cette class c'est pour autoriser le retour des vues

@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	@Override
public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/login").setViewName("login");
	registry.addViewController("/logout").setViewName("login");
	
	}
	
}
