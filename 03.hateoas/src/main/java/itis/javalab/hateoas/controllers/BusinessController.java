package itis.javalab.hateoas.controllers;

import itis.javalab.hateoas.services.ArtistsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RepositoryRestController
public class BusinessController {

    private final ArtistsService artistsService;

    @RequestMapping(value = "/businessman/{businessman-id}/deactivate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> deactivate(@PathVariable("businessman-id") Long businessmanId) {
        return ResponseEntity.ok(EntityModel.of(artistsService.deactivate(businessmanId)));
    }

}
