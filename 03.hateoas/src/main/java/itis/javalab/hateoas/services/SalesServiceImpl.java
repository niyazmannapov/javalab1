package itis.javalab.hateoas.services;

import itis.javalab.hateoas.models.Sale;
import itis.javalab.hateoas.models.Sales;
import itis.javalab.hateoas.repositories.SalesRepository;
import itis.javalab.hateoas.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SaleRepository saleRepository;

    public Sale addSale(Long salesId, Long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseThrow(IllegalArgumentException::new);
        Sales sales = salesRepository.findById(salesId).orElseThrow(IllegalArgumentException::new);
        if (!sales.getSales().contains(sale)) {
            sales.getSales().add(sale);
            salesRepository.save(sales);
        }
        return sale;
    }
}
