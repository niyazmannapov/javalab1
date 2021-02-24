package ru.itis.niyaz.mongo.repository.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.itis.niyaz.mongo.repository.models.User;
import ru.itis.niyaz.mongo.repository.models.UserData;


public interface DataRepository extends MongoRepository<UserData, String> {

    UserData findAllByAuthor(User user);

}
