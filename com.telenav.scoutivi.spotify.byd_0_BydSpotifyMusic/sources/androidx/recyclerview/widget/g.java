package androidx.recyclerview.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import i0.u;

/* JADX INFO: compiled from: FastScroller.java */
/* JADX INFO: loaded from: classes.dex */
class g extends RecyclerView.n implements RecyclerView.s {
    private static final int[] D = {R.attr.state_pressed};
    private static final int[] E = new int[0];
    int A;
    private final Runnable B;
    private final RecyclerView.t C;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f3100a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3101b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final StateListDrawable f3102c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Drawable f3103d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3104e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f3105f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final StateListDrawable f3106g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Drawable f3107h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f3108i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final int f3109j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    int f3110k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f3111l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    float f3112m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    int f3113n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    int f3114o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    float f3115p;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private RecyclerView f3118s;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    final ValueAnimator f3125z;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f3116q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f3117r = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private boolean f3119t = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f3120u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f3121v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f3122w = 0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final int[] f3123x = new int[2];

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final int[] f3124y = new int[2];

    /* JADX INFO: compiled from: FastScroller.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.q(500);
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    class b extends RecyclerView.t {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i7, int i8) {
            g.this.B(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    private class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f3128a = false;

        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3128a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f3128a) {
                this.f3128a = false;
                return;
            }
            if (((Float) g.this.f3125z.getAnimatedValue()).floatValue() == 0.0f) {
                g gVar = g.this;
                gVar.A = 0;
                gVar.y(0);
            } else {
                g gVar2 = g.this;
                gVar2.A = 2;
                gVar2.v();
            }
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    private class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            g.this.f3102c.setAlpha(iFloatValue);
            g.this.f3103d.setAlpha(iFloatValue);
            g.this.v();
        }
    }

    g(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i7, int i8, int i9) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f3125z = valueAnimatorOfFloat;
        this.A = 0;
        this.B = new a();
        this.C = new b();
        this.f3102c = stateListDrawable;
        this.f3103d = drawable;
        this.f3106g = stateListDrawable2;
        this.f3107h = drawable2;
        this.f3104e = Math.max(i7, stateListDrawable.getIntrinsicWidth());
        this.f3105f = Math.max(i7, drawable.getIntrinsicWidth());
        this.f3108i = Math.max(i7, stateListDrawable2.getIntrinsicWidth());
        this.f3109j = Math.max(i7, drawable2.getIntrinsicWidth());
        this.f3100a = i8;
        this.f3101b = i9;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new c());
        valueAnimatorOfFloat.addUpdateListener(new d());
        j(recyclerView);
    }

    private void C(float f7) {
        int[] iArrP = p();
        float fMax = Math.max(iArrP[0], Math.min(iArrP[1], f7));
        if (Math.abs(this.f3111l - fMax) < 2.0f) {
            return;
        }
        int iX = x(this.f3112m, fMax, iArrP, this.f3118s.computeVerticalScrollRange(), this.f3118s.computeVerticalScrollOffset(), this.f3117r);
        if (iX != 0) {
            this.f3118s.scrollBy(0, iX);
        }
        this.f3112m = fMax;
    }

    private void k() {
        this.f3118s.removeCallbacks(this.B);
    }

    private void l() {
        this.f3118s.X0(this);
        this.f3118s.Z0(this);
        this.f3118s.a1(this.C);
        k();
    }

    private void m(Canvas canvas) {
        int i7 = this.f3117r;
        int i8 = this.f3108i;
        int i9 = this.f3114o;
        int i10 = this.f3113n;
        this.f3106g.setBounds(0, 0, i10, i8);
        this.f3107h.setBounds(0, 0, this.f3116q, this.f3109j);
        canvas.translate(0.0f, i7 - i8);
        this.f3107h.draw(canvas);
        canvas.translate(i9 - (i10 / 2), 0.0f);
        this.f3106g.draw(canvas);
        canvas.translate(-r2, -r0);
    }

    private void n(Canvas canvas) {
        int i7 = this.f3116q;
        int i8 = this.f3104e;
        int i9 = i7 - i8;
        int i10 = this.f3111l;
        int i11 = this.f3110k;
        int i12 = i10 - (i11 / 2);
        this.f3102c.setBounds(0, 0, i8, i11);
        this.f3103d.setBounds(0, 0, this.f3105f, this.f3117r);
        if (!s()) {
            canvas.translate(i9, 0.0f);
            this.f3103d.draw(canvas);
            canvas.translate(0.0f, i12);
            this.f3102c.draw(canvas);
            canvas.translate(-i9, -i12);
            return;
        }
        this.f3103d.draw(canvas);
        canvas.translate(this.f3104e, i12);
        canvas.scale(-1.0f, 1.0f);
        this.f3102c.draw(canvas);
        canvas.scale(1.0f, 1.0f);
        canvas.translate(-this.f3104e, -i12);
    }

    private int[] o() {
        int[] iArr = this.f3124y;
        int i7 = this.f3101b;
        iArr[0] = i7;
        iArr[1] = this.f3116q - i7;
        return iArr;
    }

    private int[] p() {
        int[] iArr = this.f3123x;
        int i7 = this.f3101b;
        iArr[0] = i7;
        iArr[1] = this.f3117r - i7;
        return iArr;
    }

    private void r(float f7) {
        int[] iArrO = o();
        float fMax = Math.max(iArrO[0], Math.min(iArrO[1], f7));
        if (Math.abs(this.f3114o - fMax) < 2.0f) {
            return;
        }
        int iX = x(this.f3115p, fMax, iArrO, this.f3118s.computeHorizontalScrollRange(), this.f3118s.computeHorizontalScrollOffset(), this.f3116q);
        if (iX != 0) {
            this.f3118s.scrollBy(iX, 0);
        }
        this.f3115p = fMax;
    }

    private boolean s() {
        return u.y(this.f3118s) == 1;
    }

    private void w(int i7) {
        k();
        this.f3118s.postDelayed(this.B, i7);
    }

    private int x(float f7, float f8, int[] iArr, int i7, int i8, int i9) {
        int i10 = iArr[1] - iArr[0];
        if (i10 == 0) {
            return 0;
        }
        int i11 = i7 - i9;
        int i12 = (int) (((f8 - f7) / i10) * i11);
        int i13 = i8 + i12;
        if (i13 >= i11 || i13 < 0) {
            return 0;
        }
        return i12;
    }

    private void z() {
        this.f3118s.h(this);
        this.f3118s.k(this);
        this.f3118s.l(this.C);
    }

    public void A() {
        int i7 = this.A;
        if (i7 != 0) {
            if (i7 != 3) {
                return;
            } else {
                this.f3125z.cancel();
            }
        }
        this.A = 1;
        ValueAnimator valueAnimator = this.f3125z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.f3125z.setDuration(500L);
        this.f3125z.setStartDelay(0L);
        this.f3125z.start();
    }

    void B(int i7, int i8) {
        int iComputeVerticalScrollRange = this.f3118s.computeVerticalScrollRange();
        int i9 = this.f3117r;
        this.f3119t = iComputeVerticalScrollRange - i9 > 0 && i9 >= this.f3100a;
        int iComputeHorizontalScrollRange = this.f3118s.computeHorizontalScrollRange();
        int i10 = this.f3116q;
        boolean z6 = iComputeHorizontalScrollRange - i10 > 0 && i10 >= this.f3100a;
        this.f3120u = z6;
        boolean z7 = this.f3119t;
        if (!z7 && !z6) {
            if (this.f3121v != 0) {
                y(0);
                return;
            }
            return;
        }
        if (z7) {
            float f7 = i9;
            this.f3111l = (int) ((f7 * (i8 + (f7 / 2.0f))) / iComputeVerticalScrollRange);
            this.f3110k = Math.min(i9, (i9 * i9) / iComputeVerticalScrollRange);
        }
        if (this.f3120u) {
            float f8 = i10;
            this.f3114o = (int) ((f8 * (i7 + (f8 / 2.0f))) / iComputeHorizontalScrollRange);
            this.f3113n = Math.min(i10, (i10 * i10) / iComputeHorizontalScrollRange);
        }
        int i11 = this.f3121v;
        if (i11 == 0 || i11 == 1) {
            y(1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i7 = this.f3121v;
        if (i7 == 1) {
            boolean zU = u(motionEvent.getX(), motionEvent.getY());
            boolean zT = t(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!zU && !zT) {
                return false;
            }
            if (zT) {
                this.f3122w = 1;
                this.f3115p = (int) motionEvent.getX();
            } else if (zU) {
                this.f3122w = 2;
                this.f3112m = (int) motionEvent.getY();
            }
            y(2);
        } else if (i7 != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void b(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.f3121v == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean zU = u(motionEvent.getX(), motionEvent.getY());
            boolean zT = t(motionEvent.getX(), motionEvent.getY());
            if (zU || zT) {
                if (zT) {
                    this.f3122w = 1;
                    this.f3115p = (int) motionEvent.getX();
                } else if (zU) {
                    this.f3122w = 2;
                    this.f3112m = (int) motionEvent.getY();
                }
                y(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.f3121v == 2) {
            this.f3112m = 0.0f;
            this.f3115p = 0.0f;
            y(1);
            this.f3122w = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.f3121v == 2) {
            A();
            if (this.f3122w == 1) {
                r(motionEvent.getX());
            }
            if (this.f3122w == 2) {
                C(motionEvent.getY());
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void c(boolean z6) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void i(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        if (this.f3116q != this.f3118s.getWidth() || this.f3117r != this.f3118s.getHeight()) {
            this.f3116q = this.f3118s.getWidth();
            this.f3117r = this.f3118s.getHeight();
            y(0);
        } else if (this.A != 0) {
            if (this.f3119t) {
                n(canvas);
            }
            if (this.f3120u) {
                m(canvas);
            }
        }
    }

    public void j(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f3118s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            l();
        }
        this.f3118s = recyclerView;
        if (recyclerView != null) {
            z();
        }
    }

    void q(int i7) {
        int i8 = this.A;
        if (i8 == 1) {
            this.f3125z.cancel();
        } else if (i8 != 2) {
            return;
        }
        this.A = 3;
        ValueAnimator valueAnimator = this.f3125z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.f3125z.setDuration(i7);
        this.f3125z.start();
    }

    boolean t(float f7, float f8) {
        if (f8 >= this.f3117r - this.f3108i) {
            int i7 = this.f3114o;
            int i8 = this.f3113n;
            if (f7 >= i7 - (i8 / 2) && f7 <= i7 + (i8 / 2)) {
                return true;
            }
        }
        return false;
    }

    boolean u(float f7, float f8) {
        if (!s() ? f7 >= this.f3116q - this.f3104e : f7 <= this.f3104e / 2) {
            int i7 = this.f3111l;
            int i8 = this.f3110k;
            if (f8 >= i7 - (i8 / 2) && f8 <= i7 + (i8 / 2)) {
                return true;
            }
        }
        return false;
    }

    void v() {
        this.f3118s.invalidate();
    }

    void y(int i7) {
        if (i7 == 2 && this.f3121v != 2) {
            this.f3102c.setState(D);
            k();
        }
        if (i7 == 0) {
            v();
        } else {
            A();
        }
        if (this.f3121v == 2 && i7 != 2) {
            this.f3102c.setState(E);
            w(1200);
        } else if (i7 == 1) {
            w(1500);
        }
        this.f3121v = i7;
    }
}
