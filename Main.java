import java.io.FileReader;
import java.util.GregorianCalendar;
import java.util.Scanner;
import com.google.gson.Gson;

public class Main {

    public static void main(String[] args){
        try {

            Gson gson = new Gson();
            FileReader reader = new FileReader("userdatabase.txt");
            UserDatabase db = gson.fromJson(reader, UserDatabase.class);
            User user = new User();

            do {
                System.out.println("Username: ");
                Scanner scanner = new Scanner(System.in);
                String username = scanner.nextLine();


                for (User u : db.getUsers()) {
                    if (username.equals(u.getUsername())) {
                        user = u;
                        break;
                    }
                }


                System.out.println("Password: ");
                user.setPassword(scanner.nextLine());

            } while(!LoginController.verifyPassword(user));

            System.out.println("Login successful");

            reader = new FileReader(user.getUsername() + ".txt");

            if(user.getType() == UserType.Dentist){
                user = new Dentist();
            user = gson.fromJson(reader, Dentist.class);
            }

        else if(user.getType() == UserType.Admin)
        {
            user = new Admin();
            user = gson.fromJson(reader, Admin.class);
        }

        else if(user.getType() == UserType.Patient)
        {
            String key = user.getDecKey();
            user = new Patient();
            user = gson.fromJson(reader, Patient.class);
            user.setDecKey(key);
        }

        if(user instanceof Admin){
            ((Admin) user).createPatient(db);
        }

        else if(user instanceof Patient){

            ((Patient) user).decrypt(user.getDecKey());
            System.out.println(user.getDecKey());
            System.out.println(((Patient) user).getTelephone());
            System.out.println(((Patient) user).getAddress());
        }
            reader.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
