package com.zheng.springmvc.hibernatetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zheng.springmvc.entity.TeamEntity;
import com.zheng.springmvc.service.TeamService;

@RestController
public class TeamController {
	
	@Autowired
	TeamService teamService;

	@RequestMapping(path="/team", method = RequestMethod.GET)
	public void addTeam() {
		TeamEntity team = new TeamEntity("hey",1);
		teamService.addTeam(team);
	}
}
