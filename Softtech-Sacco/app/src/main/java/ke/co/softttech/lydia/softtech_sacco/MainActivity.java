package ke.co.softttech.lydia.softtech_sacco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.models.ServiceModel;

public class MainActivity extends AppCompatActivity {

    LinearLayout logout;
    private RecyclerView recyclerView;
    private ServicesAdapter adapter;
    private List<ServiceModel> serviceList;
    ImageButton backenterpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backenterpin = findViewById(R.id.backenterpin);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(view1 -> {startActivity(new Intent(this,LoginActivity.class));finish();});

        backenterpin.setOnClickListener(view -> {startActivity(new Intent(this,LoginActivity.class));
            finish();});
//        Menu menu  = bottomNavigationView.getMenu();
//        MenuItem menuItem = menu.getItem(0);
//        menuItem.setChecked(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.home1:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.services:
                    selectedFragment = new ServicesFragment();
                    break;
                case R.id.profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
                // It will help to replace the
                // one fragment to other.
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;

        }
    };
}