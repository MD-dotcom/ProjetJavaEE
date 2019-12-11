package com.master.ips.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.master.ips.JeeIpsApplication;

public class WebInitializer extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder
	builder) {
	return builder.sources(JeeIpsApplication.class);
	}
	}
