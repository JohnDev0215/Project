package com.amazon.music.explore.views.swipeablePages.pages;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeablePageRelativeLayout extends RelativeLayout {
    public SwipeablePageRelativeLayout(Context context) {
        super(context);
    }

    public SwipeablePageRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SwipeablePageRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }
}
