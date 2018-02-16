import java.util.Calendar;

public class Appointment {

    private Calendar date;
    private String notes;

    public Appointment(Calendar dt, String nt){
        this.date = dt;
        this.notes = nt;
    }

    public Appointment(Calendar dt){
        this.date = dt;
    }

    public Appointment(){}

    public Calendar getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void display(){
        System.out.println(this.date.getTime() + " -- " + this.notes);
    }
}
