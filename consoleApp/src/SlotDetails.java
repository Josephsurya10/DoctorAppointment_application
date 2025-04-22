public class SlotDetails {
    String doctorId;
    String slotId;
    String slotTime;

    public SlotDetails(String doctorId, String slotId, String slotTime){
        this.doctorId = doctorId;
        this.slotId = slotId;
        this.slotTime =slotTime;
    }

    public String getDoctorId(){
        return doctorId;
    }
    public String getSlotId(){
        return slotId;
    }
    public String getSlotTime(){
        return slotTime;
    }
}
