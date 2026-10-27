package org.chromium.content.browser.input;

import android.content.Context;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: classes3.dex */
public class InputMethodManagerWrapper {
    private static final boolean DEBUG_LOGS = false;
    private static final String TAG = "cr_Ime";
    private final Context mContext;

    public void notifyUserAction() {
    }

    public InputMethodManagerWrapper(Context context) {
        this.mContext = context;
    }

    private InputMethodManager getInputMethodManager() {
        return (InputMethodManager) this.mContext.getSystemService("input_method");
    }

    public void restartInput(View view) {
        getInputMethodManager().restartInput(view);
    }

    public void showSoftInput(View view, int i, ResultReceiver resultReceiver) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            getInputMethodManager().showSoftInput(view, i, resultReceiver);
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
        }
    }

    public boolean isActive(View view) {
        return getInputMethodManager().isActive(view);
    }

    public boolean hideSoftInputFromWindow(IBinder iBinder, int i, ResultReceiver resultReceiver) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            return getInputMethodManager().hideSoftInputFromWindow(iBinder, i, resultReceiver);
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
        }
    }

    public void updateSelection(View view, int i, int i2, int i3, int i4) {
        getInputMethodManager().updateSelection(view, i, i2, i3, i4);
    }

    public void updateCursorAnchorInfo(View view, CursorAnchorInfo cursorAnchorInfo) {
        getInputMethodManager().updateCursorAnchorInfo(view, cursorAnchorInfo);
    }
}
