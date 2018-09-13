package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import todolist.entity.Todo;

/**
 * Created by hello on 20/08/2018.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Modifying
    @Query("delete from todo t where t.project.projectNo = :projectNo")
    int deleteTodosByProjectNo(@Param("projectNo") long projectNo);

}
