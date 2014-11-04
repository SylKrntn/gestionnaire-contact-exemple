package krntn.syl.gestionnairecontact.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema="gestcont")
public class User implements Serializable {
	/* //////////////// */
	/* // PROPERTIES // */
	/* //////////////// */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String login;
	private String mdp;
	private boolean actived;
	@OneToMany
	private Collection<Role> roles;
	
	
	/* ////////////////// */
	/* // CONSTRUCTORS // */
	/* ////////////////// */
	public User() { }

	public User(String login, String mdp, boolean actived) {
		super();
		this.login = login;
		this.mdp = mdp;
		this.actived = actived;
	}
	
	public User(String login, String mdp, boolean actived, Collection<Role> roles) {
		super();
		this.login = login;
		this.mdp = mdp;
		this.actived = actived;
		this.roles = roles;
	}

	
	/* ///////////// */
	/* // GETTERS // */
	/* ///////////// */
	public Integer getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getMdp() {
		return mdp;
	}

	public boolean isActived() {
		return actived;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	
	/* ///////////// */
	/* // SETTERS // */
	/* ///////////// */
	public void setId(Integer id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
}
