package kg.alatoo.blogproject.model.repo;

import kg.alatoo.blogproject.model.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog,Long> {
}
