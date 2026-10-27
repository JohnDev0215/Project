package com.android.inputmethod.compat;

import android.inputmethodservice.InputMethodService;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class InputMethodServiceCompatUtils {
    private static final Method METHOD_enableHardwareAcceleration = CompatUtils.getMethod(InputMethodService.class, "enableHardwareAcceleration", new Class[0]);

    private InputMethodServiceCompatUtils() {
    }

    public static boolean enableHardwareAcceleration(final InputMethodService ims) {
        return ((Boolean) CompatUtils.invoke(ims, false, METHOD_enableHardwareAcceleration, new Object[0])).booleanValue();
    }
}
