package ru.itis.niyaz.model;

import ru.itis.niyaz.annotation.HtmlForm;
import ru.itis.niyaz.annotation.HtmlInput;

@HtmlForm(method = "post", action = "/user")
public class User {

    @HtmlInput(name = "login")
    private String login;
    @HtmlInput(name = "email")
    private String email;
    @HtmlInput(type = "password", name = "password")
    private String password;

}
