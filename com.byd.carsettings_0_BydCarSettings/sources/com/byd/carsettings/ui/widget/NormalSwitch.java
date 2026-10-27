package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class NormalSwitch extends Switch {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3441a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3442b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3443c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f3444d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f3445e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3446f;
    public long g;
    public boolean h;

    public interface a {
        void a(View view, MotionEvent motionEvent, boolean z);
    }

    public NormalSwitch(@NonNull Context context) {
        super(context);
        this.f3441a = 0;
        this.f3442b = 0;
        this.f3443c = 0;
        this.h = false;
        b(context);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.byd_switch);
        this.f3441a = typedArrayObtainStyledAttributes.getResourceId(2, this.f3441a);
        this.f3442b = typedArrayObtainStyledAttributes.getResourceId(0, this.f3442b);
        this.f3443c = typedArrayObtainStyledAttributes.getResourceId(1, this.f3443c);
        this.f3446f = typedArrayObtainStyledAttributes.getBoolean(4, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void b(Context context) {
        if (this.f3443c == 0) {
            this.f3443c = R.color.color_invalid;
        }
        setScrollBarFadeDuration(1000);
    }

    @Override // android.view.View
    public boolean isLaidOut() {
        return getWidth() > 0 && getHeight() > 0;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        b.b.m.c.a.a("NormalSwitch", "---onAttachedToWindow---isChecked: " + isChecked());
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f3444d = null;
        this.f3445e = null;
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        a aVar = this.f3445e;
        if (aVar == null || !this.f3446f) {
            return super.onTouchEvent(motionEvent);
        }
        aVar.a(this, motionEvent, !isChecked());
        return true;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.h) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - this.g;
        if (0 >= j || j >= 500) {
            this.g = jCurrentTimeMillis;
            setChecked(z, true);
        } else {
            b.b.m.c.a.a("NormalSwitch", hashCode() + "----setChecked return!!");
        }
    }

    public void setInvalid(boolean z) {
        b.b.m.c.a.a("NormalSwitch", "---setInvalid---invalid: " + z + ", isChecked: " + isChecked());
        this.h = z;
        if (z) {
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
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f3444d = onCheckedChangeListener;
    }

    public void setOnInterceptTouchListener(a aVar) {
        this.f3446f = true;
        this.f3445e = aVar;
    }

    public NormalSwitch(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3441a = 0;
        this.f3442b = 0;
        this.f3443c = 0;
        this.h = false;
        a(context, attributeSet);
        b(context);
    }

    public void setChecked(boolean z, boolean z2) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        b.b.m.c.a.a("NormalSwitch", "---setChecked---checked: " + z + ", fromUser: " + z2);
        super.setChecked(z);
        if (this.h || !z2 || (onCheckedChangeListener = this.f3444d) == null) {
            return;
        }
        onCheckedChangeListener.onCheckedChanged(this, z);
    }

    public NormalSwitch(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3441a = 0;
        this.f3442b = 0;
        this.f3443c = 0;
        this.h = false;
        a(context, attributeSet);
        b(context);
    }
}
