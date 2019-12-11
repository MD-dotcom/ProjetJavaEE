package com.master.ips.web;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.master.ips.dao.CelebriteRepository;
import com.master.ips.dao.DepartementRepository;
import com.master.ips.dao.LieuRepository;
import com.master.ips.dao.MonumentRepository;
import com.master.ips.entities.Celebrite;
import com.master.ips.entities.Departement;
import com.master.ips.entities.Lieu;
import com.master.ips.entities.Monument;
import com.master.ips.metier.IMetier;



@Controller
public class DescriptionController {
	@Autowired(required=false)
	private Monument mon;
	@Autowired
	private MonumentRepository monument;
	@Autowired
	private LieuRepository lieu;
	@Autowired
	private DepartementRepository departement;
	@Autowired
	private CelebriteRepository celebrite;
	@Autowired(required=false)
	private IMetier metier;
	
	
	@RequestMapping(value="/")
	public String index(){
		return "about";
	}
	
	@RequestMapping(value="/about")
	public String about(){
		return "about";
	}
	@RequestMapping(value="/inscrire")
	public String inscrire(){
		return "inscrire";
	}
	
	//@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
	@RequestMapping(value="/home")
	public String home(){
		return "template1";
	}
	
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})	
	@RequestMapping(value="/gererMonument")
	public String gererMonument(){
		return "gererMonument";
	
	}
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})	
	@RequestMapping(value="/gererDepartement")
	public String gererDepartement(){
		return "gererDepartement";
	
	}
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})	
	@RequestMapping(value="/gererCelebrite")
	public String gererCelebrite(){
		return "gererCelebrite";
	
	}
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
	@RequestMapping(value="/formMonument",method=RequestMethod.GET)

	public String formMonument(Model model) {
		model.addAttribute("monument", new Monument());
		return "formMonument";
	}
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
	@RequestMapping(value="/formDepartement",method=RequestMethod.GET)
     public String formDepartement(Model model) {
		model.addAttribute("departement", new Departement());
		return "formDepartement";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/addCelebriteToMonument",method=RequestMethod.POST)
	
	public String addCelebriteToMonument(@Valid Integer numCelebrite, String codeM){
		metier.addCelebriteToMonument(numCelebrite, codeM);
		return "success";
	}
	@Secured("ROLE_ADMIN")
    @RequestMapping(value="/saveDepartement",method=RequestMethod.POST)
	public String saveDepartement(@Valid Departement d){
		departement.save(d);	
		return "success";
	}
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
	@RequestMapping(value="/listMonument")
	
	public String listMonument(Model model){   //dans le model de spring ,on stocke les resultat quon veut afficher
		try {
		List<Monument> monuments=metier.listMonument();
		model.addAttribute("monument", monuments);//avant d'aller a la vue j'ai stocker la listes recuperer dans le model dans un attribut que j'ai cree (monument)
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "listMonument";
	
	} 
	
	    @Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
		@RequestMapping(value="/listDepartement")
		public String listDepartement(Model model){   //dans le model de spring ,on stocke les resultat quon veut afficher
			try {
			List<Departement> departements=metier.listDepartements();
			model.addAttribute("departement", departements);//avant d'aller a la vue j'ai stocker la listes recuperer dans le model dans un attribut que j'ai cree (monument)
			} catch (Exception e) {
				model.addAttribute("exception", e);
			}
			return "listDepartement";
		
		} 
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
	@RequestMapping(value="/chercherCelebrite")
	
	public String chercherCelebrite(Model model, String mc) {
		 
			List<Celebrite> listeCelebrite= metier.chercherCelebrites("%"+mc+"%");
				
		 model.addAttribute("chercherCelebrite",listeCelebrite);
		 
		 return "chercherCelebrite";
	}
	/*
	@RequestMapping(value="/chercherMonument")
	public String chercherMonument(Model model, String mc) {
		 
			List<Monument> listeMonument= metier.findMonumentByNomM("%"+mc+"%");
				
		 model.addAttribute("chercherMonument",listeMonument);
		System.out.println("c fait **************"); 
		 return "chercherMonument";
	}
	*/
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
	@RequestMapping(value="/chercherMonument")
	public String chercherMonument(Model model, String codeM) {
		 
			Monument monument= metier.findMonumentBycodeM(codeM);
				
		 model.addAttribute("chercherMonument",monument);
		System.out.println("c fait **************"); 
		 return "chercherMonument";
	}
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
	@RequestMapping(value="/chercherDepartement")
	public String chercherDepartement(Model model, String dep) {
		 Departement departement= metier.findDepartementByDep(dep);
		 model.addAttribute("chercherDepartement",departement);
		
		 return "chercherDepartement";
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/deleteMonument")
	
	public String delete(@RequestParam String codeM) {
		
		Monument mon = monument.getOne(codeM);
		monument.delete(mon);
		 return "success";
	}
	
	    @Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value="/deleteDepartement")
		public String deleteDepartement(@RequestParam String dep) {
			
			Departement d = departement.getOne(dep);
			departement.delete(d);
			 return "success";
		}
    @Secured(value={"ROLE_ADMIN"})
    @RequestMapping(value="/distance")
    public String distance(@RequestParam String codeMA,@RequestParam String codeMB ) {
		metier.getDistanceBetweenMonuments(codeMA, codeMB);
		 return "distance";
	}

	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
	@RequestMapping(value="/formUpdateMonument", method=RequestMethod.GET)
	public String formUpdateMonument(String codeM, Model model) {
		Monument mon=metier.findMonumentBycodeM(codeM);
	//	model.addAttribute("updateMonument", new Monument());
		model.addAttribute("Monument", mon);
		return "updateMonument";
	}
	
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
		@RequestMapping(value="/formUpdateDepartement", method=RequestMethod.GET)
		public String formUpdateDepartement(String dep, Model model) {
			Departement d=metier.findDepartementByDep(dep);
			model.addAttribute("departement", d);
			return "updateDepartement";
		}
	
	//bealdung
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/updateMonument/{codeM}",method=RequestMethod.POST)
	
	public String updateMonument(@Valid Monument mon ){
		
		 monument.save(mon);
		 
		 return "success";
	}
	
	@Secured("ROLE_ADMIN")
		@RequestMapping(value="/updateDepartement/{dep}",method=RequestMethod.POST)
		
		public String updateDepartement(@Valid Departement d ){
			
		   departement.save(d);
			 
			 return "success";
			}
	/*
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
	@RequestMapping(value = "/listCelebriteAssocieMonument")
	public String listcelebriteAssocieMonument(Model model, String codeM) {
		try {
			Collection<Celebrite> listcele = metier.getlistCelebritetByMon(codeM);
			model.addAttribute("listc", listcele);

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "ListCelebriteAssocieMonument";
	}
	*/
	
	/*
	@Secured(value={"ROLE_ADMIN","ROLE_TOURISTE"})
	@RequestMapping(value="/consulterMonument")
	public String consulterMonument(Model model, String codeM){
		
		Monument mon = monument.getOne(codeM);
		model.addAttribute("monument", mon);
		//System.out.println("***********************" +monument.getOne(codeM));
		
		return "consulterMonument";
			
	} 
	*/
	




}
