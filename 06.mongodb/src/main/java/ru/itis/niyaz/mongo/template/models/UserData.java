package ru.itis.niyaz.mongo.template.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private String documentId;
    private String title;
    private String author;
    private String data;
}
