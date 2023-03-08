package com.example.socialnetwork_javafx.repository;

import com.example.socialnetwork_javafx.exceptions.RepositoryException;
import com.example.socialnetwork_javafx.exceptions.ValidationException;

public interface Repository<E, ID>{

    E find(ID id) throws RepositoryException;
    Iterable<E> findAll();
    E save(E entity) throws RepositoryException, ValidationException;
    E delete(E entity) throws RepositoryException, ValidationException;
    E update(E updateEntity) throws RepositoryException;
}