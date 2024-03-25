package com.example.foodies_tableview;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;


public class FoodQueue {
    private int maxsize;
    private ArrayList<Customer> clients = new ArrayList<>(maxsize);

    public FoodQueue(int maxsize) {
        this.maxsize = maxsize;
    }

    public void queuedetails(int i) {
        if (maxsize < i) {
            System.out.print("\t");
            System.out.print(" ");
            System.out.print("\t");
        } else if (clients.size() == i) {
            System.out.print("\t");
            System.out.print("O");
            System.out.print("\t");
        } else if (clients.size() > i) {
            System.out.print("\t");
            System.out.print("O");
            System.out.print("\t");
        } else {
            System.out.print("\t");
            System.out.print("X");
            System.out.print("\t");
        }
    }

    public void emptyqueues(int i) {
        if (clients.size() < maxsize) {
            int emptyslots= maxsize- (clients.size());
            System.out.print("Queue " + i + " has " + emptyslots + " slots left.");
            System.out.print("\t");
        }
        else{
            System.out.print("Queue " + i + " is Full.");
            System.out.print("\t");
        }
    }

    public int size() {
        return clients.size();
    }

    public void add(Customer customer) {
        clients.add(customer);
    }

    public void remove(int index) {
        index = index - 1;
        if (clients.size() != 0) {
            if (index >= 0 && index < clients.size()) {
                clients.remove(index);
            }
        }
    }

    public void serve() {
        if (clients.size() != 0) ;
        clients.remove(0);
    }

    public void remprint(int index1) {
        String f1 = clients.get(index1).Fullname();
        System.out.println(f1 + " has been removed");
    }

    public int burgers(int index1) {
        int bur = clients.get(index1).Output();
        return bur;
    }

    public String customername(int i) {
        return clients.get(i).Fullname();
    }

    public void textfile(String outputtxt) {
        try {
            File file = new File(outputtxt);
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream obj = new ObjectOutputStream(output);
            Iterator ex = clients.iterator();
            for (int i = 0; i < clients.size(); i++) {
                Customer write = (Customer) ex.next();
                obj.writeObject(write);
            }
            output.close();
            obj.close();
            System.out.println(outputtxt + " has been made.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void loadtext(String outputtxt) {
        try {
            FileInputStream input = new FileInputStream(outputtxt);
            ObjectInputStream objinput = new ObjectInputStream(input);
            while (true) {
                try {
                    Customer info = (Customer) objinput.readObject();
                    clients.add(info);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Data has been loaded from " + outputtxt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String returnFirstname(int i){
        return clients.get(i).getFirstname();
    }
    public String returnLastname(int i){
        return clients.get(i).getLastname();
    }
    public int returnOrder(int i){
        return clients.get(i).getOrder();
    }
}

