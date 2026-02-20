package org.codedifferently;


import java.util.ArrayList;
import java.util.Scanner;

public class GlennClinicSystem {

    public ArrayList<String> patients = new ArrayList<>();
    public ArrayList<Integer> id = new ArrayList<>();
    public ArrayList<String> checked = new ArrayList<>();

    public void patientList(){
        GlennPatient gP = new GlennPatient();
        for(int i =0; i < patients.size(); i++){
            System.out.println("ID:" + id.get(i) + "| Name: " + patients.get(i) + "| Checked In: " + gP.isCheckedIn());

        }

    }

    public void addPatient(String name){
        GlennPatient gP = new GlennPatient();


        gP.setName(name);
        patients.add(gP.getName());
        gP.setId(gP.getId());
        id.add(gP.getId());
        System.out.println("Patient added successfully!");
        System.out.println("Patient ID: " + gP.getId());


    }

    public void checkPatientIn(){

        GlennPatient glennPatient = new GlennPatient();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Patient ID to check");
        int patientId = scan.nextInt();

        for (int i=0; i < id.size(); i++) {
            if(id.get(i) == patientId){

                System.out.println("Patient " + patients.get(i) + " checked in successfully.");
            }
        }

        glennPatient.checkIn();

    }


    public void searchPatient(){
        GlennPatient glennPatient = new GlennPatient();
        Scanner scan = new Scanner(System.in);
        System.out.println("Patient ID: ");
        int pId = scan.nextInt();

        for (int i=0; i < id.size(); i++){
            if(pId == id.get(i)){
                System.out.println("Patient ID:" + id.get(i) +  "\n" +
                        "Name:" + patients.get(i) + "\n" +
                        "Checked In: " + glennPatient.isCheckedIn() + "\n" +
                        "Appointment Time: " + "\n");
            }

        }


    }

    public  void schedulePatient(){

    }


    public void cancelAppointment(){

    }


    public void dailySchedule(){

    }

    public void dailyReport(){

    }

}
