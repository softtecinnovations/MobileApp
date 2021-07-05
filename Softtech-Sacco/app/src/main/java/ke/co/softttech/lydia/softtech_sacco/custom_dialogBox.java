package ke.co.softttech.lydia.softtech_sacco;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

class custom_dialogBox implements TextWatcher {
    EditText editText_one,editText_two,editText_three,editText_four;
    private View view;
    custom_dialogBox(View view){
        this.view=view;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        switch (view.getId())
        {
            case R.id.editTextone:
            if (text.length()==1)
                editText_two.requestFocus();
                break;
            case R.id.editTexttwo:
                if (text.length()==1)
                    editText_three.requestFocus();
                else if (text.length()==0)
                    editText_one.requestFocus();
                break;
            case R.id.editTextthree:
                if (text.length()==1)
                    editText_four.requestFocus();
                else if (text.length()==0)
                    editText_two.requestFocus();
                break;
            case R.id.editTextfour:
                if (text.length()==0)
                    editText_three.requestFocus();
                break;

        }
//        if (editable.length() == 1) {
//            if (editText_one.length() == 1) {
//                editText_two.requestFocus();
//            }
//
//            if (editText_two.length() == 1) {
//                editText_three.requestFocus();
//            }
//
//            if (editText_three.length() == 1) {
//                editText_four.requestFocus();
//            }
//        } else if (editable.length() == 0) {
//            if (editText_four.length() == 0) {
//                editText_three.requestFocus();
//            }
//            if (editText_three.length() == 0) {
//                editText_two.requestFocus();
//            }
//            if (editText_two.length() == 0) {
//                editText_one.requestFocus();
//            }
//        }
    }
}