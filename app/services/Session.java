package services;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import sun.misc.BASE64Encoder;
import java.security.SecureRandom;

public class Session {
    private final DBCollection sessionsCollection;

    /**
     * get session collection from db;
     * @param blogDatabase
     */
    public Session(final DB blogDatabase) {
        sessionsCollection = blogDatabase.getCollection("sessions");
        System.out.println("sessions collection created");
    }

    /**
     * Find user name by session id
     * @param sessionId
     * @return
     */
    public String findUserNameBySessionId(String sessionId) {
        DBObject session = getSession(sessionId);

        if (session == null) {
            return null;
        }
        else {
            return session.get("username").toString();
        }
    }


    /**
     * Start a new session in database
     * @param username
     * @return
     */
    public String startSession(String username) {
        SecureRandom generator = new SecureRandom();
        byte randomBytes[] = new byte[32];
        generator.nextBytes(randomBytes);

        BASE64Encoder encoder = new BASE64Encoder();

        String sessionID = encoder.encode(randomBytes);

        BasicDBObject session = new BasicDBObject("username", username);

        session.append("_id", sessionID);

        sessionsCollection.insert(session);

        return session.getString("_id");
    }

    /**
     * End session of the user by removing session id of the user
     * @param sessionID
     */
    public void endSession(String sessionID) {
        sessionsCollection.remove(new BasicDBObject("_id", sessionID));
    }

    /**
     * Retrive the session of a user from
     * @param sessionID
     * @return
     */
    public DBObject getSession(String sessionID) {
        return sessionsCollection.findOne(new BasicDBObject("_id", sessionID));
    }
}

