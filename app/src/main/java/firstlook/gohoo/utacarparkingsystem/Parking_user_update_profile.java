package firstlook.gohoo.utacarparkingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import firstlook.gohoo.utacarparkingsystem.model.User;
import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;

public class Parking_user_update_profile extends AppCompatActivity {
    private EditText firstName, lastName, userName, password, emailId, utaId, city,
            streeAdd, phoneNum, state, zipCode, vehicleNum, parkingType,role;
    Button update;
    DatabaseReference dbref;
    ValueEventListener dataListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_user_update_profile);
        firstName = (EditText) findViewById(R.id.editText2);
        lastName = (EditText) findViewById(R.id.editText3);
        userName = (EditText) findViewById(R.id.editText4);
        password = (EditText) findViewById(R.id.editText5);
          role = (EditText) findViewById(R.id.editText6);
        emailId = (EditText) findViewById(R.id.editText9);
        utaId = (EditText) findViewById(R.id.editText10);
        city = (EditText) findViewById(R.id.editText11);
        streeAdd = (EditText) findViewById(R.id.editText12);
        phoneNum = (EditText) findViewById(R.id.editText13);
        state = (EditText) findViewById(R.id.editText14);
        zipCode = (EditText) findViewById(R.id.editText15);
        vehicleNum = (EditText) findViewById(R.id.editText17);
        parkingType = (EditText) findViewById(R.id.editText18);
        dbref = FirebaseDatabase.getInstance().getReference();
        update = findViewById(R.id.button);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser = new User();
                newUser.setFirstName(firstName.getText().toString());
                newUser.setLastName(lastName.getText().toString());
                newUser.setFullName(userName.getText().toString());
                newUser.setUserPassword(password.getText().toString());
                newUser.setUserRole(role.getText().toString());
                newUser.setEmail(emailId.getText().toString());
                newUser.setUtaid(utaId.getText().toString());
                newUser.setUserCity(city.getText().toString());
                newUser.setUserAddress(streeAdd.getText().toString());
                newUser.setPhone(phoneNum.getText().toString());
                newUser.setUserstate(state.getText().toString());
                newUser.setZip(zipCode.getText().toString());
                newUser.setVehicleNumber(vehicleNum.getText().toString());
                newUser.setType(parkingType.getText().toString());
                dbref.child("users").child(FirebaseAuth.getInstance().getUid()).removeEventListener(dataListner);
                dbref.child("users").child(FirebaseAuth.getInstance().getUid()).setValue(newUser);
                finish();
            }
        });
        getData();


    }

    public void getData(){

        dataListner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if(user != null){
                    firstName.setText(user.getFirstName());
                    lastName.setText(user.getLastName());
                    userName.setText(user.getFullName());
                    password.setText(user.getUserPassword());
                    emailId.setText(user.getEmail());
                    city.setText(user.getUserCity());
                    streeAdd.setText(user.getUserAddress());
                    phoneNum.setText(user.getPhone());
                    state.setText(user.getUserstate());
                    zipCode.setText(user.getZip());
                    vehicleNum.setText(user.getVehicleNumber());
                    parkingType.setText(user.getType());
                    role.setText(user.getUserRole());
                    utaId.setText(user.getUtaid());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbref.child("users").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(dataListner);

    }
}
