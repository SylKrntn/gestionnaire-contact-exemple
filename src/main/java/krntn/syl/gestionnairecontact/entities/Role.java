package krntn.syl.gestionnairecontact.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	public Role(String nom) {
		super();
		this.nom = nom;
	}
	
	public Role(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
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


	/* ///////////// */
	/* // SETTERS // */
	/* ///////////// */
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", nom=" + nom + "]";
	}
	
}
