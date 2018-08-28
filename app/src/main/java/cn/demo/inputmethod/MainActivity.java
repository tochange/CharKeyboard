package cn.demo.inputmethod;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final CharKeyboard keyboardView = (CharKeyboard) findViewById(R.id.keyboardView);


        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!editText.hasFocus()) {
                    int inputback = editText.getInputType();


                    editText.setInputType(InputType.TYPE_NULL);
                    keyboardView.setCallback(new CharKeyboard.Callback() {
                        @Override
                        public void onItemClick(int primaryCode) {
                            Editable editable = editText.getText();
                            int start = editText.getSelectionStart();
                            switch (primaryCode) {
                                case Keyboard.KEYCODE_DELETE:
                                    if (editable != null && editable.length() > 0) {
                                        if (start > 0) {
                                            editable.delete(start - 1, start);
                                        }
                                    }
                                    break;
                                case Keyboard.KEYCODE_CANCEL:
                                    keyboardView.setVisibility(View.GONE);
                                    break;
                                default:
                                    editable.insert(start, Character.toString((char) primaryCode));
                                    break;
                            }
                        }
                    });
                    editText.setInputType(inputback);


                }
                return false;
            }
        });
    }
}
