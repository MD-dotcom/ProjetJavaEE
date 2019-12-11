package com.master.ips.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class Monument  implements Serializable {
	@Id
	private String codeM; 
	private String nomM;
	private String proprietaire;
	private String typeMonument;
	private float longitude;
	private float latitude;
	
	@ManyToOne
	@JoinColumn(name="codeInsee")
	private Lieu lieu; 	
	
	 @ManyToMany
	 @JoinTable(name="AssocieA", joinColumns=
	 @JoinColumn(name="codeM"),
	 inverseJoinColumns=@JoinColumn(name="numCelebrite"))
     private List<Celebrite> celebrites;

	public Monument() {
		super();
	}

	public Monument(String codeM, String nomM, String proprietaire, String typeMonument, float longitude,
			float latitude, Lieu lieu) {
		super();
		this.codeM = codeM;
		this.nomM = nomM;
		this.proprietaire = proprietaire;
		this.typeMonument = typeMonument;
		this.longitude = longitude;
		this.latitude = latitude;
		this.lieu = lieu;
	}

	public String getCodeM() {
		return codeM;
	}

	public void setCodeM(String codeM) {
		this.codeM = codeM;
	}

	public String getNomM() {
		return nomM;
	}

	public void setNomM(String nomM) {
		this.nomM = nomM;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getTypeMonument() {
		return typeMonument;
	}

	public void setTypeMonument(String typeMonument) {
		this.typeMonument = typeMonument;
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

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public List<Celebrite> getCelebrites() {
		return celebrites;
	}

	public void setCelebrites(List<Celebrite> celebrites) {
		this.celebrites = celebrites;
	}

	
	}

	
	

