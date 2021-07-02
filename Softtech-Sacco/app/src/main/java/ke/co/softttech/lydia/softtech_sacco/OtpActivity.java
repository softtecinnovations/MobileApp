package ke.co.softttech.lydia.softtech_sacco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.Db.DatabaseExecutor;
import ke.co.softttech.lydia.softtech_sacco.Db.UserDb_Impl;
import ke.co.softttech.lydia.softtech_sacco.Db.UserModel;

public class OtpActivity extends AppCompatActivity {
    FloatingActionButton listSaccos;
    TextView timer;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        listSaccos = findViewById(R.id.getList);
        timer = findViewById(R.id.timer);
        builder = new AlertDialog.Builder(this);

//        Custom_DialogBox custom_dialog = new Custom_DialogBox(this);
//        custom_dialog.show();
        new custom_dialogBox(this);
        listSaccos.setOnClickListener(view -> {getData1();});
    }

    public void getData1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.saccol_list,null);
        ListView saccos = view1.findViewById(R.id.saccolist);
        //Button test = view1.findViewById(R.id.test);
        // test.setOnClickListener(v -> startActivity(new Intent(this,OtpActivity.class)));

        DatabaseExecutor.getInstance().diskIO().execute(() -> {
            UserDb_Impl database = (UserDb_Impl) UserDb_Impl.getDatabase(this);
            List<UserModel> parcelList = database.daoAccess().fetchAllUsers();
            for (UserModel model : parcelList){

                List<String> list = new ArrayList<>(Arrays.asList(model.getUser_SaccoName()));
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.item,list);
                saccos.setAdapter(adapter);

            }
        });
        //selected.setOnClickListener();
        saccos.setOnItemClickListener((adapterView, view, i, l) -> startActivity(new Intent(this,EnterPin.class)));
        builder.setView(view1);
        builder.create().show();



    }

    public void setDialog(){
        builder.setView(R.layout.custom_dialog);
        Button sub = findViewById(R.id.submit);
        sub.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),OtpActivity.class));
        });

    }
}