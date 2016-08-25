package com.zheng.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zheng.springmvc.dao.TeamDAO;
import com.zheng.springmvc.entity.TeamEntity;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
     
    @Autowired
    private TeamDAO teamDAO;
 
    public void addTeam(TeamEntity team) {
        teamDAO.addTeam(team);      
    }
 
    public void updateTeam(TeamEntity team) {
        teamDAO.updateTeam(team);
    }
 
    public TeamEntity getTeam(int id) {
        return teamDAO.getTeam(id);
    }
 
    public void deleteTeam(int id) {
        teamDAO.deleteTeam(id);
    }
 
    public List<TeamEntity> getTeams() {
        return teamDAO.getTeams();
    }
 
}