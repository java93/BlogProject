package kg.alatoo.blogproject.controllers;

import kg.alatoo.blogproject.model.Blog;
import kg.alatoo.blogproject.model.repo.BlogRepository;
import kg.alatoo.blogproject.services.BlobService;
import kg.alatoo.blogproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Controller
public class MainController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlobService blobService;

    @Autowired
    private UserService userService;

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

    @GetMapping("createpost")
    public String createPost(Model model) {
        Blog newPost = new Blog().setCreatedDate(LocalDateTime.now());
        model.addAttribute("newPost",newPost);
        return "createpost";
    }

    @PostMapping(value = "savepost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String savePost(
            @ModelAttribute Blog blog,
            @RequestPart("photofile") MultipartFile photo,
            Principal principal
    ) {
        try {
            Blob blob = blobService.getBlob(photo.getInputStream(), (int) photo.getSize());
            blog.setPhoto(blob)
                    .setCreatedBy(userService.getUserByUsername(principal.getName()));
            blogRepository.save(blog);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String adminPanel() {
        return "There should be admin panel";
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
