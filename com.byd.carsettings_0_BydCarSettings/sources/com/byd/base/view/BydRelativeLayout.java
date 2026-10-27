package com.byd.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
public class BydRelativeLayout extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2457a;

    public BydRelativeLayout(Context context) {
        super(context);
        this.f2457a = true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f2457a) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    public void setDispatch(boolean z) {
        this.f2457a = z;
    }

    public BydRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2457a = true;
    }

    public BydRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2457a = true;
    }
}
