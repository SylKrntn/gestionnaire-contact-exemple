package krntn.syl.gestionnairecontact.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="contact", schema="gestcont")
public class Contact implements Serializable {
	/* //////////////// */
	/* // PROPERTIES // */
	/* //////////////// */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Size(min=4, max=255)
	private String nom;
	@Size(min=2, max=255)
	private String prenom;
	@Pattern(regexp="^(0|\\+33)[1-9]{1}([-. ]?[0-9]{2}){4}$")
	private String telephone;
	@Pattern(regexp="^[a-z0-9_-][a-z0-9._-]{1,63}@[a-z0-9]{2,64}\\.[a-z]{2,4}$")
	private String mail;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	/* ////////////////// */
	/* // CONSTRUCTORS // */
	/* ////////////////// */
	public Contact() { }
	
	public Contact(String nom, String prenom, String telephone, String mail) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
	}

	public Contact(String nom, String prenom, String telephone, String mail, User user) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.user = user;
	}

	public Contact(Integer id, String nom, String prenom, String telephone, String mail, User user) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.user = user;
	}

	
	/* ///////////// */
	/* // GETTERS // */
	/* ///////////// */
	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getMail() {
		return mail;
	}

	public User getUser() {
		return user;
	}

	
	/* ///////////// */
	/* // SETTERS // */
	/* ///////////// */
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", nom=" + nom + ", prenom=" + prenom
				+ ", telephone=" + telephone + ", mail=" + mail + ", user="
				+ user + "]";
	}
	
}
