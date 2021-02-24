package ru.itis.niyaz.mongo.hateoas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.niyaz.mongo.hateoas.models.UserData;


public interface UserDataRepository extends MongoRepository<UserData, String> {

    UserData findByData(String data);

}
