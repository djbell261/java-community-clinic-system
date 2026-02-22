package org.codedifferently;



import java.util.Scanner;

public class GlennClinicApp{
    public static void main(String[] args) {


         theClinic();

    }

//The Method That Directs the user to all the methods in the System class
    public static void theClinic() {
        Scanner scan = new Scanner(System.in);
        GlennClinicSystem system = new GlennClinicSystem();


do {
    System.out.println("Welcome to Derwin & Glenn's Geek Squad");
    System.out.println("""
                1. Add New Customer
                2. View All Customers
                3. Check In Customers
                4. Search for Customers
                5. Schedule Appointment
                6. Cancel Appointment
                7. Complete Appointment
                8. View Daily Schedule
                9. Daily Report
                10. Exit""");
    int choice = scan.nextInt();

    switch (choice) {
        case 1:
            System.out.println("Enter Customer's Name: ");
            String patientName = scan.next();

            system.addPatient(patientName);
            break;

        case 2:
            System.out.println("Customer LIST");
            system.patientList();
            break;
        case 3:
            system.checkPatientIn();

            break;
        case 4:
            system.searchPatient();
            break;
        case 5:
            system.schedulePatient();
            break;
        case 6:
            system.cancelAppointment();
            break;
        case 7:
            system.completeAppointment();
        case 8:
            system.dailySchedule();
            break;
        case 9:
            system.dailyReport();
            break;
        case 10:
            System.out.println("Goodbye, Thanks For Coming!");
            System.exit(0);
            break;
        default:
            System.out.println("Invalid Option");
            break;


    }
}while(true);





    }
}

