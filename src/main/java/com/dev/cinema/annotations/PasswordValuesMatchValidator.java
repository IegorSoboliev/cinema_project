package com.dev.cinema.annotations;

import com.dev.cinema.dto.UserRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValuesMatchValidator implements
        ConstraintValidator<PasswordValuesMatch, UserRequestDto> {

    @Override
    public boolean isValid(UserRequestDto userRequestDto, ConstraintValidatorContext
            constraintValidatorContext) {
        return userRequestDto.getPassword().equals(userRequestDto.getRepeatePassword());
    }
}
