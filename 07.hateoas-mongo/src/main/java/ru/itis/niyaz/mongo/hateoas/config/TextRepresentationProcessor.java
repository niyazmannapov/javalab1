package ru.itis.niyaz.mongo.hateoas.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.niyaz.mongo.hateoas.controllers.UserDataController;
import ru.itis.niyaz.mongo.hateoas.models.UserData;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TextRepresentationProcessor implements RepresentationModelProcessor<EntityModel<UserData>> {

    @Override
    public EntityModel<UserData> process(EntityModel<UserData> model) {
        UserData userData = model.getContent();
        if(userData != null){
            model.add(linkTo(methodOn(UserDataController.class).setExpired(userData.get_id()))
                    .withRel("data"));
        }
        return model;
    }
}
