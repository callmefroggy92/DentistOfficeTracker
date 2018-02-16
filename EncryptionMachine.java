/*******************************************
 *  EncryptionMachine class takes a key
 *  as an argument in the constructor, then
 *  uses that key to encrypt and decrypt strings.
 ******************************************/

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Random;

public class EncryptionMachine {

    private Key keySpec;
    private Cipher cipher;

    // When object is created, a key is taken which will be used for all encrypting and decrypting
    public EncryptionMachine(String key) throws Exception{
        if(key.length() != 16)
            throw new EMException("EncryptionMachineException: Invalid key length.  Key must be length 16");

        try {

            keySpec = new SecretKeySpec(key.getBytes("UTF8"), "AES");
            cipher = Cipher.getInstance("AES");

        } catch(Exception e){

        }
    }

    // Takes string as argument and returns encrypted string
    public String encrypt(String str) throws Exception{

        if(str == null)
            throw new EMException("EncryptionMachineException: empty string");

        try {

            cipher.init(Cipher.ENCRYPT_MODE, this.keySpec);

            byte[] enc = cipher.doFinal(str.getBytes("UTF8"));
            System.out.println(enc);
            return Base64.getEncoder().encodeToString(enc);


        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // Takes encrypted string as argument and returns decrypted string
    public String decrypt(String str) throws Exception{

        if(str == null)
            throw new EMException("EncryptionMachineException: empty string.");

        try{
            cipher.init(Cipher.DECRYPT_MODE, this.keySpec);
            byte[] dec = Base64.getDecoder().decode(str);
            dec = cipher.doFinal(dec);
            return new String(dec, "UTF8");

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // Creates random, 16 character string
    public static String createKey(){

        Random rand = new Random(System.currentTimeMillis());
        String str= new String();

        for(int i = 0; i < 16; i++){
            str += (char) rand.nextInt(128);
        }

        assert(str.length() == 16);

        return str;
    }

}
