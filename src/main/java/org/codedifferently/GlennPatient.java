package org.codedifferently;

public class GlennPatient {
    private int idCounter = 1;
    private int id;
    private String name;
    private boolean checkedIn;

    public void glennPatient(String name) {
        this.id = idCounter++;
        this.name = name;
        this.checkedIn = false;

    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public boolean isCheckedIn(){
        return checkedIn;
    }
    public void checkIn(){
        this.checkedIn =true;
    }
    public String toString(){
        return "ID: " +id +" | Name: " +name+"| Checked In: " + checkedIn;
    }

}
