package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes4.dex */
public class FocusedTextView extends TextView {
    private boolean focusEnabled;

    public FocusedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.focusEnabled = true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean focused) {
        if (focused) {
            super.onWindowFocusChanged(focused);
        }
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.focusEnabled;
    }

    @Override // android.widget.TextView
    public void setText(CharSequence text, TextView.BufferType type) {
        if (text == null || text.length() == 0 || !text.equals(getText())) {
            super.setText(text, type);
        }
    }

    public void setFocused(final boolean focused) {
        this.focusEnabled = focused;
    }
}
