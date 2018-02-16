import com.google.gson.Gson;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Vector;
import java.util.Base64.*;

public class Patient extends User {

    public Patient() { notes = new Vector<String>(); this.type = UserType.Patient; }
    public Patient(String nm) { this.name = nm; notes = new Vector<String>(); this.type = UserType.Patient; }

    // Private member variables
    private String telephone;
    private String dentalInsuranceProvider;
    private String dentalInsuranceNumber;
    private String address;
    private int age;
    private Gender gender;
    private Vector<String> notes;
    private String data;

    //////////////////////////////////////////////////////////////////
    // Getters and setters for each member variable
    public void setTelephone(String telephone){ this.telephone = telephone; }

    public void setDentalInsuranceProvider(String provider){ this.dentalInsuranceProvider = provider; }

    public void setDentalInsuranceNumber(String insuranceNumber) { this.dentalInsuranceNumber = insuranceNumber; }

    public void setAddress(String address) {this.address = address; }

    public void setAge(int age) { this.age = age; }

    public void setGender(Gender g) {this.gender = g; }

    public String getTelephone() { return this.telephone; }

    public String getDentalInsuranceProvider() { return this.dentalInsuranceProvider; }

    public String getDentalInsuranceNumber() { return this.dentalInsuranceNumber; }

    public String getAddress() { return this.address; }

    public Gender getGender() { return gender; }

    public int getAge() { return age; }

    public Vector<String> getNotes() { return notes; }

    public void setNotes(Vector<String> n) {this.notes = n; }
    //////////////////////////////////////////////


    // A function to add notes to the patients file
    public void addNote(String s) {this.notes.add(s); }

    // These member functions encrypt the object using the static member class PatientEncryptionMachine
    public void encrypt(String key){ PatientEncryptionMachine.encrypt(this, key); }

    public void decrypt(String key) { PatientEncryptionMachine.decrypt(this, key); }

    public void save(){
        try {

            FileWriter writer = new FileWriter(this.username + ".txt");
            Gson gson = new Gson();

            // Encrypts all user data using the user's key
            encrypt(this.decKey);

            // Securely deletes decrypted version of key
            Random rand = new Random();
            java.util.Arrays.fill(this.decKey.toCharArray(), (char)(rand.nextInt(600000)));
            this.decKey = "";

            // All data saved in JSON format
            this.data = gson.toJson(this);
            writer.write(data);

            writer.close();
        }catch(Exception e){
            // This should be added to the log
        }
    }

    /////////////////////////////////////////////////////////////////
    /*
        A static member class designed to encrypt and decrypt the parent object.
        It has two functions: encrypt and decrypt.  Both functions take a "Patient" and a "key,"
        and they both return a Patient.
     */
    public static class PatientEncryptionMachine {

        // Encryption function
        private static void encrypt(Patient patient, String key) {
            try {
                EncryptionMachine eMachine = new EncryptionMachine(key);
                patient.setDentalInsuranceNumber(eMachine.encrypt(patient.getDentalInsuranceNumber()));
                patient.setDentalInsuranceProvider(eMachine.encrypt(patient.getDentalInsuranceProvider()));
                patient.setTelephone(eMachine.encrypt(patient.getTelephone()));
                patient.setAddress(eMachine.encrypt(patient.getAddress()));


            } catch (Exception e) {

            }

        }

        private static void decrypt(Patient patient, String key) {

                try {
                    EncryptionMachine eMachine = new EncryptionMachine(key);
                    patient.setAddress(eMachine.decrypt(patient.getAddress()));
                    patient.setTelephone(eMachine.decrypt(patient.getTelephone()));
                    patient.setDentalInsuranceProvider(eMachine.decrypt(patient.getDentalInsuranceProvider()));
                    patient.setDentalInsuranceNumber(eMachine.decrypt(patient.getDentalInsuranceNumber()));

                } catch (Exception e) {

                }

        }
    }
    //////////////////////////////////////////////////////////////////
}
