package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.entity.Todo;

/**
 * Created by hello on 20/08/2018.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {


}
