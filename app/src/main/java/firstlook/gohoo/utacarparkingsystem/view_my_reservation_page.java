package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
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
import java.util.LinkedList;
import java.util.List;

import firstlook.gohoo.utacarparkingsystem.adapter.viewReservationsAdapter;
import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;

public class view_my_reservation_page extends AppCompatActivity {
    viewReservationsAdapter adapter;
    List<parkingSpot> spots = new ArrayList<>();
    DatabaseReference dbref;
    Boolean manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_reservation_page);
        this.spots = new LinkedList<>();
        RecyclerView rv = findViewById(R.id.rv1);
        Intent intent = getIntent();
        String route = intent.getStringExtra("type");

        dbref = FirebaseDatabase.getInstance().getReference();
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new viewReservationsAdapter(this.spots, getApplicationContext());
        rv.setAdapter(adapter);
        if(route != null){
            manager = true;
            adapter.calling = true;
        }else{
            manager = false;
        }
        getData();
    }
    public void getData(){
        ChildEventListener dataListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                parkingSpot _spot = dataSnapshot.getValue(parkingSpot.class);
                if(manager){
                    if(_spot!=null){
                        spots.add(_spot);
                        adapter.notifyDataSetChanged();
                    }
                }
                else{
                    if(_spot!=null && _spot.getUsername() != null && _spot.getUsername().equals(FirebaseAuth.getInstance().getUid())){
                        spots.add(_spot);
                        adapter.notifyDataSetChanged();
                    }
                }


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
        dbref = FirebaseDatabase.getInstance().getReference("reservations");
        dbref.addChildEventListener(dataListener);
    }
}
