package kg.alatoo.blogproject.controllers;

import kg.alatoo.blogproject.model.Blog;
import kg.alatoo.blogproject.model.repo.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
public class MainController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE,
            path = "blog-img/{blogId}"
    )
    @ResponseBody
    public byte[] getImage(@PathVariable("blogId") Long blogId,HttpServletResponse response) {
        Blob photo = blogRepository.findById(blogId).orElseThrow().getPhoto();
        byte[] bytes;
        try {
            bytes = photo.getBytes(0, ((int) photo.length()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setHeader("TestHeader","Almambet");
        return bytes;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("blogs",blogRepository.findAll());
        return "index";
    }

    @GetMapping("blog/{id}")
    public String blog(@PathVariable("id") Long id, Model model) {
        Blog blog = blogRepository.findById(id).orElseThrow();
        model.addAttribute(blog);

        return "post";
    }

    @GetMapping("about")
    public String about() {
        return "static/about";
    }

    @GetMapping("contact")
    public String contact() {
        return "static/contact";
    }
}
