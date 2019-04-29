package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import firstlook.gohoo.utacarparkingsystem.model.User;
import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;

public class login_sucessfull extends AppCompatActivity {
    DatabaseReference dbref;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sucessfull);
        dbref = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        getUserData();
        //createFake();
    }

    public void createFake(){
        parkingSpot spot = new parkingSpot("7:30 PM","3 Hours","MAC", "3","Basic", "1",false,false,false);
        for(int i=2;i<10;i++){
            DatabaseReference key = dbref.child("parkingspots").push();
            String k = key.getKey();
            spot.setKey(k);
            dbref.child("parkingspots").child(k).setValue(spot);
            spot.setSpot(i+"");
        }
    }

    public void getUserData(){
        ValueEventListener dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Intent myintent = null;
                if(user.getUserRole().equals("User")){
                    myintent = new Intent(getApplicationContext(), Parking_User_Homepage.class);
                }
                else if(user.getUserRole().equals("Manager")){
                    myintent = new Intent(getApplicationContext(), Parking_Manager_Homepage.class);
                }
                else if(user.getUserRole().equals("Admin")){
                    myintent = new Intent(getApplicationContext(), Admin_Homepage.class);
                }
                if(myintent != null){
                    startActivity(myintent);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbref.child("users").child(auth.getUid()).addValueEventListener(dataListener);

    }

}
