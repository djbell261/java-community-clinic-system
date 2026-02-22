package org.codedifferently;

public class GlennAppointment {
    private String timeSlot;
    private GlennPatient patient;
    private boolean completed;
    private boolean cancelled;

    public GlennAppointment(String timeSlot, GlennPatient patient) {
        this.timeSlot = timeSlot;
        this.patient = patient;
        this.completed = false;
        this.cancelled = false;
    }



    public GlennPatient getPatient() {
        return patient;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isCancelled() {
        return cancelled;
    }


    public void setTimeSlot(String timeSlot) {
        if (timeSlot != null && !timeSlot.trim().isEmpty()) {
            this.timeSlot = timeSlot;
        } else {
            System.out.println("Invalid Time");
        }
    }




    public void complete() {
        if (cancelled) {
            System.out.println("Appointment was cancelled");
            return;
        }
        if (completed) {
            System.out.println("Appointment was already completed");
            return;
        }
        completed = true;
        System.out.println("Appointment was check as completed");
    }

    public void cancel() {
        if (completed) {
            System.out.println("Can't cancel completed appointment");
            return;
        }
        if (cancelled) {
            System.out.println("Appointment already canceled");
            return;
        }
        cancelled = true;
        System.out.println("Appointment already canceled");
    }

    public String toString() {
        return timeSlot + "|" + patient.getName()  + " | Completed:  " + completed + " | Canceled: " + cancelled;
    }


}
