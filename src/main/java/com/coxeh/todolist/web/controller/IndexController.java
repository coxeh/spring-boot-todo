package com.coxeh.todolist.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coxeh.todolist.service.TodoService;
import com.coxeh.todolist.model.Todo;

@Controller
public class IndexController {
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String showIndexPage(
    		@ModelAttribute("todo") Todo todo,
    		Model model,
    		@RequestParam(value = "action", required=false) String action, 
    		@RequestParam(value = "id", required=false) String id
    	){
		
		if(action != null) {
			switch(action) {
				case "delete":
					this.todoService.removeById(id);
					break;
				case "edit":
					todo = this.todoService.findById(id);
					if(todo == null) {
						return "redirect:/?notfound";
					}
					model.addAttribute("todo", todo);
					break;
					
			}
		}
		
		List<Todo> tasks = todoService.findAll();
		model.addAttribute("tasks", tasks);
        return "index";
    }
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public String createTask(
		@Valid @ModelAttribute("todo") Todo todo, 
		BindingResult bindingResult,
		Model model
	) {
		if(bindingResult.hasErrors()) {
			List<Todo> tasks = todoService.findAll();
			model.addAttribute("tasks", tasks);
			return "index";
		}
		
		this.todoService.createOrUpdate(todo);
		
		return "redirect:/";
	}
}
