package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes2.dex */
public class InterceptRelativeLayout extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3411a;

    public InterceptRelativeLayout(Context context) {
        super(context);
        this.f3411a = false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f3411a) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setIntercept(boolean z) {
        this.f3411a = z;
    }

    public InterceptRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3411a = false;
    }

    public InterceptRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3411a = false;
    }
}
