package krntn.syl.gestionnairecontact.entities;

import java.io.Serializable;

public class Contact implements Serializable {
	private Integer id;
	private String nom;
	private String prenom;
	private String telephone;
	private String mail;
	private User user;
}
