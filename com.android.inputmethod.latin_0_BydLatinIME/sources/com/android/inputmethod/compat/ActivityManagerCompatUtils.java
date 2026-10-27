package com.android.inputmethod.compat;

import android.app.ActivityManager;
import android.content.Context;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityManagerCompatUtils {
    private static final Object LOCK = new Object();
    private static final Method METHOD_isLowRamDevice = CompatUtils.getMethod(ActivityManager.class, "isLowRamDevice", new Class[0]);
    private static volatile Boolean sBoolean;

    private ActivityManagerCompatUtils() {
    }

    public static boolean isLowRamDevice(Context context) {
        if (sBoolean == null) {
            synchronized (LOCK) {
                if (sBoolean == null) {
                    sBoolean = (Boolean) CompatUtils.invoke((ActivityManager) context.getSystemService("activity"), false, METHOD_isLowRamDevice, new Object[0]);
                }
            }
        }
        return sBoolean.booleanValue();
    }
}
