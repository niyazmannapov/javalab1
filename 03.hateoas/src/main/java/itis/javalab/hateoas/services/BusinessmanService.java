package itis.javalab.hateoas.services;

import itis.javalab.hateoas.models.Businessman;

public interface BusinessmanService {
    Businessman deactivate(Long id);
}
