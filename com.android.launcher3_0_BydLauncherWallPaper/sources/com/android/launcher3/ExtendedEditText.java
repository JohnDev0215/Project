package com.android.launcher3;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.android.launcher3.util.Logger;
import com.android.launcher3.util.UiThreadHelper;

/* JADX INFO: loaded from: classes4.dex */
public class ExtendedEditText extends EditText {
    private OnBackKeyListener mBackKeyListener;
    private boolean mForceDisableSuggestions;
    private boolean mShowImeAfterFirstLayout;

    public interface OnBackKeyListener {
        boolean onBackKey();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onDragEvent(DragEvent event) {
        return false;
    }

    public ExtendedEditText(Context context) {
        super(context);
        this.mForceDisableSuggestions = false;
    }

    public ExtendedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mForceDisableSuggestions = false;
    }

    public ExtendedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mForceDisableSuggestions = false;
    }

    public void setOnBackKeyListener(OnBackKeyListener listener) {
        this.mBackKeyListener = listener;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == 4 && event.getAction() == 1) {
            OnBackKeyListener onBackKeyListener = this.mBackKeyListener;
            if (onBackKeyListener != null) {
                return onBackKeyListener.onBackKey();
            }
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.mShowImeAfterFirstLayout) {
            post(new Runnable() { // from class: com.android.launcher3.ExtendedEditText.1
                @Override // java.lang.Runnable
                public void run() {
                    Logger.e("pengbin", "onLayout......0");
                    ExtendedEditText.this.mShowImeAfterFirstLayout = false;
                }
            });
        }
    }

    public void showKeyboard() {
        Logger.e("pengbin", "showKeyboard......0");
        this.mShowImeAfterFirstLayout = !showSoftInput();
    }

    public void hideKeyboard() {
        UiThreadHelper.hideKeyboardAsync(getContext(), getWindowToken());
    }

    private boolean showSoftInput() {
        Logger.e("pengbin", "showSoftInput......0");
        return requestFocus() && ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(this, 1);
    }

    public void dispatchBackKey() {
        hideKeyboard();
        OnBackKeyListener onBackKeyListener = this.mBackKeyListener;
        if (onBackKeyListener != null) {
            onBackKeyListener.onBackKey();
        }
    }

    public void forceDisableSuggestions(boolean forceDisableSuggestions) {
        this.mForceDisableSuggestions = forceDisableSuggestions;
    }

    @Override // android.widget.TextView
    public boolean isSuggestionsEnabled() {
        return !this.mForceDisableSuggestions && super.isSuggestionsEnabled();
    }

    public void reset() {
        View viewFocusSearch;
        if (!TextUtils.isEmpty(getText())) {
            setText("");
        }
        if (isFocused() && (viewFocusSearch = focusSearch(130)) != null) {
            viewFocusSearch.requestFocus();
        }
        hideKeyboard();
    }
}
