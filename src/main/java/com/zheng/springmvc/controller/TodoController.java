package com.zheng.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.zheng.springmvc.model.Todo;
import com.zheng.springmvc.service.TodoService;

@Controller
public class TodoController {

	private Log logger = LogFactory.getLog(TodoController.class);
	
	@Autowired
	TodoService todoService;

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
	
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodoList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		//throw new RuntimeException("hey");
		
		//change local in controller
		//LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        //localeResolver.setLocale(request, response, StringUtils.parseLocaleString("cn"));
        
		model.put("todos", todoService.retrieveTodos(retrieveLoggedinUserName()));
		model.put("name", retrieveLoggedinUserName());
		return "list-todos";
	}

	private String retrieveLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model) {
		model.put("todo", new Todo(0,retrieveLoggedinUserName(),"Default description",new Date(),false));
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(@Valid Todo todo, BindingResult bindingResult, ModelMap model) {
		if(bindingResult.hasErrors()) {
			return "todo";
		}
		todo.setUser(retrieveLoggedinUserName());
		todoService.addTodo(todo);
		model.clear();
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id, ModelMap model) {
		//delete todo
		todoService.deleteTodo(id);
		model.clear();
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, BindingResult bindingResult, ModelMap model) {
		if(bindingResult.hasErrors()) {
			return "todo";
		}
		todo.setUser(retrieveLoggedinUserName());
		todoService.updateTodo(todo);
		model.clear();
		return "redirect:/list-todos";
	}
	
    @ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest req, Exception exception) {
        logger.error("Request: " + req.getRequestURL() + " raised " + exception);
        return "error-specific";
    }

}
