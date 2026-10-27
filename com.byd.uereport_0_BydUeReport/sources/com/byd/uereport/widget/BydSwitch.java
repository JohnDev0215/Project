package com.byd.uereport.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

/* JADX INFO: loaded from: classes2.dex */
public class BydSwitch extends Switch {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3389d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3390e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f3391f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a f3392g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3393h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f3394i;

    public interface a {
        void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z7);
    }

    public BydSwitch(Context context) {
        super(context);
        this.f3389d = 0;
        this.f3390e = 0;
    }

    public void a(boolean z7, boolean z8) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        super.setChecked(z7);
        if (!z8 || (onCheckedChangeListener = this.f3391f) == null) {
            return;
        }
        onCheckedChangeListener.onCheckedChanged(this, z7);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        a aVar = this.f3392g;
        if (aVar == null || !this.f3393h) {
            return super.onTouchEvent(motionEvent);
        }
        aVar.OnBydSwitchTouch(this, motionEvent, !isChecked());
        return true;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z7) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j8 = jCurrentTimeMillis - this.f3394i;
        if (0 >= j8 || j8 >= 500) {
            this.f3394i = jCurrentTimeMillis;
            a(z7, true);
        }
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f3391f = onCheckedChangeListener;
    }

    public void setOnInterceptTouchListener(a aVar) {
        this.f3393h = true;
        this.f3392g = aVar;
    }

    public BydSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3389d = 0;
        this.f3390e = 0;
    }

    public BydSwitch(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f3389d = 0;
        this.f3390e = 0;
    }
}
