package krntn.syl.gestionnairecontact.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name="user", schema="gestcont")
public class User implements Serializable {
	/* //////////////// */
	/* // PROPERTIES // */
	/* //////////////// */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String login;
	@Size(min=8)
	private String mdp;
	@Transient
	@Size(min=8)
	private String mdpConf;
	private boolean actived;
	@OneToMany
	@JoinColumn(name="user_id")
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
	
	public User(String login, String mdp, String mdpConf, boolean actived) {
		super();
		this.login = login;
		this.mdp = mdp;
		this.mdpConf = mdpConf;
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
	
	public String getMdpConf() {
		return mdpConf;
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
	
	public void setMdpConf(String mdpConf) {
		this.mdpConf = mdpConf;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
}
