package com.example.binarplus.web02.validator;

import com.example.binarplus.web02.domain.Pengguna;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Pengguna.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Pengguna pengguna = (Pengguna) o;

        if (pengguna.getPassword().length()<6){
            errors.rejectValue("password", "lenght", "password must be at least 6 char");
        }
        if (!pengguna.getPassword().equals(pengguna.getReTypePass())) {
            errors.rejectValue("reTypePass", "match", "password must match");
        }
    }
}
