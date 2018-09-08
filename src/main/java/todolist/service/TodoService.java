package todolist.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.dto.TodoDTO;
import todolist.entity.Todo;
import todolist.repository.ProjectRepository;
import todolist.repository.TodoRepository;

/**
 * Created by hello on 28/08/2018.
 */
@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Transactional
    public Todo addTodo(TodoDTO todoDTO) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoDTO, todo);
        todo.setProject(projectRepository.getOne(todoDTO.getProjectNo()));

        return todoRepository.save(todo);
    }

    @Transactional
    public void updateTodo(TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(todoDTO.getId()).orElseThrow(RuntimeException::new);
        todo.setText(todoDTO.getText());
    }

    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
