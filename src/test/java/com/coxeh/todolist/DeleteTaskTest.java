package com.coxeh.todolist;

import static org.junit.Assert.assertNull;

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
public class DeleteTaskTest {

	@Autowired
	TodoService todoService;
	@Autowired
	TodoRepository todoRepository;
	
	protected String insertId;
	
	@Before
	public void init() {
		Todo todo = new Todo();
		todo.setTask("Testing Deletion");
		this.insertId = this.todoRepository.insert(todo).getId();
	}
	
	@Test
	public void deleteTaskTest() {
		this.todoService.removeById(this.insertId);
		assertNull(this.todoRepository.findOne(this.insertId));
	}
}
