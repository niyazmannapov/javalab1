package itis.javalab.hateoas.config;

import itis.javalab.hateoas.controllers.SaleController;
import itis.javalab.hateoas.models.Sale;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SaleRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Sale>> {
    @Override
    public EntityModel<Sale> process(EntityModel<Sale> model) {
        Sale sale = model.getContent();
        model.add(linkTo(methodOn(SaleController.class).changeName(sale.getId())).withRel("change"));
        return model;
    }
}
