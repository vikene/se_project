package firstlook.gohoo.utacarparkingsystem;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import firstlook.gohoo.utacarparkingsystem.adapter.viewReservationsAdapter;
import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;

public class view_my_no_shows_violations extends AppCompatActivity {
    List<parkingSpot> spots;
    viewReservationsAdapter adapter;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_no_shows_violations);
        RecyclerView rv = findViewById(R.id.rv3);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.spots = new ArrayList<>();
        adapter = new viewReservationsAdapter(this.spots, getApplicationContext());
        adapter.second = getIntent().getStringExtra("noshow") != null;
        rv.setAdapter(adapter);
        dbref = FirebaseDatabase.getInstance().getReference();
        getData();
    }

    public void getData(){
        ChildEventListener dataListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                parkingSpot _spot = dataSnapshot.getValue(parkingSpot.class);
                spots.add(_spot);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dbref = FirebaseDatabase.getInstance().getReference("/voilations").child(FirebaseAuth.getInstance().getUid());
        dbref.addChildEventListener(dataListener);
    }
}
