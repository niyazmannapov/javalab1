package itis.javalab.hateoas.controllers;

import itis.javalab.hateoas.services.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @RequestMapping(value = "/sales/{sales-id}/add_sale/{sale-id}")
    @ResponseBody
    public ResponseEntity<?> addSong(@PathVariable("sales-id") Long salesId,
                                     @PathVariable("sale-id") Long saleId) {
        return ResponseEntity.ok(EntityModel.of(salesService.addSale(salesId, saleId)));
    }
}
