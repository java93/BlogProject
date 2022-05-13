package kg.alatoo.blogproject.model.repo;

import kg.alatoo.blogproject.model.BlogCategory;
import org.springframework.data.repository.CrudRepository;

public interface BlogCategoryRepository extends CrudRepository<BlogCategory, String> {
}