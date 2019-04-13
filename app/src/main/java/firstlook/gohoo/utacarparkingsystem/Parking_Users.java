package firstlook.gohoo.utacarparkingsystem;

public class Parking_Users {
    public static final String TABLE_NAME = "parking_users";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_STREETADDRESS = "streetAddress";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ZIPCODE = "zipCode";
    public static final String COLUMN_VEHICLENUM = "vehicleNum";
    public static final String COLUMN_PARKINGTYPE = "parkingType";


    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String role;
    private String email;
    private String city;
    private String state;
    private String streetAddress;
    private String phone;
    private String zipCode;
    private String vehicleNum;
    private String parkingType;


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_FIRSTNAME + " TEXT," +
            COLUMN_LASTNAME + " TEXT," +
            COLUMN_USERNAME + " TEXT," +
            COLUMN_PASSWORD + " INTEGER," +
            COLUMN_ROLE + " TEXT," +
            COLUMN_EMAIL + " INT," +
            COLUMN_CITY + " TEXT," +
            COLUMN_STATE + " TEXT," +
            COLUMN_STREETADDRESS + " TEXT," +
            COLUMN_PHONE + " INTEGER," +
            COLUMN_ZIPCODE + " INTEGER," +
            COLUMN_VEHICLENUM + " INTEGER," +
            COLUMN_PARKINGTYPE + " TEXT" +
            ")";

    public Parking_Users() {
    }

    public String getCreateTableQuery(){
        return CREATE_TABLE;
    }

    public Parking_Users(int id, String firstName, String lastName, String userName, String password, String role,
                         String email, String city, String state,String streetAddress,String phone,String zipCode,
                         String vehicleNum, String parkingType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
        this.city = city;
        this.state = state;
        this.streetAddress = streetAddress;
        this.phone = phone;
        this.zipCode = zipCode;
        this.vehicleNum = vehicleNum;
        this.parkingType = parkingType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }
}
