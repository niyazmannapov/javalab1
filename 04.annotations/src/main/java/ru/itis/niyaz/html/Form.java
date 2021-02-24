package ru.itis.niyaz.html;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Form {
    private String action;
    private String method;
    private String enctype;
    private List<Input> inputs;

    public Form(String action, String method,String enctype) {
        this.action = action;
        this.method = method;
        this.enctype = enctype;
    }
}

