package ke.co.softttech.lydia.softtech_sacco;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.Db.DatabaseExecutor;
import ke.co.softttech.lydia.softtech_sacco.Db.UserDb;
import ke.co.softttech.lydia.softtech_sacco.api.Api;
import ke.co.softttech.lydia.softtech_sacco.api.apiClient;
import ke.co.softttech.lydia.softtech_sacco.api.members;
import ke.co.softttech.lydia.softtech_sacco.api.model;
import ke.co.softttech.lydia.softtech_sacco.models.PersonModel;
import ke.co.softttech.lydia.softtech_sacco.models.UserModel;
import ke.co.softttech.lydia.softtech_sacco.network.APIUtils;
import ke.co.softttech.lydia.softtech_sacco.network.RetrofitAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Registration extends AppCompatActivity {

    private static final String TAG = "Registration";
    EditText id, name, kra, phone, sacco;
    Button register;
    Button sub;
    ImageButton backregistration;
    private String Name, KRA, PhoneNumber, ID,SaccoName;
    private RetrofitAPI mRetrofitAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mRetrofitAPI = APIUtils.getAPIService();

        id = findViewById(R.id.ID);
        name = findViewById(R.id.name);
        kra = findViewById(R.id.kra);
        phone = findViewById(R.id.phone);
        register = findViewById(R.id.register);
        sacco = findViewById(R.id.sacconame);
        backregistration = findViewById(R.id.backregistration);

        backregistration.setOnClickListener(view -> {startActivity(new Intent(this,LoginActivity.class));finish();});
        register.setOnClickListener(view -> {
            //if (TextUtils.isEmpty(phone.getText())|| TextUtils.isEmpty(kvbNo.getText())){

            Name = name.getText().toString();
            KRA = kra.getText().toString();
            PhoneNumber = phone.getText().toString();
            ID = id.getText().toString();
            SaccoName = sacco.getText().toString();

            if (Name.isEmpty()) {
                name.setError("Please Fill in the Field!");
            } else if (KRA.isEmpty()) {
                kra.setError("Please Fill in the Field!");
            } else if (PhoneNumber.isEmpty()) {
                phone.setError("Please Fill in the Field!");
            } else if (ID.isEmpty()) {
                id.setError("Please Fill in the Field!");
            }else if (SaccoName.isEmpty()){
                sacco.setError("Please Fill in the Field!");
            }
            else {
                //         insertUser();
                post(Name,KRA,SaccoName,ID,PhoneNumber);
                name.getText().clear();
                sacco.getText().clear();
                id.getText().clear();
                phone.getText().clear();
                kra.getText().clear();


            }

        });

    }

    private void insertUser(){
        DatabaseExecutor.getInstance().diskIO().execute(() -> {
            UserModel model = new UserModel(Name,ID,PhoneNumber,KRA,SaccoName);
            UserDb database =  UserDb.getInstance(this);
            database.daoAccess().insertUser(model);
        });
        showDialog();

    }

    public void post(String name, String KRA, String saccoName, String ID, String phoneNumber){
        members memb = new members(ID,name,phoneNumber,KRA,saccoName);
        Retrofit retrofit = apiClient.getClient();
        Api api = retrofit.create(Api.class);
        Call<List<members>> call = apiClient.getInstance().getMyApi().createPost(memb);
        call.enqueue(new Callback<List<members>>() {

            @Override
            public void onResponse(Call<List<members>> call, Response<List<members>> response) {
//                Log.i(TAG, "post submitted to API." + response.body().toString());
                Toast.makeText(getApplicationContext(), "Data Successfully Submitted", Toast.LENGTH_SHORT).show();
                showDialog();
            }

            @Override
            public void onFailure(Call<List<members>> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Please check your internet!", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void postData(String name, String KRA, String saccoName, String ID, String phoneNumber){
        PersonModel personModel = new PersonModel(name,KRA,saccoName,ID,phoneNumber);
        mRetrofitAPI.createPost(personModel).enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                    Toast.makeText(getApplicationContext(), "Data Successfully Submitted", Toast.LENGTH_SHORT).show();

//                    showDialog();
                }
            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {
                Log.e(TAG, "Unable to submit data to API.");
                Toast.makeText(getApplicationContext(), "Data Not Submitted", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.biulder_title);
        builder.setMessage(R.string.biulder_msg)
                .setPositiveButton("OK", (dialog, id) -> {
                    startActivity(new Intent(Registration.this, LoginActivity.class));
                    finish();});
        builder.create().show();
    }
}





