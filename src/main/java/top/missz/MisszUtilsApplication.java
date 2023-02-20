package top.missz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class MisszUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MisszUtilsApplication.class, args);
    }

}
