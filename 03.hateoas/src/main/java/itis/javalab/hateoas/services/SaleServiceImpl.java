package itis.javalab.hateoas.services;

import itis.javalab.hateoas.models.Sale;
import itis.javalab.hateoas.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    @Override
    public Sale changeName(Long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseThrow(IllegalAccessError::new);
        sale.setLength(5);
        saleRepository.save(sale);
        return sale;
    }
}
