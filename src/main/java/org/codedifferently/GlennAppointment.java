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

    public String getTimeSlot() {
        return timeSlot;
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

    public void setPatient(GlennPatient patient) {
        if (patient != null) {
            this.patient = patient;
        } else {
            System.out.println("Invalid patient");
        }
    }

    public void setTimeSlot(String timeSlot) {
        if (timeSlot != null && !timeSlot.trim().isEmpty()) {
            this.timeSlot = timeSlot;
        } else {
            System.out.println("Invalid Time");
        }
    }

    public void setCompleted(boolean completed) {
        if (!cancelled) {
            this.completed = completed;
        } else {
            System.out.println("Appointment was cancelled");
        }
    }

    public void setCancelled(boolean cancelled) {
        if (!completed) {
            this.cancelled = cancelled;
        } else {
            System.out.println("Appointment was completed");
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
