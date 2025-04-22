import java.time.LocalDate;

public class AppointmentDetails {
    private static int s_apptid = 200;
    String Appointmentid;
    String doctorId;
    String patientId;
    LocalDate AppointmentTime;
    String AppointmentSlot;
    Status.status status;
    int fees;

    public AppointmentDetails(String doctorId, String patientId , LocalDate AppointmentTime, String AppointmentSlot, Status.status status, int fees){
        this.doctorId =doctorId;
        this.patientId = patientId;
        this.AppointmentTime = AppointmentTime;
        this.AppointmentSlot = AppointmentSlot;
        this.status = status;
        this.fees = fees;
        s_apptid++;
        this.Appointmentid = "AID" + s_apptid;
    }

    public String getAppointmentid(){
        return Appointmentid;
    }
    public LocalDate getAppointmentTime(){
        return AppointmentTime;
    }
    public String getAppointmentSlot(){
        return AppointmentSlot;
    }
    public String getPatientId(){
        return patientId;
    }
    public String getDoctorId(){
        return doctorId;
    }
    public int getFees(){
        return fees;
    }
    public Status.status getStatus() {
        return status;
    }

    public void cancelApp(){
        this.status = Status.status.CANCELLED;
    }
}
