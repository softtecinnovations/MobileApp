package ke.co.softttech.lydia.softtech_sacco;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.Db.DatabaseExecutor;
import ke.co.softttech.lydia.softtech_sacco.Db.UserDb;
import ke.co.softttech.lydia.softtech_sacco.Db.UserModel;

public class OtpActivity extends AppCompatActivity implements TextWatcher {
    FloatingActionButton listSaccos;
    TextView timer,noRecordFound;
    LinearLayout receiptLayout;
    AlertDialog.Builder builder;
    ListView saccos,lv;
    View view1;
    EditText editText_one,editText_two,editText_three,editText_four;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        listSaccos = findViewById(R.id.getList);
        timer = findViewById(R.id.timer);
        builder = new AlertDialog.Builder(this);

        listSaccos.setOnClickListener(view -> {getData1();});
        //getData1();
    }

    public void getData1(){
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
            saccos.setOnItemClickListener((adapterView, view, i, l) -> {startActivity(new Intent(this,EnterPin.class));
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
        builder.setItems(animals, (dialog, which) -> {
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