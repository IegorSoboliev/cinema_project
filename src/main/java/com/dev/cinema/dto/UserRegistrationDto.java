package com.dev.cinema.dto;

import com.dev.cinema.annotations.EmailConstraint;
import com.dev.cinema.annotations.PasswordValuesMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordValuesMatch
public class UserRegistrationDto {
    @NotNull
    @EmailConstraint
    private String email;
    @NotNull
    @Size(min = 1)
    private String password;
    @NotNull
    @Size(min = 1)
    private String repeatePassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatePassword() {
        return repeatePassword;
    }

}
