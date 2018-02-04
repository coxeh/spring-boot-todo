package com.coxeh.todolist;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coxeh.todolist.model.Todo;
import com.coxeh.todolist.repository.TodoRepository;
import com.coxeh.todolist.service.TodoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CreateTaskTest {
	@Autowired
	TodoService todoService;
	@Autowired
	TodoRepository todoRepository;
	
	static final String TASK = "My Test Task";
	protected String insertId;
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void createTaskTest() {
		Todo todo = new Todo();
		todo.setTask(TASK);
		Todo insertedTodo = this.todoService.createNew(todo);
		assertEquals(todo.getTask(),insertedTodo.getTask());
		this.insertId = insertedTodo.getId();
	}
	
	@After
	public void after() {
		this.todoRepository.delete(this.insertId);
	}
}
