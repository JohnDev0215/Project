package com.byd.dipilot.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class InterceptTouchRelativeLayout extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3647a;

    public InterceptTouchRelativeLayout(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f3647a ? motionEvent.getAction() == 0 : super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
        Log.i("InterceptTouchRelativeLayout", "intercept =" + this.f3647a);
    }

    public void setInterceptTouch(boolean z) {
        this.f3647a = z;
    }

    public InterceptTouchRelativeLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InterceptTouchRelativeLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
