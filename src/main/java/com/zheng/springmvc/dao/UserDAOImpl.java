package com.zheng.springmvc.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zheng.springmvc.entity.UserEntity;

import antlr.StringUtils;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
     
    private Session getCurrentSession() {
    	Session session;
    	try {
    	    session = sessionFactory.getCurrentSession();
    	} catch (HibernateException e) {
    	    session = sessionFactory.openSession();
    	}
        return session;
    }

	@Override
	public void addUser(UserEntity user) {
		getCurrentSession().save(user);
	}

	@Override
	public void updateUser(UserEntity user) {
		UserEntity userToUpdate = getUser(user.getId());
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setPassword(user.getPassword());
        getCurrentSession().update(userToUpdate);
	}

	@Override
	public UserEntity getUser(int id) {
		UserEntity user = (UserEntity) getCurrentSession().get(UserEntity.class, id);
        return user;
	}

	@Override
	public void deleteUser(int id) {
		UserEntity user = getUser(id);
        if (user != null)
            getCurrentSession().delete(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserEntity> getUsers() {
		return getCurrentSession().createQuery("from UserEntity").list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public UserEntity getUserByName(String username) {
		//http://stackoverflow.com/questions/14446048/hibernate-table-not-mapped-error
		List<UserEntity> result = getCurrentSession().createQuery(String.format("from UserEntity where username='%s'",username)).list();
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

}
