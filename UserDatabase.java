import com.google.gson.Gson;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.util.Base64;
import java.util.Vector;
import java.util.Base64.*;

public class UserDatabase {

    Vector<User> users;

    public UserDatabase() {
        users = new Vector<User>();
    }

    public void add(User newUser) {
        // Needs validation that user is not already in database
        users.add(newUser);
    }

    public void removeUser(User user) {
        // Needs created
    }

    public Vector<User> getUsers() {
        return this.users;
    }

    public void encrypt(String key) {
        // All users will be encrypted to a specific key before being saved
        UserDatabaseEncryptionMachine.encrypt(this, key);
    }

    public void save(){
        try {

            FileWriter writer = new FileWriter("userdatabase.txt");
            Gson gson = new Gson();
            String data = gson.toJson(this);
            writer.write(data);
            writer.close();

        } catch (Exception e){

        }
    }

    public void decrypt(String key) { UserDatabaseEncryptionMachine.decrypt(this, key);}
    public static class UserDatabaseEncryptionMachine {

        public static UserDatabase encrypt(UserDatabase database, String key) {

            // This will encrypt all users to a specific key
            for (User u : database.getUsers()) {
                try {

                } catch (Exception e) {

                }
            }

            return database;
        }

        public static void decrypt(UserDatabase database, String key) {

            // This will decrypt all users to a specific key
            for (User u : database.getUsers()){
                try {

                } catch (Exception e) {
                    // this should be added to the log
                }

            }

        }
    }
}
