package com.android.inputmethod.compat;

import android.os.Looper;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class LooperCompatUtils {
    private static final Method METHOD_quitSafely = CompatUtils.getMethod(Looper.class, "quitSafely", new Class[0]);

    public static void quitSafely(final Looper looper) {
        Method method = METHOD_quitSafely;
        if (method != null) {
            CompatUtils.invoke(looper, null, method, new Object[0]);
        } else {
            looper.quit();
        }
    }
}
