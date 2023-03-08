package com.example.socialnetwork_javafx;

import com.example.socialnetwork_javafx.domain.Request;
import com.example.socialnetwork_javafx.domain.User;
import com.example.socialnetwork_javafx.domain.validators.FriendshipValidator;
import com.example.socialnetwork_javafx.domain.validators.UserValidator;
import com.example.socialnetwork_javafx.repository.FriendshipDBRepo;
import com.example.socialnetwork_javafx.repository.MessageDBRepo;
import com.example.socialnetwork_javafx.repository.RequestDBRepo;
import com.example.socialnetwork_javafx.repository.UserDBRepo;
import com.example.socialnetwork_javafx.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestController {
    UserValidator userValidator = new UserValidator();
    FriendshipValidator friendshipValidator = new FriendshipValidator();
    UserDBRepo userDBRepo = new UserDBRepo(userValidator);
    FriendshipDBRepo friendshipDBRepo = new FriendshipDBRepo(friendshipValidator);
    RequestDBRepo requestDBRepo = new RequestDBRepo();
    MessageDBRepo messageDBRepo = new MessageDBRepo();
    Service service = Service.getInstance(userDBRepo,friendshipDBRepo,requestDBRepo,messageDBRepo);
    ObservableList<Request> model = FXCollections.observableArrayList();

    private void initModel() {
        String username = service.getUser().getUsername();
        List<Request> requests = new ArrayList<>();
        for (Request request : service.getAllRequests()) {
            if (request.getUsername2().equals(username)) {
                requests.add(request);
            }
        }
        model.setAll(requests);
    }

    @FXML
    private TableView<Request> requestsTableView;
    @FXML
    private TableColumn<Request, Long> idColumn;
    @FXML
    private TableColumn<Request, String> usernameColumn;
    @FXML
    private TableColumn<Request, LocalDateTime> timeColumn;
    @FXML
    private TableColumn<Request, String> statusColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username1"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("requestTime"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        requestsTableView.setItems(model);
        initModel();
    }

    public void acceptFriend(ActionEvent event){
        Request requestConfirm = requestsTableView.getSelectionModel().getSelectedItem();
        switch (requestConfirm.getStatus()) {
            case "pending.." -> {
                int id1 = service.getUser().getId();
                String username = requestConfirm.getUsername1();
                Integer id2 = null;
                for (User user : service.findAllUsers()) {
                    if (user.getUsername().equals(username)) {
                        id2 = user.getId();
                    }
                }
                service.addFriendship(id1, id2);
                requestConfirm.setStatus("confirmed!");
                service.updateRequest(requestConfirm);
                initModel();
            }
            case "confirmed!" -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Invalid data!");
                alert.setContentText("Request already confirmed!");
                alert.show();
            }
            case "declined!" -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Invalid data!");
                alert.setContentText("Request already declined!");
                alert.show();
            }
        }
    }

    public void deleteFriend(ActionEvent event){
        Request requestDeclined = requestsTableView.getSelectionModel().getSelectedItem();
        switch (requestDeclined.getStatus()) {
            case "pending.." -> {
                requestDeclined.setStatus("declined!");
                service.updateRequest(requestDeclined);
                initModel();
            }
            case "sent..." -> {
                requestDeclined.setStatus("Canceled!");
                service.deleteRequest(requestDeclined.getId());
                initModel();
            }
            case "confirmed!" -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Invalid data!");
                alert.setContentText("Request already confirmed!");
                alert.show();
            }
            case "declined!" -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Invalid data!");
                alert.setContentText("Request already declined!");
                alert.show();
            }
        }
    }

    public void returnToUserView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UtilizatorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("NetworkFX");
        stage.setScene(scene);
        stage.show();
    }
}
