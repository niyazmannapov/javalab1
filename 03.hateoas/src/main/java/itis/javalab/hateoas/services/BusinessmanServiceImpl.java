package itis.javalab.hateoas.services;

import itis.javalab.hateoas.models.Businessman;
import itis.javalab.hateoas.repositories.BusinessmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessmanServiceImpl implements ArtistsService {

    @Autowired
    private BusinessmanRepository businessmanRepository;

    @Override
    public Businessman deactivate(Long id) {
        Businessman businessman = businessmanRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        businessman.deactivate();
        businessmanRepository.save(businessman);
        return businessman;
    }
}
