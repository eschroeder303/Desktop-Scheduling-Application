package DAO;

import Database.databaseConnection;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Country DAO class.
 */
public class countryDAO {

    /**
     * Get country data from the database.
     *
     * @return country ID, country name
     */
    public static ObservableList<Country> getCountries() {
        Connection c;
        ObservableList<Country> countries = FXCollections.observableArrayList();
        try {
            c = databaseConnection.getConnection();
            String SQL = "SELECT * FROM countries";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Country newCountry = new Country(
                        rs.getInt("Country_ID"),
                        rs.getString("Country"));
                countries.add(newCountry);
            }
            return countries;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}