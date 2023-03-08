package com.example.socialnetwork_javafx.repository;

import com.example.socialnetwork_javafx.domain.User;
import com.example.socialnetwork_javafx.domain.validators.UserValidator;
import com.example.socialnetwork_javafx.exceptions.RepositoryException;
import com.example.socialnetwork_javafx.exceptions.ValidationException;
import com.example.socialnetwork_javafx.utils.DBAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBRepo implements Repository<User, Integer>{
    private final DBAccess dbAccess = new DBAccess();
    private final UserValidator validator;
    public UserDBRepo(UserValidator validator) {
        this.validator = validator;
    }

    @Override
    public User find(Integer id) throws RepositoryException {
        String sql = "SELECT * FROM users WHERE id =?";
        User user = null;
        try(Connection connection = dbAccess.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int idUser = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String email = resultSet.getString("email");
                String parola = resultSet.getString("parola");

                user = new User(idUser, nume, parola, email);

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        //open connection to DB
        //if exception is thrown, connection is lost
        try(Connection connection = dbAccess.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                int idUser = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String email = resultSet.getString("email");
                String parola = resultSet.getString("parola");

                User user = new User(idUser, nume, parola, email);
                users.add(user);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User save(User user) throws RepositoryException, ValidationException {
        String sql = "INSERT INTO users(id,nume,email,parola) VALUES(?,?,?,?)";
        validator.validate(user);
        try(Connection connection = dbAccess.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2,user.getUsername());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User delete(User user) throws RepositoryException, ValidationException {
        String sql = "DELETE FROM users WHERE id = ?";
        validator.validate(user);
        try(Connection connection = dbAccess.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User user) throws RepositoryException {
        String sql = "UPDATE users set parola = ? where id = ?";

        try(Connection connection = dbAccess.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,user.getId());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

