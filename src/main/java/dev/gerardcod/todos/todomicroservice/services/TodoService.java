package dev.gerardcod.todos.todomicroservice.services;

import java.util.List;

import dev.gerardcod.todos.commons.entities.Todo;

public interface TodoService {
	Todo create(Todo todo);
	List<Todo> findAll();
	Todo findById(Long id);
	Todo update(Long id, Todo todo);
	void deleteById(Long id);
}
