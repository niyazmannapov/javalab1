package ru.itis.niyaz.html;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Input {
    private String type;
    private String name;
    private String id;
    private String placeholder;



}
