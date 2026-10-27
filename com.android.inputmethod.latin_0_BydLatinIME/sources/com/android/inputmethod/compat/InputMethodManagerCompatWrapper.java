package com.android.inputmethod.compat;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class InputMethodManagerCompatWrapper {
    public final InputMethodManager mImm;
    private static final Method METHOD_switchToNextInputMethod = CompatUtils.getMethod(InputMethodManager.class, "switchToNextInputMethod", IBinder.class, Boolean.TYPE);
    private static final Method METHOD_shouldOfferSwitchingToNextInputMethod = CompatUtils.getMethod(InputMethodManager.class, "shouldOfferSwitchingToNextInputMethod", IBinder.class);

    public InputMethodManagerCompatWrapper(final Context context) {
        this.mImm = (InputMethodManager) context.getSystemService("input_method");
    }

    public boolean switchToNextInputMethod(final IBinder token, final boolean onlyCurrentIme) {
        return ((Boolean) CompatUtils.invoke(this.mImm, false, METHOD_switchToNextInputMethod, token, Boolean.valueOf(onlyCurrentIme))).booleanValue();
    }

    public boolean shouldOfferSwitchingToNextInputMethod(final IBinder token) {
        return ((Boolean) CompatUtils.invoke(this.mImm, false, METHOD_shouldOfferSwitchingToNextInputMethod, token)).booleanValue();
    }
}
