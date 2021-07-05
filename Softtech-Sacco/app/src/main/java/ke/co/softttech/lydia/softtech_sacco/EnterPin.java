package ke.co.softttech.lydia.softtech_sacco;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class EnterPin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);

        EditText editText1 = findViewById(R.id.et1);
//        EditText editText2 = findViewById(R.id.et2);
//        EditText editText3 = findViewById(R.id.et3);
//        EditText editText4 = findViewById(R.id.et4);

        Keyboard keyboard = findViewById(R.id.keyboard);

//        keyboard.pinEditText(R.id.et1);
//        keyboard.pinEditText(R.id.et2);
//        keyboard.pinEditText(R.id.et3);
//        keyboard.pinEditText(R.id.et4);


      InputConnection ic = editText1.onCreateInputConnection(new EditorInfo());
       keyboard.setInputConnection(ic);
        keyboard.setVisibility(View.VISIBLE);


    }
}