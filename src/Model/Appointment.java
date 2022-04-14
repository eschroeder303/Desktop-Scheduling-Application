package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Appointment class.
 */
public class Appointment {
    private int aptID;
    private String title;
    private String description;
    private String location;
    private String contactName;
    private String type;
    private LocalDate startDate;
    private LocalDateTime startTime;
    private LocalDate endDate;
    private LocalDateTime endTime;
    private int customerID;
    private int userID;
    private int contactID;

    /**
     * Appointment constructor.
     *
     * @param aptID
     * @param title
     * @param description
     * @param location
     * @param contactName
     * @param type
     * @param startDate
     * @param startTime
     * @param endDate
     * @param endTime
     * @param customerID
     * @param userID
     * @param contactID
     */
    public Appointment(int aptID, String title, String description, String location, String contactName, String type,
                       LocalDate startDate, LocalDateTime startTime, LocalDate endDate, LocalDateTime endTime, int customerID, int userID, int contactID) {
        this.aptID = aptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactName = contactName;
        this.type = type;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * Appointment alerts.
     *
     * @param aptID
     * @param startDate
     * @param startTime
     */
    public Appointment(int aptID, LocalDate startDate, LocalDateTime startTime) {
        this.aptID = aptID;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    /**
     * Appointment ID.
     */
    public int getAptID() {
        return aptID;
    }
    public void setAptID(int aptID) {
        this.aptID = aptID;
    }

    /**
     * Appointment title.
     */
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Appointment contact name.
     */
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Appointment description.
     */
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Appointment location.
     */
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Appointment type.
     */
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Appointment start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Appointment end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Appointment start time.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Appointment end time.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Appointment customer ID.
     */
    public int getCustomerID() {
        return this.customerID;
    }
    public void setCustomerID(int ID) {
        this.customerID = ID;
    }

    /**
     * Appointment user ID.
     */
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Appointment contact ID.
     */
    public int getContactID() {
        return contactID;
    }
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}