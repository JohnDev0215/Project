package com.google.android.material.timepicker;

import a.h.k.q;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import b.d.a.a.d;
import b.d.a.a.j;
import b.d.a.a.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ClockHandView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ValueAnimator f2486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f2487b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f2488c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2489d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2490e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List<c> f2491f;
    public final int g;
    public final float h;
    public final Paint i;
    public final RectF j;
    public final int k;
    public float l;
    public boolean m;
    public double n;
    public int o;

    public class a implements ValueAnimator.AnimatorUpdateListener {
        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ClockHandView.this.c(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
        }
    }

    public class b extends AnimatorListenerAdapter {
        public b(ClockHandView clockHandView) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            animator.end();
        }
    }

    public interface c {
        void a(float f2, boolean z);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ClockHandView(Context context, AttributeSet attributeSet) {
        int i = b.d.a.a.b.materialClockStyle;
        super(context, attributeSet, i);
        this.f2491f = new ArrayList();
        this.i = new Paint();
        this.j = new RectF();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.ClockHandView, i, j.Widget_MaterialComponents_TimePicker_Clock);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(k.ClockHandView_materialCircleRadius, 0);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(k.ClockHandView_selectorSize, 0);
        this.k = getResources().getDimensionPixelSize(d.material_clock_hand_stroke_width);
        this.h = r0.getDimensionPixelSize(d.material_clock_hand_center_dot_radius);
        int color = typedArrayObtainStyledAttributes.getColor(k.ClockHandView_clockHandColor, 0);
        this.i.setAntiAlias(true);
        this.i.setColor(color);
        b(0.0f, false);
        this.f2490e = ViewConfiguration.get(context).getScaledTouchSlop();
        q.b0(this, 2);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final int a(float f2, float f3) {
        int degrees = ((int) Math.toDegrees(Math.atan2(f3 - (getHeight() / 2), f2 - (getWidth() / 2)))) + 90;
        return degrees < 0 ? degrees + 360 : degrees;
    }

    public void b(float f2, boolean z) {
        ValueAnimator valueAnimator = this.f2486a;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z) {
            c(f2, false);
            return;
        }
        float f3 = this.l;
        if (Math.abs(f3 - f2) > 180.0f) {
            if (f3 > 180.0f && f2 < 180.0f) {
                f2 += 360.0f;
            }
            if (f3 < 180.0f && f2 > 180.0f) {
                f3 += 360.0f;
            }
        }
        Pair pair = new Pair(Float.valueOf(f3), Float.valueOf(f2));
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(((Float) pair.first).floatValue(), ((Float) pair.second).floatValue());
        this.f2486a = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(200L);
        this.f2486a.addUpdateListener(new a());
        this.f2486a.addListener(new b(this));
        this.f2486a.start();
    }

    public final void c(float f2, boolean z) {
        float f3 = f2 % 360.0f;
        this.l = f3;
        this.n = Math.toRadians(f3 - 90.0f);
        int height = getHeight() / 2;
        float fCos = (this.o * ((float) Math.cos(this.n))) + (getWidth() / 2);
        float fSin = (this.o * ((float) Math.sin(this.n))) + height;
        RectF rectF = this.j;
        int i = this.g;
        rectF.set(fCos - i, fSin - i, fCos + i, fSin + i);
        Iterator<c> it = this.f2491f.iterator();
        while (it.hasNext()) {
            it.next().a(f3, z);
        }
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        float width = getWidth() / 2;
        float fCos = (this.o * ((float) Math.cos(this.n))) + width;
        float f2 = height;
        float fSin = (this.o * ((float) Math.sin(this.n))) + f2;
        this.i.setStrokeWidth(0.0f);
        canvas.drawCircle(fCos, fSin, this.g, this.i);
        double dSin = Math.sin(this.n);
        double dCos = Math.cos(this.n);
        this.i.setStrokeWidth(this.k);
        canvas.drawLine(width, f2, r1 + ((int) (dCos * d)), height + ((int) (d * dSin)), this.i);
        canvas.drawCircle(width, f2, this.h, this.i);
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        b(this.l, false);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        int actionMasked = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        boolean z3 = false;
        if (actionMasked == 0) {
            this.f2487b = x;
            this.f2488c = y;
            this.f2489d = true;
            this.m = false;
            z = false;
            z2 = true;
        } else if (actionMasked == 1 || actionMasked == 2) {
            int i = (int) (x - this.f2487b);
            int i2 = (int) (y - this.f2488c);
            this.f2489d = (i2 * i2) + (i * i) > this.f2490e;
            z = this.m;
            if (actionMasked == 1) {
            }
            z2 = false;
        } else {
            z = false;
            z2 = false;
        }
        boolean z4 = this.m;
        float fA = a(x, y);
        boolean z5 = this.l != fA;
        if (z2 && z5) {
            z3 = true;
        } else if (z5 || z) {
            b(fA, false);
            z3 = true;
        }
        this.m = z4 | z3;
        return true;
    }
}
