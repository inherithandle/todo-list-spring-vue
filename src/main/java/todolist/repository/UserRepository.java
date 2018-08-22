package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.entity.User;

/**
 * Created by hello on 20/08/2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
