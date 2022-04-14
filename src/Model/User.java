package Model;

/**
 * User class.
 */
public class User {
    private final int userID;
    private final String userName;

    /**
     * User constructor.
     *
     * @param userID   User ID
     * @param userName Username
     */
    public User(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    /**
     * User ID getter.
     *
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Username getter.
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }
}