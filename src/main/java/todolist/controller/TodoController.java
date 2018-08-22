package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import todolist.entity.Todo;
import todolist.entity.User;
import todolist.repository.TodoRepository;
import todolist.repository.UserRepository;

/**
 * Created by hello on 20/08/2018.
 */
@Controller
@RequestMapping("/todo")
public class TodoController {

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value= "/", method = RequestMethod.GET)
    public String showUserTodos(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        // get todos by user no.
        //

        return "todoList";
    }

    // dangerous parameters. bad guy could pretend the user with userId.
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public String addToTodoList(@PathVariable("userId") Long userId, Todo todo) {
        todoRepository.save(todo);
        return "redirect:/{userId}";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest() {
        User joma = userRepository.findByUsername("joma");
        System.out.println(joma);
        return "todoList";
    }

}
