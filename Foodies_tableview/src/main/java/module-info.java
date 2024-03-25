module com.example.foodies_tableview {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.foodies_tableview to javafx.fxml;
    exports com.example.foodies_tableview;
}