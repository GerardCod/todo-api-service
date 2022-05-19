package dev.gerardcod.todos.todomicroservice.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gerardcod.todos.commons.entities.Todo;
import dev.gerardcod.todos.todomicroservice.repositories.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	private TodoRepository todoRepository;
	
	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	@Override
	public Todo create(Todo todo) {
		return this.todoRepository.save(todo);
	}

	@Override
	public List<Todo> findAll() {
		Iterable<Todo> todos = this.todoRepository.findAll();
		return (List<Todo>) todos;
	}

	@Override
	public Todo findById(Long id) {
		Optional<Todo> todoOptional = this.todoRepository.findById(id);
		
		if (todoOptional.isEmpty()) {
			throw new EntityNotFoundException("Cannot find the todo with id " + id);
		}
		
		return todoOptional.get();
	}

	@Override
	public Todo update(Long id, Todo todo) {
		Todo todoFound = findById(id);
		
		todoFound.setTitle(todo.getTitle());
		todoFound.setDescription(todo.getDescription());
		todoFound.setDueDate(todo.getDueDate());
		
		return this.todoRepository.save(todoFound);
	}

	@Override
	public void deleteById(Long id) {
		this.todoRepository.deleteById(id);
		
	}

}
