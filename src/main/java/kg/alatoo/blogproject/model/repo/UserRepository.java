package kg.alatoo.blogproject.model.repo;

import kg.alatoo.blogproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}