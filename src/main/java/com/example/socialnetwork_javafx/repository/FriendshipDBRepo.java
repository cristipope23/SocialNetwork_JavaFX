package com.example.socialnetwork_javafx.repository;

import com.example.socialnetwork_javafx.domain.Friendship;
import com.example.socialnetwork_javafx.domain.User;
import com.example.socialnetwork_javafx.domain.validators.FriendshipValidator;
import com.example.socialnetwork_javafx.domain.validators.UserValidator;
import com.example.socialnetwork_javafx.exceptions.RepositoryException;
import com.example.socialnetwork_javafx.utils.Constants;
import com.example.socialnetwork_javafx.utils.DBAccess;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FriendshipDBRepo implements Repository<Friendship, Integer>{
    private final DBAccess dbAccess = new DBAccess();
    FriendshipValidator friendshipValidator = new FriendshipValidator();

    public FriendshipDBRepo(FriendshipValidator validator) {
        this.friendshipValidator = validator;
    }

    @Override
    public Friendship find(Integer id) {
        String sql = "SELECT * FROM friendships WHERE id =?";
        Friendship friendship = null;
        try(Connection connection = dbAccess.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int idUser1 = resultSet.getInt("id_user1");
                int idUser2 = resultSet.getInt("id_user2");
                String date = resultSet.getString("date");

                friendship = new Friendship(id, idUser1, idUser2, date);
                friendship.setId(id);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return friendship;
    }

    @Override
    public List<Friendship> findAll() {
        List<Friendship> friendships = new ArrayList<>();

        //open connection to DB
        //if exception is thrown, connection is lost
        try(Connection connection = dbAccess.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM friendships");
            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                int idFriendship = resultSet.getInt("id");
                int idUser1 = resultSet.getInt("id_user1");
                int idUser2 = resultSet.getInt("id_user2");
                String date = resultSet.getString("date");

                Friendship friendship = new Friendship(idFriendship, idUser1, idUser2, date);
                friendships.add(friendship);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public Friendship save(Friendship friendship) {
        String sql = "INSERT INTO friendships(id, id_user1, id_user2, date) VALUES(?,?,?,?)";

        try(Connection connection = dbAccess.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, friendship.getId());
            preparedStatement.setInt(2, friendship.getIDU1());
            preparedStatement.setInt(3, friendship.getIDU2());
            preparedStatement.setString(4,friendship.getDate());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Friendship delete(Friendship friendship) {
        String sql = "DELETE FROM friendships WHERE id = ?";

        try(Connection connection = dbAccess.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, friendship.getId());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Friendship update(Friendship update) {
        String sql = "UPDATE friendships set id_user1=?, id_user2=? where id = ?";

        try(Connection connection = dbAccess.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, update.getIDU1());
            preparedStatement.setInt(2, update.getIDU2());
            preparedStatement.setInt(3, update.getId());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
