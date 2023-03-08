package com.example.socialnetwork_javafx.domain.validators;

import com.example.socialnetwork_javafx.domain.User;
import com.example.socialnetwork_javafx.exceptions.ValidationException;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User user) throws ValidationException {
        String message = "";
        if (user.getUsername() == null || user.getUsername().trim().length() == 0) {
            message += "Username can not be empty!\n";
        }
        if (user.getEmail() == null || user.getEmail().trim().length() == 0) {
            message += "Email can not be empty!\n";
        }
        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
            message += "Password can not be empty!\n";
        }
        if (message.length() > 0) {
            throw new ValidationException(message);
        }
    }
}
