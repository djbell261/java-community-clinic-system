package org.codedifferently;

public class GlennAppointment {
    private String timeSlot;
    private  GlennPatient patient;
    private boolean completed;

    public void glennAppointment(String timeSlot, GlennPatient patient)
    {
        this.timeSlot=timeSlot;
        this.patient=patient;
        this.completed=false;
    }

public String getTimeSlot(){
        return timeSlot;
}
public GlennPatient getPatient(){
        return patient;
}
public boolean isCompleted(){
        return completed;
}
public void markCompleted(){
        completed=true;
}

public String toString(){
        return timeSlot + "|" + patient.getName() + " | Priority: " +patient.getPriority() +" | Completed:  " +completed;
}


}
