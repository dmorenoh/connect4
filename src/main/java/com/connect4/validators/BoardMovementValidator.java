package com.connect4.validators;

import com.connect4.model.GameMovement;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BoardMovementValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return GameMovement.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GameMovement boardMovement = (GameMovement) target;
        if (boardMovement.getPlayer() == null || boardMovement.getPlayer().getTokenType() == null) {
            errors.rejectValue("player", "empty.player");
        }
        if (boardMovement.getColumn() == null) {
            errors.rejectValue("column", "empty.column");
        }
    }
}
