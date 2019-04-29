package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import firstlook.gohoo.utacarparkingsystem.adapter.viewParkingAdapter;
import firstlook.gohoo.utacarparkingsystem.model.User;
import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;

public class parking_user_search_parking_spot_filters extends AppCompatActivity {

    viewParkingAdapter adapter;
    RecyclerView rv;
    List<parkingSpot> spots;
    SearchView searchTime;
    SearchView searchArea;
    SearchView parking;

    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_user_search_parking_spot_filters);
        rv = findViewById(R.id.rv);
        Intent from = getIntent();
        String profile = from.getStringExtra("profile");

        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.spots = new LinkedList<>();
        adapter = new viewParkingAdapter(this.spots,getApplicationContext());
        dbref = FirebaseDatabase.getInstance().getReference();
        rv.setAdapter(adapter);
        if(profile.equals("manager")){
            adapter.setViewed(false);
        }
        getData();
        searchTime = findViewById(R.id.editText1);
        searchArea = findViewById(R.id.editText2);
        parking = findViewById(R.id.editTex3);
        searchArea.setQuery("PARKING AREA NAME", false);
        parking.setQuery("Duration", false);
        searchTime.setQuery("Start time", false);

        searchTime.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.setFilter("time");
                adapter.getFilter().filter(s);
                return false;
            }
        });
        searchArea.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.setFilter("area");
                adapter.getFilter().filter(s);
                return false;
            }
        });
        parking.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.setFilter("duration");
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }
    public void getData(){
        ChildEventListener dataListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                parkingSpot _spot = dataSnapshot.getValue(parkingSpot.class);
                if(_spot != null && _spot.getIsavailable()){
                    spots.add(_spot);
                    adapter.notifyDataSetChanged();
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
        dbref = FirebaseDatabase.getInstance().getReference("/parkingspots");
        dbref.addChildEventListener(dataListener);
    }
}
