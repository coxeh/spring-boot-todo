package com.coxeh.todolist.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.coxeh.todolist.service.TodoService;
import com.coxeh.todolist.form.TodoCreateForm;
import com.coxeh.todolist.model.Todo;

@Controller
public class IndexController {
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView showIndexPage(
    		@RequestParam(value = "action", required=false) String action, 
    		@RequestParam(value = "id", required=false) String id
    	){
		ModelAndView model = new ModelAndView("index");
		model.addObject("action", action);
		
		if(action != null) {
			switch(action) {
				case "delete":
					this.todoService.removeById(id);
					break;
				case "edit":
					Todo editTodo = this.todoService.findById(id);
					model.addObject("todo", editTodo);
					break;
					
			}
		}
		
		
		List<Todo> tasks = todoService.findAll();
		model.addObject("tasks", tasks);
        return model;
    }
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public String createTask(
		@Valid TodoCreateForm todo, 
		BindingResult bindingResult,
		@RequestParam(value = "id", required=false) String id
	) {
		if(bindingResult.hasErrors()==false) {
			Todo todoModel;
			if(id==null) {
				todoModel = new Todo();
			}else {
				todoModel = this.todoService.findById(id);
			}
			if(todoModel != null) {
				todoModel.setTask(todo.getTask());
				this.todoService.createOrUpdate(todoModel);
			}
		}
		return "redirect:/";
	}
}
