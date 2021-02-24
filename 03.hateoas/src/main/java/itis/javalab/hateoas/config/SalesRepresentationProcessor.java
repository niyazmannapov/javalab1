package itis.javalab.hateoas.config;

import itis.javalab.hateoas.models.Sales;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class SalesRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Sales>> {

    @Override
    public EntityModel<Sales> process(EntityModel<Sales> model) {
        Sales sales = model.getContent();
        if (sales != null) {
            model.add(Link.of(String.format("/sales/%s/add_sale/{sale-id}", sales.getId())).withRel("addSale"));
        }
        return model;
    }
}
