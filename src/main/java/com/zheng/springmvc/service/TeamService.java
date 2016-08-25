package com.zheng.springmvc.service;

import java.util.List;

import com.zheng.springmvc.entity.TeamEntity;

public interface TeamService {
     
    public void addTeam(TeamEntity team);
    public void updateTeam(TeamEntity team);
    public TeamEntity getTeam(int id);
    public void deleteTeam(int id);
    public List<TeamEntity> getTeams();
 
}