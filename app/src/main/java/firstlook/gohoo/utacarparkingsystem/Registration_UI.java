package firstlook.gohoo.utacarparkingsystem;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Connection;

import firstlook.gohoo.utacarparkingsystem.model.User;

public class Registration_UI extends AppCompatActivity {
    private EditText firstName, lastName, userName, password, emailId, utaId, city, streeAdd, phoneNum, state, zipCode, vehicleNum, parkingType;
    private Button sigMeUp;
    private Spinner role;
    private TextView userLogin;
    private DatabaseHelper db;
    private DatabaseReference dbref;

    // private static final String DB_URL="jdbc:mysql://192.168.0.13/SE";
    //private static final String User="root";
    //private static final String pass="root";
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__ui);
        setupUIViews();
        auth = FirebaseAuth.getInstance();

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


        long id = insertData(fName, lName, name, userpassword, userrole, email, usercity, useraddress, phone, userstate, zip, vehicleNumber, type);

        if (id >0) {
            Toast.makeText(this, "Registered Successfully" + id, Toast.LENGTH_LONG).show();
            openActivity(Login_UI.class);

        } else {
            Toast.makeText(this, "Error in registration", Toast.LENGTH_LONG).show();
        }




    }



    public void openActivity(Class<?> handlerClass) {
        Intent intent = new Intent(this, handlerClass);
        startActivity(intent);
    }

    public long insertData(final String fName, final String lName, final String name, final String userpassword, final String userrole, final String email, final String usercity,
                           final String useraddress, final String phone, final String userstate, final String zip, final String vehicleNumber, final String type) {

        auth.createUserWithEmailAndPassword(name, userpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "CREATED USER", Toast.LENGTH_LONG).show();
                            User newUser = new User();
                            newUser.setFirstName(fName);
                            newUser.setLastName(lName);
                            newUser.setFullName(name);
                            newUser.setUserPassword(userpassword);
                            newUser.setUserRole(userrole);
                            newUser.setEmail(email);
                            newUser.setUserCity(usercity);
                            newUser.setUserAddress(useraddress);
                            newUser.setPhone(phone);
                            newUser.setUserstate(userstate);
                            newUser.setZip(zip);
                            newUser.setVehicleNumber(vehicleNumber);
                            newUser.setType(type);
                            auth.getCurrentUser().getUid();
                            dbref.child("users").child(auth.getCurrentUser().getUid()).setValue(newUser);
                            openActivity(Login_UI.class);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"FAILED TO CREATE USER", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        return -1;

    }
    /*
        How to get The stored DATA ?
        Real time search
        databaseReference.child('users').orderByChild('searchLastName')
                 .startAt(queryText)
                 .endAt(queryText+"\uf8ff");

        fetching data to POJO

        ValueEventListener dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
    dbref.addValueEventListener(dataListener);


     */

    @Override
    public void onStart(){
        super.onStart();
        dbref = FirebaseDatabase.getInstance().getReference();
    }
}
