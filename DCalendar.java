import java.util.Calendar;
import java.util.Vector;

public class DCalendar {

    Vector<Appointment> calendar;
    public DCalendar(){ calendar = new Vector<Appointment>(); }

    public void addAppointment(Appointment appointment){
        calendar.add(appointment);
    }

    public void removeAppointment(Appointment appointment){
        calendar.remove(appointment);
    }

    public void display(){
        for(Appointment a : calendar){
            a.display();
        }
    }
}
