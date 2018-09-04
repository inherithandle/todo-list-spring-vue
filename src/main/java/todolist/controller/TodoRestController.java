package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
