package Controller;

import DAO.appointmentDAO;
import DAO.contactDAO;
import DAO.customerDAO;
import DAO.userDAO;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Appointment controller.
 */
public class appointmentController {
    public Label appointmentidLabel;
    public Label titleLabel;
    public Label descriptionLabel;
    public Label locationLabel;
    public Label contactLabel;
    public Label typeLabel;
    public Label startLabel;
    public Label endLabel;
    public Label customeridLabel;
    public Label useridLabel;
    public Label appointmentLabel;
    @FXML private TextField idField;
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private TextField locationField;
    @FXML private ComboBox contactCombo;
    @FXML private TextField typeField;
    @FXML private DatePicker startDatePicker;
    @FXML private ComboBox startTimeCombo;
    @FXML private DatePicker endDatePicker;
    @FXML private ComboBox endTimeCombo;
    @FXML private ComboBox customerCombo;
    @FXML private ComboBox userCombo;

    /**
     * Parse data for each field on appointment controller launch.
     */
    public void sendData(Appointment appointment) {
        ObservableList<String> contactList = FXCollections.observableArrayList();
        ObservableList<String> customerList = FXCollections.observableArrayList();
        ObservableList<String> userList = FXCollections.observableArrayList();
        ObservableList<String> timeList = FXCollections.observableArrayList();

        try {
            ObservableList<Contact> contacts = contactDAO.buildContactsList();
            if (contacts != null) {
                for (Contact contact : contacts) {
                    contactList.add(contact.getID() + " - " + contact.getName());
                }
            }

            ObservableList<Customer> customers = customerDAO.buildCustomerList();
            if (customers != null) {
                for (Customer customer : customers) {
                    customerList.add(customer.getCustomerID() + " - " + customer.getCustomerName());
                }
            }

            ObservableList<User> users = userDAO.buildUsersList();
            if (users != null) {
                for (User user : users) {
                    userList.add(user.getUserID() + " - " + user.getUserName());
                }
            }

            LocalTime start = LocalTime.of(8, 0);
            LocalTime end = LocalTime.of(22, 0);
            timeList.add(start.toString());
            while (start.isBefore(end)) {
                start = start.plusMinutes(5);
                timeList.add(start.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        contactCombo.setItems(contactList);
        customerCombo.setItems(customerList);
        userCombo.setItems(userList);
        startTimeCombo.setItems(timeList);
        endTimeCombo.setItems(timeList);

        if (appointment != null) try {
            idField.setText(Integer.toString(appointment.getAptID()));
            titleField.setText(appointment.getTitle());
            descriptionField.setText(appointment.getDescription());
            locationField.setText(appointment.getLocation());
            contactCombo.setValue(appointment.getContactID() + " - " + appointment.getContactName());
            typeField.setText(appointment.getType());
            startDatePicker.setValue(appointment.getStartDate());
            startTimeCombo.setValue(appointment.getStartTime().toLocalTime());
            endDatePicker.setValue(appointment.getEndDate());
            endTimeCombo.setValue(appointment.getEndTime().toLocalTime());
            customerCombo.setValue(appointment.getCustomerID());
            userCombo.setValue(appointment.getUserID());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove non-int values from contact.
     */
    public int cleanContact() {
        String s = String.valueOf(contactCombo.getValue());
        String contactToInt = s.replaceAll("\\D+", "");
        return Integer.parseInt(contactToInt);
    }

    /**
     * Remove non-int values from customer ID.
     */
    public int cleanCustomer() {
        String s = String.valueOf(customerCombo.getValue());
        String customerToInt = s.replaceAll("\\D+", "");
        return Integer.parseInt(customerToInt);
    }

    /**
     * Remove non-int values from user ID.
     */
    public int cleanUser() {
        String s = String.valueOf(userCombo.getValue());
        String userToInt = s.replaceAll("\\D+", "");
        return Integer.parseInt(userToInt);
    }

    /**
     * Validate date pickers and time.
     */
    public boolean wrongTime() {
        LocalDateTime start = LocalDateTime.of(startDatePicker.getValue(),
                LocalTime.parse(startTimeCombo.getSelectionModel().getSelectedItem().toString()));
        LocalDateTime end = LocalDateTime.of(endDatePicker.getValue(),
                LocalTime.parse(endTimeCombo.getSelectionModel().getSelectedItem().toString()));

        return end.isBefore(start);
    }

    /**
     * Set appointment ID for overlap function.
     *
     * @return appointment ID
     */
    public int appointmentID() {
        int appointmentID;
        if (idField.getText().isEmpty()) {
            appointmentID = 0;
        } else {
            appointmentID = Integer.parseInt(idField.getText());
        }
        return appointmentID;
    }

    /**
     * Check if customer appointment times overlap.
     */
    public boolean overlap() {

        LocalDateTime start = LocalDateTime.of(startDatePicker.getValue(),
                LocalTime.parse(startTimeCombo.getSelectionModel().getSelectedItem().toString()));
        LocalDateTime end = LocalDateTime.of(endDatePicker.getValue(),
                LocalTime.parse(endTimeCombo.getSelectionModel().getSelectedItem().toString()));

        int appointmentID = appointmentID();
        ObservableList<Appointment> appointmentCustomerID = appointmentDAO.getCustomerID(cleanCustomer());

        if (appointmentCustomerID != null) {
            for (Appointment appointment : appointmentCustomerID) {

                if (appointmentID == appointment.getAptID()) {
                    System.out.println(appointmentCustomerID);
                    System.out.println(appointmentID);
                    return false;
                }

                if (appointment.getStartTime().isBefore(end) && (start.isBefore(appointment.getEndTime()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check for empty fields.
     *
     * @return true/false
     */
    public boolean emptyField() {
        if (titleField.getText().isEmpty()) {
            return true;
        } else if (descriptionField.getText().isEmpty()) {
            return true;
        } else if (locationField.getText().isEmpty()) {
            return true;
        } else if (contactCombo.getSelectionModel().isEmpty()) {
            return true;
        } else if (typeField.getText().isEmpty()) {
            return true;
        } else if (startDatePicker.getValue() == null) {
            return true;
        } else if (endDatePicker.getValue() == null) {
            return true;
        } else if (startTimeCombo.getValue().toString().isEmpty()) {
            return true;
        } else if (endTimeCombo.getValue().toString().isEmpty()) {
            return true;
        } else if (customerCombo.getValue().toString().isEmpty()) {
            return true;
        } else return userCombo.getValue().toString().isEmpty();
    }

    /**
     * Validate fields and update database.
     *
     * @param actionEvent button click
     * @throws Exception
     */
    public void saveButtonClick(ActionEvent actionEvent) throws Exception {
        if (emptyField()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please complete all fields");
            alert.showAndWait();
            return;
        }
       else if (overlap()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please check for appointment overlap");
            alert.showAndWait();
            return;
        }
       else if (wrongTime()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointment end time is before start time");
            alert.showAndWait();
            return;
        } else if (idField.getText().isEmpty()) {
            appointmentDAO.createAppointment(
                    titleField.getText(),
                    descriptionField.getText(),
                    locationField.getText(),
                    cleanContact(),
                    typeField.getText(),
                    LocalDateTime.of(startDatePicker.getValue(),
                            LocalTime.parse(startTimeCombo.getSelectionModel().getSelectedItem().toString())),
                    LocalDateTime.of(endDatePicker.getValue(),
                            LocalTime.parse(endTimeCombo.getSelectionModel().getSelectedItem().toString())),
                    cleanCustomer(),
                    (cleanUser()));
        } else try {
            appointmentDAO.updateAppointments(
                    Integer.parseInt(idField.getText()),
                    titleField.getText(),
                    descriptionField.getText(),
                    locationField.getText(),
                    cleanContact(),
                    typeField.getText(),
                    LocalDateTime.of(startDatePicker.getValue(),
                            LocalTime.parse(startTimeCombo.getSelectionModel().getSelectedItem().toString())),
                    LocalDateTime.of(endDatePicker.getValue(),
                            LocalTime.parse(endTimeCombo.getSelectionModel().getSelectedItem().toString())),
                    cleanCustomer(),
                    (cleanUser()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        cancelButtonClick(actionEvent);
    }

    /**
     * Return to Home screen.
     *
     * @param actionEvent button click
     * @throws Exception
     */
    public void cancelButtonClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Home.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 670);
        stage.setTitle("Schedule Manager");
        stage.setScene(scene);
        stage.show();
    }
}