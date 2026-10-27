package com.facebook.react.views.textinput;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;

/* JADX INFO: loaded from: classes6.dex */
class ReactEditTextInputConnectionWrapper extends InputConnectionWrapper {
    private ReactEditText mEditText;
    private EventDispatcher mEventDispatcher;
    private boolean mIsBatchEdit;

    @Nullable
    private String mKey;

    public ReactEditTextInputConnectionWrapper(InputConnection inputConnection, ReactContext reactContext, ReactEditText reactEditText, EventDispatcher eventDispatcher) {
        super(inputConnection, false);
        this.mKey = null;
        this.mEventDispatcher = eventDispatcher;
        this.mEditText = reactEditText;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        this.mIsBatchEdit = true;
        return super.beginBatchEdit();
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        this.mIsBatchEdit = false;
        String str = this.mKey;
        if (str != null) {
            dispatchKeyEvent(str);
            this.mKey = null;
        }
        return super.endBatchEdit();
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence charSequence, int i) {
        int selectionStart = this.mEditText.getSelectionStart();
        int selectionEnd = this.mEditText.getSelectionEnd();
        boolean composingText = super.setComposingText(charSequence, i);
        int selectionStart2 = this.mEditText.getSelectionStart();
        dispatchKeyEventOrEnqueue(((selectionStart2 < selectionStart || selectionStart2 <= 0) || (!(selectionStart == selectionEnd) && (selectionStart2 == selectionStart))) ? "Backspace" : String.valueOf(this.mEditText.getText().charAt(selectionStart2 - 1)));
        return composingText;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence charSequence, int i) {
        String string = charSequence.toString();
        if (string.length() <= 2) {
            if (string.equals("")) {
                string = "Backspace";
            }
            dispatchKeyEventOrEnqueue(string);
        }
        return super.commitText(charSequence, i);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int i, int i2) {
        dispatchKeyEvent("Backspace");
        return super.deleteSurroundingText(i, i2);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            boolean z = keyEvent.getUnicodeChar() < 58 && keyEvent.getUnicodeChar() > 47;
            if (keyEvent.getKeyCode() == 67) {
                dispatchKeyEvent("Backspace");
            } else if (keyEvent.getKeyCode() == 66) {
                dispatchKeyEvent("Enter");
            } else if (z) {
                dispatchKeyEvent(String.valueOf(keyEvent.getNumber()));
            }
        }
        return super.sendKeyEvent(keyEvent);
    }

    private void dispatchKeyEventOrEnqueue(String str) {
        if (this.mIsBatchEdit) {
            this.mKey = str;
        } else {
            dispatchKeyEvent(str);
        }
    }

    private void dispatchKeyEvent(String str) {
        if (str.equals("\n")) {
            str = "Enter";
        }
        this.mEventDispatcher.dispatchEvent(new ReactTextInputKeyPressEvent(this.mEditText.getId(), str));
    }
}
