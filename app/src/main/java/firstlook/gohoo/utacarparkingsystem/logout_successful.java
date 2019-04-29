package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class logout_successful extends AppCompatActivity {
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_successful);
        mauth = FirebaseAuth.getInstance();
        mauth.signOut();
        Intent newInt = new Intent(getApplicationContext(), Login_UI.class);
        startActivity(newInt);
    }
}
