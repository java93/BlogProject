package kg.alatoo.blogproject.model.repo;

import kg.alatoo.blogproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}