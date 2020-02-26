package com.dev.cinema.dto;

import javax.validation.constraints.NotNull;

public class UserResponseDto {
    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
