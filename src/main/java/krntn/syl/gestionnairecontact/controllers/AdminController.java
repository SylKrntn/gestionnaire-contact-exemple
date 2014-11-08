package krntn.syl.gestionnairecontact.controllers;

import krntn.syl.gestionnairecontact.HomeController;
import krntn.syl.gestionnairecontact.entities.Contact;
import krntn.syl.gestionnairecontact.metier.dao.IAdminDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private final int USER_ID = 5;
	
	@Autowired
	private IAdminDAO dao;
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String contacts(Model model) {
		logger.info("liste des contacts de l'utilisateur connecté");
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", dao.getUserContacts(USER_ID));
		return "contacts";
	}
}
