package com.byd.bluetoothcall.f;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: compiled from: KeyboardsUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static boolean a(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }
}
