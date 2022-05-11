package kg.alatoo.blogproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;

@Controller
public class MainController {

    @GetMapping
    public String home() {
        return "index";
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
