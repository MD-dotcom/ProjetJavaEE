package com.master.ips.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.master.ips.entities.Monument;



public interface MonumentRepository extends JpaRepository<Monument, String>{

	@Query("select m from Monument m where m.nomM like =:x")
	public List<Monument> findMonumentByNomM(@Param("x") String motCle);
	
	@Query("select m from Monument m where m.codeM =:x")
	public Monument findMonumentByCodeM(@Param("x") String codeM);
	
}
