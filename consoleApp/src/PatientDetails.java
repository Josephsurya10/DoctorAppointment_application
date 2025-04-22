public class PatientDetails extends PersonalDetails{
    private static int s_patientId = 1000;
    String patientid;

    public PatientDetails(String name,String fathername,Status.Gender gender, String phone, int age, double walletBalance){
        super(name,fathername,gender,phone,age, walletBalance);
        s_patientId++;
        this.patientid = "PID" + s_patientId;
    }

    public String getPatientid(){
        return patientid;
    }
}
