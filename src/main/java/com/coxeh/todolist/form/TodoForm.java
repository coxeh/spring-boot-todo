package com.coxeh.todolist.form;

import javax.validation.constraints.Size;

public class TodoForm {

	@Size(min=1)
	private String task;
	private String id;
	
	public String getTask() {
		return this.task;
	}
	
	public TodoForm setTask(String task) {
		this.task = task;
		return this;
	}
	
	public String getId() {
		return this.id;
	}
	
	public TodoForm setId(String id) {
		this.id = id;
		return this;
	}
	
	public Boolean isEditing() {
		return this.id !=null && this.id.length()>0;
	}
}
