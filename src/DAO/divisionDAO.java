package DAO;

import Database.databaseConnection;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Division DAO class.
 */
public class divisionDAO {

    /**
     * Get country data from the database.
     *
     * @param country
     * @return division ID, country ID, division
     */
    public ObservableList<Division> getCountry(String country) {
        try {
            String SQL = "SELECT first_level_divisions.Division_ID, countries.Country_ID, first_level_divisions.Division  FROM countries, first_level_divisions WHERE countries.Country = ? and countries.Country_ID = first_level_divisions.Country_ID;";
            Connection c = databaseConnection.getConnection();
            ObservableList<Division> divisions = FXCollections.observableArrayList();

            PreparedStatement ps = c.prepareStatement(SQL);
            ps.setString(1, country);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                divisions.add(createDivision(rs));
            }

            return divisions;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create division object.
     *
     * @param rs
     * @throws SQLException
     */
    private Division createDivision(ResultSet rs) throws SQLException {
        return new Division(
                rs.getInt("Division_ID"),
                rs.getInt("Country_ID"),
                rs.getString("Division"));
    }
}
