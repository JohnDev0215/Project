package com.android.systemui.stackdivider.view.help;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
public class HelpView extends RelativeLayout {
    View.OnKeyListener mOnKeyListener;

    public HelpView(Context context) {
        super(context);
        this.mOnKeyListener = null;
    }

    public HelpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mOnKeyListener = null;
    }

    public HelpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mOnKeyListener = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        View.OnKeyListener onKeyListener;
        if (event.getKeyCode() == 4 && (onKeyListener = this.mOnKeyListener) != null) {
            return onKeyListener.onKey(this, event.getKeyCode(), event);
        }
        return super.dispatchKeyEvent(event);
    }

    @Override // android.view.View
    public void setOnKeyListener(View.OnKeyListener onKeyListener) {
        this.mOnKeyListener = onKeyListener;
        super.setOnKeyListener(onKeyListener);
    }
}
