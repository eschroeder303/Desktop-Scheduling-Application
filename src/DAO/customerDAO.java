package DAO;

import Database.databaseConnection;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Customer DAO class.
 */
public class customerDAO {

    /**
     * Get customer data from the database.
     *
     * @return customer data
     */
    public static ObservableList<Customer> buildCustomers() {
        Connection c;
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        try {
            c = databaseConnection.getConnection();
            String SQL = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, countries.Country, first_level_divisions.Division, customers.Division_ID, countries.Country_ID FROM customers INNER JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID;";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Customer newCustomer = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getString("Country"),
                        rs.getString("Division"),
                        rs.getInt("Division_ID"),
                        rs.getInt("Country_ID"));

                customers.add(newCustomer);
            }
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Update customer information.
     *
     * @param CustomerID
     * @param CustomerName
     * @param CustomerAddress
     * @param CustomerPostalCode
     * @param CustomerPhone
     * @param DivisionID
     * @throws SQLException
     */
    public static void updateCustomer(int CustomerID, String CustomerName, String CustomerAddress, String CustomerPostalCode, String CustomerPhone, String DivisionID) throws SQLException {
        Connection c;
        try {
            c = databaseConnection.getConnection();
            String SQL = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";

            PreparedStatement statement = c.prepareStatement(SQL);
            statement.setString(1, CustomerName);
            statement.setString(2, CustomerAddress);
            statement.setString(3, CustomerPostalCode);
            statement.setString(4, CustomerPhone);
            statement.setInt(5, Integer.parseInt(DivisionID));
            statement.setInt(6, CustomerID);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create new customer.
     *
     * @param CustomerName
     * @param CustomerAddress
     * @param CustomerPostalCode
     * @param CustomerPhone
     * @param DivisionID
     * @throws SQLException
     */
    public static void createCustomer(String CustomerName, String CustomerAddress, String CustomerPostalCode, String CustomerPhone, String DivisionID) throws SQLException {
        Connection c;
        try {
            c = databaseConnection.getConnection();
            String SQL = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = c.prepareStatement(SQL);
            statement.setString(1, CustomerName);
            statement.setString(2, CustomerAddress);
            statement.setString(3, CustomerPostalCode);
            statement.setString(4, CustomerPhone);
            statement.setInt(5, Integer.parseInt(DivisionID));
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete existing customer.
     *
     * @param CustomerID
     * @throws SQLException
     */
    public static void deleteCustomer(int CustomerID) throws SQLException {
        Connection c;
        try {
            c = databaseConnection.getConnection();
            String SQL = "DELETE FROM customers WHERE Customer_ID = ?;";

            PreparedStatement statement = c.prepareStatement(SQL);
            statement.setInt(1, CustomerID);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create customer list.
     *
     * @return customer ID, customer name
     */
    public static ObservableList<Customer> buildCustomerList() {
        Connection c;
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        try {
            c = databaseConnection.getConnection();
            String SQL = "SELECT Customer_ID, Customer_Name FROM customers;";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Customer newCustomer = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"));
                customers.add(newCustomer);
            }
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}