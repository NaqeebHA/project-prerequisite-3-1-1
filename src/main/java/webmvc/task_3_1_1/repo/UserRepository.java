package webmvc.task_3_1_1.repo;

import org.springframework.data.repository.CrudRepository;
import webmvc.task_3_1_1.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
