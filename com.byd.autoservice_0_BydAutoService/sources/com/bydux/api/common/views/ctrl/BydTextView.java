package com.bydux.api.common.views.ctrl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class BydTextView extends TextView {
    public BydTextView(Context context) {
        super(context);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z3, int i3, Rect rect) {
        if (z3) {
            super.onFocusChanged(z3, i3, rect);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean z3) {
        if (z3) {
            super.onWindowFocusChanged(z3);
        }
    }

    public BydTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
