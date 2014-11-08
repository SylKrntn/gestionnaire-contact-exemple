package krntn.syl.gestionnairecontact.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role", schema="gestcont")
public class Role implements Serializable {
	/* //////////////// */
	/* // PROPERTIES // */
	/* //////////////// */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nom;
//	@ManyToOne
//	private User user;
	
	
	/* ////////////////// */
	/* // CONSTRUCTORS // */
	/* ////////////////// */
	public Role() { }
	
//	public Role(String nom, User user) {
//		super();
//		this.nom = nom;
//		this.user = user;
//	}
	
//	public Role(Integer id, String nom, User user) {
//		super();
//		this.id = id;
//		this.nom = nom;
//		this.user = user;
//	}




	/* ///////////// */
	/* // GETTERS // */
	/* ///////////// */
	public Integer getId() {
		return id;
	}


	public String getNom() {
		return nom;
	}
	
//	public User getUser() {
//		return user;
//	}


	/* ///////////// */
	/* // SETTERS // */
	/* ///////////// */
	public void setId(Integer id) {
		this.id = id;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}
	
//	public void setUser(User user) {
//		this.user = user;
//	}
}
