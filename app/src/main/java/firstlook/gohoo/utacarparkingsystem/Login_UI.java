package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login_UI extends AppCompatActivity {
    private Button registration_button;
    private Button login_button;
    private EditText userName, password;
    FirebaseDatabase db;

    @Override
    public void onStart(){
        super.onStart();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), login_sucessfull.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__ui);
        setupUIViews();


        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(Registration_UI.class);
            }

        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_check();
                //openActivity(Parking_User_Homepage.class);
            }

        });
    }

    public void openActivity(Class<?> handlerClass) {
        Intent intent = new Intent(this, handlerClass);
        startActivity(intent);
    }

    private void setupUIViews() {
        registration_button = (Button) findViewById(R.id.registration_button);
        login_button = (Button) findViewById(R.id.login_button);
        userName = (EditText) findViewById(R.id.etloginuserName);
        password = (EditText) findViewById(R.id.etloginpassword);

    }



    public void login_check() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(userName.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    openActivity(login_sucessfull.class);
                }
            }
        });
    }
}




