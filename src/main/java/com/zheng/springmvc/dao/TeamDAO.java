package com.zheng.springmvc.dao;

import java.util.List;

import com.zheng.springmvc.entity.TeamEntity;

public interface TeamDAO {
     
    public void addTeam(TeamEntity team);
    public void updateTeam(TeamEntity team);
    public TeamEntity getTeam(int id);
    public void deleteTeam(int id);
    public List<TeamEntity> getTeams();
 
}