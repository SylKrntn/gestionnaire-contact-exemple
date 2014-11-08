package krntn.syl.gestionnairecontact.metier.dao;

import java.util.ArrayList;

import krntn.syl.gestionnairecontact.entities.Contact;
import krntn.syl.gestionnairecontact.entities.Role;
import krntn.syl.gestionnairecontact.entities.User;

public interface IAdminDAO {

	public Integer addUser(User user);
	public User findUser(Integer userId);
	public User findUser(String login, String password);
	public User findUserByName(String login);
	public void updateUser(User user);
	public void removeUser(Integer userId);
	public ArrayList<User> getUsers();
	
	public Integer addRole(Role role);
	public Role findRole(Integer roleId);
	public void updateRole(Role role);
	public void removeRole(Integer roleId);
	
	public void setUserRole(Role role, Integer userId);
	
	public Integer addContact(Contact contact);
	public Contact findUserContact(Integer contactId, Integer userId);
	public ArrayList<Contact> getUserContacts(Integer userId);
	public ArrayList<Contact> getContacts();
	public void updateContact(Contact contact);
	public void removeContact(Integer contactId);
}
