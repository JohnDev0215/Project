package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes.dex */
public class BydTabLayout extends TabLayout {
    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }

    public BydTabLayout(Context context) {
        this(context, null);
    }

    public BydTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BydTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
