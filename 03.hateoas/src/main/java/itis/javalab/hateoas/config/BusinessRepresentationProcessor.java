package itis.javalab.hateoas.config;

import itis.javalab.hateoas.controllers.BusinessController;
import itis.javalab.hateoas.models.Businessman;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BusinessRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Businessman>> {


    @Override
    public EntityModel<Businessman> process(EntityModel<Businessman> model) {
        Businessman businessman = model.getContent();
        if (businessman != null && businessman.getIsActive()) {
            model.add(linkTo(methodOn(BusinessController.class).deactivate(businessman.getId())).withRel("deactivate"));
        }
        return model;

    }
}
