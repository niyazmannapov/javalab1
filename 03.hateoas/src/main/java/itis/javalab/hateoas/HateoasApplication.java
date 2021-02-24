package itis.javalab.hateoas;

import itis.javalab.hateoas.models.*;
import itis.javalab.hateoas.repositories.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasApplication.class, args);

    }

}
