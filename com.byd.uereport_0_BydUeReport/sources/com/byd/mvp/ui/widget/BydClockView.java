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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f2828d = BydClockView.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f2829e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BydTimer f2830f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f2831g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TextView f2832h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public TextView f2833i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public TextView f2834j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2835k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2836l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2837m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2838n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f2839o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f2840p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f2841q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public View f2842r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public b f2843s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public FrameLayout f2844t;

    public class a implements BydTimer.BydTimerListener {
        public a() {
        }

        @Override // com.bydux.api.common.views.ctrl.BydTimer.BydTimerListener
        public void onTimeUp(View view) {
            BydClockView.this.e();
        }
    }

    public interface b {
        void onUiUpdated();
    }

    public BydClockView(Context context) {
        super(context);
        this.f2831g = null;
        this.f2832h = null;
        this.f2833i = null;
        this.f2834j = null;
        this.f2835k = 0;
        this.f2836l = 0;
        this.f2837m = 0;
        this.f2838n = 0;
        this.f2839o = 0.0f;
        this.f2840p = 0.0f;
        this.f2841q = 0.0f;
        b(context);
    }

    public final void b(Context context) {
        this.f2829e = context;
        this.f2844t = new FrameLayout(context);
        this.f2831g = new TextView(this.f2829e);
        this.f2832h = new TextView(this.f2829e);
        this.f2833i = new TextView(this.f2829e);
        this.f2834j = new TextView(this.f2829e);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f2844t.setLayoutParams(layoutParams);
        this.f2831g.setLayoutParams(layoutParams);
        this.f2832h.setLayoutParams(layoutParams);
        this.f2833i.setLayoutParams(layoutParams);
        this.f2834j.setLayoutParams(layoutParams);
        addView(this.f2844t);
        addView(this.f2831g);
        addView(this.f2832h);
        addView(this.f2833i);
        addView(this.f2834j);
        BydTimer bydTimer = new BydTimer(context);
        this.f2830f = bydTimer;
        bydTimer.setBydTimerListener(new a());
    }

    public final void c(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = this.f2829e.obtainStyledAttributes(attributeSet, R$styleable.BydClock);
        this.f2835k = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_background, this.f2835k);
        this.f2836l = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_hour, this.f2836l);
        this.f2837m = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_minute, this.f2837m);
        this.f2838n = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BydClock_byd_clock_second, this.f2838n);
        typedArrayObtainStyledAttributes.recycle();
        int i8 = this.f2835k;
        if (i8 > 0) {
            setClockBackground(this.f2829e.getDrawable(i8));
        }
        int i9 = this.f2836l;
        if (i9 > 0) {
            setClockHour(this.f2829e.getDrawable(i9));
        }
        int i10 = this.f2837m;
        if (i10 > 0) {
            setClockMinute(this.f2829e.getDrawable(i10));
        }
        int i11 = this.f2838n;
        if (i11 > 0) {
            setClockSecond(this.f2829e.getDrawable(i11));
        }
    }

    public void d() {
        b bVar = this.f2843s;
        if (bVar != null) {
            bVar.onUiUpdated();
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j8) {
        view.setPivotX(view.getMeasuredWidth() / 2);
        view.setPivotY(view.getMeasuredHeight() / 2);
        return super.drawChild(canvas, view, j8);
    }

    public final void e() {
        int systemTime = BydDateTimeHelper.getSystemTime(this.f2829e, 3);
        int systemTime2 = BydDateTimeHelper.getSystemTime(this.f2829e, 4);
        int systemTime3 = BydDateTimeHelper.getSystemTime(this.f2829e, 5);
        float f8 = ((systemTime % 12) * 30) + (systemTime2 / 2);
        float f9 = (systemTime2 * 6) + (systemTime3 / 10);
        float f10 = systemTime3 * 6;
        if (this.f2839o != f8) {
            this.f2839o = f8;
            this.f2832h.setRotation(f8);
        }
        if (this.f2840p != f9) {
            this.f2840p = f9;
            this.f2833i.setRotation(f9);
        }
        if (this.f2841q != f10) {
            this.f2841q = f10;
            this.f2834j.setRotation(f10);
        }
        d();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        e();
        BydTimer bydTimer = this.f2830f;
        if (bydTimer != null) {
            bydTimer.startTimer(1000L);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydTimer bydTimer = this.f2830f;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
    }

    public void setClockBackground(Drawable drawable) {
        this.f2831g.setBackground(drawable);
    }

    public void setClockHour(Drawable drawable) {
        this.f2832h.setBackground(drawable);
    }

    public void setClockMinute(Drawable drawable) {
        this.f2833i.setBackground(drawable);
    }

    public void setClockSecond(Drawable drawable) {
        this.f2834j.setBackground(drawable);
    }

    public void setOnUiUpdateCallback(b bVar) {
        this.f2843s = bVar;
    }

    public void setSubView(View view) {
        if (view == null) {
            return;
        }
        View view2 = this.f2842r;
        if (view2 != null) {
            this.f2844t.removeView(view2);
        }
        this.f2842r = view;
        this.f2844t.addView(view);
        invalidate();
    }

    public BydClockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2831g = null;
        this.f2832h = null;
        this.f2833i = null;
        this.f2834j = null;
        this.f2835k = 0;
        this.f2836l = 0;
        this.f2837m = 0;
        this.f2838n = 0;
        this.f2839o = 0.0f;
        this.f2840p = 0.0f;
        this.f2841q = 0.0f;
        b(context);
        c(attributeSet);
    }
}
