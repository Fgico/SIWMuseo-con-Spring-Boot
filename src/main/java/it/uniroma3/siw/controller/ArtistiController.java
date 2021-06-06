package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.ArtistiService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.validator.ArtistaValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class ArtistiController {
	@Autowired
	private ArtistiService artistServ;
	
	@Autowired
	private ArtistaValidator artValid;
	
	@RequestMapping(value="/artisti", method= RequestMethod.GET)
	public String tuttiArtisti(Model model) {
		model.addAttribute("artisti", artistServ.getAll());
		return "artisti";
	}
	
	@RequestMapping(value="/artisti/{id}", method= RequestMethod.GET)
	public String artistaPerId(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", artistServ.getArtista(id));
		return "artista";
	}
	
	@RequestMapping(value="/admin/delete/artista/{id}", method= RequestMethod.GET)
	public String eliminaArtista(@PathVariable("id") Long id, Model model) {
		artistServ.elimina(id);
		model.addAttribute(artistServ.getAll());
		return "artisti";
	}
	
	@RequestMapping(value="/admin/addartist", method= RequestMethod.GET)
	public String addArtist (Model model) {
		model.addAttribute("artista", new Artista());
		return "creaArtista";
	}
	
	@RequestMapping(value="/admin/addartist", method= RequestMethod.POST)
	public String addArtistFinalize(@ModelAttribute("artista") Artista artista,Model model,BindingResult bindingResult) {
		artValid.validate(artista, bindingResult);
		if(!bindingResult.hasErrors()) {
			artistServ.salva(artista);
			model.addAttribute("artisti",artistServ.getAll());
			return "artisti";
		}
		return "creaArtista";
	}


}
