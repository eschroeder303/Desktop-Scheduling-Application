package Model;

/**
 * Contact class.
 */
public class Contact {
    private int ID;
    private String name;
    private String email;

    /**
     * Contact constructor.
     *
     * @param ID
     * @param name
     * @param email
     */
    public Contact(int ID, String name, String email) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    /**
     * Contact ID.
     */
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Contact name getter.
     */
    public String getName() {
        return name;
    }

    /**
     * Contact name setter.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Contact email getter.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Contact email setter.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}