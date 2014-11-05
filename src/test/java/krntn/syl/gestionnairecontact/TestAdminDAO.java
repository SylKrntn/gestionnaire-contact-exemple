package krntn.syl.gestionnairecontact;

import static org.junit.Assert.*;

import java.util.ArrayList;

import krntn.syl.gestionnairecontact.entities.Role;
import krntn.syl.gestionnairecontact.entities.User;
import krntn.syl.gestionnairecontact.metier.dao.AdminDAO;
import krntn.syl.gestionnairecontact.metier.dao.IAdminDAO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAdminDAO {
	private ClassPathXmlApplicationContext context = null;
	private IAdminDAO adminDao = null;
	
	@Before
	public void setUp() throws Exception {
		if (context == null) { context = new ClassPathXmlApplicationContext(new String[] {"application-context.xml"}); }
		if (adminDao == null) { adminDao = (IAdminDAO) context.getBean("adminDao"); }
	}

	/**
	 * Ajoute 2 utilisateurs dans la base de donnée.
	 * Le test a réussi si le nombre d'utilisateurs en base de donnée + 2 (avant ajout)
	 * est égal au nombre d'utilisateurs en base de donnée (après ajout)
	 */
	@Test
	public void addUserTest() {
//		fail("not yet implemented");
		try {
			ArrayList<User> avant = adminDao.getUsers();
			adminDao.addUser(new User("login1", "mdp1", false));
			adminDao.addUser(new User("login2", "mdp2", false));
			ArrayList<User> apres = adminDao.getUsers();
			assertTrue(avant.size()+2 == apres.size());
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	/**
	 * Recherche un utilisateur à partir de son ID
	 * Le test a réussi si l'ID de l'utilasteur est égal à l'ID utilisé pour le rechercher
	 */
	@Test
	public void findUserTest() {
//		fail("not yet implemented");
		try {
			int userId = adminDao.addUser(new User("login3", "mdp3", false));
			User user = adminDao.findUser(userId);
			assertTrue(user.getId() == userId);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	/**
	 * Met à jour les informations d'un utilisateur
	 * Le test a réussi si l'utilisateur est activé et qu'avant il ne l'était pas
	 */
	@Test
	public void updateUserTest() {
//		fail("not yet implemented");
		try {
			int userId = adminDao.addUser(new User("login4", "mdp4", false));
			User user = adminDao.findUser(userId);
			boolean avant = user.isActived();
			user.setActived(true);
			adminDao.updateUser(user);
			assertTrue(user.isActived() && user.isActived() != avant);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	/**
	 * Supprime un utilisateur
	 * Le test a réussi si le nombre d'utilisateurs en base de donnée - 1 (avant suppression)
	 * est égal au nombre d'utilisateurs en base de donnée (après suppression) 
	 */
	@Test
	public void removeUserTest() {
//		fail("not yet implemented");
		try {
			int userId = adminDao.addUser(new User("login5", "mdp5", false));
			ArrayList<User> avant = adminDao.getUsers();
			User user = adminDao.findUser(userId);
			adminDao.removeUser(user.getId());
			ArrayList<User> apres = adminDao.getUsers();
			assertTrue(avant.size() - 1 == apres.size());
		} catch(Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void addRoleTest() {
		fail("not yet implemented");
		try {
			
		} catch(Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void findRoleTest() {
		fail("not yet implemented");
		try {
			
		} catch(Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void updateRoleTest() {
		fail("not yet implemented");
		try {
			
		} catch(Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void removeRoleTest() {
		fail("not yet implemented");
		try {
			
		} catch(Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}

	
	@Test
	public void setUserRoleTest() {
		fail("not yet implemented");
		try {
			
		} catch(Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}

}
