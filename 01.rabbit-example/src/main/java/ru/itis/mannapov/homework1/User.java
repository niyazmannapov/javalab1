package ru.itis.mannapov.homework1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String passNumber;
    private Integer age;
    private Date startDate;

}
