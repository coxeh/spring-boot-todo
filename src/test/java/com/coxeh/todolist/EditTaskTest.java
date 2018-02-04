package com.coxeh.todolist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class EditTaskTest {
	@Autowired
	TodoService todoService;
	@Autowired
	TodoRepository todoRepository;
	
	static final String TASK = "My Test Task Edited";
	protected String insertId;
	
	@Before
	public void init() {
		Todo todo = new Todo();
		todo.setTask("Test Editing");
		todo = this.todoRepository.insert(todo);
		this.insertId = todo.getId();
	}
	
	@Test
	public void editTaskTest() {
		Todo todo = this.todoService.findById(this.insertId);
		assertNotNull(todo);
		todo.setTask(TASK);
		todo = this.todoService.createOrUpdate(todo);
		assertEquals(todo.getTask(),TASK);
	}
	
	@After
	public void after() {
		this.todoRepository.delete(this.insertId);
	}
}
