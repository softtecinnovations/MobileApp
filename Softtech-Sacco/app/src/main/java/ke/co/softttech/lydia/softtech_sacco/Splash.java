package ke.co.softttech.lydia.softtech_sacco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
    Handler handler;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Context mContext;
    private TextView splash;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash = findViewById(R.id.appName);

       ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
       if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected())
       {
            handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent=new Intent(Splash.this,LoginActivity.class);
                startActivity(intent);
                finish();
            },5000);
        }

        Toast.makeText(this, "No Internet Connection",Toast.LENGTH_LONG).show();
   }

        @Override
        protected void onResume() {
            super.onResume();

            // Fetching the stored data
            // from the SharedPreference
            sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);


            String saccoName = sharedPreferences.getString("saccoName", "");

            // Setting the fetched data
            // in the EditTexts
            splash.setText(saccoName);

        }

    }
