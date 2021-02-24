package ru.itis.niyaz.mongo.hateoas.services;

import ru.itis.niyaz.mongo.hateoas.models.UserData;

public interface UserDataService {

    UserData findByData(String data);
}
