import com.google.gson.Gson;

import java.io.FileWriter;
import java.util.Scanner;

public class Admin extends User{

    private String data;

    public Admin(){ type = UserType.Admin;}

    public void createPatient(UserDatabase database){
        try {

            // Used for testing purposes
            Patient patient = new Patient();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter patients full name: ");
            patient.setName(scanner.nextLine());

            System.out.println("Enter patients address: ");
            patient.setAddress(scanner.nextLine());

            System.out.println("Enter patients telephone: ");
            patient.setTelephone(scanner.nextLine());

            System.out.println("Enter patients insurance provider");
            patient.setDentalInsuranceProvider(scanner.nextLine());

            System.out.println("Enter patients insurance number: ");
            patient.setDentalInsuranceNumber(scanner.nextLine());

            System.out.println("Enter patients username: ");
            patient.setUsername(scanner.nextLine());

            System.out.println("Enter patients password: ");
            patient.setPassword(scanner.nextLine());

            LoginController.hashUserPassword(patient);

            patient.save();

            patient.setDecKey("");

            database.add(patient);
            database.save();
        } catch(Exception e){
            e.printStackTrace();
        }

    }


    public void save(){
        try {
            FileWriter writer = new FileWriter(this.username + ".txt");
            Gson gson = new Gson();

            this.data = gson.toJson(this);
            writer.write(data);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
