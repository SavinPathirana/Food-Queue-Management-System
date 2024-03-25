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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class queuedetailsController implements Initializable {
    @FXML
    private TableView<Customer> table;

    @FXML
    private TableColumn<Customer, String> c1;

    @FXML
    private TableColumn<Customer, String> c2;

    @FXML
    private TableColumn<Customer, Integer> c3;

    @FXML
    private Label customerdetails;

    @FXML
    private TextField searchbar;

    @FXML
    private Label foundnotfound;

    @FXML
    private Button waitinglistbutton;
    @FXML
    private Stage waitinglist;
    private Scene waitingdetails;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<Customer> details= FXCollections.observableArrayList();
        for(int i=0;i<Main.clientlist1size();i++){
            details.add(new Customer(Main.returnclientlist1Firstname(i),Main.returnclientlist1Lastname(i),Main.returnclientlist1order(i)));
        }
        for(int i=0;i<Main.clientlist2size();i++){
           details.add(new Customer(Main.returnclientlist2Firstname(i),Main.returnclientlist2Lastname(i),Main.returnclientlist2order(i)));
       }
        for(int i=0;i<Main.clientlist3size();i++){
            details.add(new Customer(Main.returnclientlist3Firstname(i),Main.returnclientlist3Lastname(i),Main.returnclientlist3order(i)));
       }

        table.setItems(details);
        c1.setCellValueFactory(new PropertyValueFactory<Customer,String>("Firstname"));
        c2.setCellValueFactory(new PropertyValueFactory<Customer,String>("Lastname"));
        c3.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("order"));

    }
    public void searchresults(String Firstname,String Lastname,int burgercount){
        ObservableList<Customer> searchresults = FXCollections.observableArrayList();
        searchresults.add(new Customer(Firstname,Lastname,burgercount));
        customerdetails.setText(Firstname+" "+Lastname+" has ordered "+burgercount+" burgers.");
    }
    public void search(){
        for(int i=0;i<Main.waitingsize();i++) {
            if (i < Main.clientlist1size()) {
                if (searchbar.getText().equals(Main.returnclientlist1Firstname(i))) {
                    searchresults(Main.returnclientlist1Firstname(i), Main.returnclientlist1Lastname(i), Main.returnclientlist1order(i));
                    foundnotfound.setText("This customer is in queue 1.");
                    break;
                }
            }
             if (i<Main.clientlist2size()){
                 if (searchbar.getText().equals(Main.returnclientlist2Firstname(i))) {
                     searchresults(Main.returnclientlist2Firstname(i),Main.returnclientlist2Lastname(i),Main.returnclientlist2order(i));
                     foundnotfound.setText("This customer is in queue 2.");
                     break;
                 }
            }
             if(i<Main.clientlist3size()){
                 if (searchbar.getText().equals(Main.returnclientlist3Firstname(i))) {
                     searchresults(Main.returnclientlist3Firstname(i),Main.returnclientlist3Lastname(i),Main.returnclientlist3order(i));
                     foundnotfound.setText("This customer is in queue 3.");
                     break;
                 }
             }
             if(Main.waitinglistreturn(i)!=null){
                 if (searchbar.getText().equals(Main.waitingqueuereturnFirstname(i))) {
                     searchresults(Main.waitingqueuereturnFirstname(i), Main.waitingqueuereturnLastname(i), Main.waitingqueuereturnorder(i));
                     foundnotfound.setText("This customer is in the waiting list.");
                     break;
                 }
                 }
             else{
                 foundnotfound.setText("There is no customer with that name.");
             }
        }
    }
    public void nextwindow(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("waitinglist.fxml"));
            waitinglist=(Stage)((Node)event.getSource()).getScene().getWindow();
            waitingdetails=new Scene(root);
            waitinglist.setScene(waitingdetails);
            waitinglist.show();}catch (Exception e){}
    }
}
