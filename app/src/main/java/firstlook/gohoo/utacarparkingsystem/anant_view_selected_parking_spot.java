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

import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;

public class anant_view_selected_parking_spot extends AppCompatActivity {
    DatabaseReference dbref;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    TextView t8;
    TextView t9;
    parkingSpot spotdetails;
    ValueEventListener dataListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anant_view_selected_parking_spot);
        dbref = FirebaseDatabase.getInstance().getReference();
        Intent key = getIntent();
        final String _key = key.getStringExtra("key");
        getUserData(_key);
        t1 = findViewById(R.id.textView1);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView4);
        t4 = findViewById(R.id.textView5);
        t5 = findViewById(R.id.textView6);
        Button btn = findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref.removeEventListener(dataListener);
                spotdetails.setIsavailable(false);
                dbref.child("parkingspots").child(_key).setValue(spotdetails);
                finish();
            }
        });

    }


    public void getUserData(String _key){
         dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 spotdetails = dataSnapshot.getValue(parkingSpot.class);
                if(spotdetails != null){
                    t1.setText("Parking Spot Number : "+spotdetails.getSpot());
                    t2.setText("Parking Area name : "+spotdetails.getArea());
                    t3.setText("Floor Number : "+spotdetails.getFloor());
                    t4.setText("Parking Spot status: "+spotdetails.getIsavailable());
                    t5.setText("Parking type: "+spotdetails.getType());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbref.child("parkingspots").child(_key).addValueEventListener(dataListener);

    }
}
