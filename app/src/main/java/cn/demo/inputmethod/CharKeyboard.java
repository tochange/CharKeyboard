package cn.demo.inputmethod;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.AttributeSet;
import android.view.View;

public class CharKeyboard extends android.inputmethodservice.KeyboardView {
    private Callback callback;

    public CharKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CharKeyboard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public interface Callback {
        void onItemClick(int primaryCode);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    private void init(Context context) {
        setOnKeyboardActionListener(listener);
        setKeyboard(new Keyboard(context, R.xml.qwerty));
        setPreviewEnabled(true);
        showKeyboard();
    }

    private OnKeyboardActionListener listener = new OnKeyboardActionListener() {

        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            if (callback != null) {
                callback.onItemClick(primaryCode);
            }
        }
    };

    public void showKeyboard() {
        int visibility = getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            setVisibility(View.VISIBLE);
        }
    }

}
