package com.android.inputmethod.accessibility;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
final class AccessibilityLongPressTimer extends Handler {
    private static final int MSG_LONG_PRESS = 1;
    private final LongPressTimerCallback mCallback;
    private final long mConfigAccessibilityLongPressTimeout;

    public interface LongPressTimerCallback {
        void performLongClickOn(Key key);
    }

    public AccessibilityLongPressTimer(final LongPressTimerCallback callback, final Context context) {
        this.mCallback = callback;
        this.mConfigAccessibilityLongPressTimeout = context.getResources().getInteger(R.integer.config_accessibility_long_press_key_timeout);
    }

    @Override // android.os.Handler
    public void handleMessage(final Message msg) {
        if (msg.what == 1) {
            cancelLongPress();
            this.mCallback.performLongClickOn((Key) msg.obj);
        } else {
            super.handleMessage(msg);
        }
    }

    public void startLongPress(final Key key) {
        cancelLongPress();
        sendMessageDelayed(obtainMessage(1, key), this.mConfigAccessibilityLongPressTimeout);
    }

    public void cancelLongPress() {
        removeMessages(1);
    }
}
