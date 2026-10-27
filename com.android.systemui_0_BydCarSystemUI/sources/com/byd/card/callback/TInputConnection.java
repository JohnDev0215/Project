package com.byd.card.callback;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

/* JADX INFO: loaded from: classes.dex */
public class TInputConnection extends InputConnectionWrapper {
    private OnKeyTouchEvent mOnKeyTouch;

    public interface OnKeyTouchEvent {
        boolean onKeyTouch(KeyEvent event);
    }

    public TInputConnection(InputConnection target, boolean mutable) {
        super(target, mutable);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        return super.deleteSurroundingText(beforeLength, afterLength);
    }

    public void setOnKeyTouchEvent(OnKeyTouchEvent backspaceListener) {
        this.mOnKeyTouch = backspaceListener;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent event) {
        OnKeyTouchEvent onKeyTouchEvent;
        if (event.getAction() == 0 && (onKeyTouchEvent = this.mOnKeyTouch) != null) {
            onKeyTouchEvent.onKeyTouch(event);
        }
        return super.sendKeyEvent(event);
    }
}
