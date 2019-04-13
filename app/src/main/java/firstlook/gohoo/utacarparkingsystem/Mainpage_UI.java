package firstlook.gohoo.utacarparkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainpage_UI extends AppCompatActivity {
    private Button registration_button;
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage__ui);
        registration_button = (Button) findViewById(R.id.registration_button);
        login_button = (Button) findViewById(R.id.login_button);
        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(Registration_UI.class);
            }

        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(Login_UI.class);
            }

        });
    }

    public void openActivity(Class<?> handlerClass){
        Intent intent = new Intent(this, handlerClass);
        startActivity(intent);
    }
}


