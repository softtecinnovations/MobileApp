package ke.co.softttech.lydia.softtech_sacco;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

class custom_dialogBox extends Dialog implements TextWatcher {
    EditText editText_one,editText_two,editText_three,editText_four;
    
    public custom_dialogBox(@NonNull Context context) {
        super(context);
       View view1 = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialog,null);
        editText_one = view1.findViewById(R.id.editTextone);
        editText_two = view1.findViewById(R.id.editText_two);
        editText_three = view1.findViewById(R.id.editTextthree);
        editText_four = view1.findViewById(R.id.editTextfour);

        editText_one.addTextChangedListener(new custom_dialogBox(context.getApplicationContext()));
        editText_two.addTextChangedListener(new custom_dialogBox(context.getApplicationContext()));
        editText_three.addTextChangedListener(new custom_dialogBox(context.getApplicationContext()));
        editText_four.addTextChangedListener(new custom_dialogBox(context.getApplicationContext()));

//        editText_one = findViewById(R.id.editTextone);
//        editText_two = findViewById(R.id.editTexttwo);
//        editText_three = findViewById(R.id.editTextthree);
//        editText_four = findViewById(R.id.editTextfour);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

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