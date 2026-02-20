package org.codedifferently;

import java.util.concurrent.atomic.AtomicInteger;

public class GlennPatient {
    private static AtomicInteger idCounter = new AtomicInteger(1000);
    private int id;
    private int age;
    private String name;
    private boolean checkedIn;
    private String priority; // Emergency , Reg, Follow

    public void glennPatient(String name) {
        this.id = idCounter.getAndIncrement();
        this.name = name;
        this.age = age;
        this.priority = priority;
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

    public String getPriority() {
        return priority;
    }

    public int setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("Invalid ID.");
        }
        return id;
    }

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
        return "ID: " + id + " | Name: " + name + "Age: " + age + " | Priority: " + priority + "| Checked In: " + checkedIn;
    }

}
