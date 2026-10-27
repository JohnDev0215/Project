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
import b.g.k.t;

/* JADX INFO: compiled from: FastScroller.java */
/* JADX INFO: loaded from: classes.dex */
class d extends RecyclerView.n implements RecyclerView.s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1271a = {R.attr.state_pressed};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f1272b = new int[0];
    final ValueAnimator B;
    int C;
    private final Runnable D;
    private final RecyclerView.t E;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f1273c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f1274d;
    final StateListDrawable e;
    final Drawable f;
    private final int g;
    private final int h;
    private final StateListDrawable i;
    private final Drawable j;
    private final int k;
    private final int l;
    int m;
    int n;
    float o;
    int p;
    int q;
    float r;
    private RecyclerView u;
    private int s = 0;
    private int t = 0;
    private boolean v = false;
    private boolean w = false;
    private int x = 0;
    private int y = 0;
    private final int[] z = new int[2];
    private final int[] A = new int[2];

    /* JADX INFO: compiled from: FastScroller.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.q(500);
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    class b extends RecyclerView.t {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            d.this.B(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    private class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f1277a = false;

        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1277a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f1277a) {
                this.f1277a = false;
                return;
            }
            if (((Float) d.this.B.getAnimatedValue()).floatValue() == 0.0f) {
                d dVar = d.this;
                dVar.C = 0;
                dVar.y(0);
            } else {
                d dVar2 = d.this;
                dVar2.C = 2;
                dVar2.v();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FastScroller.java */
    private class C0024d implements ValueAnimator.AnimatorUpdateListener {
        C0024d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            d.this.e.setAlpha(iFloatValue);
            d.this.f.setAlpha(iFloatValue);
            d.this.v();
        }
    }

    d(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.B = valueAnimatorOfFloat;
        this.C = 0;
        this.D = new a();
        this.E = new b();
        this.e = stateListDrawable;
        this.f = drawable;
        this.i = stateListDrawable2;
        this.j = drawable2;
        this.g = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.h = Math.max(i, drawable.getIntrinsicWidth());
        this.k = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.l = Math.max(i, drawable2.getIntrinsicWidth());
        this.f1273c = i2;
        this.f1274d = i3;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new c());
        valueAnimatorOfFloat.addUpdateListener(new C0024d());
        j(recyclerView);
    }

    private void C(float f) {
        int[] iArrP = p();
        float fMax = Math.max(iArrP[0], Math.min(iArrP[1], f));
        if (Math.abs(this.n - fMax) < 2.0f) {
            return;
        }
        int iX = x(this.o, fMax, iArrP, this.u.computeVerticalScrollRange(), this.u.computeVerticalScrollOffset(), this.t);
        if (iX != 0) {
            this.u.scrollBy(0, iX);
        }
        this.o = fMax;
    }

    private void k() {
        this.u.removeCallbacks(this.D);
    }

    private void l() {
        this.u.W0(this);
        this.u.X0(this);
        this.u.Y0(this.E);
        k();
    }

    private void m(Canvas canvas) {
        int i = this.t;
        int i2 = this.k;
        int i3 = this.q;
        int i4 = this.p;
        this.i.setBounds(0, 0, i4, i2);
        this.j.setBounds(0, 0, this.s, this.l);
        canvas.translate(0.0f, i - i2);
        this.j.draw(canvas);
        canvas.translate(i3 - (i4 / 2), 0.0f);
        this.i.draw(canvas);
        canvas.translate(-r2, -r0);
    }

    private void n(Canvas canvas) {
        int i = this.s;
        int i2 = this.g;
        int i3 = i - i2;
        int i4 = this.n;
        int i5 = this.m;
        int i6 = i4 - (i5 / 2);
        this.e.setBounds(0, 0, i2, i5);
        this.f.setBounds(0, 0, this.h, this.t);
        if (!s()) {
            canvas.translate(i3, 0.0f);
            this.f.draw(canvas);
            canvas.translate(0.0f, i6);
            this.e.draw(canvas);
            canvas.translate(-i3, -i6);
            return;
        }
        this.f.draw(canvas);
        canvas.translate(this.g, i6);
        canvas.scale(-1.0f, 1.0f);
        this.e.draw(canvas);
        canvas.scale(1.0f, 1.0f);
        canvas.translate(-this.g, -i6);
    }

    private int[] o() {
        int[] iArr = this.A;
        int i = this.f1274d;
        iArr[0] = i;
        iArr[1] = this.s - i;
        return iArr;
    }

    private int[] p() {
        int[] iArr = this.z;
        int i = this.f1274d;
        iArr[0] = i;
        iArr[1] = this.t - i;
        return iArr;
    }

    private void r(float f) {
        int[] iArrO = o();
        float fMax = Math.max(iArrO[0], Math.min(iArrO[1], f));
        if (Math.abs(this.q - fMax) < 2.0f) {
            return;
        }
        int iX = x(this.r, fMax, iArrO, this.u.computeHorizontalScrollRange(), this.u.computeHorizontalScrollOffset(), this.s);
        if (iX != 0) {
            this.u.scrollBy(iX, 0);
        }
        this.r = fMax;
    }

    private boolean s() {
        return t.z(this.u) == 1;
    }

    private void w(int i) {
        k();
        this.u.postDelayed(this.D, i);
    }

    private int x(float f, float f2, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 == 0) {
            return 0;
        }
        int i5 = i - i3;
        int i6 = (int) (((f2 - f) / i4) * i5);
        int i7 = i2 + i6;
        if (i7 >= i5 || i7 < 0) {
            return 0;
        }
        return i6;
    }

    private void z() {
        this.u.h(this);
        this.u.k(this);
        this.u.l(this.E);
    }

    public void A() {
        int i = this.C;
        if (i != 0) {
            if (i != 3) {
                return;
            } else {
                this.B.cancel();
            }
        }
        this.C = 1;
        ValueAnimator valueAnimator = this.B;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.B.setDuration(500L);
        this.B.setStartDelay(0L);
        this.B.start();
    }

    void B(int i, int i2) {
        int iComputeVerticalScrollRange = this.u.computeVerticalScrollRange();
        int i3 = this.t;
        this.v = iComputeVerticalScrollRange - i3 > 0 && i3 >= this.f1273c;
        int iComputeHorizontalScrollRange = this.u.computeHorizontalScrollRange();
        int i4 = this.s;
        boolean z = iComputeHorizontalScrollRange - i4 > 0 && i4 >= this.f1273c;
        this.w = z;
        boolean z2 = this.v;
        if (!z2 && !z) {
            if (this.x != 0) {
                y(0);
                return;
            }
            return;
        }
        if (z2) {
            float f = i3;
            this.n = (int) ((f * (i2 + (f / 2.0f))) / iComputeVerticalScrollRange);
            this.m = Math.min(i3, (i3 * i3) / iComputeVerticalScrollRange);
        }
        if (this.w) {
            float f2 = i4;
            this.q = (int) ((f2 * (i + (f2 / 2.0f))) / iComputeHorizontalScrollRange);
            this.p = Math.min(i4, (i4 * i4) / iComputeHorizontalScrollRange);
        }
        int i5 = this.x;
        if (i5 == 0 || i5 == 1) {
            y(1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i = this.x;
        if (i == 1) {
            boolean zU = u(motionEvent.getX(), motionEvent.getY());
            boolean zT = t(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!zU && !zT) {
                return false;
            }
            if (zT) {
                this.y = 1;
                this.r = (int) motionEvent.getX();
            } else if (zU) {
                this.y = 2;
                this.o = (int) motionEvent.getY();
            }
            y(2);
        } else if (i != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void b(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.x == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean zU = u(motionEvent.getX(), motionEvent.getY());
            boolean zT = t(motionEvent.getX(), motionEvent.getY());
            if (zU || zT) {
                if (zT) {
                    this.y = 1;
                    this.r = (int) motionEvent.getX();
                } else if (zU) {
                    this.y = 2;
                    this.o = (int) motionEvent.getY();
                }
                y(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.x == 2) {
            this.o = 0.0f;
            this.r = 0.0f;
            y(1);
            this.y = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.x == 2) {
            A();
            if (this.y == 1) {
                r(motionEvent.getX());
            }
            if (this.y == 2) {
                C(motionEvent.getY());
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void c(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void i(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        if (this.s != this.u.getWidth() || this.t != this.u.getHeight()) {
            this.s = this.u.getWidth();
            this.t = this.u.getHeight();
            y(0);
        } else if (this.C != 0) {
            if (this.v) {
                n(canvas);
            }
            if (this.w) {
                m(canvas);
            }
        }
    }

    public void j(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.u;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            l();
        }
        this.u = recyclerView;
        if (recyclerView != null) {
            z();
        }
    }

    void q(int i) {
        int i2 = this.C;
        if (i2 == 1) {
            this.B.cancel();
        } else if (i2 != 2) {
            return;
        }
        this.C = 3;
        ValueAnimator valueAnimator = this.B;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.B.setDuration(i);
        this.B.start();
    }

    boolean t(float f, float f2) {
        if (f2 >= this.t - this.k) {
            int i = this.q;
            int i2 = this.p;
            if (f >= i - (i2 / 2) && f <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    boolean u(float f, float f2) {
        if (!s() ? f >= this.s - this.g : f <= this.g / 2) {
            int i = this.n;
            int i2 = this.m;
            if (f2 >= i - (i2 / 2) && f2 <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    void v() {
        this.u.invalidate();
    }

    void y(int i) {
        if (i == 2 && this.x != 2) {
            this.e.setState(f1271a);
            k();
        }
        if (i == 0) {
            v();
        } else {
            A();
        }
        if (this.x == 2 && i != 2) {
            this.e.setState(f1272b);
            w(1200);
        } else if (i == 1) {
            w(1500);
        }
        this.x = i;
    }
}
