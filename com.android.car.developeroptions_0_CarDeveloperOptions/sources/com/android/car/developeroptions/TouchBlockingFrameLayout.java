package com.android.car.developeroptions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class TouchBlockingFrameLayout extends FrameLayout {
    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public TouchBlockingFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
