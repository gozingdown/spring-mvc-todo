package com.zheng.springmvc.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zheng.springmvc.entity.TeamEntity;

//Annotation @Repository Indicates that the annotated class is a “DAO”.
@Repository
public class TeamDAOImpl implements TeamDAO {
     
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
 
    public void addTeam(TeamEntity team) {
        getCurrentSession().save(team);
    }
 
    public void updateTeam(TeamEntity team) {
        TeamEntity teamToUpdate = getTeam(team.getId());
        teamToUpdate.setName(team.getName());
        teamToUpdate.setRating(team.getRating());
        getCurrentSession().update(teamToUpdate);
         
    }
 
    public TeamEntity getTeam(int id) {
        TeamEntity team = (TeamEntity) getCurrentSession().get(TeamEntity.class, id);
        return team;
    }
 
    public void deleteTeam(int id) {
        TeamEntity team = getTeam(id);
        if (team != null)
            getCurrentSession().delete(team);
    }
 
    @SuppressWarnings("unchecked")
    public List<TeamEntity> getTeams() {
        return getCurrentSession().createQuery("from TeamEntity").list();
    }
 
}