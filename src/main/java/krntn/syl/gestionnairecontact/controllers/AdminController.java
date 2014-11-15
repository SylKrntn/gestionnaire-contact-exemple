package krntn.syl.gestionnairecontact.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.validation.Valid;

import krntn.syl.gestionnairecontact.entities.Contact;
import krntn.syl.gestionnairecontact.entities.Role;
import krntn.syl.gestionnairecontact.entities.User;
import krntn.syl.gestionnairecontact.metier.dao.IAdminDAO;

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
@RequestMapping(value="/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private IAdminDAO dao;
	
	Authentication auth = null;
	UserDetails currentUser = null;
	User user = null;
	
	/**
	 * Affiche la page d'accueil de l'espace admin
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/accueil", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("/admin/accueil");
		
		
		currentUser = getAuthenticatedUser();
		
		user = dao.findUserByName((String)currentUser.getUsername());
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		return "adminHome";
	}
	
	/**
	 * Affiche la page d'administration des utilisateurs
	 * @param model
	 * @return la vue et son modèle
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String user(Model model) {
		logger.info("/admin/users");
		
		currentUser = getAuthenticatedUser();
		
		user = dao.findUserByName((String)currentUser.getUsername());
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("user", new User());
		model.addAttribute("users", dao.getUsers());
		return "adminUsers";
	}
	
	/**
	 * Enregistre ou met à jour un utilisateur en BDD
	 * @param u {User} : l'utilisateur à enregistrer en BDD
	 * @param bindingResult
	 * @param model
	 * @return la vue et son modèle
	 */
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@Valid User u, BindingResult bindingResult, Model model) {
		logger.info("/admin/saveUser");
		
		System.out.println(u.toString());
		
		User oldUser = dao.findUser(u.getId());
		u.setRoles(oldUser.getRoles());
		
		Collection<Role> roles = u.getRoles();
		Iterator it = roles.iterator();
		while (it.hasNext()) {
			Role role = (Role) it.next();
			System.out.println(role.getId());
			System.out.println(role.getNom());
		}
		
		// Vérifie s'il y a des erreurs dans les champs...
		if (bindingResult.hasErrors()) {
			model.addAttribute("users", dao.getUsers());
			return "adminUsers";// retourne la vue et affiche les erreurs
		}
		
		currentUser = getAuthenticatedUser();
		
		user = dao.findUserByName((String)currentUser.getUsername());
		
		if (u.getId() != null ) {
//			Collection<Role> roles = new ArrayList<Role>();
//			Iterator it = u.getRoles().iterator();
//			while (it.hasNext()) {
//				Role role = (Role) it.next();
//				System.out.println(role);
//				System.out.println(role.getId());
//				System.out.println(role.getNom());
//				System.out.println("nom rôle " + role.getNom());
//				roles.add(role);
//			}
//			u.setRoles(roles);
			dao.updateUser(u);
		}
		else {
//			u.setRoles(new ArrayList<Role>());
			dao.addUser(u);
		}
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("user", new User());
		model.addAttribute("users", dao.getUsers());
		return "adminUsers";
	}
	
	/**
	 * Edite les données d'un utilisateur
	 * @param id {Integer} : identifiant de l'utilisateur à éditer
	 * @param model
	 * @return la vue et son modèle
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public String editUser(Integer id, Model model) {
		logger.info("/admin/editUser");
		
		currentUser = getAuthenticatedUser();
		
		user = dao.findUserByName((String)currentUser.getUsername());
		
		User userToEdit = dao.findUser(id);
		Collection<Role> roles = userToEdit.getRoles();
		Iterator it = roles.iterator();
		while (it.hasNext()) {
			Role role = (Role) it.next();
			System.out.println(role.getId());
			System.out.println(role.getNom());
		}
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("user", userToEdit);
		model.addAttribute("users", dao.getUsers());
		return "adminUsers";
	}
	
	/**
	 * Supprime un utilisateur (et ses rôles associés)
	 * @param id {Integer} : identifiant de l'utilisateur
	 * @param model {Model} : 
	 * @return la vue et son modèle
	 */
	@RequestMapping(value="/delUser", method = RequestMethod.GET)
	public String delUser(Model model, Integer userId, Integer r1, Integer r2) {
		logger.info("/admin/delUser");
		
		currentUser = getAuthenticatedUser();
		
		user = dao.findUserByName((String)currentUser.getUsername());
		System.out.println("id user " + userId);
		System.out.println("id rôle " + r1);
		System.out.println("id rôle " + r2);
		System.out.println("id connectedUser " + user.getId());
		
//		if (r1 != null) {
//			dao.removeRole(r1);
//		}
//		if (r2 != null) {
//			dao.removeRole(r2);
//		}
		if (userId != null) {
			ArrayList<Contact> contacts = dao.getUserContacts(userId);
			if (contacts != null) {
				for (int i=contacts.size() - 1; i>=0; i--) {
					dao.removeContact(contacts.get(i).getId());
				}
			}
			dao.removeUser(userId);
		}
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("user", new User());
		model.addAttribute("users", dao.getUsers());
		return "adminUsers";
	}
	
	// TODO
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public String role(Integer id, Model model) {
		logger.info("liste des contacts de l'utilisateur connecté");
		
		currentUser = getAuthenticatedUser();
		
		user = dao.findUserByName((String)currentUser.getUsername());
		
		model.addAttribute("appName", "Gestionnaire de contacts");
		model.addAttribute("username", user.getLogin());
		model.addAttribute("user", dao.findUser(id));
		model.addAttribute("users", dao.getUsers());
		return "adminRoles";
	}
	
	/**
	 * récupère l'utilisateur authentifié et le renvoie
	 * @return l'utilisateur authentifié
	 */
	private UserDetails getAuthenticatedUser() {
		UserDetails authenticatedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(authenticatedUser.getUsername());
		System.out.println(authenticatedUser.getPassword());
		return authenticatedUser;
	}
	
}
