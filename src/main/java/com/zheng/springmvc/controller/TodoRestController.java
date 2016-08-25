package com.zheng.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zheng.springmvc.model.Todo;
import com.zheng.springmvc.service.TodoService;

@RestController
public class TodoRestController {
	@Autowired
	TodoService service;
	
	@RequestMapping(path="/todos")
	public List<Todo> getAllTodos() {
		return service.retrieveTodos("zheng");
	}
	
	@RequestMapping(path="/todo/{id}")
	public Todo getTodo(@PathVariable int id) {
		return service.retrieveTodo(id);
	}
}
