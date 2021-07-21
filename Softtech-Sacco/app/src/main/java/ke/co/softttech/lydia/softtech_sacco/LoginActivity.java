package ke.co.softttech.lydia.softtech_sacco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ke.co.softttech.lydia.softtech_sacco.otp.OtpActivity;

public class LoginActivity extends AppCompatActivity {
    EditText phone;
    TextView register;
    FloatingActionButton otpBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String phoneNumber;//= phone.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = findViewById(R.id.phone);
        register = findViewById(R.id.regtxt);
        otpBtn = findViewById(R.id.floatingActionButton);

        register.setOnClickListener(view -> {startActivity(new Intent(this,Registration.class));});

        otpBtn.setOnClickListener(view -> {

            if (TextUtils.isEmpty(getPhoneNumber())){
                phone.setError("Input your phone number");
            }else if (getPhoneNumber().length()!=10){
                phone.setError("Invalid phone number");
            }else {
                setRegister(register);
                sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString("phoneNumber",phone.getText().toString());
                editor.apply();
                startActivity(new Intent(this, OtpActivity.class));
                //new ProfileFragment().getUserData(phoneNumber);
            }
        });
        register.setOnClickListener(view -> {
            startActivity(new Intent(this,Registration.class));
        });
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