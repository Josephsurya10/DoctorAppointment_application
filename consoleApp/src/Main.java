
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Main {

    static  ArrayList<PatientDetails> patientDetails = new ArrayList<>();
    static  ArrayList<DoctorDetails> doctorDetails =  new ArrayList<>();
    static  ArrayList<SlotDetails> slotDetails = new ArrayList<>();
    static  ArrayList<AppointmentDetails> appointmentDetails =new ArrayList<>();


    static PatientDetails currentPatient;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        mainmenu();
    }

    static void mainmenu() {
        String choice;
        do{
            System.out.println("\nWelcome To Online Hospital Management Application");
            System.out.println("1. Patient Registration\n2. Login\n3. Exit");
            System.out.print("Enter choice: ");
            int option = Integer.parseInt(sc.nextLine());

            switch (option){
                case 1:{
                    patientRegistration();
                    break;
                }
                case 2: {
                    login();
                    break;
                }
                case 3 : {
                    System.out.println("exiting application");
                    return;
                }
                default:{
                    System.out.println("the option is invalid");
                    break;
                }
            }
            System.out.println("do you want to continue");
            choice = sc.nextLine().toLowerCase();
        }while(choice != "yes");
    }

    private static void login() {
        System.out.print("\nEnter your Patient ID: ");
        String id = sc.nextLine().toUpperCase();
        boolean found = false;
        for(PatientDetails patient : patientDetails){
            if(patient.getPatientid() == id){
                currentPatient = patient;
                found = true;
                System.out.println("Login successfull");
                System.out.println("going to submenu");
                submenu();
                return;
            }
        }
        if(!found){
            System.out.println("PatientId not found");
        }
    }

    private static void submenu() {
        System.out.println("-----------Submenu------------");
        int option;
        do{
            System.out.println("\n--- Sub Menu ---");
            System.out.println("1. Book Appointment\n2. Appointment History\n3. Cancel Appointment\n4. Wallet Recharge\n5. Show Balance\n6. Logout");
            System.out.print("Choose an option: ");
            option = sc.nextInt();

            switch (option){
                case 1 :{
                    bookappointment();
                    break;
                }
                case 2: {
                    appointmentHistory();
                    break;
                }
                case 3: {
                    cancelappointment();
                    break;
                }
                case 4: {
                    walletrecharge();
                    break;
                }
                case 5 :{
                    System.out.println("Current Balance : "+ currentPatient.showBalance());
                    break;
                }
                case 6 :{
                    System.out.println("logging out");
                    break;
                }
                default:{
                    System.out.println("Invalid Input");
                    break;
                }
            }
        }while(option != 6);
    }

     static void walletrecharge() {
         System.out.println("Enter the amount to recharge : ");
         double amt = sc.nextDouble();
         if(amt > 0){
             currentPatient.walletrecharge(amt);
             System.out.println("Wallet recharged. current balance : "+ currentPatient.showBalance() );
         }
         else{
             System.out.println("Invalid amount");
         }
    }

    static void cancelappointment() {
        boolean found =false;
        for(AppointmentDetails appointmentDetails1 : appointmentDetails){
            if(appointmentDetails1.getPatientId().equals(currentPatient.getPatientid())){
                found = true;
                System.out.println(appointmentDetails1.getAppointmentid()+":" + appointmentDetails1.getAppointmentTime()+":"+appointmentDetails1.getAppointmentSlot()+":"+appointmentDetails1.getStatus());
            }
        }
        if(!found){
            System.out.println("Appointment not found");
            return;
        }
         System.out.println("Enter the appointment id to cancel : ");
        String slotid = sc.nextLine();
        for(AppointmentDetails appointmentDetails1 : appointmentDetails){
            if(appointmentDetails1.getAppointmentid().equals(slotid)){
               appointmentDetails1.cancelApp();
               currentPatient.walletrecharge(appointmentDetails1.getFees());
                System.out.println("Appointment cancelled. Refund the amount successfully");
                return;
            }
        }
         System.out.println("Invalid appointment id");
    }

    static void appointmentHistory() {
         System.out.println("----------------appointmentHistory-----------");
        for(AppointmentDetails appointmentDetails1 : appointmentDetails){
            if(appointmentDetails1.getPatientId().equals(currentPatient.getPatientid())){
                System.out.println(appointmentDetails1.Appointmentid + ":" + appointmentDetails1.getAppointmentTime() + ":"+ appointmentDetails1.getAppointmentSlot()+":"+appointmentDetails1.getStatus());
            }
        }
    }

    static void bookappointment() {
        System.out.println("Doctors List");
        for(DoctorDetails doctor : doctorDetails){
            System.out.println(doctor.getDoctorid() + ":" + doctor.getName() + ":" + doctor.getSpecialization() + ":" + doctor.getFees());
        }

        System.out.println("select a doctorid");
        String docId = sc.nextLine();
        DoctorDetails doctor = null;
        for(DoctorDetails doctorDetails1 : doctorDetails){
            if(doctorDetails1.getDoctorid() == docId){
                doctor = doctorDetails1;
                break;
            }
        }

        if(doctor == null){
            System.out.println("Doctor not found");
            return;
        }

        System.out.println("available slots");
        for(SlotDetails slot : slotDetails){
            if(slot.getDoctorId() == docId){
                System.out.println(slot.getSlotId()+ ":" + slot.getSlotTime());
            }
        }

        System.out.println("select a slot id");
        String slotid = sc.nextLine();

        System.out.println("select a date(dd/MM/yyyy)");
        LocalDate slotdate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        boolean slotbooked = false;
        for(AppointmentDetails apdetails : appointmentDetails){
            if(apdetails.getAppointmentSlot() == slotid && apdetails.getDoctorId() == docId &&
            apdetails.getStatus() == Status.status.BOOKED && apdetails.getAppointmentTime().equals(slotdate)){
                slotbooked =true;
                break;
            }
        }

        if(slotbooked){
            System.out.println("cant book this slot , it has already been booked");
            return;
        }

        if(currentPatient.showBalance() < doctor.fees){
            System.out.println("Insuffient balance");
            return;
        }

        currentPatient.deductBalance(doctor.fees);
        AppointmentDetails appointmentDetails1 = new AppointmentDetails(
                currentPatient.patientid,
                docId,
                slotdate,
                slotid,
                Status.status.BOOKED,
                doctor.getFees()
        );
        appointmentDetails.add(appointmentDetails1);
        System.out.println("appointment booked!" + "your appointment id : " + appointmentDetails1.getAppointmentid());


    }

    static void patientRegistration() {
        System.out.println("\n--- Patient Registration ---");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Father Name: ");
        String fatherName = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Gender (MALE/FEMALE): ");
        Status.Gender gender = Status.Gender.valueOf(sc.nextLine().toUpperCase());
        System.out.print("Wallet Balance: ");
        double balance = Double.parseDouble(sc.nextLine());

        PatientDetails patientDetails1 = new PatientDetails(name,fatherName, gender,phone,age,balance);
        patientDetails.add(patientDetails1);
        System.out.println("patient registration successfull");
    }
}