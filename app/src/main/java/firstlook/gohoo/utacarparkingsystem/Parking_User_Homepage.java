package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Parking_User_Homepage extends AppCompatActivity {
    Button viewProfile;
    Button updateProfile;
    Button search;
    Button view;
    Button noshow;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking__user__homepage);
        viewProfile = findViewById(R.id.parkinguser1);
        updateProfile = findViewById(R.id.parkinguser2);
        search = findViewById(R.id.parkinguser3);
        view = findViewById(R.id.parkinguser4);
        noshow = findViewById(R.id.parkinguser5);
        logout = findViewById(R.id.parkinguser6);
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), view_profile.class);
                startActivity(intent);
            }
        });
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Parking_user_update_profile.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), parking_user_search_parking_spot_filters.class);
                intent.putExtra("profile","user");
                startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), view_my_reservation_page.class);
                startActivity(intent);
            }
        });
        noshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), view_my_no_shows_violations.class);
                intent.putExtra("noshow","noshow");
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), logout_successful.class);
                startActivity(intent);
            }
        });
    }
}
