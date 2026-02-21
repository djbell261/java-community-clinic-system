package org.codedifferently;


import java.util.ArrayList;
import java.util.Scanner;

public class GlennClinicSystem {

    private final ArrayList<GlennPatient> patients = new ArrayList<>();
    private final ArrayList<GlennAppointment> appointments = new ArrayList<>();

    private final String[] timeSlots = {"9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM"};
    private final GlennAppointment[] schedule = new GlennAppointment[timeSlots.length];

    // Use ONE scanner for the system
    private final Scanner scan = new Scanner(System.in);


    public void patientList(){

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }


        for (GlennPatient p : patients) {
            System.out.println("ID: " + p.getId()
                    + " | Name: " + p.getName()
                    + " | Checked In: " + p.isCheckedIn());
        }

    }

    public void addPatient(String name){


        GlennPatient p = new GlennPatient();
        p.setName(name);

        patients.add(p);

        System.out.println("Patient added successfully!");
        System.out.println("Patient ID: " + p.getId());


    }

    public void checkPatientIn(){

        System.out.println("Enter Patient ID to check");
        int patientId = scan.nextInt();

        scan.nextLine(); // consume leftover newline

        boolean found = false;

        for (GlennPatient p : patients) {
            if (p.getId() == patientId) {
                p.checkIn(); // ✅ THIS is what actually changes checkedIn to true
                System.out.println("Patient " + p.getName() + " checked in successfully.");
                found = true;
                break; // stop looping once we found them
            }
        }

        if (!found) {
            System.out.println("No patient found with ID: " + patientId);
        }


        }



    public void searchPatient(){
        if (patients.isEmpty()) {
            System.out.println("No patients exist yet. Add a patient first.");


        }
        System.out.print("Enter Patient ID to check in: ");
        int patientId = scan.nextInt();

        for (GlennPatient p : patients){

            if(p.getId() == patientId){
                p.checkIn();
                System.out.println("Patient " + p.getName() + " checked in successfully.");

            }
        }

    }

    public  void schedulePatient(){

        if (patients.isEmpty()) {
            System.out.println("No patients exist yet. Add a patient first.");
            return;
        }

        System.out.println("Available Time Slots:");
        for (int i = 0; i < timeSlots.length; i++) {
            String status = (schedule[i] == null) ? "Available" : "Booked";
            System.out.println((i + 1) + ". " + timeSlots[i] + " - " + status);
        }

        System.out.print("Select a time slot (1-" + timeSlots.length + "): ");
        int slotChoice = scan.nextInt();

        if (slotChoice < 1 || slotChoice > timeSlots.length) {
            System.out.println("Invalid time slot.");
            return;
        }

        int slotIndex = slotChoice - 1;

        if (schedule[slotIndex] != null) {
            System.out.println("That time slot is already booked. Please choose another.");
            return;
        }

        System.out.print("Enter Patient ID: ");
        int patientId = scan.nextInt();

        GlennPatient p = findPatientById(patientId);

        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        if (!p.isCheckedIn()) {
            System.out.println("Patient is not checked in yet. Please check them in first.");
            return;
        }

        GlennAppointment appt = new GlennAppointment(timeSlots[slotIndex], p);

        schedule[slotIndex] = appt;
        appointments.add(appt);

        System.out.println("Appointment scheduled at "
                + timeSlots[slotIndex] + " for " + p.getName() + ".");

    }


    public void cancelAppointment(){

        System.out.print("Enter Patient ID to cancel appointment: ");
        int patientId = scan.nextInt();

        int slotIndex = findSlotIndex(patientId);
        if (slotIndex == -1) {
            System.out.println("No scheduled appointment found for this patient.");
            return;
        }

        GlennAppointment appt = schedule[slotIndex];
        appt.cancel();              // marks canceled in the appointment object
        schedule[slotIndex] = null; // frees the slot so it shows "Available"

        System.out.println("Appointment cancelled successfully.");

    }


    public void dailySchedule(){

        System.out.println("Daily Schedule:");
        for (int i = 0; i < timeSlots.length; i++) {
            if (schedule[i] == null) {
                System.out.println(timeSlots[i] + " - Available");
            } else {
                GlennAppointment appt = schedule[i];
                System.out.println(timeSlots[i] + " - " + appt.getPatient().getName());
            }
        }


    }

    public void dailyReport(){

        int checkedInCount = 0;
        for (GlennPatient p : patients) {
            if (p.isCheckedIn()) checkedInCount++;
        }

        int scheduledCount = 0;
        for (GlennAppointment a : schedule) {
            if (a != null) scheduledCount++;
        }

        int completedCount = 0;
        int cancelledCount = 0;
        for (GlennAppointment a : appointments) {
            if (a.isCompleted()) completedCount++;
            if (a.isCancelled()) cancelledCount++;
        }

        System.out.println("Daily Summary Report");
        System.out.println("-------------------------");
        System.out.println("Total Patients: " + patients.size());
        System.out.println("Total Patients Checked In: " + checkedInCount);
        System.out.println("Appointments Scheduled (active): " + scheduledCount);
        System.out.println("Appointments Completed: " + completedCount);
        System.out.println("Appointments Cancelled: " + cancelledCount);

    }

    private GlennPatient findPatientById(int id) {
        for (GlennPatient p : patients) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private int findSlotIndex(int patientId) {
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i] != null && schedule[i].getPatient().getId() == patientId) {
                return i;
            }
        }
        return -1;
    }



}
