package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;

@Controller
public class LogInController {

	@Autowired
	private CredentialsService credentialsService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String goHome(Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goHomeNoLabel(Model model) {
		return "home";
	}
	
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String seeInfo(Model model) {
		return "info";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String login (Model model) {
		return "loginForm";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "home";
	}
	
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        
//    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
//    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
//            return "admin/home";
//        }
        return "admin";
    }
	
}
