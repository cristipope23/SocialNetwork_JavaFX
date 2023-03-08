package com.example.socialnetwork_javafx.repository;

import com.example.socialnetwork_javafx.domain.Request;
import com.example.socialnetwork_javafx.utils.DBAccess;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDBRepo implements Repository<Request, Integer> {
    private final DBAccess dbAccess = new DBAccess();


    @Override
    public Request save(Request entity) {
        String query = "INSERT INTO requests(id_request, username1, username2, request_time, status) VALUES(?, ?, ?,?,?)";
        try (Connection connection = dbAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getUsername1());
            statement.setString(3, entity.getUsername2());
            statement.setTimestamp(4, Timestamp.valueOf(entity.getRequestTime()));
            statement.setString(5, entity.getStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public Request delete(Request request) {
        String query = "DELETE FROM requests WHERE id_request = ?";

        try (Connection connection = dbAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, request.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return find(request.getId());
    }

    @Override
    public Request find(Integer id) {
        String query = "SELECT * FROM requests WHERE id_request=?";
        Request request = null;
        try (Connection connection = dbAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username1 = resultSet.getString("username1");
                String username2 = resultSet.getString("username2");
                LocalDateTime requestTime = resultSet.getTimestamp("request_time").toLocalDateTime();
                String status = resultSet.getString("status");
                request = new Request(id, username1, username2, requestTime, status);
                request.setId(id);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public Request update(Request entity) {
        String query = "UPDATE requests SET status=? WHERE id_request = ?";

        try (Connection connection = dbAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, entity.getStatus());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public Iterable<Request> findAll() {
        List<Request> requests = new ArrayList<>();

        String query = "SELECT * from requests";
        try (Connection connection = dbAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                int idRequest = resultSet.getInt("id_request");
                String username1 = resultSet.getString("username1");
                String username2 = resultSet.getString("username2");
                LocalDateTime requestTime = resultSet.getTimestamp("request_time").toLocalDateTime();
                String status = resultSet.getString("status");

                Request request = new Request(idRequest, username1, username2, requestTime, status);
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }
}
