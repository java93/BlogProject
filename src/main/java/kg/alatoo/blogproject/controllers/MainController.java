package kg.alatoo.blogproject.controllers;

import kg.alatoo.blogproject.model.Blog;
import kg.alatoo.blogproject.model.repo.BlogRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class MainController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE,
            value = "get-img"
    )
    @ResponseBody
    public byte[] getImage(HttpServletResponse response) {
        response.setHeader("Content-Type","image/jpeg"); // same as produces = "image/jpeg" in @GetMapping annotation
        byte[] bytes = new byte[0];
        try (InputStream inputStream = new FileInputStream("./src/main/resources/static/img/about-01.jpg")) {
            System.out.println("inputStream = " + inputStream);
//            assert inputStream != null;
            bytes = inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("bytes.length = " + bytes.length);
        return bytes;
    }

    @GetMapping
    public String home(
            Model model,
            HttpServletResponse response) {
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
