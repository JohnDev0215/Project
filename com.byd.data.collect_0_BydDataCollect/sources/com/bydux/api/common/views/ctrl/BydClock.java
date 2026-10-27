package com.bydux.api.common.views.ctrl;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.bydux.api.common.views.ctrl.BydTimer;
import d.a.a.e;

/* JADX INFO: loaded from: classes.dex */
public class BydClock extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3553b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3554c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BydTimer f3555d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f3556e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextView f3557f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextView f3558g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private TextView f3559h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f3560i;
    private int j;
    private int k;
    private int l;
    private float m;
    private float n;
    private float o;

    class a implements BydTimer.b {
        a() {
        }

        @Override // com.bydux.api.common.views.ctrl.BydTimer.b
        public void a(View view) {
            BydClock.this.d();
        }
    }

    public BydClock(Context context) {
        super(context);
        this.f3553b = getClass().getSimpleName();
        this.f3556e = null;
        this.f3557f = null;
        this.f3558g = null;
        this.f3559h = null;
        this.f3560i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        b(context);
    }

    private void b(Context context) {
        this.f3554c = context;
        this.f3556e = new TextView(context);
        this.f3557f = new TextView(this.f3554c);
        this.f3558g = new TextView(this.f3554c);
        this.f3559h = new TextView(this.f3554c);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f3556e.setLayoutParams(layoutParams);
        this.f3557f.setLayoutParams(layoutParams);
        this.f3558g.setLayoutParams(layoutParams);
        this.f3559h.setLayoutParams(layoutParams);
        addView(this.f3556e);
        addView(this.f3557f);
        addView(this.f3558g);
        addView(this.f3559h);
        BydTimer bydTimer = new BydTimer(context);
        this.f3555d = bydTimer;
        bydTimer.setBydTimerListener(new a());
    }

    private void c(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.f3554c.obtainStyledAttributes(attributeSet, e.BydClock);
            this.f3560i = typedArrayObtainStyledAttributes.getResourceId(e.BydClock_byd_clock_background, this.f3560i);
            this.j = typedArrayObtainStyledAttributes.getResourceId(e.BydClock_byd_clock_hour, this.j);
            this.k = typedArrayObtainStyledAttributes.getResourceId(e.BydClock_byd_clock_minute, this.k);
            this.l = typedArrayObtainStyledAttributes.getResourceId(e.BydClock_byd_clock_second, this.l);
            typedArrayObtainStyledAttributes.recycle();
            int i2 = this.f3560i;
            if (i2 > 0) {
                setClockBackground(this.f3554c.getDrawable(i2));
            }
            int i3 = this.j;
            if (i3 > 0) {
                setClockHour(this.f3554c.getDrawable(i3));
            }
            int i4 = this.k;
            if (i4 > 0) {
                setClockMinute(this.f3554c.getDrawable(i4));
            }
            int i5 = this.l;
            if (i5 > 0) {
                setClockSecond(this.f3554c.getDrawable(i5));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        int iA = d.a.a.f.b.a.a.a(this.f3554c, 3);
        int iA2 = d.a.a.f.b.a.a.a(this.f3554c, 4);
        int iA3 = d.a.a.f.b.a.a.a(this.f3554c, 5);
        float f2 = ((iA % 12) * 30) + (iA2 / 2);
        float f3 = (iA2 * 6) + (iA3 / 10);
        float f4 = iA3 * 6;
        if (this.m != f2) {
            this.m = f2;
            this.f3557f.setRotation(f2);
        }
        if (this.n != f3) {
            this.n = f3;
            this.f3558g.setRotation(f3);
        }
        if (this.o != f4) {
            this.o = f4;
            this.f3559h.setRotation(f4);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        view.setPivotX(view.getMeasuredWidth() / 2);
        view.setPivotY(view.getMeasuredHeight() / 2);
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        d();
        BydTimer bydTimer = this.f3555d;
        if (bydTimer != null) {
            bydTimer.e(200L);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydTimer bydTimer = this.f3555d;
        if (bydTimer != null) {
            bydTimer.g();
        }
    }

    public void setClockBackground(Drawable drawable) {
        this.f3556e.setBackground(drawable);
    }

    public void setClockHour(Drawable drawable) {
        this.f3557f.setBackground(drawable);
    }

    public void setClockMinute(Drawable drawable) {
        this.f3558g.setBackground(drawable);
    }

    public void setClockSecond(Drawable drawable) {
        this.f3559h.setBackground(drawable);
    }

    public BydClock(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3553b = getClass().getSimpleName();
        this.f3556e = null;
        this.f3557f = null;
        this.f3558g = null;
        this.f3559h = null;
        this.f3560i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        b(context);
        c(attributeSet);
    }
}
