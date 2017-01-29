package com.example.pcremote;

import android.view.KeyEvent;

/**
 * Created by Sakib on 1/12/2016.
 */
public class KeyboardManager {

    public static String getValueToSend(int keyCode) {

        if (keyCode == KeyEvent.KEYCODE_DEL) {
            return "BACK_SPACE";

        }
        else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            return "ENTER";

        }
        else if (keyCode == KeyEvent.KEYCODE_TAB) {
            return "TAB";

        }
        else if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT
                || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT) {
            return "CAPS";

        }
        else if (keyCode == KeyEvent.KEYCODE_MINUS) {
            return "-";

        }
        else if (keyCode == KeyEvent.KEYCODE_PLUS) {
            return "+";

        }
        else if (keyCode == KeyEvent.KEYCODE_PERIOD) {
            return ".";
        }
        else {
            return null;
        }
    }

}
