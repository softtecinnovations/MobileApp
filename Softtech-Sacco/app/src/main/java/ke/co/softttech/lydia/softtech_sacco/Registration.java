package ke.co.softttech.lydia.softtech_sacco;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import ke.co.softttech.lydia.softtech_sacco.Db.DatabaseExecutor;
import ke.co.softttech.lydia.softtech_sacco.Db.UserDb;
import ke.co.softttech.lydia.softtech_sacco.Db.UserModel;

public class Registration extends AppCompatActivity {

    EditText id, name, kra, phone, sacco;
    Button register;
    Button sub;
    ImageButton backregistration;
    private String Name, KRA, PhoneNumber, ID,SaccoName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        id = findViewById(R.id.ID);
        name = findViewById(R.id.name);
        kra = findViewById(R.id.kra);
        phone = findViewById(R.id.phone);
        register = findViewById(R.id.register);
        sacco = findViewById(R.id.sacconame);
        backregistration = findViewById(R.id.backregistration);

        backregistration.setOnClickListener(view -> startActivity(new Intent(this,LoginActivity.class)));
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
                DatabaseExecutor.getInstance().diskIO().execute(() -> {
                    UserModel model = new UserModel(Name,ID,PhoneNumber,KRA,SaccoName);
                    UserDb database =  UserDb.getInstance(this);
                    database.daoAccess().insertUser(model);
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Your data has been sent to sacco admin. The sacco will contact you.")
                        .setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, id) -> startActivity(new Intent(Registration.this, EnterPin.class)));
                builder.create().show();

            }
        });



    }

}





