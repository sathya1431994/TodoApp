package com.example.todo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://todo4yu.herokuapp.com", maxAge = 3600)
@RequestMapping("/todolist")
public class TodoController {
	@Autowired
	private TodoRepository todorepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Todo> showall() {
		return todorepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Todo create(@RequestBody Todo todo) {
		return todorepository.save(todo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		todorepository.delete(id);
	}

	@RequestMapping(value = "/ids", method = RequestMethod.POST)
	public void clearall(@RequestBody Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
			todorepository.delete(ids[i]);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Todo updateItem(@RequestBody Todo todo, @PathVariable Long id) {
		todo.setId(id);
		return todorepository.saveAndFlush(todo);
	}

}
