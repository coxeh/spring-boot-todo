package com.coxeh.todolist.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Todo {

	@Id
	private String id;
	
	@Size(min=1)
	private String task;
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date createdAt;
	
	public Todo() {
		this.createdAt = new Date();
	}
	
	// Setters
	public Todo setTask(String task) {
		this.task = task;
		return this;
	}
	public Todo setId(String id) {
		this.id = id;
		return this;
	}

	//Getters
	public String getTask() {
		return this.task;
	}
	public Date getCreatedAt() {
		return this.createdAt;
	}
	public String getId() {
		return this.id;
	}

	
	public Boolean isEditing() {
		return this.id !=null && this.id.length()>0;
	}
	
}
