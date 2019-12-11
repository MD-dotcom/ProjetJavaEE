package com.master.ips.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/about")
	public String abouts(){
		
		return "about";
	}
	
	@RequestMapping(value="/home")
	public String home(){
		return "template1";
	}
	
	
	@RequestMapping(value="/monument")
	public String gererMonument(){
		return "gererMonument";
	
	}

	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formMonument(Model model) {
		model.addAttribute("monument", new Monument());
		return "formGestion";
	}
	
	@RequestMapping(value="/saveMonument",method=RequestMethod.POST)
	public String saveMonument(@Valid Monument m ){
		
		 monument.save(m);
				
		return "success";
	}
	
	@RequestMapping(value="/listMonument")
	public String listMonument(Model model){   //dans le model de spring ,on stocke les resultat quon veut afficher
		try {
		List<Monument> monuments=metier.listMonument();
		model.addAttribute("monument", monuments);//avant d'aller a la vue j'ai stocker la listes recuperer dans le model dans un attribut que j'ai cree (monument)
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "list";
	
	} 
	
	@RequestMapping(value="/chercherMonument",method=RequestMethod.GET)
	public String chercherMonument(Model model,String motCle) {
		 
			List<Monument> mon= metier.findMonumentByNomM("%"+motCle+"%");
				
		 model.addAttribute("chercherMonument",mon);
		 
		 System.out.println("donnee recuperee");
		 
		 return "chercherMonument";
	}
	
	
	@RequestMapping(value="/deleteMonument")
	public String delete(@RequestParam String codeM) {
		
		metier.deleteMonument(codeM);
		
		 //model.addAttribute("monument", new Monument());
		 //model.addAttribute("monuments", metier.listMonument());
		 
		 return "success";
	}

	@RequestMapping(value="/formUpdate", method=RequestMethod.GET)
	public String formUpdate(String codeM, Model model) {
		Monument mon=metier.findMonumentByCodeM(codeM);
	//	model.addAttribute("updateMonument", new Monument());
		model.addAttribute("Monument", mon);
		return "update";
	}
	
	//bealdung
	@RequestMapping(value="/updateMonument/{codeM}",method=RequestMethod.POST)
	public String updateMonument(@Valid Monument mon ){
		
		 monument.save(mon);
		 
		 return "success";
				
	
	}
	/*
	@RequestMapping(value="/consulterMonument",method=RequestMethod.GET)
	public String consulterMonument(String nomM, Model model){   //dans le model de spring ,on stocke les resultat quon veut afficher
		
			Monument monument=metier.getMonument(nomM);
			model.addAttribute("consulterMonument", monument);//avant d'aller a la vue j'ai stocker la listes recuperer dans le model dans un attribut que j'ai cree (monument)
			
			
			return "consulterMonument";
	} 
	
	@RequestMapping(value="/consulterMonument")
	public String consulterMonument(Model model, String codeM){
		
		Monument mon = metier.getMonument(codeM);
		model.addAttribute("monument", mon);
		System.out.println("*******************************************************************************" +monument.getOne(codeM));
		
		return "consulterMonument";
			
	} 
	/*
	@GetMapping("/update/{codeM}")
    public String showUpdateLieuForm(@PathVariable("codeM") String codeM, Model model) {
        Monument monument = metier.findMonumentByCodeM(codeM);
        model.addAttribute("monument", monument);
   
        return "Update";
    }
    
    //Le traitement de MAJ d'un lieu et sauvegarde en base (La requête de MAJ viens de la page Update-lieu)
    @PostMapping("/updateMonument/{codeM}")
    public String updateMonument(@Valid Monument monument, Model model) {
        
         metier.addMonument(monument);
         //model.addAttribute("users", userRepository.findAll());
            return "success";
    }
   

*/

		/*
	@RequestMapping("/listCelebrite")
	public String listCelebrite() {
			
		return "celebrites";
	
	
	@RequestMapping(value="/saveOperation", method=RequestMethod.POST)
	public String saveOperation(Model model, 
			String typeOperation, String codeCompte,
			double montant, String codeCompte2){
		
		try{
			if(typeOperation.equals("VERS")){
				banqueMetier.verser(codeCompte, montant);
			}
			else if(typeOperation.equals("RETR")){
				banqueMetier.retirer(codeCompte, montant);
			} 
			if (typeOperation.equals("VIR")){
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
		} catch(Exception e) {
			model.addAttribute("error", e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte+
					"&error="+e.getMessage();
		}
		
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
	*/


}
