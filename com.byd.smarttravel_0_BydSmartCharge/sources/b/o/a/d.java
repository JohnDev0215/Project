package b.o.a;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import b.g.j.t;

/* JADX INFO: compiled from: FastScroller.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends RecyclerView.n implements RecyclerView.s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f1377a = {R.attr.state_pressed};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1378b = new int[0];
    public final ValueAnimator B;
    public int C;
    public final Runnable D;
    public final RecyclerView.t E;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1379c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1380d;
    public final StateListDrawable e;
    public final Drawable f;
    public final int g;
    public final int h;
    public final StateListDrawable i;
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
        public void b(RecyclerView recyclerView, int i, int i2) {
            d.this.B(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1383a = false;

        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1383a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f1383a) {
                this.f1383a = false;
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

    /* JADX INFO: renamed from: b.o.a.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FastScroller.java */
    public class C0037d implements ValueAnimator.AnimatorUpdateListener {
        public C0037d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            d.this.e.setAlpha(iFloatValue);
            d.this.f.setAlpha(iFloatValue);
            d.this.v();
        }
    }

    public d(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
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
        this.f1379c = i2;
        this.f1380d = i3;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new c());
        valueAnimatorOfFloat.addUpdateListener(new C0037d());
        j(recyclerView);
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

    public void B(int i, int i2) {
        int iComputeVerticalScrollRange = this.u.computeVerticalScrollRange();
        int i3 = this.t;
        this.v = iComputeVerticalScrollRange - i3 > 0 && i3 >= this.f1379c;
        int iComputeHorizontalScrollRange = this.u.computeHorizontalScrollRange();
        int i4 = this.s;
        boolean z = iComputeHorizontalScrollRange - i4 > 0 && i4 >= this.f1379c;
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

    public final void C(float f) {
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

    public final void k() {
        this.u.removeCallbacks(this.D);
    }

    public final void l() {
        this.u.removeItemDecoration(this);
        this.u.removeOnItemTouchListener(this);
        this.u.removeOnScrollListener(this.E);
        k();
    }

    public final void m(Canvas canvas) {
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

    public final void n(Canvas canvas) {
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

    public final int[] o() {
        int[] iArr = this.A;
        int i = this.f1380d;
        iArr[0] = i;
        iArr[1] = this.s - i;
        return iArr;
    }

    public final int[] p() {
        int[] iArr = this.z;
        int i = this.f1380d;
        iArr[0] = i;
        iArr[1] = this.t - i;
        return iArr;
    }

    public void q(int i) {
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

    public final void r(float f) {
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

    public final boolean s() {
        return t.z(this.u) == 1;
    }

    public boolean t(float f, float f2) {
        if (f2 >= this.t - this.k) {
            int i = this.q;
            int i2 = this.p;
            if (f >= i - (i2 / 2) && f <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean u(float f, float f2) {
        if (!s() ? f >= this.s - this.g : f <= this.g / 2) {
            int i = this.n;
            int i2 = this.m;
            if (f2 >= i - (i2 / 2) && f2 <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    public void v() {
        this.u.invalidate();
    }

    public final void w(int i) {
        k();
        this.u.postDelayed(this.D, i);
    }

    public final int x(float f, float f2, int[] iArr, int i, int i2, int i3) {
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

    public void y(int i) {
        if (i == 2 && this.x != 2) {
            this.e.setState(f1377a);
            k();
        }
        if (i == 0) {
            v();
        } else {
            A();
        }
        if (this.x == 2 && i != 2) {
            this.e.setState(f1378b);
            w(1200);
        } else if (i == 1) {
            w(1500);
        }
        this.x = i;
    }

    public final void z() {
        this.u.addItemDecoration(this);
        this.u.addOnItemTouchListener(this);
        this.u.addOnScrollListener(this.E);
    }
}
