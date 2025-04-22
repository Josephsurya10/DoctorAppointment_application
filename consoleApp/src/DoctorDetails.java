public class DoctorDetails extends PersonalDetails{
    private static int s_doctorid = 100;
    String doctorId;
    int experience;
    String specialization;
    int fees;

    public DoctorDetails(String name, Status.Gender gender , String phone, int experience, String specialization, int fees){
        super(name,gender,phone);
        s_doctorid++;
        this.experience = experience;
        this.specialization = specialization;
        this.fees = fees;
        this.doctorId = "DID" + s_doctorid;
    }

    public  String getDoctorid(){
        return doctorId;
    }
    public int getExperience(){
        return experience;
    }
    public int getFees(){
        return fees;
    }
    public String getSpecialization(){
        return specialization;
    }
}
