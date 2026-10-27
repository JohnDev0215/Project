package com.byd.base.view;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.byd.base.R$drawable;
import m4.c;

/* JADX INFO: loaded from: classes.dex */
public class BydSwitch extends Switch implements s2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f2604d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f2605e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2606f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f2607g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public volatile boolean f2608h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2609i;

    public interface a {
        void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z7);
    }

    public BydSwitch(Context context) {
        super(context);
        this.f2607g = 300L;
        this.f2609i = false;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        setTrackDrawable(null);
        setBackground(e0.a.d(context, R$drawable.switch_off));
    }

    public void b(boolean z7, boolean z8) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        this.f2608h = z7;
        super.setChecked(z7);
        if (z7) {
            setBackground(e0.a.d(getContext(), R$drawable.switch_on));
        } else {
            setBackground(e0.a.d(getContext(), R$drawable.switch_off));
        }
        if (!z8 || (onCheckedChangeListener = this.f2604d) == null) {
            return;
        }
        onCheckedChangeListener.onCheckedChanged(this, z7);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f2609i = false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        m4.a.e(parcelable, "checked", Boolean.valueOf(this.f2608h));
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f2609i = true;
        }
        if (action == 0 && c.a(this.f2607g)) {
            return false;
        }
        a aVar = this.f2605e;
        if (aVar == null || !this.f2606f || action != 1) {
            return super.onTouchEvent(motionEvent);
        }
        aVar.OnBydSwitchTouch(this, motionEvent, !isChecked());
        return true;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z7) {
        if (this.f2609i) {
            b(z7, true);
        }
    }

    public void setEverTouch(boolean z7) {
        this.f2609i = z7;
    }

    @Override // s2.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z7) {
        super.setInvalid(z7);
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f2604d = onCheckedChangeListener;
    }

    public void setOnInterceptTouchListener(a aVar) {
        this.f2606f = true;
        this.f2605e = aVar;
    }

    public void setTime(long j8) {
        this.f2607g = j8;
    }

    public BydSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2607g = 300L;
        this.f2609i = false;
        a(context, attributeSet);
    }

    public BydSwitch(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2607g = 300L;
        this.f2609i = false;
        a(context, attributeSet);
    }
}
