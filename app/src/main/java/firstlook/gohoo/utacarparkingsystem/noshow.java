package firstlook.gohoo.utacarparkingsystem;

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
import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;

public class noshow extends AppCompatActivity {
    TextView username;
    TextView date;
    TextView start;
    TextView duration;
    TextView area;
    TextView spot;
    TextView voilation;
    parkingSpot _spot;
    DatabaseReference dbref;
    String _key;
    ValueEventListener dataListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewselectednoshow);
        _key = getIntent().getStringExtra("key");
        username = findViewById(R.id.username);
        date = findViewById(R.id.date);
        start = findViewById(R.id.time);
        duration = findViewById(R.id.duration);
        area = findViewById(R.id.area);
        spot = findViewById(R.id.spot);
        voilation = findViewById(R.id.Error);
        dbref = FirebaseDatabase.getInstance().getReference();
        getData();
    }

    public void getData(){
        dataListner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                parkingSpot user = dataSnapshot.getValue(parkingSpot.class);

                if(user != null){
                    username.setText("Username: "+user.getUsername().substring(user.getUsername().length()-4));
                    date.setText("Date: "+ user.getDate());
                    start.setText("Start Time: "+user.getStarttime());
                    duration.setText("Duration: "+user.getDuration());
                    area.setText("Area: "+user.getArea());
                    spot.setText("Spot: "+ user.getSpot());
                    voilation.setText("Voilations "+user.getVoilations());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbref.child("voilations").child(FirebaseAuth.getInstance().getUid()).child(_key).addValueEventListener(dataListner);

    }
}
