package com.android.systemui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class BydTextView extends TextView {
    private int mNormalColor;
    private int mTintColor;

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public BydTextView(Context context) {
        this(context, null);
    }

    public BydTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BydTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BydTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mNormalColor = R.color.color_hvac_closed;
        this.mTintColor = R.color.color_hvac_opened;
        init();
    }

    private void init() {
        updateTextColor();
    }

    @Override // android.widget.TextView, android.view.View
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        updateTextColor();
    }

    private void updateTextColor() {
        if (!isSelected()) {
            setTextColor(getResources().getColor(this.mNormalColor, null));
        } else {
            setTextColor(getResources().getColor(this.mTintColor, null));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            setTextColor(getResources().getColor(this.mNormalColor, null));
            setAlpha(1.0f);
        } else {
            setAlpha(0.45f);
        }
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
