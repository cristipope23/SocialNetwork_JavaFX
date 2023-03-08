module com.example.socialnetwork_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.socialnetwork_javafx to javafx.fxml;
    opens com.example.socialnetwork_javafx.domain to javafx.fxml,javafx.base;
    exports com.example.socialnetwork_javafx;
}