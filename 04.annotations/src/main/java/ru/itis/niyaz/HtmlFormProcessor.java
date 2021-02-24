package ru.itis.niyaz;

import com.google.auto.service.AutoService;
import ru.itis.niyaz.annotation.HtmlForm;
import ru.itis.niyaz.annotation.HtmlInput;
import ru.itis.niyaz.html.Form;
import ru.itis.niyaz.html.Input;
import ru.itis.niyaz.html.UserFormBuilder;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.niyaz.annotation.HtmlForm"})
public class HtmlFormProcessor extends AbstractProcessor {

    private UserFormBuilder userFormBuilder;

    public HtmlFormProcessor() {
        userFormBuilder = new UserFormBuilder();
    }

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        annotatedElements.forEach(element -> {
            Form form = new Form(element.getAnnotation(HtmlForm.class).action(),
                    element.getAnnotation(HtmlForm.class).method(),
                    element.getAnnotation(HtmlForm.class).enctype());
            element.getEnclosedElements()
                    .stream()
                    .filter(field -> field.getAnnotation(HtmlInput.class) != null)
                    .forEach(field -> form.getInputs().add(new Input(field.getAnnotation(HtmlInput.class).type(),
                            field.getAnnotation(HtmlInput.class).name(),
                            field.getAnnotation(HtmlInput.class).id(),
                            field.getAnnotation(HtmlInput.class).placeholder())));
            String path = HtmlFormProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1)
                    + element.getSimpleName()
                    + "_form.html";
            userFormBuilder.buildHtmlForm(path, form);
        });
        return true;
    }
}