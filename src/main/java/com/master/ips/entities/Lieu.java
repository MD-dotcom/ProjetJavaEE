package com.master.ips.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lieu implements Serializable{
	@Id
	private String codeInsee;
	private String nomCom;
	private float longitude;
	private float latitude;
	
	@ManyToOne
	@JoinColumn(name="dep") //FK
	private Departement departement; 
	
	@OneToMany(mappedBy="lieu", cascade={CascadeType.ALL})
	private Collection<Monument>Monuments;

	public Lieu() {
		super();
	}

	public Lieu(String codeInsee, String nomCom, float longitude, float latitude) {
		super();
		this.codeInsee = codeInsee;
		this.nomCom = nomCom;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getCodeInsee() {
		return codeInsee;
	}

	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}

	public String getNomCom() {
		return nomCom;
	}

	public void setNomCom(String nomCom) {
		this.nomCom = nomCom;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Collection<Monument> getMonuments() {
		return Monuments;
	}

	public void setMonuments(Collection<Monument> monuments) {
		Monuments = monuments;
	}
	
	
	

}
