package ru.itis.niyaz.mongo.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "texts")
public class UserData {

    private String _id;

    private String title;

    private String author;

    private String data;
}
