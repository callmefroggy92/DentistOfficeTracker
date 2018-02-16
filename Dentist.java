import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;
import com.google.gson.Gson;

public class Dentist extends User{

    private Vector<Patient> patients;

    public Dentist(){
        patients = new Vector<Patient>();
        this.type = UserType.Dentist;
    }

    public void addPatient(Patient pt){
        // Needs validation that vector does not already contain patient
           patients.add(pt);
    }

    public void removePatient(Patient pt){
        // Needs created
    }

        public void save() throws Exception{
            try {

                // Each patient will encrypt itself using the dentist's key before saving
                for(Patient p : patients){
                    p.encrypt(this.decKey);
                }

                // All info is saved in JSON style
                Gson gson = new Gson();
                String data = gson.toJson(this);

                FileWriter writer = new FileWriter(this.username + ".txt");
                BufferedWriter bw = new BufferedWriter(writer);

                bw.write(data);

                bw.close();
                writer.close();

            } catch(Exception e){

            }
        }

}
