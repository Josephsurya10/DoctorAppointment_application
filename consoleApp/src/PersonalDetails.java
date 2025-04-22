public class PersonalDetails {
    String name;
    String fathername;
    Status.Gender gender;
    String phone;
    int age;
    double walletBalance;

    public PersonalDetails(String name,String fathername,Status.Gender gender, String phone, int age, double walletBalance){
        this.name= name;
        this.fathername = fathername;
        this.gender = gender;
        this.phone = phone;
        this.age = age;
        this.walletBalance = walletBalance;
    }
    public PersonalDetails(String name, Status.Gender gender, String phone){
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public void walletrecharge(double amt){
        this.walletBalance += amt;
    }
    public void deductBalance(double amt){
        this.walletBalance -= amt;
    }
    public double showBalance(){
        return walletBalance;
    }
    public String getName(){
        return name;
    }
    public String getFathername(){
        return fathername;
    }
    public Status.Gender getGender() {
        return gender;
    }
    public int age(){
        return age;
    }
}
