package org.codedifferently;

import java.util.concurrent.atomic.AtomicInteger;


public class GlennPatient {
    //Properties
    // Makes it so our id counter is going up by 1 everytime someone is added
    private static AtomicInteger idCounter = new AtomicInteger(1000);
    private int id;
    private String name;
    private boolean checkedIn;
    private String priority; // Emergency , Reg, Follow

//Constructor
    public GlennPatient() {
        this.id = idCounter.getAndIncrement();
        this.checkedIn = false;

    }
//Getters
    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

//Setters
    public void setPriority(String priority){
        this.priority = priority.toUpperCase();
    }

    public String getPriority(){
        return priority;
    }
//Methods
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid Name.");
        }
    }


    public void checkIn() {
        this.checkedIn = true;
    }

//toString
    public String toString() {
        return "ID: " + id + " | Name: " + name  + " | " + "| Checked In: " + checkedIn;
    }

}
