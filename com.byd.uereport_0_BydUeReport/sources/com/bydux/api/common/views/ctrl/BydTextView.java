package com.bydux.api.common.views.ctrl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes2.dex */
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
    public void onFocusChanged(boolean z7, int i8, Rect rect) {
        if (z7) {
            super.onFocusChanged(z7, i8, rect);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean z7) {
        if (z7) {
            super.onWindowFocusChanged(z7);
        }
    }

    public BydTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
