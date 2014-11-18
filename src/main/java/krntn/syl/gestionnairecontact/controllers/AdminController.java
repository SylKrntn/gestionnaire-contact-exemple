package krntn.syl.gestionnairecontact.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
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
	public String saveUser(@Valid User u, BindingResult bindingResult, HttpServletRequest req, Model model) {
		logger.info("/admin/saveUser");
		
		System.out.println(u.toString());
		System.out.println(req.getParameter("ROLE_USER"));
		System.out.println(req.getParameter("ROLE_ADMIN"));
		
		// Vérifie s'il y a des erreurs dans les champs...
		if (bindingResult.hasErrors()) {
			model.addAttribute("users", dao.getUsers());
			return "adminUsers";// retourne la vue et affiche les erreurs
		}
		
		String userRole = req.getParameter("ROLE_USER");
		String adminRole = req.getParameter("ROLE_ADMIN");
		
		currentUser = getAuthenticatedUser();
		
		user = dao.findUserByName((String)currentUser.getUsername());
		
//		u.setRoles(new ArrayList<Role>());
//		Collection<Role> roless = u.getRoles();
//		Iterator its = roless.iterator();
//		while (its.hasNext()) {
//			Role rolee = (Role) its.next();
//			System.out.println(rolee);
//			System.out.println(rolee.toString());
//			System.out.println(rolee.getId());
//			System.out.println(rolee.getNom());
//		}
		
		// TODO: factoriser le code et améliorer l'algorithme de mise à jour des données de l'utilisateur
		// Si l'utilisateur a un ID, c'est une mise à jour
		if (u.getId() != null ) {
			Collection<Role> rolesToDelete = new ArrayList<Role>();// tableau qui contiendra les rôles à supprimer
			User oldUser = dao.findUser(u.getId());// contient les anciennes données de l'utilisateur
			Collection<Role> oldRoles = oldUser.getRoles();// contient les anciens rôles de l'utilisateur
			oldUser = null;
			
			u.setRoles(oldRoles);// affecte les anciens rôles à l'utilisateur
			
			// Si ROLE_USER est coché
			if (userRole != null) {
				System.out.println("userRole = on");
				boolean checked = false;
				
				// Si l'utilisateur avait un ou plusieurs rôles,
				// on vérifie s'il avait déjà le rôle ROLE_USER
				if (oldRoles != null) {
					Iterator<Role> it = oldRoles.iterator();
					while (it.hasNext()) {
						Role role = (Role) it.next();
						if (role.getNom().equals("ROLE_USER")) {
							System.out.println("userRole coché et ROLE_USER existe déjà");
							checked = true;// confirme que l'utilisateur avait déjà le rôle ROLE_USER
						}
					}
				}
				// Si l'utilisateur n'avait pas déjà ce rôle, on le lui ajoute
				if (checked == false) {
					System.out.println("ajoute ROLE_USER à l'utilisateur");
					u.getRoles().add(new Role("ROLE_USER"));
				}
			}
			// Si ROLE_USER n'est pas coché
			else {
				// Si l'utilisateur avait un ou plusieurs rôles,
				// on vérifie s'il avait déjà le rôle ROLE_USER
				if (oldRoles != null) {
					Iterator<Role> it = oldRoles.iterator();
					while (it.hasNext()) {
						Role role = (Role) it.next();
						// Si l'utilisateur avait déjà ce rôle,
						// on le marque comme étant à supprimer
						if (role.getNom().equals("ROLE_USER")) {
							rolesToDelete.add(role);
						}
					}
				}
			}
			
			// Si ROLE_ADMIN est coché (même logique que précédemment)
			if (adminRole != null) {
				System.out.println("adminRole = on");
				boolean checked = false;
				if (oldRoles != null) {
					Iterator<Role> it = oldRoles.iterator();
					while (it.hasNext()) {
						Role role = (Role) it.next();
						if (role.getNom().equals("ROLE_ADMIN")) {
							System.out.println("adminRole coché et ROLE_ADMIN existe déjà");
							checked = true;
						}
					}
				}
				
				if (checked == false) {
					System.out.println("ajoute ROLE_ADMIN à l'utilisateur");
					u.getRoles().add(new Role("ROLE_ADMIN"));
				}
			}
			// Si ROLE_ADMIN n'est pas coché
			else {
				if (oldRoles != null) {
					Iterator<Role> it = oldRoles.iterator();
					while (it.hasNext()) {
						Role role = (Role) it.next();
						if (role.getNom().equals("ROLE_ADMIN")) {
							rolesToDelete.add(role);
						}
					}
				}
			}
			
			// Avant de mettre à jour les données de l'utilisateur,
			// on vérifie s'il y a des rôles à supprimer et on les supprime
			Iterator<Role> rolesToDeleteIter = rolesToDelete.iterator();
			while (rolesToDeleteIter.hasNext()) {
				Role role = (Role) rolesToDeleteIter.next();
				u.getRoles().remove(role);
			}
			
			dao.updateUser(u);// met à jour l'utilisateur en BDD avec ses nouvelles données
			
			// Supprime les rôles qui ne sont plus associés à l'utilisateur dans la BDD
			rolesToDeleteIter = rolesToDelete.iterator();
			while (rolesToDeleteIter.hasNext()) {
				Role role = (Role) rolesToDeleteIter.next();
				dao.removeRole(role.getId());
			}
			
		}
		// Si l'utilisateur n'a pas d'ID, c'est un nouvel utilisateur
		else {
			dao.addUser(u);// enregistre le nouvel utilisateur en BDD
			
			// Affecte les rôles à l'utilisateur
			// Si ROLE_USER est coché, on enregistre ce rôle et on l'ajoute à l'utilisateur
			if (userRole != null) {
				int roleId = dao.addRole(new Role("ROLE_USER"));
				Role role = dao.findRole(roleId);
				dao.setUserRole(role, u.getId());
			}
			//Si ROLE_ADMIN est coché, on enregistre ce rôle et on l'ajoute à l'utilisateur
			if (adminRole != null) {
				int roleId = dao.addRole(new Role("ROLE_ADMIN"));
				Role role = dao.findRole(roleId);
				dao.setUserRole(role, u.getId());
			}
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
		Iterator<Role> it = roles.iterator();
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
