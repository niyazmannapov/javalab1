package ru.itis.niyaz.mongo.hateoas.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.niyaz.mongo.hateoas.services.UserDataService;


@RepositoryRestController
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataService userDataService;


    @RequestMapping(value = "/texts/{data}",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> setExpired(@PathVariable("data") String data){
        return ResponseEntity.ok(
                EntityModel.of(userDataService.findByData(data))
        );
    }
}
