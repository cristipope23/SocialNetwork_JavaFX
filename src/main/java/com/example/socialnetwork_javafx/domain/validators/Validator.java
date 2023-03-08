package com.example.socialnetwork_javafx.domain.validators;

import com.example.socialnetwork_javafx.exceptions.ValidationException;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
