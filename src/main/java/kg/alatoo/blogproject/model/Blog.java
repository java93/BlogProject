package kg.alatoo.blogproject.model;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Lob
    private Blob photo;
    private LocalDateTime createdDate;
    @ManyToOne
    private User createdBy;

    public Long getId() {
        return id;
    }

    public Blog setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Blog setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Blog setContent(String content) {
        this.content = content;
        return this;
    }

    public Blob getPhoto() {
        return photo;
    }

    public Blog setPhoto(Blob photo) {
        this.photo = photo;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Blog setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Blog setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}
