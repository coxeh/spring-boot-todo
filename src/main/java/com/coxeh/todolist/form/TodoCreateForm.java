package com.coxeh.todolist.form;

import javax.validation.constraints.Size;

public class TodoCreateForm {

	@Size(min=1)
	private String task;
	
	public String getTask() {
		return this.task;
	}
	
	public TodoCreateForm setTask(String task) {
		this.task = task;
		return this;
	}
}
