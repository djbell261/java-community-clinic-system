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
            System.out.println("No Customer found.");
            return;
        }
      /*
        for (int i =0; i < patients.size(); i++){
            GlennPatient p = new GlennPatient();

            System.out.println("ID: " + p.getId()
                    + " | Name: " + p.getName()
                    + " | Checked In: " + p.isCheckedIn());

        }

       */


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

        System.out.println("Customer added successfully!");
        System.out.println("Customer ID: " + p.getId());


    }

    public void checkPatientIn(){

        System.out.println("Enter Customer ID to check");
        int patientId = scan.nextInt();

        scan.nextLine(); // consume leftover newline

        boolean found = false;

        for (GlennPatient p : patients) {
            if (p.getId() == patientId) {
                p.checkIn(); // changes checkedIn to true
                System.out.println("Customer " + p.getName() + " checked in successfully.");
                found = true;
                break; // stop looping once we found them
            }
        }

        if (!found) {
            System.out.println("No Customer found with ID: " + patientId);
        }


        }



    public void searchPatient(){
        if (patients.isEmpty()) {
            System.out.println("No Customer exist yet. Add a patient first.");


        }
        System.out.print("Enter Customer ID to Find: ");
        int patientId = scan.nextInt();

        for (GlennPatient p : patients){

            if(p.getId() == patientId){

                System.out.println("Customer " + p.getName() + " is in the Database");

            }
        }

    }

    public void schedulePatient(){

        if(patients.isEmpty()){
            System.out.println("No customers exist yet.");
            return;
        }

        System.out.print("Enter Customer ID: ");
        int patientId = scan.nextInt();
        scan.nextLine();

        GlennPatient p = findPatientById(patientId);

        if(p == null){
            System.out.println("Customer not found.");
            return;
        }

        if(!p.isCheckedIn()){
            System.out.println("Customer must be checked in first.");
            return;
        }

        System.out.print("Enter Priority (Emergency / Regular / Follow-Up): ");
        String priority = scan.nextLine();
        p.setPriority(priority);


        // EMERGENCY PATIENT

        if(p.getPriority().equals("EMERGENCY")){

            int slotIndex = findEarliestAvailableSlot();

            if(slotIndex == -1){
                System.out.println("No available slots today.");
                return;
            }

            GlennAppointment appt = new GlennAppointment(timeSlots[slotIndex], p);
            schedule[slotIndex] = appt;
            appointments.add(appt);

            System.out.println("Emergency Customer scheduled at " + timeSlots[slotIndex]);
            return;
        }


        // REGULAR OR FOLLOW-UP


        System.out.println("Available Time Slots:");

        for(int i = 0; i < timeSlots.length; i++){

            String status = (schedule[i] == null) ? "Available" : "Booked";
            System.out.println((i+1) + ". " + timeSlots[i] + " - " + status);
        }

        System.out.print("Select Slot: ");
        int slotChoice = scan.nextInt();

        if(slotChoice < 1 || slotChoice > timeSlots.length){
            System.out.println("Invalid Slot");
            return;
        }

        int slotIndex = slotChoice - 1;

        if(schedule[slotIndex] != null){
            System.out.println("Slot already booked.");
            return;
        }

        // FOLLOW-UP RULE
        if(p.getPriority().equals("FOLLOW-UP")){

            if(!isFollowUpSlot(slotIndex)){
                System.out.println("Follow-up customers can only be scheduled at 1:00 PM or 2:00 PM");
                return;
            }
        }

        GlennAppointment appt = new GlennAppointment(timeSlots[slotIndex], p);
        schedule[slotIndex] = appt;
        appointments.add(appt);

        System.out.println("Appointment scheduled at " + timeSlots[slotIndex]);
    }


    public void cancelAppointment(){

        System.out.print("Enter Customer ID to cancel appointment: ");
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

    public void completeAppointment(){

        System.out.print("Enter Customer ID to complete appointment: ");
        int patientId = scan.nextInt();

        int slotIndex = findSlotIndex(patientId);
        if (slotIndex == -1) {
            System.out.println("No scheduled appointment found for this Customer.");
            return;
        }

        GlennAppointment appt = schedule[slotIndex];
        appt.complete();              // marks complete in the appointment object
        schedule[slotIndex] = null; // frees the slot so it shows "Available"

        System.out.println("Appointment completed successfully.");

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
        System.out.println("Total Customers: " + patients.size());
        System.out.println("Total Customers Checked In: " + checkedInCount);
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

    private int findEarliestAvailableSlot(){

        for(int i = 0; i < schedule.length; i++){

            if(schedule[i] == null){
                return i;
            }
        }

        return -1;
    }
    private boolean isFollowUpSlot(int slotIndex){

        if(slotIndex == 3 || slotIndex == 4){
            return true;
        }

        return false;
    }


}
