package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Parking_Manager_Homepage extends AppCompatActivity {
    Button logout;
    Button searchParking;
    Button searchReservation;
    Button viewProfile;
    Button updateProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking__manager__homepage);
        viewProfile  = findViewById(R.id.parkingmanager1);
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), view_profile.class);

                startActivity(intent);
            }
        });
        updateProfile = findViewById(R.id.parkingmanager2);
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Parking_user_update_profile.class);

                startActivity(intent);
            }
        });
        searchParking = findViewById(R.id.parkingmanager3);
        searchParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), parking_user_search_parking_spot_filters.class);
                intent.putExtra("profile","manager");
                startActivity(intent);
            }
        });
        logout = findViewById(R.id.parkingmanager6);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), logout_successful.class);
                startActivity(intent);
            }
        });
        searchReservation = findViewById(R.id.parkingmanager5);
        searchReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), view_my_reservation_page.class);
                intent.putExtra("type","manager");
                startActivity(intent);
            }
        });
    }
}
