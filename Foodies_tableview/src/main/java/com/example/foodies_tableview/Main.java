package com.example.foodies_tableview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("queuedetails.fxml"));
        stage.setTitle("Foodie Fave Queue Management System");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static FoodQueue clientlist1 = new FoodQueue(2);
    public static FoodQueue clientlist2 = new FoodQueue(3);
    public static FoodQueue clientlist3 = new FoodQueue(5);
    public static WaitingList waitinglist = new WaitingList(10);

    public static void main(String[] args) {
        Scanner option = new Scanner(System.in);
        String clients[] = new String[99];
        int[] burgerstock = new int[1]; //The array to store the burger stock
        burgerstock[0] = 50; //Number of burgers in the stock
        int income[] = new int[3];
        int[] waitingcount = new int[1];
        waitingcount[0] = 0;
        boolean loop = true;
        while (loop) { //Start of the while loop
            System.out.println("\n --------Menu--------");   //Prints the menu
            System.out.println(  //Prints the contents of the menu
                    """
                            100 or VFQ: View all Queues.
                            101 or VEQ: View all Empty Queues.
                            102 or ACQ: Add customer to a Queue.
                            103 or RCQ: Remove a customer from a Queue.
                            104 or PCQ: Remove a served customer.
                            105 or VCS: View Customers Sorted in alphabetical order.
                            106 or SPD: Store Program Data into file.
                            107 or LPD: Load Program Data from file.
                            108 or STK: View Remaining burgers Stock.
                            109 or AFS: Add burgers to Stock.
                            110 or IFQ: Income of each Queue.
                            112 or GUI: View Customer information.
                            999 or EXT: Exit the Program.
                            """);
            System.out.print("Select your option:");
            String chosenoption = option.next(); //A scanner to help the user choose menu options
            switch (chosenoption) {  //A switch case to guide the user to selected option
                case "100", "VFQ": {
                    View_all_queues(clientlist1, clientlist2, clientlist3,waitinglist); //The method for this option and its parameters
                    break; //This is used to prevent the program from entering to next option
                }          //The two comments above this comment are applicable for most of the switch cases below
                case "101", "VEQ": {
                    View_all_empty_queues(clientlist1, clientlist2, clientlist3);
                    break;
                }
                case "102", "ACQ": {
                    Add_customer_to_a_Queue(clientlist1, clientlist2, clientlist3, option,waitinglist,waitingcount);
                    break;
                }
                case "103", "RCQ": {
                    Remove_a_customer_from_a_Queue(clientlist1, clientlist2, clientlist3, option,waitinglist,waitingcount);
                    break;
                }
                case "104", "PCQ": {
                    Remove_a_served_customer(clientlist1, clientlist2, clientlist3, option, burgerstock,income,waitinglist, waitingcount);
                    break;
                }
                case "105", "VCS": {
                    View_Customers_Sorted_in_alphabetical_order(clientlist1, clientlist2, clientlist3, clients);
                    break;
                }
                case "106", "SPD": {
                    Store_Program_Data_into_file(burgerstock, clients, clientlist1, clientlist2, clientlist3);
                    break;
                }
                case "107", "LPD": {
                    Load_Program_Data_from_file(clientlist1, clientlist2, clientlist3, burgerstock, clients);
                    break;
                }
                case "108", "STK": {
                    View_Remaining_burgers_stock(burgerstock);
                    break;
                }
                case "109", "AFS": {
                    Add_burgers_to_Stock(option, burgerstock);
                    break;
                }
                case "110", "IFQ":{
                    Income_of_each_Queue(income);
                    break;
                }
                case "112","GUI":{
                    launch();
                    break;
                }
                case "999", "EXT": {
                    System.out.println("Thankyou for using this program."); //Exit message
                    loop = false; //Closing the while loop to exit the program
                }
            }
        }
    }
    private static void View_all_queues(FoodQueue clientlist1, FoodQueue clientlist2, FoodQueue clientlist3, WaitingList waitinglist) {
        System.out.println("""
                **************************
                *        Cashiers        *
                    1       2       3  
                """);
        for (int i = 1; i < 6; i++) {
            clientlist1.queuedetails(i);
            clientlist2.queuedetails(i);
            clientlist3.queuedetails(i);
            System.out.println();
        }
        System.out.println();
        System.out.println("**************************");
        System.out.println("X-Not Occupied     O-Occupied");
    }

    private static void View_all_empty_queues(FoodQueue clientlist1, FoodQueue clientlist2, FoodQueue clientlist3) {
        clientlist1.emptyqueues(1);
        clientlist2.emptyqueues(2);
        clientlist3.emptyqueues(3);

    }

    private static void Add_customer_to_a_Queue(FoodQueue clientlist1, FoodQueue clientlist2, FoodQueue clientlist3, Scanner option, WaitingList waitinglist, int[] waitingcount) {
        try {
            System.out.println("Enter the first name of the customer:");
            String Firstname = option.next();
            System.out.println("Enter the last name of the customer:");
            String Lastname = option.next();
            System.out.print("What is the order given by the customer:");
            int order = option.nextInt();
            if (clientlist1.size() == 0) {
                clientlist1.add(new Customer(Firstname, Lastname, order));
                System.out.println(Firstname + " " + Lastname + " has been added to Queue 1.");
            } else if (clientlist2.size() == 0) {
                clientlist2.add(new Customer(Firstname, Lastname, order));
                System.out.println(Firstname + " " + Lastname + " has been added to Queue 2.");
            } else if (clientlist3.size() == 0) {
                clientlist3.add(new Customer(Firstname, Lastname, order));
                System.out.println(Firstname + " " + Lastname + " has been added to Queue 3.");
            } else if (clientlist1.size() < clientlist2.size() && clientlist1.size() < clientlist3.size() && clientlist1.size() < 2) {
                clientlist1.add(new Customer(Firstname, Lastname, order));
                System.out.println(Firstname + " " + Lastname + " has been added to Queue 1.");
            } else if (clientlist2.size() < clientlist1.size() && clientlist2.size() < clientlist3.size() && clientlist2.size() < 3) {
                clientlist2.add(new Customer(Firstname, Lastname, order));
                System.out.println(Firstname + " " + Lastname + " has been added to Queue 2.");
            } else if (clientlist2.size() < clientlist3.size() && clientlist2.size() < 3) {
                clientlist2.add(new Customer(Firstname, Lastname, order));
                System.out.println(Firstname + " " + Lastname + " has been added to Queue 2.");
            } else if (clientlist3.size() < clientlist2.size() && clientlist3.size() < 5) {
                clientlist3.add(new Customer(Firstname, Lastname, order));
                System.out.println(Firstname + " " + Lastname + " has been added to Queue 3.");
            } else if (clientlist1.size() == clientlist2.size() && clientlist1.size()==clientlist3.size() &&clientlist1.size() < 2) {
                clientlist1.add(new Customer(Firstname, Lastname, order));
                System.out.println(Firstname + " " + Lastname + " has been added to Queue 1.");
            } else if (clientlist2.size() == clientlist3.size() && clientlist2.size() < 3) {
                clientlist2.add(new Customer(Firstname, Lastname, order));
                System.out.println(Firstname + " " + Lastname + " has been added to Queue 2.");
            } else if (clientlist3.size() < 5) {
                clientlist3.add(new Customer(Firstname, Lastname, order));
                System.out.println(Firstname + " " + Lastname + " has been added to Queue 3.");
            } else {
                System.out.println("All Queues are full");
                System.out.println(Firstname + " " + Lastname + " has been added to the Waiting List.");
                Customer waitdetails = new Customer(Firstname, Lastname, order);
                waitinglist.enqueue(waitdetails);
                waitingcount[0] = waitingcount[0] + 1;
            }
        }
        catch (Exception order){
            System.out.println("Please enter the order in Integer format.");
            option.next();
        }
    }

    private static void Remove_a_customer_from_a_Queue(FoodQueue clientlist1, FoodQueue clientlist2, FoodQueue clientlist3, Scanner option, WaitingList waitinglist, int[] waitingcount) {
        System.out.println("From which Queue do you want to remove the customer?");
        String remove = option.next();
        System.out.println("From which position do you want to remove the customer?");
        int index = option.nextInt();
        if (remove.equals("1")) {
            if (index > 2) {
                System.out.println("Please enter a valid position");
            }
            else if (index==0) {
                System.out.println("Please enter a valid position");
            }
            else {
                try {
                    int index1 = index - 1;
                    clientlist1.remprint(index1);
                    clientlist1.remove(index);
                    if (waitingcount[0] != 0) {
                        Customer waitingcustomer = waitinglist.dequeue();
                        clientlist1.add(waitingcustomer);
                        waitingcount[0] = waitingcount[0] - 1;
                    }
                } catch (Exception exception) {
                    System.out.println("There is no one in this position.");
                }
            }
        }
        else if (remove.equals("2")) {
            if (index > 3) {
                System.out.println("Please enter a valid position");
            }
            else if (index == 0) {
                System.out.println("Please enter a valid position");
            }
            else {
                try {
                    int index1 = index - 1;
                    clientlist2.remprint(index1);
                    clientlist2.remove(index);
                    if (waitingcount[0] !=0 ) {
                        Customer waitingcustomer = waitinglist.dequeue();
                        clientlist2.add(waitingcustomer);
                        waitingcount[0] = waitingcount[0]-1;
                    }
                }
                catch (Exception exception) {
                    System.out.println("There is no one in this position.");
                }
            }
        }
        else if (remove.equals("3")) {
            if (index > 5) {
                System.out.println("Please enter a valid position");
            } else if (index == 0) {
                System.out.println("Please enter a valid position");
            } else {
                try {
                    int index1 = index - 1;
                    clientlist3.remprint(index1);
                    clientlist3.remove(index);
                    if (waitingcount[0] !=0 ) {
                        Customer waitingcustomer = waitinglist.dequeue();
                        clientlist3.add(waitingcustomer);
                        waitingcount[0]= waitingcount[0]-1;
                    }
                }
                catch(Exception exception){
                    System.out.println("There is no one in this position.");
                }
            }
        }
        else{
            System.out.println("Please enter a valid Queue");
        }
    }

    private static void Remove_a_served_customer(FoodQueue clientlist1, FoodQueue clientlist2, FoodQueue clientlist3, Scanner option, int[] burgerstock, int[] income, WaitingList waitinglist, int[] waitingcount) {
        System.out.println("Which cashier are you?");
        int cashier = option.nextInt();
        if(cashier == 1){
            try {
                int burger = clientlist1.burgers(0);
                burgerstock[0] = burgerstock[0] - burger;
                income[0] = income[0] + burger;
                System.out.println("There are " + burgerstock[0] + " burgers left");
                clientlist1.remove(1);
                if (waitingcount[0] !=0 ) {
                    Customer waitingcustomer = waitinglist.dequeue();
                    clientlist1.add(waitingcustomer);
                    waitingcount[0]= waitingcount[0]-1;
                }
            }
            catch(Exception exception){
                System.out.println("There is no one in this queue.");
            }
        }
        else if(cashier == 2) {
            try {
                int burger = clientlist2.burgers(0);
                burgerstock[0] = burgerstock[0] - burger;
                income[1] = income[1] + burger;
                System.out.println("There are " + burgerstock[0] + " burgers left");
                clientlist2.remove(1);
                if (waitingcount[0] !=0 ) {
                    Customer waitingcustomer = waitinglist.dequeue();
                    clientlist2.add(waitingcustomer);
                    waitingcount[0]= waitingcount[0]-1;
                }
            }
            catch(Exception exception){
                System.out.println("There is no one in this queue.");
            }
        }
        if(cashier == 3) {
            try {
                int burger = clientlist3.burgers(0);
                burgerstock[0] = burgerstock[0] - burger;
                income[2] = income[2] + burger;
                System.out.println("There are " + burgerstock[0] + " burgers left");
                clientlist3.remove(1);
                if (waitingcount[0] !=0 ) {
                    Customer waitingcustomer = waitinglist.dequeue();
                    clientlist3.add(waitingcustomer);
                    waitingcount[0]=waitingcount[0]-1;
                }
            }
            catch(Exception exception){
                System.out.println("There is no one in this queue.");
            }
        }
        if (burgerstock[0]<=10){
            System.out.println("There are only "+ burgerstock[0] + " burgers left please restock. ");
        }
    }

    private static void View_Customers_Sorted_in_alphabetical_order(FoodQueue clientlist1, FoodQueue clientlist2, FoodQueue clientlist3, String[] clients) {
        for(int i=0;i<clients.length;i++){
            clients[i]= null;
        }
        int location =0;
        for(int i=0;i< clientlist1.size();i++){
            String name = clientlist1.customername(i);
            clients[location]=name;
            location++;
        }
        for(int i=0;i< clientlist2.size();i++) {
            String name = clientlist2.customername(i);
            clients[location] = name;
            location++;
        }
        for(int i=0;i< clientlist3.size();i++) {
            String name = clientlist3.customername(i);
            clients[location] = name;
            location++;
        }
        for (int i = 0; i < clients.length - 1; i++) {
            for (int num = i + 1; num < clients.length; num++) { //bubble sort is used to sort the names in alphabetical order
                if (clients[i] != null && clients[num] != null) {
                    if (0 < clients[i].compareToIgnoreCase(clients[num])) {
                        String order = clients[i];
                        clients[i] = clients[num];
                        clients[num] = order;
                    }
                }
            }
        }

        for (String element : clients) { //This will print the customers in the alphabetical order
            if (element != null) {
                System.out.println(element);
            }
        }
    }

    private static void Store_Program_Data_into_file(int[] burgerstock, String[] clients, FoodQueue clientlist1, FoodQueue clientlist2, FoodQueue clientlist3){
        clientlist1.textfile("clientlist1.txt");
        clientlist2.textfile("clientlist2.txt");
        clientlist3.textfile("clientlist3.txt");
        try {
            File file = new File("sales.txt");
            FileWriter textwrite = new FileWriter("sales.txt");
            textwrite.write("These are the number of burgers left:"); //The burger stock
            String strburgerstock = Integer.toString(burgerstock[0]);
            textwrite.write(strburgerstock + "\n");
            textwrite.write("These are all customers in alphabetical order:\n"); //The customers in alphabetical order
            for (String element : clients) {
                if(element==null){
                    textwrite.write("\n");
                }
                else {
                    textwrite.write(element + "\n");
                }
            }
            textwrite.close();
            System.out.println( file.getName()+" has been made");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void Load_Program_Data_from_file(FoodQueue clientlist1, FoodQueue clientlist2, FoodQueue clientlist3, int[] burgerstock, String[] clients) {
        clientlist1.loadtext("clientlist1.txt");
        clientlist2.loadtext("clientlist2.txt");
        clientlist3.loadtext("clientlist3.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sales.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("These are the number of burgers left:")) {
                    String[] items = line.split(":");//This will load data after ":" sign
                    burgerstock[0] = Integer.parseInt(items[1].trim());
                }
            }
            System.out.println("Data has been loaded from sales.txt.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void View_Remaining_burgers_stock(int[] burgerstock) {
        System.out.println("The remaining burger stock is " + burgerstock[0]);
    }

    private static void Add_burgers_to_Stock(Scanner option, int[] burgerstock) {
        System.out.println("How many burgers do you want to add?");
        Integer addedburgers = option.nextInt();
        burgerstock[0] = burgerstock[0] + addedburgers;
        if(burgerstock[0]>50){
            int rest = burgerstock[0]-50;
            System.out.println("The max capacity of burger stock is 50.The rest will be removed."); //A message to tell the user that the user has added more burgers than the burger limit of the stock
            System.out.println(rest+" burgers has been removed."); // This message wil show the user extra burger count and that they were removed
            burgerstock[0]=50;
        }
        else {
            System.out.println(addedburgers + " burgers has been added.New burger stock is " + burgerstock[0]); //A message to show that burgers has been added also the number of burgers that was added and the new burger stock
        }

    }
    private static void Income_of_each_Queue(int[] income) {
        int Q1income = income[0]*650;
        int Q2income = income[1]*650;
        int Q3income = income[2]*650;
        System.out.println("Income of Queue 1: "+ Q1income);
        System.out.println("Income of Queue 2: "+ Q2income);
        System.out.println("Income of Queue 3: "+ Q3income);
    }
    public static String returnclientlist1Firstname(int i){
        return clientlist1.returnFirstname(i);
    }
    public static String returnclientlist1Lastname(int i){
        return clientlist1.returnLastname(i);
    }
    public static int returnclientlist1order(int i){
        return clientlist1.returnOrder(i);
    }
    public static String returnclientlist2Firstname(int i){
        return clientlist2.returnFirstname(i);
    }
    public static String returnclientlist2Lastname(int i){
        return clientlist2.returnLastname(i);
    }
    public static int returnclientlist2order(int i){
        return clientlist2.returnOrder(i);
    }
    public static String returnclientlist3Firstname(int i){
        return clientlist3.returnFirstname(i);
    }
    public static String returnclientlist3Lastname(int i){
        return clientlist3.returnLastname(i);
    }
    public static int returnclientlist3order(int i){
        return clientlist3.returnOrder(i);
    }
    public static int clientlist1size(){
        return clientlist1.size();
    }
    public static int clientlist2size(){
        return clientlist2.size();
    }
    public static int clientlist3size(){
        return clientlist3.size();
    }
    public static Customer waitinglistreturn(int i){
        return waitinglist.returnindex(i);
    }
    public static String waitingqueuereturnFirstname(int i){
        return waitinglist.returnwaitingFirstname(i);
    }
    public static String waitingqueuereturnLastname(int i){
        return waitinglist.returnwaitingLastname(i);
    }
    public static int waitingqueuereturnorder(int i){
        return waitinglist.returnwaitingorder(i);
    }
    public static int waitingsize(){
        return waitinglist.maxsize();
    }
}
