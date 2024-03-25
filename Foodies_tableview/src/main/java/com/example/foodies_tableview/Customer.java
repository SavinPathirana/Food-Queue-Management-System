package com.example.foodies_tableview;

import java.io.Serializable;

public class Customer implements Serializable {
    private String Firstname;
    private String Lastname;
    private int order;



    public String Fullname(){
        return Firstname + " " +Lastname;
    }
    public String getFirstname(){
        return Firstname;
    }
    public String getLastname(){
        return Lastname;
    }
    public int getOrder(){
        return order;
    }
    public Customer (String Firstname,String Lastname,int order){
        this.Firstname=Firstname;
        this.Lastname=Lastname;
        this.order=order;
    }
    public int Output (){
        System.out.println(Firstname + " " + Lastname+ " has been served " + order +" burgers.");
        return order;
    }




}
