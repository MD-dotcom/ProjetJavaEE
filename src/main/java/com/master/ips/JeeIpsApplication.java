package com.master.ips;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.master.ips.dao.MonumentRepository;
import com.master.ips.entities.Monument;
import com.master.ips.metier.IMetier;


@SpringBootApplication
public class JeeIpsApplication implements CommandLineRunner {
	@Autowired
	private MonumentRepository monument;
	
	public static void main(String[] args) {
		SpringApplication.run(JeeIpsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Monument c2 = monument.save(new Monument("mon2", "mon1", "mon1", "mon1", 34f, 45f))	;	
		Monument c3 = monument.save(new Monument("mon3", "mon1", "mon1", "mon1", 34f, 45f))	;	
		Monument c4 = monument.save(new Monument("mon4", "mon1", "mon1", "mon1", 34f, 45f))	;	
	*/
	}
}
