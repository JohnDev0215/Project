package com.ts.car.input;

import android.content.Context;
import android.hardware.input.InputManager;
import android.view.InputEvent;
import android.view.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class KeycodeUtil {
    public static final String FIELD_NAME = "INJECT_INPUT_EVENT_MODE_ASYNC";
    public static final int FLAG_NOT_INTERCEPT = 32768;
    public static final String METHOD_NAME = "injectInputEvent";
    public static final String TAG = "KeycodeUtil";
    public static final long LONG_PRESS_TIMEOUT_DEFAULT = 2000;
    private static long mDefaultLongpressTimeout = LONG_PRESS_TIMEOUT_DEFAULT;

    public static void sendKeyEvent(Context context, KeyEvent keyEvent, int targetDisplay) {
        InputManager inputManager = (InputManager) context.getSystemService("input");
        KeyEvent event = KeyEvent.changeFlags(keyEvent, keyEvent.getFlags() | FLAG_NOT_INTERCEPT);
        try {
            Method injectEventMethod = inputManager.getClass().getDeclaredMethod(METHOD_NAME, InputEvent.class, Integer.TYPE);
            injectEventMethod.setAccessible(true);
            Field modeField = inputManager.getClass().getDeclaredField(FIELD_NAME);
            modeField.setAccessible(true);
            int mode = modeField.getInt(inputManager.getClass());
            injectEventMethod.invoke(inputManager, event, Integer.valueOf(mode));
        } catch (Exception ex) {
            LogUtils.loge(TAG, "sendKeyEvent failed! " + ex);
        }
    }

    public static void setDefaultLongpressTimeout(long timeout) {
        if (timeout > 0) {
            mDefaultLongpressTimeout = timeout;
        } else {
            LogUtils.loge(TAG, "The default longpress timeout in config file is invalide!");
        }
    }

    public static long getDefaultLongpressTimeout() {
        return mDefaultLongpressTimeout;
    }
}
