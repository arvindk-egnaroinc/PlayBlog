package services;

/**
 * Created by Arvind on 9/10/2016.
 */
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class User {
    private final DBCollection usersCollection;
    private Random random = new SecureRandom();

    public User(final DB blogDatabase) {
        usersCollection = blogDatabase.getCollection("users");
        System.out.println("users collection created");
    }

    /**
     * Add user to users collection and validate user is unique
     * @param username
     * @param password
     * @param email
     * @return
     */
    public boolean addUser(String username, String password, String email) {

        String passwordHash = makePasswordHash(password, Integer.toString(random.nextInt()));

        BasicDBObject user = new BasicDBObject();

        user.append("_id", username).append("password", passwordHash);

        if (email != null && !email.equals("")) {
            user.append("email", email);
        }

        try {
            usersCollection.insert(user);
            return true;
        } catch (MongoException e) {
            System.out.println("Username already in use: " + username);
            return false;
        }
    }

    /**
     * Validate login
     * @param username
     * @param password
     * @return
     */
    public DBObject validateLogin(String username, String password) {
        DBObject user;

        user = usersCollection.findOne(new BasicDBObject("_id", username));

        if (user == null) {
            System.out.println("User not in database");
            return null;
        }

        String hashedAndSalted = user.get("password").toString();

        String salt = hashedAndSalted.split(",")[1];

        if (!hashedAndSalted.equals(makePasswordHash(password, salt))) {
            System.out.println("Submitted password is not a match");
            return null;
        }
        System.out.println("Validate login "+user);
        return user;
    }

    /**
     * Encrypt password and store into db
     * @param password
     * @param salt
     * @return
     */
    private static  String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password + "," + salt;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return encoder.encode(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
    }
}
