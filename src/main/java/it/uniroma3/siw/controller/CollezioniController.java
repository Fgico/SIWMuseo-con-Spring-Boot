package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.validator.CollezioneValidator;

@Controller
public class CollezioniController {
	
	@Autowired
	private CollezioneService collServ;
	
	@Autowired
	private CollezioneValidator collVal;
	
	@RequestMapping(value="/collezioni", method= RequestMethod.GET)
	public String tutteCollezioni(Model model) {
		model.addAttribute("collezioni", collServ.tutti());
		return "collezioni";
	}
	
	@RequestMapping(value="/collezioni/{id}", method= RequestMethod.GET)
	public String collezioniPerId(@PathVariable("id") Long id, Model model) {
		model.addAttribute("collezione", collServ.prendi(id));
		return "collezione";
	}
	
	@RequestMapping(value="/admin/delete/collezione/{id}", method= RequestMethod.GET)
	public String eliminaCollezione(@PathVariable("id") Long id, Model model) {
		collServ.elimina(id);
		model.addAttribute(collServ.tutti());
		return "collezioni";
	}
	
	@RequestMapping(value="/admin/addcoll", method= RequestMethod.GET)
	public String addArtist (Model model) {
		model.addAttribute("collezione", new Collezione());
		return "creaCollezione";
	}
	
	@RequestMapping(value="/admin/addcoll", method= RequestMethod.POST)
	public String addArtistFinalize(@ModelAttribute("artista") Collezione c,Model model,BindingResult bindingResult) {
		collVal.validate(c, bindingResult);
		if(!bindingResult.hasErrors()) {
			collServ.metti(c);
			return "collezioni";
		}
		return "creaCollezione";
	}
}
