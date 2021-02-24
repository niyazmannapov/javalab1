package ru.itis.mannapov.homework2;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Render {

    public static String render(Student student, String templateFile) {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setDefaultEncoding("UTF-8");
        try {
            Template template = cfg.getTemplate(templateFile);
            StringWriter stringWriter = new StringWriter();
            Map<String, Student> data = new HashMap<>();
            data.put("student", student);
            template.process(data, stringWriter);
            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            return "FAILED";
        }
    }
}
