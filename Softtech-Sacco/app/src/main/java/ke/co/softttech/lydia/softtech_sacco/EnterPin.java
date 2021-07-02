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

    private InputConnection inputConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);

        EditText editText1 = findViewById(R.id.et1);
//        EditText editText2 = findViewById(R.id.et2);
//        EditText editText3 = findViewById(R.id.et3);
//        EditText editText4 = findViewById(R.id.et4);

//        pinEditText(R.id.et1);
//        pinEditText(R.id.et2);
//        pinEditText(R.id.et3);
//        pinEditText(R.id.et4);

        Keyboard keyboard = findViewById(R.id.keyboard);



      InputConnection ic = editText1.onCreateInputConnection(new EditorInfo());
       keyboard.setInputConnection(ic);
        keyboard.setVisibility(View.VISIBLE);


    }

//    public void setInputConnection(InputConnection ic) {
//        inputConnection = ic;
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    public void pinEditText(int et1){
//        EditText editText1 = findViewById(R.id.et1);
//        Keyboard keyboard = findViewById(R.id.keyboard);
//
//        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override public void onFocusChange(View v, boolean hasFocus) {
//               InputConnection ic = editText1.onCreateInputConnection(new EditorInfo());
//               setInputConnection(ic);
//           }
//        });
//
//        editText1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                EditText edittext = (EditText) v;
//                int inType = edittext.getInputType();       // Backup the input type
//                edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
//                edittext.onTouchEvent(event);               // Call native handler
//                edittext.setInputType(inType);              // Restore input type
//                return true; // Consume touch event
//
//            }
//
//        });
//
//        ;
//    }
}