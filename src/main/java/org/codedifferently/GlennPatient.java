package org.codedifferently;

import java.util.concurrent.atomic.AtomicInteger;

public class GlennPatient {
    private static AtomicInteger idCounter = new AtomicInteger(1000);
    private int id;
    private String name;
    private boolean checkedIn;
    //private String priority; // Emergency , Reg, Follow

    public GlennPatient() {
        this.id = idCounter.getAndIncrement();
        this.checkedIn = false;

    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

   /* public String getPriority() {
        return priority;
    }

    */



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


    public String toString() {
        return "ID: " + id + " | Name: " + name  + " | " + "| Checked In: " + checkedIn;
    }

}
