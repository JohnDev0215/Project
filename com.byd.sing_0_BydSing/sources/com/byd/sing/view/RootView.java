package com.byd.sing.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import com.byd.sing.SingApplication;

/* JADX INFO: loaded from: classes.dex */
public class RootView extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3297b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3298c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3299d;
    private int e;

    public RootView(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.f3298c = ViewConfiguration.get(SingApplication.a()).getScaledTouchSlop();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f3297b = false;
            this.f3299d = (int) motionEvent.getRawX();
            this.e = (int) motionEvent.getRawY();
        } else if (action == 1) {
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            if (this.f3297b) {
                return Math.abs(this.f3299d - rawX) - this.f3298c > 0 || Math.abs(this.e - rawY) - this.f3298c > 0;
            }
        } else if (action == 2) {
            this.f3297b = true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public RootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public RootView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }
}
