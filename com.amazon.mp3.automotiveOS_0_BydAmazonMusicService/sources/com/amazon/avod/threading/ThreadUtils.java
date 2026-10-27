package com.amazon.avod.threading;

import android.os.Looper;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class ThreadUtils {
    private static boolean TURNED_OFF = false;

    private ThreadUtils() {
    }

    @Deprecated
    public static void throwIfCalledFromUI() {
        if (TURNED_OFF || Looper.getMainLooper() != Looper.myLooper()) {
            return;
        }
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        throw new UnsupportedOperationException(String.format(Locale.US, "Cannot call method %s#%s() from UI thread", stackTrace[1].getClassName(), stackTrace[1].getMethodName()));
    }
}
