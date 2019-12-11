package com.master.ips.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.master.ips.entities.Departement;


public interface DepartementRepository extends JpaRepository<Departement, String>{

	@Query("select d from Departement d where d.dep =:x")
	public Departement findDepartementByDep(@Param("x") String dep);
}
