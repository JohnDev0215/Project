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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3771a = BydClockView.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f3772b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BydTimer f3773c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f3774d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f3775e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f3776f;
    public TextView g;
    public int h;
    public int i;
    public int j;
    public int k;
    public float l;
    public float m;
    public float n;
    public View o;
    public b p;
    public FrameLayout q;

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
        this.f3774d = null;
        this.f3775e = null;
        this.f3776f = null;
        this.g = null;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        b(context);
    }

    private void setClockBackground(Drawable drawable) {
        this.f3774d.setBackground(drawable);
    }

    private void setClockHour(Drawable drawable) {
        this.f3775e.setBackground(drawable);
    }

    private void setClockMinute(Drawable drawable) {
        this.f3776f.setBackground(drawable);
    }

    private void setClockSecond(Drawable drawable) {
        this.g.setBackground(drawable);
    }

    public final void b(Context context) {
        this.f3772b = context;
        this.q = new FrameLayout(context);
        this.f3774d = new TextView(this.f3772b);
        this.f3775e = new TextView(this.f3772b);
        this.f3776f = new TextView(this.f3772b);
        this.g = new TextView(this.f3772b);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.q.setLayoutParams(layoutParams);
        this.f3774d.setLayoutParams(layoutParams);
        this.f3775e.setLayoutParams(layoutParams);
        this.f3776f.setLayoutParams(layoutParams);
        this.g.setLayoutParams(layoutParams);
        addView(this.q);
        addView(this.f3774d);
        addView(this.f3775e);
        addView(this.f3776f);
        addView(this.g);
        BydTimer bydTimer = new BydTimer(context);
        this.f3773c = bydTimer;
        bydTimer.setBydTimerListener(new a());
    }

    public final void c(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = this.f3772b.obtainStyledAttributes(attributeSet, R$styleable.BydClock);
        this.h = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_background, this.h);
        this.i = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_hour, this.i);
        this.j = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_minute, this.j);
        this.k = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_second, this.k);
        typedArrayObtainStyledAttributes.recycle();
        int i = this.h;
        if (i > 0) {
            setClockBackground(this.f3772b.getDrawable(i));
        }
        int i2 = this.i;
        if (i2 > 0) {
            setClockHour(this.f3772b.getDrawable(i2));
        }
        int i3 = this.j;
        if (i3 > 0) {
            setClockMinute(this.f3772b.getDrawable(i3));
        }
        int i4 = this.k;
        if (i4 > 0) {
            setClockSecond(this.f3772b.getDrawable(i4));
        }
    }

    public void d() {
        b bVar = this.p;
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
        int systemTime = BydDateTimeHelper.getSystemTime(this.f3772b, 3);
        int systemTime2 = BydDateTimeHelper.getSystemTime(this.f3772b, 4);
        int systemTime3 = BydDateTimeHelper.getSystemTime(this.f3772b, 5);
        float f2 = ((systemTime % 12) * 30) + (systemTime2 / 2);
        float f3 = (systemTime2 * 6) + (systemTime3 / 10);
        float f4 = systemTime3 * 6;
        if (this.l != f2) {
            this.l = f2;
            this.f3775e.setRotation(f2);
        }
        if (this.m != f3) {
            this.m = f3;
            this.f3776f.setRotation(f3);
        }
        if (this.n != f4) {
            this.n = f4;
            this.g.setRotation(f4);
        }
        d();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        e();
        BydTimer bydTimer = this.f3773c;
        if (bydTimer != null) {
            bydTimer.startTimer(1000L);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydTimer bydTimer = this.f3773c;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
    }

    public void setOnUiUpdateCallback(b bVar) {
        this.p = bVar;
    }

    public void setSubView(View view) {
        if (view == null) {
            return;
        }
        View view2 = this.o;
        if (view2 != null) {
            this.q.removeView(view2);
        }
        this.o = view;
        this.q.addView(view);
        invalidate();
    }

    public BydClockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3774d = null;
        this.f3775e = null;
        this.f3776f = null;
        this.g = null;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        b(context);
        c(attributeSet);
    }
}
