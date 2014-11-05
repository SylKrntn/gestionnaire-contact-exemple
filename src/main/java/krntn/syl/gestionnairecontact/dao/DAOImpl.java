package krntn.syl.gestionnairecontact.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import krntn.syl.gestionnairecontact.entities.Contact;
import krntn.syl.gestionnairecontact.entities.Role;
import krntn.syl.gestionnairecontact.entities.User;

public class DAOImpl implements IDAO {

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public Integer addUser(User user) {
		em.persist(user);
		return user.getId();
	}
	@Override
	public User findUser(Integer userId) {
		return em.find(User.class, userId);
	}
	@Override
	public void updateUser(User user) {
		em.merge(user);
	}
	@Override
	public void removeUser(Integer userId) {
		User user = em.find(User.class, userId);
		em.remove(user);
	}
	@Override
	public ArrayList<User> getUsers() {
		Query query = em.createQuery("SELECT u FROM User u");
		return (ArrayList<User>) query.getResultList();
	}
	
	
	@Override
	public Integer addRole(Role role) {
		em.persist(role);
		return role.getId();
	}
	@Override
	public Role findRole(Integer roleId) {
		return em.find(Role.class, roleId);
	}
	@Override
	public void updateRole(Role role) {
		em.merge(role);
	}
	@Override
	public void removeRole(Integer roleId) {
		Role role = em.find(Role.class, roleId);
		em.remove(role);
	}

	@Override
	public void setUserRole(Role role, Integer userId) {
		User user = em.find(User.class, userId);
		user.getRoles().add(role);
	}
	
	
	@Override
	public Integer addContact(Contact contact) {
		em.persist(contact);
		return contact.getId();
	}
	@Override
	public Contact findUserContact(Integer contactId, Integer userId) {
		User user = em.find(User.class, userId);
		Query query = em.createQuery("SELECT c FROM Contact c WHERE c.user = ? AND c.id = ?");
		query.setParameter(1, user);
		query.setParameter(2, contactId);
		return (Contact) query.getResultList().get(0);
	}
	@Override
	public ArrayList<Contact> getUserContacts(Integer userId) {
		User user = em.find(User.class, userId);
		Query query = em.createQuery("SELECT c FROM Contact c WHERE c.user = ?");
		query.setParameter(1, user);
		return (ArrayList<Contact>) query.getResultList();
	}
	@Override
	public ArrayList<Contact> getContacts() {
		Query query = em.createQuery("SELECT c FROM Contact c");
		return (ArrayList<Contact>) query.getResultList();
	}
	@Override
	public void updateContact(Contact contact) {
		em.merge(contact);
	}
	@Override
	public void removeContact(Integer contactId) {
		Contact contact = em.find(Contact.class, contactId);
		em.remove(contact);
	}
}
