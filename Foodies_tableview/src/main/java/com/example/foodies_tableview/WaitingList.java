package com.example.foodies_tableview;

public class WaitingList {
    private Customer[] q;
    private int maxsize;
    private int front;
    private int rear;

    public WaitingList(int maxsize) {
        this.q = new Customer[maxsize];
        this.maxsize = maxsize;
        this.rear = -1;
        this.front = -1;
    }
    public void enqueue(Customer customer) {
        if ((front == 0 && rear == maxsize - 1) || (front == rear + 1)) {
            System.out.println("Queue is full.");
        } else {
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % this.maxsize;
            q[rear] = customer;
        }
    }

    public Customer dequeue() {
        if (front == -1) {
            throw new IllegalStateException("There is no one waiting in waiting queue.");
        }
        Customer wait = q[front];
        q[front] = null;
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % this.maxsize;
        }
        return wait;
    }
    public int maxsize(){
        return q.length;
    }
    public String returnwaitingFirstname(int i){
        return q[i].getFirstname();
    }
    public String returnwaitingLastname(int i){
        return q[i].getLastname();
    }
    public int returnwaitingorder(int i){
        return q[i].getOrder();
    }
    public Customer returnindex(int i){
        return q[i];
    }
}