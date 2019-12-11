package com.master.ips.metier;

import java.util.Collection;
import java.util.List;

import com.master.ips.entities.Celebrite;
import com.master.ips.entities.Departement;
import com.master.ips.entities.Lieu;
import com.master.ips.entities.Monument;

public interface IMetier {
	
	//monument
	public Monument findMonumentBycodeM(String codeM);
	public Monument getMonument(String codeM);
	public void addMonument(Monument m);
	public void addMonument(Monument m,String FK_CodeInsee);
	public List<Monument> listMonument();
	public List<Monument> findMonumentByNomM(String motCle);
	public void deleteMonument(String codeM);
	public Monument updateMonument(Monument n);
	public void addMonumentToLieu(String codeM, String codeInsee);
	public float getDistanceBetweenMonuments(String nomMonA,String nomMonB);
	
	
	//departement
	public Departement findDepartementByDep(String dep);
	public void addDepartement(Departement d);
	public List<Departement> listDepartements();
	public void deletedep(String dep);
	public void updateDepartement(Departement d);
	
	//celebrite
	public void addCelebrite(Celebrite c);
	public List<Celebrite> listCelebrites();
	public void deleteCelebrite(Integer numCelebrite);
	public void updateCelebrite(Celebrite c);
	public void addCelebriteToMonument(Integer numCelebrite, String codeM);
	public List<Celebrite> chercherCelebrites(String string);
	public Collection getlistCelebritetByMon(String codeM);
	
	//lieu
	public void addLieu(Lieu l);
	public List<Lieu> listLieux();
	public void deleteLieu(String l);
	public void updateLieu(Lieu l);
	;
	
	
	
	
	

}
