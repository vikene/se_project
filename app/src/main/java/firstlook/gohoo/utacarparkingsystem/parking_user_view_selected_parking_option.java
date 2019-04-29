package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;

public class parking_user_view_selected_parking_option extends AppCompatActivity {
    DatabaseReference dbref;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    TextView t8;
    CheckBox camera;
    CheckBox cart;
    CheckBox history;
    Button reserve;
    parkingSpot spot;
    ValueEventListener dataListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_user_view_selected_parking_option);
        Intent key = getIntent();
        final String _key = key.getStringExtra("key");
        final String _route = key.getStringExtra("from");

        dbref = FirebaseDatabase.getInstance().getReference();
        t1 = findViewById(R.id.textView1);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView4);
        t4 = findViewById(R.id.textView5);
        t5 = findViewById(R.id.textView6);
        t6 = findViewById(R.id.textView7);
        t7 = findViewById(R.id.textView8);
        camera = findViewById(R.id.checkbox1);
        cart = findViewById(R.id.checkbox2);
        history = findViewById(R.id.checkbox3);
        reserve = findViewById(R.id.button2);
        if(_route != null){
            // this is from modify
            reserve.setText("Update");
            getTransData(_key);

            reserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbref.removeEventListener(dataListner);
                    spot.setCamera(camera.isChecked());
                    spot.setHistory(history.isChecked());
                    spot.setCart(cart.isChecked());
                    spot.setUsername(FirebaseAuth.getInstance().getUid());
                    spot.setKey(_key);
                    dbref.child("reservations").child(_key).setValue(spot);
                    finish();
                }
            });
        }
        else{
            getUserData(_key);
            reserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbref.removeEventListener(dataListner);
                    spot.setCamera(camera.isChecked());
                    spot.setHistory(history.isChecked());
                    spot.setCart(cart.isChecked());
                    spot.setUsername(FirebaseAuth.getInstance().getUid());
                    DatabaseReference ref =dbref.child("reservations").push();
                    spot.setKey(ref.getKey());
                    dbref.child("reservations").child(ref.getKey()).setValue(spot);
                    dbref.child("parkingspots").child(_key).removeValue();

                    finish();
                }
            });
        }



    }
    public void getTransData(String _key){
        dataListner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                parkingSpot spotdetails = dataSnapshot.getValue(parkingSpot.class);
                spot = spotdetails;
                if(spotdetails != null){
                    t1.setText("Start Time: "+spotdetails.getStarttime());
                    t2.setText("Duration of Parking: "+spotdetails.getDuration());
                    t3.setText("Parking Area Name: "+spotdetails.getArea());
                    t4.setText("Floor Number: "+ spotdetails.getFloor());
                    t5.setText("Parking Type: "+spotdetails.getType());
                    t6.setText("Parking Spot Number: "+spotdetails.getSpot());
                    camera.setChecked(spotdetails.getCamera());
                    cart.setChecked(spotdetails.getCart());
                    history.setChecked(spotdetails.getHistory());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbref.child("reservations").child(_key).addValueEventListener(dataListner);

    }
    public void getUserData(String _key){
        dataListner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                parkingSpot spotdetails = dataSnapshot.getValue(parkingSpot.class);
                spot = spotdetails;
                if(spotdetails != null){
                    t1.setText("Start Time: "+spotdetails.getStarttime());
                    t2.setText("Duration of Parking: "+spotdetails.getDuration());
                    t3.setText("Parking Area Name: "+spotdetails.getArea());
                    t4.setText("Floor Number: "+ spotdetails.getFloor());
                    t5.setText("Parking Type: "+spotdetails.getType());
                    t6.setText("Parking Spot Number: "+spotdetails.getSpot());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbref.child("parkingspots").child(_key).addValueEventListener(dataListner);

    }
}
