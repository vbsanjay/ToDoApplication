package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepository;

@RestController
public class TodoJPAResource {
	
	private TodoService todoService;
	
	private TodoRepository todoRepository;
	
	public TodoJPAResource(TodoService todoService, TodoRepository todoRepository) {
		this.todoService = todoService;
		this.todoRepository = todoRepository;
	}
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		//return todoService.findByUsername(username);
		return todoRepository.findByUsername(username);
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
		return todoRepository.findById(id).get();
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
		todoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username, @PathVariable int id,
			 @RequestBody Todo todo) {
		//todoService.updateTodo(todo);
		todoRepository.save(todo);
		return todo;
	}
	
	@PostMapping("/users/{username}/todos")
	public Todo addTodo(@PathVariable String username, @RequestBody Todo todo) {
		todo.setUsername(username);
		todo.setId(null);
		return todoRepository.save(todo);
		
//		Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
//		return createdTodo;
	}
}
