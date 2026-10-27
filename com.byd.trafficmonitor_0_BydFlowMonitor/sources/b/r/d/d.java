package b.r.d;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import b.h.m.t;

/* JADX INFO: compiled from: FastScroller.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends RecyclerView.n implements RecyclerView.s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f3073a = {R.attr.state_pressed};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f3074b = new int[0];
    public final ValueAnimator B;
    public int C;
    public final Runnable D;
    public final RecyclerView.t E;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3075c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f3076d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final StateListDrawable f3077e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Drawable f3078f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3079g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f3080h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final StateListDrawable f3081i;
    public final Drawable j;
    public final int k;
    public final int l;
    public int m;
    public int n;
    public float o;
    public int p;
    public int q;
    public float r;
    public RecyclerView u;
    public int s = 0;
    public int t = 0;
    public boolean v = false;
    public boolean w = false;
    public int x = 0;
    public int y = 0;
    public final int[] z = new int[2];
    public final int[] A = new int[2];

    /* JADX INFO: compiled from: FastScroller.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.q(500);
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    public class b extends RecyclerView.t {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i2, int i3) {
            d.this.B(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3084a = false;

        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3084a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f3084a) {
                this.f3084a = false;
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

    /* JADX INFO: renamed from: b.r.d.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FastScroller.java */
    public class C0059d implements ValueAnimator.AnimatorUpdateListener {
        public C0059d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            d.this.f3077e.setAlpha(iFloatValue);
            d.this.f3078f.setAlpha(iFloatValue);
            d.this.v();
        }
    }

    public d(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i2, int i3, int i4) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.B = valueAnimatorOfFloat;
        this.C = 0;
        this.D = new a();
        this.E = new b();
        this.f3077e = stateListDrawable;
        this.f3078f = drawable;
        this.f3081i = stateListDrawable2;
        this.j = drawable2;
        this.f3079g = Math.max(i2, stateListDrawable.getIntrinsicWidth());
        this.f3080h = Math.max(i2, drawable.getIntrinsicWidth());
        this.k = Math.max(i2, stateListDrawable2.getIntrinsicWidth());
        this.l = Math.max(i2, drawable2.getIntrinsicWidth());
        this.f3075c = i3;
        this.f3076d = i4;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new c());
        valueAnimatorOfFloat.addUpdateListener(new C0059d());
        j(recyclerView);
    }

    public void A() {
        int i2 = this.C;
        if (i2 != 0) {
            if (i2 != 3) {
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

    public void B(int i2, int i3) {
        int iComputeVerticalScrollRange = this.u.computeVerticalScrollRange();
        int i4 = this.t;
        this.v = iComputeVerticalScrollRange - i4 > 0 && i4 >= this.f3075c;
        int iComputeHorizontalScrollRange = this.u.computeHorizontalScrollRange();
        int i5 = this.s;
        boolean z = iComputeHorizontalScrollRange - i5 > 0 && i5 >= this.f3075c;
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
            float f2 = i4;
            this.n = (int) ((f2 * (i3 + (f2 / 2.0f))) / iComputeVerticalScrollRange);
            this.m = Math.min(i4, (i4 * i4) / iComputeVerticalScrollRange);
        }
        if (this.w) {
            float f3 = i5;
            this.q = (int) ((f3 * (i2 + (f3 / 2.0f))) / iComputeHorizontalScrollRange);
            this.p = Math.min(i5, (i5 * i5) / iComputeHorizontalScrollRange);
        }
        int i6 = this.x;
        if (i6 == 0 || i6 == 1) {
            y(1);
        }
    }

    public final void C(float f2) {
        int[] iArrP = p();
        float fMax = Math.max(iArrP[0], Math.min(iArrP[1], f2));
        if (Math.abs(this.n - fMax) < 2.0f) {
            return;
        }
        int iX = x(this.o, fMax, iArrP, this.u.computeVerticalScrollRange(), this.u.computeVerticalScrollOffset(), this.t);
        if (iX != 0) {
            this.u.scrollBy(0, iX);
        }
        this.o = fMax;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i2 = this.x;
        if (i2 == 1) {
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
        } else if (i2 != 2) {
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

    public final void k() {
        this.u.removeCallbacks(this.D);
    }

    public final void l() {
        this.u.X0(this);
        this.u.Y0(this);
        this.u.Z0(this.E);
        k();
    }

    public final void m(Canvas canvas) {
        int i2 = this.t;
        int i3 = this.k;
        int i4 = this.q;
        int i5 = this.p;
        this.f3081i.setBounds(0, 0, i5, i3);
        this.j.setBounds(0, 0, this.s, this.l);
        canvas.translate(0.0f, i2 - i3);
        this.j.draw(canvas);
        canvas.translate(i4 - (i5 / 2), 0.0f);
        this.f3081i.draw(canvas);
        canvas.translate(-r2, -r0);
    }

    public final void n(Canvas canvas) {
        int i2 = this.s;
        int i3 = this.f3079g;
        int i4 = i2 - i3;
        int i5 = this.n;
        int i6 = this.m;
        int i7 = i5 - (i6 / 2);
        this.f3077e.setBounds(0, 0, i3, i6);
        this.f3078f.setBounds(0, 0, this.f3080h, this.t);
        if (!s()) {
            canvas.translate(i4, 0.0f);
            this.f3078f.draw(canvas);
            canvas.translate(0.0f, i7);
            this.f3077e.draw(canvas);
            canvas.translate(-i4, -i7);
            return;
        }
        this.f3078f.draw(canvas);
        canvas.translate(this.f3079g, i7);
        canvas.scale(-1.0f, 1.0f);
        this.f3077e.draw(canvas);
        canvas.scale(1.0f, 1.0f);
        canvas.translate(-this.f3079g, -i7);
    }

    public final int[] o() {
        int[] iArr = this.A;
        int i2 = this.f3076d;
        iArr[0] = i2;
        iArr[1] = this.s - i2;
        return iArr;
    }

    public final int[] p() {
        int[] iArr = this.z;
        int i2 = this.f3076d;
        iArr[0] = i2;
        iArr[1] = this.t - i2;
        return iArr;
    }

    public void q(int i2) {
        int i3 = this.C;
        if (i3 == 1) {
            this.B.cancel();
        } else if (i3 != 2) {
            return;
        }
        this.C = 3;
        ValueAnimator valueAnimator = this.B;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.B.setDuration(i2);
        this.B.start();
    }

    public final void r(float f2) {
        int[] iArrO = o();
        float fMax = Math.max(iArrO[0], Math.min(iArrO[1], f2));
        if (Math.abs(this.q - fMax) < 2.0f) {
            return;
        }
        int iX = x(this.r, fMax, iArrO, this.u.computeHorizontalScrollRange(), this.u.computeHorizontalScrollOffset(), this.s);
        if (iX != 0) {
            this.u.scrollBy(iX, 0);
        }
        this.r = fMax;
    }

    public final boolean s() {
        return t.z(this.u) == 1;
    }

    public boolean t(float f2, float f3) {
        if (f3 >= this.t - this.k) {
            int i2 = this.q;
            int i3 = this.p;
            if (f2 >= i2 - (i3 / 2) && f2 <= i2 + (i3 / 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean u(float f2, float f3) {
        if (!s() ? f2 >= this.s - this.f3079g : f2 <= this.f3079g / 2) {
            int i2 = this.n;
            int i3 = this.m;
            if (f3 >= i2 - (i3 / 2) && f3 <= i2 + (i3 / 2)) {
                return true;
            }
        }
        return false;
    }

    public void v() {
        this.u.invalidate();
    }

    public final void w(int i2) {
        k();
        this.u.postDelayed(this.D, i2);
    }

    public final int x(float f2, float f3, int[] iArr, int i2, int i3, int i4) {
        int i5 = iArr[1] - iArr[0];
        if (i5 == 0) {
            return 0;
        }
        int i6 = i2 - i4;
        int i7 = (int) (((f3 - f2) / i5) * i6);
        int i8 = i3 + i7;
        if (i8 >= i6 || i8 < 0) {
            return 0;
        }
        return i7;
    }

    public void y(int i2) {
        if (i2 == 2 && this.x != 2) {
            this.f3077e.setState(f3073a);
            k();
        }
        if (i2 == 0) {
            v();
        } else {
            A();
        }
        if (this.x == 2 && i2 != 2) {
            this.f3077e.setState(f3074b);
            w(1200);
        } else if (i2 == 1) {
            w(1500);
        }
        this.x = i2;
    }

    public final void z() {
        this.u.h(this);
        this.u.k(this);
        this.u.l(this.E);
    }
}
