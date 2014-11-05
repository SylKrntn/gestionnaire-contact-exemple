package krntn.syl.gestionnairecontact.controllers;

import javax.validation.Valid;

import krntn.syl.gestionnairecontact.HomeController;
import krntn.syl.gestionnairecontact.entities.Contact;
import krntn.syl.gestionnairecontact.entities.User;
import krntn.syl.gestionnairecontact.metier.dao.IContactDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;

import com.sun.org.apache.regexp.internal.recompile;

@Controller
@RequestMapping(value="/defaut")
public class DefaultController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private final int USER_ID = 1;
	
	@Autowired
	private IContactDAO dao;
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String contacts(Model model) {
		logger.info("liste des contacts de l'utilisateur connecté");
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", dao.getUserContacts(USER_ID));
		return "contacts";
	}
	
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public String saveContact(@Valid Contact c, BindingResult bindingResult, Model model) {
		logger.info("liste des contacts de l'utilisateur connecté");
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacts", dao.getUserContacts(USER_ID));
			return "contacts";
		}
		
		User user = dao.findUser(USER_ID);
		c.setUser(user);
		dao.addContact(c);
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", dao.getUserContacts(USER_ID));
		return "/contacts";
	}
}
