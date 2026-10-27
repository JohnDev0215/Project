package com.byd.base.view;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import b.b.m.g.c;
import com.byd.base.R$drawable;

/* JADX INFO: loaded from: classes.dex */
public class BydSwitch extends Switch implements b.b.a.j.c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f2465a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f2466b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2467c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f2468d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile boolean f2469e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2470f;

    public interface a {
        void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z);
    }

    public BydSwitch(@NonNull Context context) {
        super(context);
        this.f2468d = 300L;
        this.f2470f = false;
        d(context, null);
    }

    public final void d(Context context, @Nullable AttributeSet attributeSet) {
        setTrackDrawable(null);
        setBackground(ContextCompat.getDrawable(context, R$drawable.switch_off));
    }

    public boolean e() {
        return this.f2470f;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f2470f = false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        b.b.m.g.a.d(parcelable, "checked", Boolean.valueOf(this.f2469e));
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f2470f = true;
        }
        if (action == 0 && c.a(this.f2468d)) {
            return false;
        }
        a aVar = this.f2466b;
        if (aVar == null || !this.f2467c || action != 1) {
            return super.onTouchEvent(motionEvent);
        }
        aVar.OnBydSwitchTouch(this, motionEvent, !isChecked());
        return true;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.f2470f) {
            setChecked(z, true);
        }
    }

    public void setEverTouch(boolean z) {
        this.f2470f = z;
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f2465a = onCheckedChangeListener;
    }

    public void setOnInterceptTouchListener(a aVar) {
        this.f2467c = true;
        this.f2466b = aVar;
    }

    public void setTime(long j) {
        this.f2468d = j;
    }

    public void setChecked(boolean z, boolean z2) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        this.f2469e = z;
        super.setChecked(z);
        if (z) {
            setBackground(ContextCompat.getDrawable(getContext(), R$drawable.switch_on));
        } else {
            setBackground(ContextCompat.getDrawable(getContext(), R$drawable.switch_off));
        }
        if (!z2 || (onCheckedChangeListener = this.f2465a) == null) {
            return;
        }
        onCheckedChangeListener.onCheckedChanged(this, z);
    }

    public BydSwitch(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2468d = 300L;
        this.f2470f = false;
        d(context, attributeSet);
    }

    public BydSwitch(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2468d = 300L;
        this.f2470f = false;
        d(context, attributeSet);
    }
}
