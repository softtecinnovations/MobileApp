package ke.co.softttech.lydia.softtech_sacco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home1:
                        startActivity(new Intent(Services.this,HomeActivity.class));
                        break;
                    case R.id.services:
                        startActivity(new Intent(Services.this,Services.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(Services.this,Profile.class));
                        break;
                }
                return true;
            }
        });
    }
}