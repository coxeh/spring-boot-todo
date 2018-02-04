package com.coxeh.todolist.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.coxeh.todolist.service.TodoService;
import com.coxeh.todolist.form.TodoCreateForm;
import com.coxeh.todolist.model.Todo;

@Controller
public class IndexController {
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView showIndexPage(){
		List<Todo> tasks = todoService.findAll();
		ModelAndView model = new ModelAndView("index");
		model.addObject("tasks", tasks);
        return model;
    }
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ModelAndView createTask(@Valid TodoCreateForm todo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()==false) {
			Todo todoModel = new Todo();
			todoModel.setTask(todo.getTask());
			this.todoService.createNew(todoModel);
		}
	
		return this.showIndexPage();	
	}
}
