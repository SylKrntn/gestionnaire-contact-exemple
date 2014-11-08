package krntn.syl.gestionnairecontact.metier.dao;

import java.util.ArrayList;

import krntn.syl.gestionnairecontact.dao.IDAO;
import krntn.syl.gestionnairecontact.entities.Contact;
import krntn.syl.gestionnairecontact.entities.User;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ContactDAO implements IContactDAO {

	private IDAO dao;
	
	public void setDao(IDAO dao) {
		this.dao = dao;
	}

	@Override
	public Integer addContact(Contact contact) {
		return dao.addContact(contact);
	}

	@Override
	public Contact findUserContact(Integer contactId, Integer userId) {
		return dao.findUserContact(contactId, userId);
	}

	@Override
	public ArrayList<Contact> getUserContacts(Integer userId) {
		return dao.getUserContacts(userId);
	}

	@Override
	public ArrayList<Contact> getContacts() {
		return dao.getContacts();
	}
	
	@Override
	public void updateContact(Contact contact) {
		dao.updateContact(contact);
	}

	@Override
	public void removeContact(Integer contactId) {
		dao.removeContact(contactId);
	}

	@Override
	public User findUser(Integer userId) {
		return dao.findUser(userId);
	}
	
	@Override
	public User findUser(String login, String password) {
		return dao.findUser(login, password);
	}

	@Override
	public User findUserByName(String login) {
		return dao.findUserByName(login);
	}
}
