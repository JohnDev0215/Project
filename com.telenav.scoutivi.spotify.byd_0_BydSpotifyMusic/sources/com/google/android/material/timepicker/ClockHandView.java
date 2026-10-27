package com.google.android.material.timepicker;

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
import i0.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import o2.k;
import o2.l;

/* JADX INFO: loaded from: classes.dex */
class ClockHandView extends View {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ValueAnimator f4944e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f4945g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f4946h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f4947i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f4948j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f4949k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final List<d> f4950l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final int f4951m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final float f4952n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final Paint f4953o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final RectF f4954p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final int f4955q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f4956r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f4957s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private c f4958t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private double f4959u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f4960v;

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ClockHandView.this.m(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
        }
    }

    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            animator.end();
        }
    }

    public interface c {
        void a(float f7, boolean z6);
    }

    public interface d {
        void a(float f7, boolean z6);
    }

    public ClockHandView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, o2.b.materialClockStyle);
    }

    private void c(Canvas canvas) {
        int height = getHeight() / 2;
        float width = getWidth() / 2;
        float fCos = (this.f4960v * ((float) Math.cos(this.f4959u))) + width;
        float f7 = height;
        float fSin = (this.f4960v * ((float) Math.sin(this.f4959u))) + f7;
        this.f4953o.setStrokeWidth(0.0f);
        canvas.drawCircle(fCos, fSin, this.f4951m, this.f4953o);
        double dSin = Math.sin(this.f4959u);
        double dCos = Math.cos(this.f4959u);
        this.f4953o.setStrokeWidth(this.f4955q);
        canvas.drawLine(width, f7, r1 + ((int) (dCos * d)), height + ((int) (d * dSin)), this.f4953o);
        canvas.drawCircle(width, f7, this.f4952n, this.f4953o);
    }

    private int e(float f7, float f8) {
        int degrees = ((int) Math.toDegrees(Math.atan2(f8 - (getHeight() / 2), f7 - (getWidth() / 2)))) + 90;
        return degrees < 0 ? degrees + 360 : degrees;
    }

    private Pair<Float, Float> h(float f7) {
        float f8 = f();
        if (Math.abs(f8 - f7) > 180.0f) {
            if (f8 > 180.0f && f7 < 180.0f) {
                f7 += 360.0f;
            }
            if (f8 < 180.0f && f7 > 180.0f) {
                f8 += 360.0f;
            }
        }
        return new Pair<>(Float.valueOf(f8), Float.valueOf(f7));
    }

    private boolean i(float f7, float f8, boolean z6, boolean z7, boolean z8) {
        float fE = e(f7, f8);
        boolean z9 = false;
        boolean z10 = f() != fE;
        if (z7 && z10) {
            return true;
        }
        if (!z10 && !z6) {
            return false;
        }
        if (z8 && this.f4945g) {
            z9 = true;
        }
        l(fE, z9);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(float f7, boolean z6) {
        float f8 = f7 % 360.0f;
        this.f4956r = f8;
        this.f4959u = Math.toRadians(f8 - 90.0f);
        int height = getHeight() / 2;
        float width = (getWidth() / 2) + (this.f4960v * ((float) Math.cos(this.f4959u)));
        float fSin = height + (this.f4960v * ((float) Math.sin(this.f4959u)));
        RectF rectF = this.f4954p;
        int i7 = this.f4951m;
        rectF.set(width - i7, fSin - i7, width + i7, fSin + i7);
        Iterator<d> it = this.f4950l.iterator();
        while (it.hasNext()) {
            it.next().a(f8, z6);
        }
        invalidate();
    }

    public void b(d dVar) {
        this.f4950l.add(dVar);
    }

    public RectF d() {
        return this.f4954p;
    }

    public float f() {
        return this.f4956r;
    }

    public int g() {
        return this.f4951m;
    }

    public void j(int i7) {
        this.f4960v = i7;
        invalidate();
    }

    public void k(float f7) {
        l(f7, false);
    }

    public void l(float f7, boolean z6) {
        ValueAnimator valueAnimator = this.f4944e;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z6) {
            m(f7, false);
            return;
        }
        Pair<Float, Float> pairH = h(f7);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(((Float) pairH.first).floatValue(), ((Float) pairH.second).floatValue());
        this.f4944e = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(200L);
        this.f4944e.addUpdateListener(new a());
        this.f4944e.addListener(new b());
        this.f4944e.start();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        k(f());
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z6;
        boolean z7;
        boolean z8;
        c cVar;
        int actionMasked = motionEvent.getActionMasked();
        float x6 = motionEvent.getX();
        float y6 = motionEvent.getY();
        if (actionMasked == 0) {
            this.f4946h = x6;
            this.f4947i = y6;
            this.f4948j = true;
            this.f4957s = false;
            z6 = false;
            z7 = false;
            z8 = true;
        } else if (actionMasked == 1 || actionMasked == 2) {
            int i7 = (int) (x6 - this.f4946h);
            int i8 = (int) (y6 - this.f4947i);
            this.f4948j = (i7 * i7) + (i8 * i8) > this.f4949k;
            boolean z9 = this.f4957s;
            z6 = actionMasked == 1;
            z8 = false;
            z7 = z9;
        } else {
            z6 = false;
            z7 = false;
            z8 = false;
        }
        boolean zI = i(x6, y6, z7, z8, z6) | this.f4957s;
        this.f4957s = zI;
        if (zI && z6 && (cVar = this.f4958t) != null) {
            cVar.a(e(x6, y6), this.f4948j);
        }
        return true;
    }

    public ClockHandView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f4950l = new ArrayList();
        Paint paint = new Paint();
        this.f4953o = paint;
        this.f4954p = new RectF();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.ClockHandView, i7, k.Widget_MaterialComponents_TimePicker_Clock);
        this.f4960v = typedArrayObtainStyledAttributes.getDimensionPixelSize(l.ClockHandView_materialCircleRadius, 0);
        this.f4951m = typedArrayObtainStyledAttributes.getDimensionPixelSize(l.ClockHandView_selectorSize, 0);
        this.f4955q = getResources().getDimensionPixelSize(o2.d.material_clock_hand_stroke_width);
        this.f4952n = r6.getDimensionPixelSize(o2.d.material_clock_hand_center_dot_radius);
        int color = typedArrayObtainStyledAttributes.getColor(l.ClockHandView_clockHandColor, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        k(0.0f);
        this.f4949k = ViewConfiguration.get(context).getScaledTouchSlop();
        u.t0(this, 2);
        typedArrayObtainStyledAttributes.recycle();
    }
}
