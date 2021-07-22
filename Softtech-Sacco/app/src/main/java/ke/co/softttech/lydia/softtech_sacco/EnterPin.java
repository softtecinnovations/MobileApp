package ke.co.softttech.lydia.softtech_sacco;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import ke.co.softttech.lydia.softtech_sacco.models.PersonModel;
import ke.co.softttech.lydia.softtech_sacco.network.APIUtils;
import ke.co.softttech.lydia.softtech_sacco.network.RetrofitAPI;
import ke.co.softttech.lydia.softtech_sacco.otp.OtpActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterPin extends AppCompatActivity {
    ImageButton backotp;
    private static final String TAG = "EnterPin";
    private String pin;
    private RetrofitAPI mRetrofitAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);
        mRetrofitAPI = APIUtils.getAPIService();

        backotp = findViewById(R.id.backotp);
        EditText editText1 = findViewById(R.id.et1);
        Keyboard keyboard = findViewById(R.id.keyboard);

        pin = editText1.getText().toString();

        backotp.setOnClickListener(view -> {
            PersonModel personModel = new PersonModel();
            mRetrofitAPI.createPost(personModel).enqueue(new Callback<PersonModel>() {
                @Override
                public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {

                    if(response.isSuccessful()) {
                        Log.i(TAG, "pin submitted to API." + response.body().toString());

                       startActivity();


                    }
                }

                @Override
                public void onFailure(Call<PersonModel> call, Throwable t) {
                    Log.e(TAG, "Unable to send pin to API.");
                }

            });


        });


      InputConnection ic = editText1.onCreateInputConnection(new EditorInfo());
       keyboard.setInputConnection(ic);
        keyboard.setVisibility(View.VISIBLE);

    }

    private void startActivity() {

        startActivity(new Intent(this,OtpActivity.class));
        onStop();
    }
}