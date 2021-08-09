package ke.co.softttech.lydia.softtech_sacco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Keyboard extends LinearLayout implements View.OnClickListener {

    private TextView button1, button2, button3, button4,
            button5, button6, button7, button8,
            button9, button0;
    private ImageButton  buttonDelete, buttonEnter;
//    private Activity activity_enter_pin;
//    Keyboard keyboard= (Keyboard)findViewById(R.id.keyboard);

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public Keyboard(Context context, int keyboard) {
        this(context, null, 0);
    }

    public Keyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Keyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        button1 =  findViewById(R.id.button_1);
        button1.setOnClickListener(this);
        button2 =  findViewById(R.id.button_2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(this);
        button4 =  findViewById(R.id.button_4);
        button4.setOnClickListener(this);
        button5 =  findViewById(R.id.button_5);
        button5.setOnClickListener(this);
        button6 =  findViewById(R.id.button_6);
        button6.setOnClickListener(this);
        button7 =  findViewById(R.id.button_7);
        button7.setOnClickListener(this);
        button8 =  findViewById(R.id.button_8);
        button8.setOnClickListener(this);
        button9 =  findViewById(R.id.button_9);
        button9.setOnClickListener(this);
        button0 =  findViewById(R.id.button_0);
        button0.setOnClickListener(this);
        buttonDelete = (ImageButton) findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);
        buttonEnter = (ImageButton) findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(view -> {Intent intent = new Intent(getContext(), MainActivity.class);
        context.startActivity(intent);
        context.stopService(new Intent(context,LoginActivity.class));});

        keyValues.put(R.id.button_1, "1");
        keyValues.put(R.id.button_2, "2");
        keyValues.put(R.id.button_3, "3");
        keyValues.put(R.id.button_4, "4");
        keyValues.put(R.id.button_5, "5");
        keyValues.put(R.id.button_6, "6");
        keyValues.put(R.id.button_7, "7");
        keyValues.put(R.id.button_8, "8");
        keyValues.put(R.id.button_9, "9");
        keyValues.put(R.id.button_0, "0");
        keyValues.put(R.id.button_enter, "\n");
    }

    @Override
    public void onClick(View view) {
        if (inputConnection == null)
            return;

        if (view.getId() == R.id.button_delete) {
            CharSequence selectedText = inputConnection.getSelectedText(0);

            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                inputConnection.commitText("", 1);
            }
        } else {
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value, 1);
        }
    }

//    public void showCustomKeyboard( View v ) {
//        keyboard.setVisibility(View.VISIBLE);
//        keyboard.setEnabled(true);
//        if( v!=null ) {
//            activity_enter_pin.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        }
//    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }

//    @SuppressLint("ClickableViewAccessibility")
//    public void pinEditText(int resID){
//        EditText edittext= (EditText) findViewById(resID);
//
//        edittext.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) showCustomKeyboard(v);
//            }
//        });
//
//        edittext.setOnTouchListener(new OnTouchListener() {
//            @Override public boolean onTouch(View v, MotionEvent event) {
//                EditText edittext = (EditText) v;
//                int inType = edittext.getInputType();       // Backup the input type
//                edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
//                edittext.onTouchEvent(event);               // Call native handler
//                edittext.setInputType(inType);              // Restore input type
//                return true; // Consume touch event
//
//
//            }
//        });
//    }
}

