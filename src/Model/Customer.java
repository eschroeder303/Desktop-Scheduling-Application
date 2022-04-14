package Model;

/**
 * Customer class.
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private String country;
    private String division;
    private int divisionID;
    private int countryID;

    /**
     * Customer constructor.
     *
     * @param customerID
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhone
     * @param country
     * @param division
     * @param divisionID
     * @param countryID
     */
    public Customer(int customerID, String customerName, String customerAddress, String customerPostalCode,
                    String customerPhone, String country, String division, int divisionID, int countryID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.country = country;
        this.division = division;
        this.customerPhone = customerPhone;
        this.divisionID = divisionID;
        this.countryID = countryID;
    }

    /**
     * Customer constructor for the customer list.
     *
     * @param customerID
     * @param customerName
     */
    public Customer(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
    }

    /**
     * Customer ID.
     */
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Customer name.
     */
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Customer address.
     */
    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Customer postal code.
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Customer phone number.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Customer country.
     */
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Customer division.
     */
    public String getDivision() {
        return division;
    }
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Customer division ID.
     */
    public int getCustomerDivisionID() {
        return divisionID;
    }
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Customer country ID.
     */
    public int getCountryID() {
        return countryID;
    }
}