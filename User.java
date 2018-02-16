import java.util.Calendar;
import java.util.Map;

public class User {

    public String username;
    protected String name;
    protected String password;
    protected String hash;
    protected String salt;
    protected Map<Calendar, String> messages;
    protected String key;
    protected UserType type;
    protected String decKey;

    public User(){}
    public User(String name){ this.name = name; }

    public void setName(String name){ this.name = name; }
    public void setPassword(String pw) {this.password = pw; }
    public void setHash(String hsh) {this.hash = hsh; }
    public void setSalt(String slt) {this.salt = slt; }
    public void setKey(String ky) {this.key = ky; }
    public void setUsername(String username) { this.username = username; }
    public void setDecKey(String decKey) { this.decKey = decKey; }

    public String getName() {return this.name; }
    public String getPassword() { return this.password; }
    public String getHash() { return this.hash; }
    public String getSalt() { return this.salt; }
    public String getKey() { return this.key; }
    public String getUsername() { return this.username; }
    public UserType getType() { return this.type; }
    public String getDecKey() { return this.decKey; }

    public void receiveMessage(String message){
        this.messages.put(Calendar.getInstance(), message);
    }

    public void sendMessage(User user, String message){
        user.receiveMessage(message);
    }

    public Map<Calendar, String> getMessages() {return this.messages; }
}
