package krntn.syl.gestionnairecontact;

import static org.junit.Assert.*;

import java.util.ArrayList;

import krntn.syl.gestionnairecontact.entities.Contact;
import krntn.syl.gestionnairecontact.entities.User;
import krntn.syl.gestionnairecontact.metier.dao.IContactDAO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestContactDAO {
	private ClassPathXmlApplicationContext context = null;
	private IContactDAO contactDao = null;
	
	@Before
	public void setUp() throws Exception {
		if (context == null) { context = new ClassPathXmlApplicationContext(new String[] {"application-context.xml"}); }
		if (contactDao == null) { contactDao = (IContactDAO) context.getBean("contactDao"); }
	}

	@Test
	public void addContactTest() {
//		fail("Not yet implemented");
		try {
			User user1 = contactDao.findUser(1);
			User user2 = contactDao.findUser(2);
			
			ArrayList<Contact> avant = contactDao.getContacts();
			contactDao.addContact(new Contact("nom1", "prenom1", "11-11-11-11-11", "mail1@email.com", user1));
			contactDao.addContact(new Contact("nom2", "prenom2", "22.22.22.22.22", "mail2@email.com", user1));
			contactDao.addContact(new Contact("nom3", "prenom3", "33 33 33 33 33", "mail3@email.com", user2));
			contactDao.addContact(new Contact("nom4", "prenom4", "4444444444", "mail4@email.com", user2));
			ArrayList<Contact> apres = contactDao.getContacts();
			assertTrue(avant.size()+4 == apres.size());
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}

	@Test
	public void findUserContactTest() {
//		fail("Not yet implemented");
		final int USER_ID = 1;
		try {
			User user = contactDao.findUser(USER_ID);
			int contactId = contactDao.addContact(new Contact("nom5", "prenom5", "55 55 55 55 55", "mail5@email.com", user));
			Contact contact = contactDao.findUserContact(contactId, USER_ID);
			assertTrue(contact.getId() == contactId);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}

	@Test
	public void getUserContactsTest() {
//		fail("Not yet implemented");
		final int USER_ID = 1;
		try {
			ArrayList<Contact> contacts = contactDao.getUserContacts(USER_ID);
			assertTrue(contacts.size() > 0);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}

	@Test
	public void updateContactTest() {
//		fail("Not yet implemented");
		final int USER_ID = 1;
		try {
			User user = contactDao.findUser(USER_ID);
			Contact contact = new Contact("nom7", "prenom7", "77.77.77.77.77", "mail7@emial.com", user);
			int contactId = contactDao.addContact(contact);
			if (contactId > 0 ) {
				Contact contactAvant = contactDao.findUserContact(contactId, USER_ID);
				Contact contactModif = contactDao.findUserContact(contactId, USER_ID);
				contactModif.setTelephone("99-99-99-99-99");// change le numéro de téléphone du premier contact
				contactDao.updateContact(contactModif);
				Contact contactApres = contactDao.findUserContact(contactAvant.getId(), USER_ID);
				assertTrue(contactAvant.getId() == contactApres.getId() && !contactAvant.getTelephone().equals(contactApres.getTelephone()));
			}
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}

	@Test
	public void removeContactTest() {
//		fail("Not yet implemented");
		final int USER_ID = 1;
		ArrayList<Contact> contactsAvant = null;
		ArrayList<Contact> contactsApres = null;
		try {
			User user = contactDao.findUser(USER_ID);
			int contactId = contactDao.addContact(new Contact("nom6", "prenom6", "666-666-66-66", "mail6@email.com", user));
			if (contactId > 0) {
				contactsAvant = contactDao.getUserContacts(USER_ID);
			}
			contactDao.removeContact(contactId);
			contactsApres = contactDao.getUserContacts(USER_ID);
			assertTrue(contactsAvant.size()-1 == contactsApres.size());
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
}
