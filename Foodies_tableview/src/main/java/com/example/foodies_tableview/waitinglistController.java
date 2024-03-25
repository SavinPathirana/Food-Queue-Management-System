
package com.example.foodies_tableview;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.Stage;

        import java.net.URL;
        import java.util.ResourceBundle;

public class waitinglistController implements Initializable {
    @FXML
    private TableView<Customer> table2;

    @FXML
    private TableColumn<Customer, String> c01;

    @FXML
    private TableColumn<Customer, String> c02;

    @FXML
    private TableColumn<Customer, Integer> c03;
    @FXML
    private Button backbutton;

    @FXML
    private Stage back;
    private Scene goback;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Customer> info= FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            if(Main.waitinglistreturn(i) != null) {
                info.add(new Customer(Main.waitingqueuereturnFirstname(i), Main.waitingqueuereturnLastname(i), Main.waitingqueuereturnorder(i)));
            }
        }
        table2.setItems(info);
        c01.setCellValueFactory(new PropertyValueFactory<Customer,String>("Firstname"));
        c02.setCellValueFactory(new PropertyValueFactory<Customer,String>("Lastname"));
        c03.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("order"));
    }
    public void goback(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("queuedetails.fxml"));
            back=(Stage)((Node)event.getSource()).getScene().getWindow();
            goback=new Scene(root);
            back.setScene(goback);
            back.show();}catch (Exception e){}
    }
}
