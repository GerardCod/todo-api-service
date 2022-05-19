package dev.gerardcod.todos.todomicroservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.gerardcod.todos.commons.entities.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

}
