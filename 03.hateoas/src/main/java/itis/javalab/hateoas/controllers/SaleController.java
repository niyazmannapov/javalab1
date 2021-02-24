package itis.javalab.hateoas.controllers;

import itis.javalab.hateoas.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;


    @RequestMapping(value = "/sale/{sale-id}/change", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> changeName(@PathVariable("sale-id") Long saleId) {
        return ResponseEntity.ok(EntityModel.of(saleService.changeName(saleId)));
    }
}
