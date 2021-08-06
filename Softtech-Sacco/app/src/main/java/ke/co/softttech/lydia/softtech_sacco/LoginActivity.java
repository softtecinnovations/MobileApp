package ke.co.softttech.lydia.softtech_sacco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.api.Api;
import ke.co.softttech.lydia.softtech_sacco.api.apiClient;
import ke.co.softttech.lydia.softtech_sacco.api.members;
import ke.co.softttech.lydia.softtech_sacco.otp.OtpActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    EditText phone;
    TextView register,dialogmngs;
    ProgressBar progressBar;
    FloatingActionButton otpBtn;

    View view,viewMsg;
    AlertDialog.Builder builder;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    String phoneNumber;//= phone.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = findViewById(R.id.phone);
        progressBar = findViewById(R.id.progressBar);
        register = findViewById(R.id.regtxt);
        otpBtn = findViewById(R.id.floatingActionButton);
        view= LayoutInflater.from(this).inflate(R.layout.progressbar,null);
         builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setCancelable(true);

        register.setOnClickListener(view -> {startActivity(new Intent(this,Registration.class));finish();});

        otpBtn.setOnClickListener(view -> {

            if (TextUtils.isEmpty(getPhoneNumber())){
                phone.setError("Input your phone number");
            }else if (getPhoneNumber().length()!=10){
                phone.setError("Invalid phone number");
            }else {

                authenticateUser();

                setRegister(register);
                sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString("phoneNumber",phone.getText().toString());
                editor.apply();

            }
        });
    }

    public void authenticateUser() {
        Retrofit retrofit = apiClient.getClient();
        Api api = retrofit.create(Api.class);
        Call<List<members>> call = apiClient.getInstance().getMyApi().getSaccoss();
        //progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<members>>() {
            @Override
            public void onResponse(Call<List<members>> call, Response<List<members>> response) {
                List<members> user = response.body();
                String[] heroes = new String[user.size()];
                for (int i = 0; i < user.size(); i++) {
                    heroes[i] = user.get(i).getPhonenumber();
                    if (getPhoneNumber().equals(heroes[i])){
                        setRegister(register);
                        startActivity(new Intent(getApplicationContext(), OtpActivity.class));
                        finish();
                        phone.getText().clear();
                    }else {
                        builder.setView(null);
//                        dialogmngs.setText("You are not registered in any sacco");
//                        builder.setView(dialogmngs);
                        builder.setMessage("You are not a registered member of any Sacco");
                        builder.setNegativeButton("ok",(dialog,id) -> {closeContextMenu();});
                        builder.create().show();
                    }
                }
            }


            @Override
            public void onFailure(Call<List<members>> call, Throwable throwable) {
                builder.setView(null);
//                dialogmngs.setText("You are not registered in any sacco");
//                builder.setView(dialogmngs);
                builder.setMessage("Unable to Login");
                builder.setNegativeButton("ok",(dialog,id) -> {closeContextMenu();});
                builder.create().show();
                Toast.makeText(getApplicationContext(), "Something is wrong somewhere,ensure you are connected to the net", Toast.LENGTH_LONG).show();
            }
        });
        builder.create().show();
    }

    public String getPhoneNumber(){
        String phoneNum=phone.getText().toString();
        return phoneNum;
    }

    public void setRegister(TextView register) {
        this.register = register;
        register.setVisibility(View.GONE);
    }

    public void setPhone(){

    }

    @Override
    protected void onStart() {
        super.onStart();
        register.setVisibility(View.VISIBLE);
    }
}