package ru.itis.niyaz.mongo.template;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.niyaz.mongo.template.config.SimpleMongoConfig;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SimpleMongoConfig.class);
    }
}
