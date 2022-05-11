package kg.alatoo.blogproject;

import kg.alatoo.blogproject.model.Blog;
import kg.alatoo.blogproject.model.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.time.LocalDateTime;

@SpringBootApplication
public class BlogProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogProjectApplication.class, args);
    }

    @Autowired
    BlogRepo blogRepo;

    @Bean
    public CommandLineRunner bootstrap() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                File file = new File("./src/main/resources/static/img/img-01.jpg");

                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] filearr = new byte[(int) file.length()];
                fileInputStream.read(filearr);
                fileInputStream.close();
                Blog blog1 = new Blog()
                        .setTitle("Simple and useful HTML layout")
                        .setContent("This is a description of the video post. You can also have an image instead of the video. You can free download Xtra Blog Template from TemplateMo website. Phasellus maximus quis est sit amet maximus. Vestibulum vel rutrum lorem, ac sodales augue. Aliquam erat volutpat. Duis lectus orci, blandit in arcu est, elementum tincidunt lectus. Praesent vel justo tempor, varius lacus a, pharetra lacus.\n" +
                                "\n" +
                                "Duis pretium efficitur nunc. Mauris vehicula nibh nisi. Curabitur gravida neque dignissim, aliquet nulla sed, condimentum nulla. Pellentesque id venenatis quam, id cursus velit. Fusce semper tortor ac metus iaculis varius. Praesent aliquam ex vel lectus ornare tristique. Nunc et eros quis enim feugiat tincidunt et vitae dui.")
                        .setCreatedDate(LocalDateTime.now())
                        .setPhoto(new SerialBlob(filearr));
                Blog blog2 = new Blog();
                blogRepo.save(blog1);

                Blog next = blogRepo.findAll().iterator().next();
                Blob photo = next.getPhoto();
                byte[] bytes = photo.getBytes(0, (int) photo.length());
                FileOutputStream fileOutputStream = new FileOutputStream("test.jpg");
                fileOutputStream.write(bytes);
            }
        };
    }
}
