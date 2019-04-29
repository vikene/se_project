package firstlook.gohoo.utacarparkingsystem;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;

public class utsab_view_selected_reservation_by_parking_manager extends AppCompatActivity {
    TextView reservationNumber;
    TextView userName;
    TextView date;
    TextView startTime;
    TextView endTime;
    TextView parkingareaName;
    TextView parkingspotNumber;
    TextView floorNumber;
    TextView  parkingType;
    TextView  parkingOptions;
    Button  cancelReservation;
    Button modifyReservation;
    Button   modifyparkingOptions;
    Button   logOut;
    DatabaseReference dbref;
    parkingSpot _spot;
    ValueEventListener dataListener;
    String _key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utsab_view_selected_reservation_by_parking_manager);
        _key = getIntent().getStringExtra("key");
        reservationNumber = (TextView) findViewById(R.id.textView);
        userName = (TextView) findViewById(R.id.username);
        date = (TextView) findViewById(R.id.date);
        startTime = (TextView) findViewById(R.id.starttime);
        endTime = (TextView) findViewById(R.id.endtime);
        parkingareaName = (TextView) findViewById(R.id.parkingareaname);
        parkingspotNumber = (TextView) findViewById(R.id.parkingspotnum);
        floorNumber = (TextView) findViewById(R.id.floor);
        parkingType = (TextView) findViewById(R.id.parkingtype);
        parkingOptions = (TextView) findViewById(R.id.options);
        cancelReservation = (Button) findViewById(R.id.deletebutton);
        modifyReservation = (Button) findViewById(R.id.setoverdueutton);
        modifyparkingOptions = (Button) findViewById(R.id.setnoshowbutton);
        logOut = (Button) findViewById(R.id.logout);
        modifyReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                   if( _spot.getVoilations() != null){
                       _spot.setVoilations(_spot.getVoilations() + " Overdue ");
                   }
                   else {
                       _spot.setVoilations(" Overdue ");
                   }
                    dbref.child("voilations").child(_spot.getUsername()).child(_spot.getKey()).setValue(_spot);

                finish();
            }
        });
        modifyparkingOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (_spot.getVoilations() != null) {
                    _spot.setVoilations(_spot.getVoilations() + " No show ");
                } else {
                    _spot.setVoilations(" No Show ");
                }
                dbref.child("voilations").child(_spot.getUsername()).child(_spot.getKey()).setValue(_spot);

                finish();
            }

        });
        cancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref.child("reservations").child(_key).removeEventListener(dataListener);
                dbref.child("reservations").child(_key).removeValue();
                DatabaseReference ref = dbref.child("parkingspots").push();
                _spot.setUsername("");
                if(ref.getKey() != null){
                    _spot.setKey(ref.getKey());
                    dbref.child("parkingspots").child(ref.getKey()).setValue(_spot);
                }
                finish();
            }
        });
        getData();
    }
    public void getData(){
         dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                parkingSpot spot = dataSnapshot.getValue(parkingSpot.class);
                if(spot!=null){
                    _spot = spot;
                    reservationNumber.setText("Reservation id "+spot.getKey().substring(spot.getKey().length()-4));
                    userName.setText("username "+spot.getUsername().substring(spot.getUsername().length()-4));
                    date.setText("Date: "+spot.getDate());
                    startTime.setText("Start Time: "+spot.getStarttime());
                    endTime.setText("Duration:  "+spot.getDuration());
                    parkingareaName.setText("Area: "+spot.getArea());
                    parkingspotNumber.setText("Parking spot: "+spot.getSpot());
                    floorNumber.setText("Floor No: "+spot.getFloor());
                    parkingType.setText("Parking Type: "+spot.getType());
                    String options = "";
                    if(spot.getCart()){
                        options += " cart";
                    }
                    if(spot.getCamera()){
                        options += " Camera";
                    }
                    if(spot.getHistory()){
                        options += " History";
                    }
                    parkingOptions.setText("Parking options: "+options);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.child("reservations").child(_key).addValueEventListener(dataListener);
    }
}
