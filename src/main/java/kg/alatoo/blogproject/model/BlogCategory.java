package kg.alatoo.blogproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BlogCategory {
    @Id
    public String category;

    public BlogCategory() {
    }

    public BlogCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public BlogCategory setCategory(String category) {
        this.category = category;
        return this;
    }
}
