package com.coxeh.todolist.repository;

import com.coxeh.todolist.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {

}
