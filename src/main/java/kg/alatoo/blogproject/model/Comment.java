package kg.alatoo.blogproject.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private LocalDateTime createdDate;

    @ManyToOne
    private User author;

    @ManyToOne
    private Blog blog;

    @ManyToOne
    private Comment replyTo;

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Comment setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Comment setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Comment setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Blog getBlog() {
        return blog;
    }

    public Comment setBlog(Blog blog) {
        this.blog = blog;
        return this;
    }

    public Comment getReplyTo() {
        return replyTo;
    }

    public Comment setReplyTo(Comment replyTo) {
        this.replyTo = replyTo;
        return this;
    }
}
