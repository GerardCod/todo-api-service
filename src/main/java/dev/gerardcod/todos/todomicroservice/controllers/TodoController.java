package dev.gerardcod.todos.todomicroservice.controllers;

import java.net.URI;
import java.util.List;

import dev.gerardcod.todos.commons.exceptions.BadRequestBodyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.gerardcod.todos.commons.dtos.CreateTodoDTO;
import dev.gerardcod.todos.commons.entities.Todo;
import dev.gerardcod.todos.todomicroservice.services.TodoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {
	
	private TodoService todoService;

	@Autowired
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	@PostMapping
	public ResponseEntity<Todo> create(@RequestBody @Valid CreateTodoDTO todoDTO, BindingResult result) {
		if (result.hasFieldErrors())
			throw new BadRequestBodyException("The request body is invalid", result.getFieldErrors());

		Todo todoCreated = this.todoService.create(todoDTO.toTodo());
		
		URI todoCreatedUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoCreated.getId()).toUri();
		return ResponseEntity.created(todoCreatedUri).body(todoCreated);
	}
	
	@GetMapping
	public ResponseEntity<List<Todo>> findAllTodos() {
		List<Todo> todos = this.todoService.findAll();
		return ResponseEntity.ok(todos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Todo> findById(@PathVariable Long id) {
		Todo todoFound = this.todoService.findById(id);
		return ResponseEntity.ok(todoFound);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody @Valid CreateTodoDTO todoDTO, BindingResult result) {
		if (result.hasFieldErrors())
			throw new BadRequestBodyException("The request body is invalid", result.getFieldErrors());

		Todo todoUpdated = this.todoService.update(id, todoDTO.toTodo());
		return ResponseEntity.ok(todoUpdated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.todoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
