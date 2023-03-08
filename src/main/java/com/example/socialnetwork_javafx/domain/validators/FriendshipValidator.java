package com.example.socialnetwork_javafx.domain.validators;

import com.example.socialnetwork_javafx.domain.Friendship;
import com.example.socialnetwork_javafx.exceptions.ValidationException;

import java.util.Objects;

public class FriendshipValidator implements Validator<Friendship> {

    @Override
    public void validate(Friendship entity) throws ValidationException {
        String errMsg = "";
        if(Objects.equals(entity.getIDU1(), entity.getIDU2()))
            errMsg+="Ids must be different";
        if (errMsg.length() > 0) {
            throw new ValidationException(errMsg);
        }
    }
}
