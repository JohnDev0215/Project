package com.byd.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.byd.mvp.R$styleable;
import com.bydux.api.common.tools.datetime.BydDateTimeHelper;
import com.bydux.api.common.views.ctrl.BydTimer;

/* JADX INFO: loaded from: classes2.dex */
public class BydClockView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f706b = BydClockView.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f707c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BydTimer f708d;
    public TextView e;
    public TextView f;
    public TextView g;
    public TextView h;
    public int i;
    public int j;
    public int k;
    public int l;
    public float m;
    public float n;
    public float o;
    public View p;
    public b q;
    public FrameLayout r;

    public class a implements BydTimer.BydTimerListener {
        public a() {
        }

        @Override // com.bydux.api.common.views.ctrl.BydTimer.BydTimerListener
        public void onTimeUp(View view) {
            BydClockView.this.e();
        }
    }

    public interface b {
        void a();
    }

    public BydClockView(Context context) {
        super(context);
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        b(context);
    }

    private void setClockBackground(Drawable drawable) {
        this.e.setBackground(drawable);
    }

    private void setClockHour(Drawable drawable) {
        this.f.setBackground(drawable);
    }

    private void setClockMinute(Drawable drawable) {
        this.g.setBackground(drawable);
    }

    private void setClockSecond(Drawable drawable) {
        this.h.setBackground(drawable);
    }

    public final void b(Context context) {
        this.f707c = context;
        this.r = new FrameLayout(context);
        this.e = new TextView(this.f707c);
        this.f = new TextView(this.f707c);
        this.g = new TextView(this.f707c);
        this.h = new TextView(this.f707c);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.r.setLayoutParams(layoutParams);
        this.e.setLayoutParams(layoutParams);
        this.f.setLayoutParams(layoutParams);
        this.g.setLayoutParams(layoutParams);
        this.h.setLayoutParams(layoutParams);
        addView(this.r);
        addView(this.e);
        addView(this.f);
        addView(this.g);
        addView(this.h);
        BydTimer bydTimer = new BydTimer(context);
        this.f708d = bydTimer;
        bydTimer.setBydTimerListener(new a());
    }

    public final void c(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = this.f707c.obtainStyledAttributes(attributeSet, R$styleable.BydClock);
        this.i = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_background, this.i);
        this.j = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_hour, this.j);
        this.k = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_minute, this.k);
        this.l = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_second, this.l);
        typedArrayObtainStyledAttributes.recycle();
        int i = this.i;
        if (i > 0) {
            setClockBackground(this.f707c.getDrawable(i));
        }
        int i2 = this.j;
        if (i2 > 0) {
            setClockHour(this.f707c.getDrawable(i2));
        }
        int i3 = this.k;
        if (i3 > 0) {
            setClockMinute(this.f707c.getDrawable(i3));
        }
        int i4 = this.l;
        if (i4 > 0) {
            setClockSecond(this.f707c.getDrawable(i4));
        }
    }

    public void d() {
        b bVar = this.q;
        if (bVar != null) {
            bVar.a();
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        view.setPivotX(view.getMeasuredWidth() / 2);
        view.setPivotY(view.getMeasuredHeight() / 2);
        return super.drawChild(canvas, view, j);
    }

    public final void e() {
        int systemTime = BydDateTimeHelper.getSystemTime(this.f707c, 3);
        int systemTime2 = BydDateTimeHelper.getSystemTime(this.f707c, 4);
        int systemTime3 = BydDateTimeHelper.getSystemTime(this.f707c, 5);
        float f = ((systemTime % 12) * 30) + (systemTime2 / 2);
        float f2 = (systemTime2 * 6) + (systemTime3 / 10);
        float f3 = systemTime3 * 6;
        if (this.m != f) {
            this.m = f;
            this.f.setRotation(f);
        }
        if (this.n != f2) {
            this.n = f2;
            this.g.setRotation(f2);
        }
        if (this.o != f3) {
            this.o = f3;
            this.h.setRotation(f3);
        }
        d();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        e();
        BydTimer bydTimer = this.f708d;
        if (bydTimer != null) {
            bydTimer.startTimer(1000L);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydTimer bydTimer = this.f708d;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
    }

    public void setOnUiUpdateCallback(b bVar) {
        this.q = bVar;
    }

    public void setSubView(View view) {
        if (view == null) {
            return;
        }
        View view2 = this.p;
        if (view2 != null) {
            this.r.removeView(view2);
        }
        this.p = view;
        this.r.addView(view);
        invalidate();
    }

    public BydClockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0;
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
