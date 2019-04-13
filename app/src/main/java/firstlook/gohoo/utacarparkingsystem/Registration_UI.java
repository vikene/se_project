package firstlook.gohoo.utacarparkingsystem;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import java.sql.Connection;

public class Registration_UI extends AppCompatActivity {
    private EditText firstName, lastName, userName, password, emailId, utaId, city, streeAdd, phoneNum, state, zipCode, vehicleNum, parkingType;
    private Button sigMeUp;
    private Spinner role;
    private TextView userLogin;
    private DatabaseHelper db;

    // private static final String DB_URL="jdbc:mysql://192.168.0.13/SE";
    //private static final String User="root";
    //private static final String pass="root";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__ui);
        setupUIViews();


        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(Login_UI.class);

            }
        });

        sigMeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              validate();

            }
        });



        Log.e("TEXT","-----------------Came here to onCreate-----------------");

        db = new DatabaseHelper(Registration_UI.this);
        Log.e("TEXT","-----------------Came here Too-----------------");


    }

    private void setupUIViews() {
        firstName = (EditText) findViewById(R.id.etfirstName);
        lastName = (EditText) findViewById(R.id.etlastName);
        userName = (EditText) findViewById(R.id.etuserName);
        password = (EditText) findViewById(R.id.etPassword);
        role = (Spinner) findViewById(R.id.etRole);
        emailId = (EditText) findViewById(R.id.etEmail);
        utaId = (EditText) findViewById(R.id.etUtaId);
        city = (EditText) findViewById(R.id.etCity);
        streeAdd = (EditText) findViewById(R.id.etAddress);
        phoneNum = (EditText) findViewById(R.id.etPhone);
        state = (EditText) findViewById(R.id.etState);
        zipCode = (EditText) findViewById(R.id.etZip);
        vehicleNum = (EditText) findViewById(R.id.etVehicleNum);
        parkingType = (EditText) findViewById(R.id.etParkingType);
        sigMeUp = (Button) findViewById(R.id.Sign_me_up);
        userLogin = (TextView) findViewById(R.id.text_login);

    }

        public void validate() {

        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String name = userName.getText().toString();
        String userpassword = password.getText().toString();
        String userrole = role.getSelectedItem().toString();
        String email = emailId.getText().toString();
        String usercity = city.getText().toString();
        String useraddress = streeAdd.getText().toString();
        String phone = phoneNum.getText().toString();
        String userstate = state.getText().toString();
        String zip = zipCode.getText().toString();
        String vehicleNumber = vehicleNum.getText().toString();
        String type = parkingType.getText().toString();

        if (fName.isEmpty() && lName.isEmpty() && name.isEmpty() && useraddress.isEmpty()
                && userpassword.isEmpty() && userrole.isEmpty() && email.isEmpty() && usercity.isEmpty() && phone.isEmpty() && userstate.isEmpty()
                && zip.isEmpty() && vehicleNumber.isEmpty() && type.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            long id = insertData(fName, lName, name, userpassword, userrole, email, usercity, useraddress, phone, userstate, zip, vehicleNumber, type);

            if (id >0) {
                Toast.makeText(this, "Registered Successfully" + id, Toast.LENGTH_LONG).show();
                openActivity(Login_UI.class);

            } else {
                Toast.makeText(this, "Error in registration", Toast.LENGTH_LONG).show();
            }

        }


    }



    public void openActivity(Class<?> handlerClass) {
        Intent intent = new Intent(this, handlerClass);
        startActivity(intent);
    }

    public long insertData(String fName, String lName, String name, String userpassword, String userrole, String email, String usercity,
                           String useraddress, String phone, String userstate, String zip, String vehicleNumber, String type) {
        return db.insertParkingUser(fName, lName, name, userpassword, userrole, email, usercity, useraddress, phone, userstate, zip, vehicleNumber, type);

    }
}
