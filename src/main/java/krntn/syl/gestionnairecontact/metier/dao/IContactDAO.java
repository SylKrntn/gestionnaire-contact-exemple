package krntn.syl.gestionnairecontact.metier.dao;

import java.util.ArrayList;

import krntn.syl.gestionnairecontact.entities.Contact;
import krntn.syl.gestionnairecontact.entities.User;

public interface IContactDAO {
	public Integer addContact(Contact contact);
	public Contact findUserContact(Integer contactId, Integer userId);
	public ArrayList<Contact> getUserContacts(Integer userId);
	public ArrayList<Contact> getContacts();
	public void updateContact(Contact contact);
	public void removeContact(Integer contactId);
	
	public User findUser(Integer userId);
	public User findUser(String login, String password);
	public User findUserByName(String login);
}
