package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class Login_UI extends AppCompatActivity {
    private Button registration_button;
    private Button login_button;
    private EditText userName, password;
    DatabaseHelper db;

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
       db= new DatabaseHelper(this);
        String username = userName.getText().toString();
        String userpassword = password.getText().toString();
        String role="parking user";
        String Role1="Parking Manager";
        String Role2="Admin";

        if (username.isEmpty() || userpassword.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
        }

        else{
            Log.e("TEXT", "-----------------iam inside login now-----------------");


          boolean checkUsernamePassword=db.checkUsernamePasswordParkinUser(username,userpassword);

            Log.e("TEXT", "-----------------iam inside login now-----------------");

          if(checkUsernamePassword){

              Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
              openActivity(Parking_User_Homepage.class);
              Log.e("TEXT", "-----------------I AM A PARKING USER! Don't mess with me!! -----------------");
          }
          else {
             // Toast.makeText(getApplicationContext(), "Wrong username and password", Toast.LENGTH_LONG).show();
              boolean checkUsernamePasswordmanager=db.checkUsernamePasswordPakingManager(username,userpassword);
              if(checkUsernamePasswordmanager){
                  Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                  Log.e("TEXT", "-----------------I AM A PARKING MANAGER-----------------");
                  openActivity(Parking_Manager_Homepage.class);

              }

              else{
                  boolean checkUsernamePasswordAdmin=db.checkUsernamePasswordAdmin(username,userpassword);
                  if(checkUsernamePasswordAdmin) {
                      Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                      Log.e("TEXT", "-----------------I am Admin -----------------");
                      openActivity(Admin_Homepage.class);
                  }

                  else{
                      Toast.makeText(getApplicationContext(), "Wrong username and password", Toast.LENGTH_LONG).show();
                  }
              }
          }




    }
}}




