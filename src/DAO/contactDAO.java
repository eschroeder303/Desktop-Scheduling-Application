package DAO;

import Database.databaseConnection;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Contact DAO class.
 */
public class contactDAO {

    /**
     * Get contact data from the database.
     *
     * @return contact ID, name, email
     */
    public static ObservableList<Contact> buildContactsList() {
        Connection c;
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        try {
            c = databaseConnection.getConnection();
            String SQL = "SELECT * FROM contacts;";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Contact newContact = new Contact(
                        rs.getInt("Contact_ID"),
                        rs.getString("Contact_Name"),
                        rs.getString("Email"));
                contacts.add(newContact);
            }
            return contacts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}