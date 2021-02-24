package ru.itis.niyaz.mongo.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.niyaz.mongo.hateoas.models.UserData;
import ru.itis.niyaz.mongo.hateoas.repositories.UserDataRepository;

@Service
public class UserDataServiceImpl implements UserDataService {


    @Autowired
    private UserDataRepository userDataRepository;


    public UserData findByData(String data) {
        UserData userData = userDataRepository.findByData(data);
        return userData;
    }


}
