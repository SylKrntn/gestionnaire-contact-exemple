package krntn.syl.gestionnairecontact;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import krntn.syl.gestionnairecontact.entities.Role;
import krntn.syl.gestionnairecontact.entities.User;
import krntn.syl.gestionnairecontact.metier.dao.IAdminDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IAdminDAO dao;
	
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Page de connexion");
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("user", new User());
		return "home";
	}
	
	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public String createAccount(@Valid User u, BindingResult bindingResult, Model model) {
		logger.info("Page de création de compte");
		
		// Vérifie s'il y a des erreurs dans les propriétés de l'entité User
		if (bindingResult.hasErrors()) {
			model.addAttribute("appName", "Gestionnaire de contacts");
//			model.addAttribute("user", new User());
			return "home";
		}
		
		// Vérifie si les mots de passe sont null et ne sont pas identiques
		if (u.getMdp() == null || u.getMdpConf() == null || !u.getMdp().equals(u.getMdpConf())) {
			model.addAttribute("appName", "Gestionnaire de contacts");
			model.addAttribute("user", new User());
			model.addAttribute("errorMessage", "Le mot de passe et le mot de passe de confirmation sont différents.");
			return "home";
		}
		
		u.setActived(true);// active directement l'utilisateur pour les tests
		dao.addUser(u);
		int roleId = dao.addRole(new Role("ROLE_USER"));
		Role role = dao.findRole(roleId);
		dao.setUserRole(role, u.getId());
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("user", new User());
		return "home";
	}
	
	@RequestMapping(value = "/connectToAccount", method = RequestMethod.POST)
	public String connectToAccount(@Valid User u, BindingResult bindingResult, Model model) {
		logger.info("Page de création de compte");
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("user", new User());
		return "home";
	}
}
