package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import todolist.dto.TodoDTO;
import todolist.entity.Todo;
import todolist.service.TodoService;

/**
 * Created by hello on 04/09/2018.
 */
@RestController
@RequestMapping("/rest")
public class TodoRestController {

    @Autowired
    TodoService todoService;

    @PostMapping("/todo")
    public TodoDTO insertTodo(Authentication authentication, @RequestBody TodoDTO todoDTO) {
        Todo todo = todoService.addTodo(todoDTO);
        todoDTO.setId(todo.getId());

        return todoDTO;
    }

    @PutMapping("/todo")
    public TodoDTO updateTodo(Authentication authentication, @RequestBody TodoDTO todoDTO) {
        todoService.updateTodo(todoDTO);
        return todoDTO;
    }

    @DeleteMapping("/todo")
    public ResponseEntity<Void> deleteTodo(Authentication authentication, @RequestBody TodoDTO todoDTO) {
        todoService.deleteTodo(todoDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
