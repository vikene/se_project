package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import firstlook.gohoo.utacarparkingsystem.model.User;
import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;

public class parking_user_view_my_selected_reservation extends AppCompatActivity {
    DatabaseReference dbref;
    ValueEventListener dataListener;
    parkingSpot spotdetails;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    TextView t8;
    TextView t9;
    Button modify;
    Button modifyOption;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_user_view_my_selected_reservation);
        dbref = FirebaseDatabase.getInstance().getReference();
        Intent key = getIntent();
        final String _key = key.getStringExtra("key");
        getUserData(_key);
        t1 = findViewById(R.id.textView1);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView4);
        t4 = findViewById(R.id.textView5);
        t5 = findViewById(R.id.textView6);
        t6 = findViewById(R.id.textView7);
        t7 = findViewById(R.id.textView8);
        t8= findViewById(R.id.textView9);
        modify = findViewById(R.id.button1);
        modifyOption = findViewById(R.id.button2);
        cancel = findViewById(R.id.button4);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref.child("reservations").child(_key).removeEventListener(dataListener);
                dbref.child("reservations").child(_key).removeValue();
                DatabaseReference ref = dbref.child("parkingspots").push();
                spotdetails.setUsername("");
                if(ref.getKey() != null){
                    spotdetails.setKey(ref.getKey());
                    dbref.child("parkingspots").child(ref.getKey()).setValue(spotdetails);
                }
                finish();

            }
        });
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), parking_user_view_selected_parking_option.class);
                intent.putExtra("from","reservations");
                intent.putExtra("key", spotdetails.getKey());
                startActivity(intent);
            }
        });
        modifyOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), parking_user_view_selected_parking_option.class);
                intent.putExtra("from","reservations");
                intent.putExtra("key", spotdetails.getKey());
                startActivity(intent);
            }
        });


    }
    public void getUserData(String _key){
         dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 spotdetails = dataSnapshot.getValue(parkingSpot.class);
                if(spotdetails != null){
                    t1.setText("Start Time: "+spotdetails.getStarttime());
                    t2.setText("Duration of Parking: "+spotdetails.getDuration());
                    t3.setText("Parking area name: "+spotdetails.getArea());
                    t4.setText("Floor number: "+spotdetails.getFloor());
                    t5.setText("Parking type: "+spotdetails.getType());
                    t6.setText("Parking spot number: "+spotdetails.getSpot());
                    t7.setText("Reservation no: "+spotdetails.getKey());
                    String toprint = "";
                    if(spotdetails.getCamera()){
                        toprint += " Camera ";
                    }
                    if(spotdetails.getCart()){
                        toprint += " Cart ";
                    }
                    if(spotdetails.getHistory()){
                        toprint += " History ";
                    }
                    t8.setText("Parking option selected:  "+toprint);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbref.child("reservations").child(_key).addValueEventListener(dataListener);

    }
}
