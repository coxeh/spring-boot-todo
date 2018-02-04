package com.coxeh.todolist.service;

import com.coxeh.todolist.repository.TodoRepository;
import com.coxeh.todolist.model.Todo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
	@Autowired
	private TodoRepository repository;
	
	public List<Todo> findAll(){
		return this.repository.findAll();
	}
	
	public Todo createNew(Todo todo) {
		return this.repository.insert(todo);
	}
	
	public Todo createOrUpdate(Todo todo) {
		return this.repository.save(todo);
	}
	
	public void removeById(String id) {
		this.repository.delete(id);	
	}
	
	public Todo findById(String id) {
		return this.repository.findOne(id);
	}
	
}
