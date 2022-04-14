package DAO;

import Database.databaseConnection;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User DAO class.
 */
public class userDAO {

    /**
     * Create user list.
     *
     * @return user ID, username
     */
    public static ObservableList<User> buildUsersList() {
        Connection c;
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            c = databaseConnection.getConnection();
            String SQL = "SELECT User_ID, User_Name FROM users;";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                User newUser = new User(
                        rs.getInt("User_ID"),
                        rs.getString("User_Name"));
                users.add(newUser);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Check login.
     *
     * @param username
     * @param password
     * @return matching username/password fields
     * @throws SQLException
     */
    public static boolean checkLogin(String username, String password) throws SQLException {
        String search = "SELECT * FROM users WHERE User_Name=? AND Password=?";

        PreparedStatement ps = databaseConnection.getConnection().prepareStatement(search);

        ps.setString(1, username);
        ps.setString(2, password);

        try {
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            return (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}