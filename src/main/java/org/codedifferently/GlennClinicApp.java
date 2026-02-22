package org.codedifferently;



import java.util.Scanner;

public class GlennClinicApp{
    public static void main(String[] args) {


         theClinic();

    }
    /*
    Quick cheat sheet: where methods belong
ClinicApp (controller)
display menu
get user input
call system methods
loop until exit

ClinicSystem (logic + storage)
add/search/check-in patient
schedule/cancel appointments
prevent double booking
daily report calculations

Patient (individual record)
checkIn()
getters/setters
toString()/display()

Appointment (individual record)
cancel()/complete()
getters/setters
toString()/display()
     */

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

/* Example output
Welcome to Derwin Bell's Community Clinic System

1. Add New Patient
2. View All Patients
3. Check In Patient
4. Search for Patient
5. Schedule Appointment
6. Cancel Appointment
7. Complete Appointment
8. View Daily Schedule
9. Daily Report
10. Exit

Enter your choice: 1

Enter Patient Name: John Smith
Enter Phone Number: 555-1234

Patient added successfully!
Patient ID: 101

Enter your choice: 2

Patient List:
ID: 101 | Name: John Smith | Checked In: No
ID: 102 | Name: Sarah Johnson | Checked In: No

Enter your choice: 3

Enter Patient ID to Check In: 101

Patient John Smith checked in successfully.

Enter your choice: 4

Enter Patient ID to search: 102

Patient Found!
-------------------------
Patient ID: 102
Name: Sarah Johnson
Phone Number: 555-7890
Checked In: Yes
Appointment Time: 11:00 AM

Enter your choice: 5

Available Time Slots:
1. 9:00 AM
2. 10:00 AM
3. 11:00 AM
4. 1:00 PM
5. 2:00 PM

Select a time slot: 2
Enter Patient ID: 101

Appointment scheduled at 10:00 AM.

Enter your choice: 6

Enter Patient ID to cancel appointment: 102

Appointment for Sarah Johnson at 11:00 AM has been canceled successfully.

Enter your choice: 7

Daily Schedule:
9:00 AM - Available
10:00 AM - John Smith
11:00 AM - Available
1:00 PM - Available
2:00 PM - Available

Enter your choice: 8

Daily Summary Report
-------------------------
Total Patients Checked In: 1
Appointments Scheduled: 1
Appointments Completed: 0
Waitlisted Patients: 0

Enter your choice: 9

Thank you for using the Community Clinic System.
Goodbye!


 */