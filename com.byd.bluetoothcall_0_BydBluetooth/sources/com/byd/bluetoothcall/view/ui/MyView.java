package com.byd.bluetoothcall.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
public class MyView extends RelativeLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient float f3468b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private transient float f3469c;

    public MyView(Context context) {
        super(context);
        this.f3468b = 0.0f;
        this.f3469c = 0.0f;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f3468b = motionEvent.getX();
            this.f3469c = motionEvent.getY();
        } else if (motionEvent.getAction() == 2 && (Math.abs(motionEvent.getX() - this.f3468b) > 40.0f || Math.abs(motionEvent.getY() - this.f3469c) > 40.0f)) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public MyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3468b = 0.0f;
        this.f3469c = 0.0f;
    }

    public MyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3468b = 0.0f;
        this.f3469c = 0.0f;
    }
}
