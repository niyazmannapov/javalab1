package ru.itis.niyaz.mongo.repository.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "data")
public class UserData {
    @Id
    private String documentId;
    private String title;
    private String author;
    private String userData;
}
