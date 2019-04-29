package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import firstlook.gohoo.utacarparkingsystem.model.User;

public class view_profile extends AppCompatActivity {
    DatabaseReference dbref;
    private TextView firstName, lastName, userName, password, emailId, utaId, city,
            streeAdd, phoneNum, state, zipCode, vehicleNum, parkingType,role;
    ValueEventListener dataListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        dbref = FirebaseDatabase.getInstance().getReference();
        firstName = (TextView) findViewById(R.id.textView1);
        lastName = (TextView) findViewById(R.id.textView2);
        userName = (TextView) findViewById(R.id.textView3);
        password = (TextView) findViewById(R.id.textView4);
        role = (TextView) findViewById(R.id.textView5);
        emailId = (TextView) findViewById(R.id.textView7);
        utaId = (TextView) findViewById(R.id.textView6);
        city = (TextView) findViewById(R.id.textView10);
        streeAdd = (TextView) findViewById(R.id.textView9);
        phoneNum = (TextView) findViewById(R.id.textView8);
        state = (TextView) findViewById(R.id.textView12);
        zipCode = (TextView) findViewById(R.id.textView13);
        vehicleNum = (TextView) findViewById(R.id.textView14);
        parkingType = (TextView) findViewById(R.id.textView15);
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
