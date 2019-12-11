package com.master.ips.metier;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.lucene.util.SloppyMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.master.ips.dao.CelebriteRepository;
import com.master.ips.dao.DepartementRepository;
import com.master.ips.dao.LieuRepository;
import com.master.ips.dao.MonumentRepository;
import com.master.ips.entities.Celebrite;
import com.master.ips.entities.Departement;
import com.master.ips.entities.Lieu;
import com.master.ips.entities.Monument;

@Service
@Transactional
public class MetierImpl implements IMetier{
	@Autowired
	private MonumentRepository monumentRepository;
	@Autowired
	private DepartementRepository departementRepository;
	@Autowired
	private CelebriteRepository celebriteRepository;
	@Autowired
	private LieuRepository lieuRepository;
	
  
   @Override
   public List<Monument> findMonumentByNomM(String motCle){
	   return monumentRepository.findMonumentByNomM(motCle);
   }
   
	@Override
	public Monument getMonument(String codeM) {
		return monumentRepository.getOne(codeM);
	}
	
	@Override
	public void addMonument(Monument m) {
		monumentRepository.save(m);
		
	}
	
	
	@Override
	public void addMonument(Monument m, String FK_CodeInsee) {
		Set<Monument> monuments = new HashSet<>();
        Lieu lieu1 = new Lieu();

        Optional<Lieu> byId = lieuRepository.findById(FK_CodeInsee);
        if (!byId.isPresent()) {
          System.out.println("ce lieu n'existe pas");
        }
        Lieu lieu = byId.get();

        //tie Author to monument
        m.setLieu(lieu);

        Monument monument1 =monumentRepository.save(m);
        //tie monument to lieu
        monuments.add(monument1);
        lieu1.setMonuments(monuments);
       
	}


	@Override
	public List<Monument> listMonument() {
		List<Monument> monuments= monumentRepository.findAll();
		return monuments;
	}

	
	@Override
	public void deleteMonument(String codeM) {
		Monument monument=monumentRepository.getOne(codeM);
		monumentRepository.delete(monument);
		
	}

	@Override
	public Monument updateMonument(Monument n) {
		return n;
		
		
	}

	@Override
	public void addMonumentToLieu(String codeM, String codeInsee) {
		Monument m=monumentRepository.getOne(codeM);
		Lieu l=lieuRepository.getOne(codeInsee);
		l.getMonuments().add(m);
		
	}

	@Override
	public float getDistanceBetweenMonuments(String codeMonA, String codeMonB) {
		    float latMa = monumentRepository.getOne(codeMonA).getLatitude();
			float longMa=monumentRepository.getOne(codeMonA).getLongitude();
			float latMb=monumentRepository.getOne(codeMonB).getLatitude();
			float longiMb = monumentRepository.getOne(codeMonB).getLongitude();
		    float dist= (float) SloppyMath.haversinKilometers(latMa, longMa, latMb, longiMb);
			return dist;
		}

	@Override
	public void addDepartement(Departement d) {
		departementRepository.save(d);
		
	}

	@Override
	public List<Departement> listDepartements() {
		List<Departement> departements= departementRepository.findAll();
		return departements;
	}

	
	@Override
	public void deletedep(String dep) {
		Departement departement=departementRepository.getOne(dep);
		departementRepository.delete(departement);
		
	}

	@Override
	public void updateDepartement(Departement d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCelebrite(Celebrite c) {
		celebriteRepository.save(c);
				
	}

	@Override
	public void deleteCelebrite(Integer numCelebrite) {
		Celebrite celebrite=celebriteRepository.getOne(numCelebrite);
		celebriteRepository.delete(celebrite);
		
	}

	@Override
	public void updateCelebrite(Celebrite c) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addCelebriteToMonument(Integer numCelebrite, String codeM) {
		Monument mo=monumentRepository.getOne(codeM);
		Celebrite c =celebriteRepository.getOne(numCelebrite);
		mo.getCelebrites().add(c);
		
	}

	@Override
	public void addLieu(Lieu l) {
		lieuRepository.save(l);
		
	}

	@Override
	public List<Lieu> listLieux() {
		List<Lieu> lieux= lieuRepository.findAll();
		return lieux;
	}


	@Override
	public void deleteLieu(String l) {
		Lieu lieu=lieuRepository.getOne(l);
		lieuRepository.delete(lieu);
		
		
	}

	@Override
	public void updateLieu(Lieu l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Monument findMonumentBycodeM(String codeM) {
		return monumentRepository.findMonumentByCodeM(codeM);
	}

	@Override
	public Departement findDepartementByDep(String dep) {
		return departementRepository.findDepartementByDep(dep);
	}

	@Override
	public List<Celebrite> listCelebrites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Celebrite> chercherCelebrites(String string) {
		List<Celebrite> celebrites= celebriteRepository.findAll();
		return celebrites;
	}

	@Override
	public Collection getlistCelebritetByMon(String codeM) {
		Monument m=monumentRepository.findMonumentByCodeM(codeM);
		return m.getCelebrites();
	
	}

	
	
}
