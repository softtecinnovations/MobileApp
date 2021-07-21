package ke.co.softttech.lydia.softtech_sacco.otp;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ke.co.softttech.lydia.softtech_sacco.Db.DatabaseExecutor;
import ke.co.softttech.lydia.softtech_sacco.Db.UserDb;
import ke.co.softttech.lydia.softtech_sacco.EnterPin;
import ke.co.softttech.lydia.softtech_sacco.LoginActivity;
import ke.co.softttech.lydia.softtech_sacco.R;
import ke.co.softttech.lydia.softtech_sacco.api.Api;
import ke.co.softttech.lydia.softtech_sacco.api.apiClient;
import ke.co.softttech.lydia.softtech_sacco.api.model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OtpActivity extends AppCompatActivity implements TextWatcher {
    private static final int REQ_USER_CONSENT = 200;
    SmsBroadcastReceiver smsBroadcastReceiver;
    GoogleApi mGoogleApiClient;
    FloatingActionButton listSaccos;
    ImageButton bactotp;
    AlertDialog.Builder builder;
    ListView saccos,lv;
    View view1;
    TextView timer,textMessage, saccoName;
    Button otpbtn;
    EditText editText_one,editText_two,editText_three,editText_four,otpedt;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        listSaccos = findViewById(R.id.getList);
        timer = findViewById(R.id.timer);
        textMessage = findViewById(R.id.textmessage);
        otpedt = findViewById(R.id.otpedt);
        otpbtn = findViewById(R.id.otpbtn);
        bactotp = findViewById(R.id.backotp);
        builder = new AlertDialog.Builder(this);


//        otpbtn.setOnClickListener(view -> startSmsUserContent());

        listSaccos.setOnClickListener(view -> {getSqlData();});
        bactotp.setOnClickListener(view -> startActivity(new Intent(this, LoginActivity.class)));
        //getData1();
        //startSmsUserContent();



        counter(15000);
        timer.setOnClickListener(view1 -> {
            counter(20000);
            timer.setOnClickListener(view -> {
                counter(30000);
                timer.setTextColor(getResources().getColor(R.color.design_default_color_error,getTheme()));
                timer.setText("Several attempts, try later.");
                timer.setClickable(false);
//                timer.setOnClickListener(view2 -> {
//                });
            });
        });

        }


    private void counter(int time){
            new CountDownTimer(time,1000){
                @Override
                public void onTick(long l) {
                    timer.setText("Time remaining: "+l/1000);
                }

                @Override
                public void onFinish() {
                    timer.setTextColor(getResources().getColor(R.color.sottechblue,getTheme()));
                    timer.setText("Time out, Resend Code");

                }
            }.start();
    }

    private void startSmsUserContent(){
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsUserConsent("0716867385").addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_USER_CONSENT){
            if ((resultCode == RESULT_OK)&&(data!=null)){
                String messange = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                Toast.makeText(getApplicationContext(),messange,Toast.LENGTH_LONG).show();
                //textMessage.setText(String.format("%s - %s",getString(R.string.app_name),messange));

                getOtpMessage(messange);
            }
        }
    }

    private void getOtpMessage(String message){
        Pattern pattern = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()){
           //  otpedt.setText(matcher.group(0));
        }
    }

    private void registerBroadcastReceiver(){
        smsBroadcastReceiver = new SmsBroadcastReceiver();
        smsBroadcastReceiver.smsBroadcastReceiverListner = new SmsBroadcastReceiver.SmsBroadcastReceiverListener() {
            @Override
            public void onSuccess(Intent intent) {
                startActivityForResult(intent,REQ_USER_CONSENT);
            }

            @Override
            public void onFailure() {

            }
        };
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsBroadcastReceiver);
    }

    public void getData1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        view1 = LayoutInflater.from(this).inflate(R.layout.saccol_list,null);
        saccos = view1.findViewById(R.id.saccolist);
        builder.setView(view1);

        Retrofit retrofit = apiClient.getClient();
        Api api = retrofit.create(Api.class);
        Call<List<model>> call = apiClient.getInstance().getMyApi().getSaccoss();
        call.enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                List<model> user = response.body();
                String[] heroes = new String[user.size()];
                for (int i = 0;i<user.size();i++){
                    heroes[i] = user.get(i).getName();
                }

                ArrayAdapter adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.item,heroes);
                saccos.setAdapter(adapter);
                saccos.setOnItemClickListener((adapterView, view, i, l) -> {startActivity(new Intent(getApplicationContext(), EnterPin.class));
                    closeContextMenu();
                });
            }

            @Override
            public void onFailure(Call<List<model>> call, Throwable t) {
                //Toast.makeText(this, "Something is wrong somewhere", Toast.LENGTH_SHORT).show();
            }
        });


        builder.create().show();
    }

    public void getSqlData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        view1 = LayoutInflater.from(this).inflate(R.layout.saccol_list,null);
        saccos = view1.findViewById(R.id.saccolist);
        builder.setView(view1);
            DatabaseExecutor.getInstance().diskIO().execute(() -> {
            UserDb database =  UserDb.getInstance(this);
            List<String> list = database.daoAccess().getSaccoName();
            String[] list1 = list.toArray(new String[0]);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.item,list1);
            saccos.setAdapter(adapter);
            saccos.setOnItemClickListener((adapterView, view, i, l) -> {
                String Name = ((TextView) view.findViewById(R.id.item_selected)).getText().toString();

                sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString("saccoName",Name);
                editor.apply();
                startActivity(new Intent(this, EnterPin.class));
                closeContextMenu();
            });
        });
        builder.create().show();
    }

    public void another(){
        String[] animals = {"horse", "cow", "camel", "sheep", "goat"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        view1 = LayoutInflater.from(this).inflate(R.layout.list,null);
        lv = view1.findViewById(R.id.lv);
        builder.setView(view1);
        //builder.setTitle("Choose an animal");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.item,animals);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((adapterView, view, i, l) -> startActivity(new Intent(this,EnterPin.class)));
        builder.create().show();

    }

    public void displaySaccos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an animal");

        // add a list
        String[] animals = {"horse", "cow", "camel", "sheep", "goat"};
        builder.setItems(animals, (dialog,which) -> {
            switch (which) {
                case 0: // horse
                case 1: // cow
                case 2: // camel
                case 3: // sheep
                case 4: // goat
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() == 1) {
            if (editText_one.length() == 1) {
                editText_two.requestFocus();
            }

            if (editText_two.length() == 1) {
                editText_three.requestFocus();
            }

            if (editText_three.length() == 1) {
                editText_four.requestFocus();
            }
        } else if (editable.length() == 0) {
            if (editText_four.length() == 0) {
                editText_three.requestFocus();
            }
            if (editText_three.length() == 0) {
                editText_two.requestFocus();
            }
            if (editText_two.length() == 0) {
                editText_one.requestFocus();
            }
        }
    }
}