package kg.alatoo.blogproject;

import kg.alatoo.blogproject.model.Blog;
import kg.alatoo.blogproject.model.Role;
import kg.alatoo.blogproject.model.User;
import kg.alatoo.blogproject.model.repo.BlogRepository;
import kg.alatoo.blogproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Set;

@SpringBootApplication
public class BlogProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogProjectApplication.class, args);
    }

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    UserService userService;

    @Bean
    public CommandLineRunner bootstrap() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                File userPhotoMan = new File("./src/main/resources/static/img/comment-1.jpg");

                Role role_admin = new Role("ROLE_ADMIN");
                Role role_user = new Role("ROLE_USER");

                User admin = saveUser("Almambet", "Totoev", "almambet", role_admin, userPhotoMan);

                User user = saveUser("Mukhammed", "Akzhol uulu", "mukhammed", role_user, userPhotoMan);


                File img01 = new File("./src/main/resources/static/img/img-01.jpg");
                File img02 = new File("./src/main/resources/static/img/img-02.jpg");
                File img03 = new File("./src/main/resources/static/img/img-03.jpg");
                File img04 = new File("./src/main/resources/static/img/img-04.jpg");

                String description = "You are allowed to convert this template as any kind of CMS theme or template for your custom website builder. You can also use this for your clients. Thank you for choosing us.";
                String content = """
                        This is a description of the video post. You can also have an image instead of the video. You can free download Xtra Blog Template from TemplateMo website. Phasellus maximus quis est sit amet maximus. Vestibulum vel rutrum lorem, ac sodales augue. Aliquam erat volutpat. Duis lectus orci, blandit in arcu est, elementum tincidunt lectus. Praesent vel justo tempor, varius lacus a, pharetra lacus.
                        Duis pretium efficitur nunc. Mauris vehicula nibh nisi. Curabitur gravida neque dignissim, aliquet nulla sed, condimentum nulla. Pellentesque id venenatis quam, id cursus velit. Fusce semper tortor ac metus iaculis varius. Praesent aliquam ex vel lectus ornare tristique. Nunc et eros quis enim feugiat tincidunt et vitae dui.""";

                String title1 = "Simple and useful HTML layout";
                String title2 = "Multi-purpose blog template";
                String title3 = "How can you apply Xtra Blog";
                String title4 = "A little restriction to apply";

                saveBlog(title1,description,content,img01,admin);
                saveBlog(title2,description,content,img02,admin);
                saveBlog(title3,description,content,img03,user);
                saveBlog(title4,description,content,img04,user);
            }

            private User saveUser(String firstname, String lastname, String username_password, Role role_user, File photo) throws IOException {
                try(FileInputStream fileInputStream = new FileInputStream(photo)) {
                    User user = new User().setFirstName(firstname)
                            .setLastName(lastname)
                            .setUsername(username_password)
                            .setPassword(username_password)
                            .setRoles(Set.of(role_user))
                            .setPhoto(fileInputStream.readAllBytes());

                    userService.saveUser(user);
                    return user;
                }
            }

            private void saveBlog(String title,String description, String content,File img01,User author) throws IOException {
                Blog blog1;
                try (FileInputStream fileInputStream = new FileInputStream(img01)) {

                    blog1 = new Blog()
                            .setTitle(title)
                            .setContent(content)
                            .setDescription(description)
                            .setCreatedDate(LocalDateTime.now())
                            .setCreatedBy(author)
                            .setPhoto(fileInputStream.readAllBytes());
                }
                blogRepository.save(blog1);
            }
        };
    }

    @Bean("base64encoder")
    public Base64EncoderToString base64() {
        return bytes -> Base64.getEncoder().encodeToString(bytes);
    }
}

interface Base64EncoderToString{
    String encode(byte[] bytes);
}
