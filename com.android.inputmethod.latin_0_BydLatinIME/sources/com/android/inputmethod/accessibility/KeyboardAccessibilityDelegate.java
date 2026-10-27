package com.android.inputmethod.accessibility;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.KeyDetector;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.KeyboardView;

/* JADX INFO: loaded from: classes2.dex */
public class KeyboardAccessibilityDelegate<KV extends KeyboardView> extends AccessibilityDelegateCompat {
    protected static final boolean DEBUG_HOVER = false;
    public static final int HOVER_EVENT_POINTER_ID = 0;
    private static final String TAG = "KeyboardAccessibilityDelegate";
    private KeyboardAccessibilityNodeProvider<KV> mAccessibilityNodeProvider;
    protected final KeyDetector mKeyDetector;
    private Keyboard mKeyboard;
    protected final KV mKeyboardView;
    private Key mLastHoverKey;

    protected void onHoverMoveWithin(final Key key) {
    }

    public void performLongClickOn(final Key key) {
    }

    public KeyboardAccessibilityDelegate(final KV keyboardView, final KeyDetector keyDetector) {
        this.mKeyboardView = keyboardView;
        this.mKeyDetector = keyDetector;
        ViewCompat.setAccessibilityDelegate(keyboardView, this);
    }

    public void setKeyboard(final Keyboard keyboard) {
        if (keyboard == null) {
            return;
        }
        KeyboardAccessibilityNodeProvider<KV> keyboardAccessibilityNodeProvider = this.mAccessibilityNodeProvider;
        if (keyboardAccessibilityNodeProvider != null) {
            keyboardAccessibilityNodeProvider.setKeyboard(keyboard);
        }
        this.mKeyboard = keyboard;
    }

    protected final Keyboard getKeyboard() {
        return this.mKeyboard;
    }

    protected final void setLastHoverKey(final Key key) {
        this.mLastHoverKey = key;
    }

    protected final Key getLastHoverKey() {
        return this.mLastHoverKey;
    }

    protected void sendWindowStateChanged(final int resId) {
        if (resId == 0) {
            return;
        }
        sendWindowStateChanged(this.mKeyboardView.getContext().getString(resId));
    }

    protected void sendWindowStateChanged(final String text) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(32);
        this.mKeyboardView.onInitializeAccessibilityEvent(accessibilityEventObtain);
        accessibilityEventObtain.getText().add(text);
        accessibilityEventObtain.setContentDescription(null);
        ViewParent parent = this.mKeyboardView.getParent();
        if (parent != null) {
            parent.requestSendAccessibilityEvent(this.mKeyboardView, accessibilityEventObtain);
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public KeyboardAccessibilityNodeProvider<KV> getAccessibilityNodeProvider(final View host) {
        return getAccessibilityNodeProvider();
    }

    protected KeyboardAccessibilityNodeProvider<KV> getAccessibilityNodeProvider() {
        if (this.mAccessibilityNodeProvider == null) {
            this.mAccessibilityNodeProvider = new KeyboardAccessibilityNodeProvider<>(this.mKeyboardView, this);
        }
        return this.mAccessibilityNodeProvider;
    }

    protected final Key getHoverKeyOf(final MotionEvent event) {
        int actionIndex = event.getActionIndex();
        return this.mKeyDetector.detectHitKey((int) event.getX(actionIndex), (int) event.getY(actionIndex));
    }

    public boolean onHoverEvent(final MotionEvent event) {
        int actionMasked = event.getActionMasked();
        if (actionMasked == 7) {
            onHoverMove(event);
            return true;
        }
        if (actionMasked == 9) {
            onHoverEnter(event);
            return true;
        }
        if (actionMasked == 10) {
            onHoverExit(event);
            return true;
        }
        Log.w(getClass().getSimpleName(), "Unknown hover event: " + event);
        return true;
    }

    protected void onHoverEnter(final MotionEvent event) {
        Key hoverKeyOf = getHoverKeyOf(event);
        if (hoverKeyOf != null) {
            onHoverEnterTo(hoverKeyOf);
        }
        setLastHoverKey(hoverKeyOf);
    }

    protected void onHoverMove(final MotionEvent event) {
        Key lastHoverKey = getLastHoverKey();
        Key hoverKeyOf = getHoverKeyOf(event);
        if (hoverKeyOf != lastHoverKey) {
            if (lastHoverKey != null) {
                onHoverExitFrom(lastHoverKey);
            }
            if (hoverKeyOf != null) {
                onHoverEnterTo(hoverKeyOf);
            }
        }
        if (hoverKeyOf != null) {
            onHoverMoveWithin(hoverKeyOf);
        }
        setLastHoverKey(hoverKeyOf);
    }

    protected void onHoverExit(final MotionEvent event) {
        Key lastHoverKey = getLastHoverKey();
        if (lastHoverKey != null) {
            onHoverExitFrom(lastHoverKey);
        }
        Key hoverKeyOf = getHoverKeyOf(event);
        if (hoverKeyOf != null) {
            onHoverExitFrom(hoverKeyOf);
        }
        setLastHoverKey(null);
    }

    public void performClickOn(final Key key) {
        simulateTouchEvent(0, key);
        simulateTouchEvent(1, key);
    }

    private void simulateTouchEvent(final int touchAction, final Key key) {
        int iCenterX = key.getHitBox().centerX();
        int iCenterY = key.getHitBox().centerY();
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, touchAction, iCenterX, iCenterY, 0);
        this.mKeyboardView.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    protected void onHoverEnterTo(final Key key) {
        key.onPressed();
        this.mKeyboardView.invalidateKey(key);
        KeyboardAccessibilityNodeProvider<KV> accessibilityNodeProvider = getAccessibilityNodeProvider();
        accessibilityNodeProvider.onHoverEnterTo(key);
        accessibilityNodeProvider.performActionForKey(key, 64);
    }

    protected void onHoverExitFrom(final Key key) {
        key.onReleased();
        this.mKeyboardView.invalidateKey(key);
        getAccessibilityNodeProvider().onHoverExitFrom(key);
    }
}
