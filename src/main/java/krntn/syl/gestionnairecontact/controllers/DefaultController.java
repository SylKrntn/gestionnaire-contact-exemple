package krntn.syl.gestionnairecontact.controllers;

import javax.validation.Valid;

import krntn.syl.gestionnairecontact.HomeController;
import krntn.syl.gestionnairecontact.entities.Contact;
import krntn.syl.gestionnairecontact.entities.User;
import krntn.syl.gestionnairecontact.metier.dao.IContactDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/defaut")
public class DefaultController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IContactDAO dao;
	
	Authentication auth = null;
	UserDetails currentUser = null;
	User user = null;
	
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String contacts(Model model) {
		logger.info("liste des contacts de l'utilisateur connecté");
		
		currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();;
		System.out.println(currentUser.getUsername());
		System.out.println(currentUser.getPassword());
		user = dao.findUserByName((String)currentUser.getUsername());
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", dao.getUserContacts(user.getId()));
		return "contacts";
	}
	
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public String saveContact(@Valid Contact c, BindingResult bindingResult, Model model) {
		logger.info("liste des contacts de l'utilisateur connecté");
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacts", dao.getUserContacts(user.getId()));
			return "contacts";
		}
		
		currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user = dao.findUserByName((String)currentUser.getUsername());
		
		c.setUser(user);
		dao.addContact(c);
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", dao.getUserContacts(user.getId()));
		return "/contacts";
	}
}
