package com.byd.autoservice.function;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.byd.autoservice.R;
import com.bydux.api.common.tools.datetime.BydDateTimeHelper;
import com.bydux.api.common.views.ctrl.BydTimer;

/* JADX INFO: loaded from: classes.dex */
public class BydClockView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f1836b = BydClockView.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f1837c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BydTimer f1838d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f1839e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f1840f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f1841g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TextView f1842h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1843i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1844j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1845k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1846l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f1847m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f1848n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f1849o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public View f1850p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public b f1851q;

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
        this.f1839e = null;
        this.f1840f = null;
        this.f1841g = null;
        this.f1842h = null;
        this.f1843i = 0;
        this.f1844j = 0;
        this.f1845k = 0;
        this.f1846l = 0;
        this.f1847m = 0.0f;
        this.f1848n = 0.0f;
        this.f1849o = 0.0f;
        b(context);
    }

    public final void b(Context context) {
        this.f1837c = context;
        this.f1839e = new TextView(context);
        this.f1840f = new TextView(this.f1837c);
        this.f1841g = new TextView(this.f1837c);
        this.f1842h = new TextView(this.f1837c);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f1839e.setLayoutParams(layoutParams);
        this.f1840f.setLayoutParams(layoutParams);
        this.f1841g.setLayoutParams(layoutParams);
        this.f1842h.setLayoutParams(layoutParams);
        addView(this.f1839e);
        addView(this.f1840f);
        addView(this.f1841g);
        addView(this.f1842h);
        BydTimer bydTimer = new BydTimer(context);
        this.f1838d = bydTimer;
        bydTimer.setBydTimerListener(new a());
    }

    public final void c(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = this.f1837c.obtainStyledAttributes(attributeSet, R.styleable.BydClock);
        this.f1843i = typedArrayObtainStyledAttributes.getResourceId(0, this.f1843i);
        this.f1844j = typedArrayObtainStyledAttributes.getResourceId(1, this.f1844j);
        this.f1845k = typedArrayObtainStyledAttributes.getResourceId(2, this.f1845k);
        this.f1846l = typedArrayObtainStyledAttributes.getResourceId(3, this.f1846l);
        typedArrayObtainStyledAttributes.recycle();
        int i3 = this.f1843i;
        if (i3 > 0) {
            setClockBackground(this.f1837c.getDrawable(i3));
        }
        int i4 = this.f1844j;
        if (i4 > 0) {
            setClockHour(this.f1837c.getDrawable(i4));
        }
        int i5 = this.f1845k;
        if (i5 > 0) {
            setClockMinute(this.f1837c.getDrawable(i5));
        }
        int i6 = this.f1846l;
        if (i6 > 0) {
            setClockSecond(this.f1837c.getDrawable(i6));
        }
    }

    public void d() {
        b bVar = this.f1851q;
        if (bVar != null) {
            bVar.a();
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j3) {
        view.setPivotX(view.getMeasuredWidth() / 2);
        view.setPivotY(view.getMeasuredHeight() / 2);
        return super.drawChild(canvas, view, j3);
    }

    public final void e() {
        int systemTime = BydDateTimeHelper.getSystemTime(this.f1837c, 3);
        int systemTime2 = BydDateTimeHelper.getSystemTime(this.f1837c, 4);
        int systemTime3 = BydDateTimeHelper.getSystemTime(this.f1837c, 5);
        float f3 = ((systemTime % 12) * 30) + (systemTime2 / 2);
        float f4 = (systemTime2 * 6) + (systemTime3 / 10);
        float f5 = systemTime3 * 6;
        if (this.f1847m != f3) {
            this.f1847m = f3;
            this.f1840f.setRotation(f3);
        }
        if (this.f1848n != f4) {
            this.f1848n = f4;
            this.f1841g.setRotation(f4);
        }
        if (this.f1849o != f5) {
            this.f1849o = f5;
            this.f1842h.setRotation(f5);
        }
        d();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        e();
        BydTimer bydTimer = this.f1838d;
        if (bydTimer != null) {
            bydTimer.startTimer(1000L);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydTimer bydTimer = this.f1838d;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
    }

    public void setClockBackground(Drawable drawable) {
        this.f1839e.setBackground(drawable);
    }

    public void setClockHour(Drawable drawable) {
        this.f1840f.setBackground(drawable);
    }

    public void setClockMinute(Drawable drawable) {
        this.f1841g.setBackground(drawable);
    }

    public void setClockSecond(Drawable drawable) {
        this.f1842h.setBackground(drawable);
    }

    public void setOnUiUpdateCallback(b bVar) {
        this.f1851q = bVar;
    }

    public void setSubView(View view) {
        if (view == null) {
            return;
        }
        View view2 = this.f1850p;
        if (view2 != null) {
            removeView(view2);
        }
        this.f1850p = view;
        addView(view);
        invalidate();
    }

    public BydClockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1839e = null;
        this.f1840f = null;
        this.f1841g = null;
        this.f1842h = null;
        this.f1843i = 0;
        this.f1844j = 0;
        this.f1845k = 0;
        this.f1846l = 0;
        this.f1847m = 0.0f;
        this.f1848n = 0.0f;
        this.f1849o = 0.0f;
        b(context);
        c(attributeSet);
    }
}
