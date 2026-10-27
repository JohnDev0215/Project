package com.byd.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.byd.mvp.R$color;
import com.byd.mvp.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class NormalSwitch extends Switch {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2896d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2897e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2898f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f2899g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f2900h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2901i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f2902j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f2903k;

    public interface a {
        void a(View view, MotionEvent motionEvent, boolean z7);
    }

    public NormalSwitch(Context context) {
        super(context);
        this.f2896d = 0;
        this.f2897e = 0;
        this.f2898f = 0;
        this.f2903k = false;
        b(context);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.byd_switch);
        this.f2896d = typedArrayObtainStyledAttributes.getResourceId(R$styleable.byd_switch_bgOpenColor, this.f2896d);
        this.f2897e = typedArrayObtainStyledAttributes.getResourceId(R$styleable.byd_switch_bgCloseColor, this.f2897e);
        this.f2898f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.byd_switch_bgDisableColor, this.f2898f);
        this.f2901i = typedArrayObtainStyledAttributes.getBoolean(R$styleable.byd_switch_intercept, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void b(Context context) {
        if (this.f2898f == 0) {
            this.f2898f = R$color.color_invalid;
        }
        setScrollBarFadeDuration(1000);
    }

    public void c(boolean z7, boolean z8) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        x3.a.a("NormalSwitch", "---setChecked---checked: " + z7 + ", fromUser: " + z8);
        super.setChecked(z7);
        if (this.f2903k || !z8 || (onCheckedChangeListener = this.f2899g) == null) {
            return;
        }
        onCheckedChangeListener.onCheckedChanged(this, z7);
    }

    @Override // android.view.View
    public boolean isLaidOut() {
        return getWidth() > 0 && getHeight() > 0;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        x3.a.a("NormalSwitch", "---onAttachedToWindow---isChecked: " + isChecked());
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f2899g = null;
        this.f2900h = null;
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        a aVar = this.f2900h;
        if (aVar == null || !this.f2901i) {
            return super.onTouchEvent(motionEvent);
        }
        aVar.a(this, motionEvent, !isChecked());
        return true;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z7) {
        if (this.f2903k) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j8 = jCurrentTimeMillis - this.f2902j;
        if (0 >= j8 || j8 >= 500) {
            this.f2902j = jCurrentTimeMillis;
            c(z7, true);
        } else {
            x3.a.a("NormalSwitch", hashCode() + "----setChecked return!!");
        }
    }

    public void setInvalid(boolean z7) {
        x3.a.a("NormalSwitch", "---setInvalid---invalid: " + z7 + ", isChecked: " + isChecked());
        this.f2903k = z7;
        if (z7) {
            setAlpha(0.3f);
            setClickable(false);
            setFocusable(false);
        } else {
            setAlpha(1.0f);
            setClickable(true);
            setFocusable(true);
        }
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f2899g = onCheckedChangeListener;
    }

    public void setOnInterceptTouchListener(a aVar) {
        this.f2901i = true;
        this.f2900h = aVar;
    }

    public NormalSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2896d = 0;
        this.f2897e = 0;
        this.f2898f = 0;
        this.f2903k = false;
        a(context, attributeSet);
        b(context);
    }

    public NormalSwitch(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2896d = 0;
        this.f2897e = 0;
        this.f2898f = 0;
        this.f2903k = false;
        a(context, attributeSet);
        b(context);
    }
}
