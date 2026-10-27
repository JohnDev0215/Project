package com.bydux.api.common.views.ctrl;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class BydTextView extends TextView {
    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public BydTextView(Context context) {
        super(context);
    }

    public BydTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus);
        }
    }
}
