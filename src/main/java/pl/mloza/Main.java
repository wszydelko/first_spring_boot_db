package pl.mloza;

/*
* Pochodzi ze strony: http://blog.mloza.pl/spring-boot-interakcja-z-baza-danych-czyli-spring-data-jpa/
*
* */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.mloza.repozytory.TaskRepository;


@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories(basePackageClasses = TaskRepository.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}