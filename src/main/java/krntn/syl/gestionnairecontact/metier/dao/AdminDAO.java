package krntn.syl.gestionnairecontact.metier.dao;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import krntn.syl.gestionnairecontact.dao.IDAO;
import krntn.syl.gestionnairecontact.entities.Role;
import krntn.syl.gestionnairecontact.entities.User;

@Transactional
public class AdminDAO implements IAdminDAO {

	private IDAO dao;
	
	public void setDao(IDAO dao) {
		this.dao = dao;
	}
	
	
	@Override
	public Integer addUser(User user) {
		return dao.addUser(user);
	}
	
	@Override
	public User findUser(Integer userId) {
		return dao.findUser(userId);
	}
	
	@Override
	public void updateUser(User user) {
		dao.updateUser(user);
	}
	
	@Override
	public void removeUser(Integer userId) {
		dao.removeUser(userId);
	}
	
	@Override
	public ArrayList<User> getUsers() {
		return dao.getUsers();
	}

	@Override
	public Integer addRole(Role role) {
		return dao.addRole(role);
	}
	
	@Override
	public Role findRole(Integer roleId) {
		return dao.findRole(roleId);
	}
	
	@Override
	public void updateRole(Role role) {
		dao.updateRole(role);
	}
	
	@Override
	public void removeRole(Integer roleId) {
		dao.removeRole(roleId);
	}

	
	@Override
	public void setUserRole(Role role, Integer userId) {
		dao.setUserRole(role, userId);
	}
	
}
