package ke.co.softttech.lydia.softtech_sacco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
    Handler handler;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected())
//        {
            handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent=new Intent(Splash.this,LoginActivity.class);
                startActivity(intent);
                finish();
            },2000);
        }
//        Toast.makeText(this, "No Internet Connection",Toast.LENGTH_LONG).show();
//    }


    }
