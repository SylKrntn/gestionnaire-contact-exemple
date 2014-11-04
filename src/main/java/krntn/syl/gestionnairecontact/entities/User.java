package krntn.syl.gestionnairecontact.entities;

import java.util.Collection;

public class User {
	private Integer id;
	private String login;
	private String mdp;
	private boolean actived;
	private Collection<Role> roles;
	private Collection<Contact> contacts;
}
