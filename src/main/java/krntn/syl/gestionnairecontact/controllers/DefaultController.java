package krntn.syl.gestionnairecontact.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.validation.Valid;

import krntn.syl.gestionnairecontact.HomeController;
import krntn.syl.gestionnairecontact.entities.Contact;
import krntn.syl.gestionnairecontact.entities.Role;
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
	
	/**
	 * 
	 * @param model
	 * @return la vue
	 */
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String contacts(Model model) {
		logger.info("liste des contacts de l'utilisateur connecté");
		
//		currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();;
//		System.out.println(currentUser.getUsername());
//		System.out.println(currentUser.getPassword());
		currentUser = getAuthenticatedUser();
		user = dao.findUserByName((String)currentUser.getUsername());
		
		Iterator<Role> it = user.getRoles().iterator();
		while (it.hasNext()) {
			System.out.println(it.next().getNom());
		}
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("privileges", user.getRoles());
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", dao.getUserContacts(user.getId()));
		return "contacts";
	}
	
	/**
	 * 
	 * @param c {Contact} : les données du contact à enregistrer
	 * @param bindingResult {BindingResult} : vérifie la validité des données
	 * @param model {Model} : 
	 * @return la vue et son modèle
	 */
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public String saveContact(@Valid Contact c, BindingResult bindingResult, Model model) {
		logger.info("liste des contacts de l'utilisateur connecté");
		System.out.println(c.toString());
		// Vérifie s'il y a des erreurs dans les champs...
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacts", dao.getUserContacts(user.getId()));
			return "contacts";// retourne la vue et affiche les erreurs
		}
		
		// Récupère l'utilisateur connecté
//		currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		currentUser = getAuthenticatedUser();
		user = dao.findUserByName((String)currentUser.getUsername());
		
		if (user != null ) {
			c.setUser(user);// affecte l'utilisateur au contact (= le contact appartient à l'utilisateur connecté)
		}
		
		// Si le contact a un ID, il existe déjà en BDD, donc c'est une modification
		if (c.getId() != null) {
			dao.updateContact(c);
		}
		// Sinon, c'est un nouveau contact et on l'ajoute en BDD
		else {
			dao.addContact(c);// enregistre le contact en BDD
		}
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", dao.getUserContacts(user.getId()));
		return "/contacts";
	}
	
	/**
	 * Edite un contact
	 * @param id {Integer} : identifiant du contact
	 * @param model {Model} : 
	 * @return la vue et son modèle
	 */
	@RequestMapping(value="/editContact", method = RequestMethod.GET)
	public String editContact(Integer id, Model model) {
		
//		currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		currentUser = getAuthenticatedUser();
		user = dao.findUserByName((String)currentUser.getUsername());
		System.out.println("id contact " + id);
		System.out.println("id user " + user.getId());
		
		Contact c = dao.findUserContact(id, user.getId());
		System.out.println("c.id " + c.getId());
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("contact", c);
		model.addAttribute("contacts", dao.getUserContacts(user.getId()));
		return "contacts";
	}
	
	/**
	 * Supprime un contact
	 * @param id {Integer} : identifiant du contact
	 * @param model {Model} : 
	 * @return la vue et son modèle
	 */
	@RequestMapping(value="/delContact", method = RequestMethod.GET)
	public String delContact(Integer id, Model model) {
		
//		currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		currentUser = getAuthenticatedUser();
		user = dao.findUserByName((String)currentUser.getUsername());
		System.out.println("id contact " + id);
		System.out.println("id user " + user.getId());
		
		dao.removeContact(id);
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", dao.getUserContacts(user.getId()));
		return "contacts";
	}
	
	private UserDetails getAuthenticatedUser() {
		UserDetails authenticatedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(authenticatedUser.getUsername());
		System.out.println(authenticatedUser.getPassword());
		return authenticatedUser;
	}
}
