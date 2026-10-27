package c1;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import o0.t;

/* JADX INFO: compiled from: FastScroller.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends RecyclerView.n implements RecyclerView.s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f2213a = {R.attr.state_pressed};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f2214b = new int[0];
    public final ValueAnimator B;
    public int C;
    public final Runnable D;
    public final RecyclerView.t E;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2215c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2216d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final StateListDrawable f2217e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Drawable f2218f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2219g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f2220h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final StateListDrawable f2221i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Drawable f2222j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f2223k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2224l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2225m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2226n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f2227o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2228p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f2229q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f2230r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public RecyclerView f2233u;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f2231s = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f2232t = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f2234v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f2235w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2236x = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f2237y = 0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int[] f2238z = new int[2];
    public final int[] A = new int[2];

    /* JADX INFO: compiled from: FastScroller.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.k(500);
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    public class b extends RecyclerView.t {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrolled(RecyclerView recyclerView, int i8, int i9) {
            d.this.v(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2241d = false;

        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2241d = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f2241d) {
                this.f2241d = false;
                return;
            }
            if (((Float) d.this.B.getAnimatedValue()).floatValue() == 0.0f) {
                d dVar = d.this;
                dVar.C = 0;
                dVar.s(0);
            } else {
                d dVar2 = d.this;
                dVar2.C = 2;
                dVar2.p();
            }
        }
    }

    /* JADX INFO: renamed from: c1.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FastScroller.java */
    public class C0011d implements ValueAnimator.AnimatorUpdateListener {
        public C0011d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            d.this.f2217e.setAlpha(iFloatValue);
            d.this.f2218f.setAlpha(iFloatValue);
            d.this.p();
        }
    }

    public d(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i8, int i9, int i10) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.B = valueAnimatorOfFloat;
        this.C = 0;
        this.D = new a();
        this.E = new b();
        this.f2217e = stateListDrawable;
        this.f2218f = drawable;
        this.f2221i = stateListDrawable2;
        this.f2222j = drawable2;
        this.f2219g = Math.max(i8, stateListDrawable.getIntrinsicWidth());
        this.f2220h = Math.max(i8, drawable.getIntrinsicWidth());
        this.f2223k = Math.max(i8, stateListDrawable2.getIntrinsicWidth());
        this.f2224l = Math.max(i8, drawable2.getIntrinsicWidth());
        this.f2215c = i9;
        this.f2216d = i10;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new c());
        valueAnimatorOfFloat.addUpdateListener(new C0011d());
        d(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i8 = this.f2236x;
        if (i8 == 1) {
            boolean zO = o(motionEvent.getX(), motionEvent.getY());
            boolean zN = n(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!zO && !zN) {
                return false;
            }
            if (zN) {
                this.f2237y = 1;
                this.f2230r = (int) motionEvent.getX();
            } else if (zO) {
                this.f2237y = 2;
                this.f2227o = (int) motionEvent.getY();
            }
            s(2);
        } else if (i8 != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void b(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.f2236x == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean zO = o(motionEvent.getX(), motionEvent.getY());
            boolean zN = n(motionEvent.getX(), motionEvent.getY());
            if (zO || zN) {
                if (zN) {
                    this.f2237y = 1;
                    this.f2230r = (int) motionEvent.getX();
                } else if (zO) {
                    this.f2237y = 2;
                    this.f2227o = (int) motionEvent.getY();
                }
                s(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.f2236x == 2) {
            this.f2227o = 0.0f;
            this.f2230r = 0.0f;
            s(1);
            this.f2237y = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.f2236x == 2) {
            u();
            if (this.f2237y == 1) {
                l(motionEvent.getX());
            }
            if (this.f2237y == 2) {
                w(motionEvent.getY());
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void c(boolean z7) {
    }

    public void d(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f2233u;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            f();
        }
        this.f2233u = recyclerView;
        if (recyclerView != null) {
            t();
        }
    }

    public final void e() {
        this.f2233u.removeCallbacks(this.D);
    }

    public final void f() {
        this.f2233u.removeItemDecoration(this);
        this.f2233u.removeOnItemTouchListener(this);
        this.f2233u.removeOnScrollListener(this.E);
        e();
    }

    public final void g(Canvas canvas) {
        int i8 = this.f2232t;
        int i9 = this.f2223k;
        int i10 = this.f2229q;
        int i11 = this.f2228p;
        this.f2221i.setBounds(0, 0, i11, i9);
        this.f2222j.setBounds(0, 0, this.f2231s, this.f2224l);
        canvas.translate(0.0f, i8 - i9);
        this.f2222j.draw(canvas);
        canvas.translate(i10 - (i11 / 2), 0.0f);
        this.f2221i.draw(canvas);
        canvas.translate(-r2, -r0);
    }

    public final void h(Canvas canvas) {
        int i8 = this.f2231s;
        int i9 = this.f2219g;
        int i10 = i8 - i9;
        int i11 = this.f2226n;
        int i12 = this.f2225m;
        int i13 = i11 - (i12 / 2);
        this.f2217e.setBounds(0, 0, i9, i12);
        this.f2218f.setBounds(0, 0, this.f2220h, this.f2232t);
        if (!m()) {
            canvas.translate(i10, 0.0f);
            this.f2218f.draw(canvas);
            canvas.translate(0.0f, i13);
            this.f2217e.draw(canvas);
            canvas.translate(-i10, -i13);
            return;
        }
        this.f2218f.draw(canvas);
        canvas.translate(this.f2219g, i13);
        canvas.scale(-1.0f, 1.0f);
        this.f2217e.draw(canvas);
        canvas.scale(1.0f, 1.0f);
        canvas.translate(-this.f2219g, -i13);
    }

    public final int[] i() {
        int[] iArr = this.A;
        int i8 = this.f2216d;
        iArr[0] = i8;
        iArr[1] = this.f2231s - i8;
        return iArr;
    }

    public final int[] j() {
        int[] iArr = this.f2238z;
        int i8 = this.f2216d;
        iArr[0] = i8;
        iArr[1] = this.f2232t - i8;
        return iArr;
    }

    public void k(int i8) {
        int i9 = this.C;
        if (i9 == 1) {
            this.B.cancel();
        } else if (i9 != 2) {
            return;
        }
        this.C = 3;
        ValueAnimator valueAnimator = this.B;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.B.setDuration(i8);
        this.B.start();
    }

    public final void l(float f8) {
        int[] iArrI = i();
        float fMax = Math.max(iArrI[0], Math.min(iArrI[1], f8));
        if (Math.abs(this.f2229q - fMax) < 2.0f) {
            return;
        }
        int iR = r(this.f2230r, fMax, iArrI, this.f2233u.computeHorizontalScrollRange(), this.f2233u.computeHorizontalScrollOffset(), this.f2231s);
        if (iR != 0) {
            this.f2233u.scrollBy(iR, 0);
        }
        this.f2230r = fMax;
    }

    public final boolean m() {
        return t.A(this.f2233u) == 1;
    }

    public boolean n(float f8, float f9) {
        if (f9 >= this.f2232t - this.f2223k) {
            int i8 = this.f2229q;
            int i9 = this.f2228p;
            if (f8 >= i8 - (i9 / 2) && f8 <= i8 + (i9 / 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean o(float f8, float f9) {
        if (!m() ? f8 >= this.f2231s - this.f2219g : f8 <= this.f2219g / 2) {
            int i8 = this.f2226n;
            int i9 = this.f2225m;
            if (f9 >= i8 - (i9 / 2) && f9 <= i8 + (i9 / 2)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        if (this.f2231s != this.f2233u.getWidth() || this.f2232t != this.f2233u.getHeight()) {
            this.f2231s = this.f2233u.getWidth();
            this.f2232t = this.f2233u.getHeight();
            s(0);
        } else if (this.C != 0) {
            if (this.f2234v) {
                h(canvas);
            }
            if (this.f2235w) {
                g(canvas);
            }
        }
    }

    public void p() {
        this.f2233u.invalidate();
    }

    public final void q(int i8) {
        e();
        this.f2233u.postDelayed(this.D, i8);
    }

    public final int r(float f8, float f9, int[] iArr, int i8, int i9, int i10) {
        int i11 = iArr[1] - iArr[0];
        if (i11 == 0) {
            return 0;
        }
        int i12 = i8 - i10;
        int i13 = (int) (((f9 - f8) / i11) * i12);
        int i14 = i9 + i13;
        if (i14 >= i12 || i14 < 0) {
            return 0;
        }
        return i13;
    }

    public void s(int i8) {
        if (i8 == 2 && this.f2236x != 2) {
            this.f2217e.setState(f2213a);
            e();
        }
        if (i8 == 0) {
            p();
        } else {
            u();
        }
        if (this.f2236x == 2 && i8 != 2) {
            this.f2217e.setState(f2214b);
            q(1200);
        } else if (i8 == 1) {
            q(1500);
        }
        this.f2236x = i8;
    }

    public final void t() {
        this.f2233u.addItemDecoration(this);
        this.f2233u.addOnItemTouchListener(this);
        this.f2233u.addOnScrollListener(this.E);
    }

    public void u() {
        int i8 = this.C;
        if (i8 != 0) {
            if (i8 != 3) {
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

    public void v(int i8, int i9) {
        int iComputeVerticalScrollRange = this.f2233u.computeVerticalScrollRange();
        int i10 = this.f2232t;
        this.f2234v = iComputeVerticalScrollRange - i10 > 0 && i10 >= this.f2215c;
        int iComputeHorizontalScrollRange = this.f2233u.computeHorizontalScrollRange();
        int i11 = this.f2231s;
        boolean z7 = iComputeHorizontalScrollRange - i11 > 0 && i11 >= this.f2215c;
        this.f2235w = z7;
        boolean z8 = this.f2234v;
        if (!z8 && !z7) {
            if (this.f2236x != 0) {
                s(0);
                return;
            }
            return;
        }
        if (z8) {
            float f8 = i10;
            this.f2226n = (int) ((f8 * (i9 + (f8 / 2.0f))) / iComputeVerticalScrollRange);
            this.f2225m = Math.min(i10, (i10 * i10) / iComputeVerticalScrollRange);
        }
        if (this.f2235w) {
            float f9 = i11;
            this.f2229q = (int) ((f9 * (i8 + (f9 / 2.0f))) / iComputeHorizontalScrollRange);
            this.f2228p = Math.min(i11, (i11 * i11) / iComputeHorizontalScrollRange);
        }
        int i12 = this.f2236x;
        if (i12 == 0 || i12 == 1) {
            s(1);
        }
    }

    public final void w(float f8) {
        int[] iArrJ = j();
        float fMax = Math.max(iArrJ[0], Math.min(iArrJ[1], f8));
        if (Math.abs(this.f2226n - fMax) < 2.0f) {
            return;
        }
        int iR = r(this.f2227o, fMax, iArrJ, this.f2233u.computeVerticalScrollRange(), this.f2233u.computeVerticalScrollOffset(), this.f2232t);
        if (iR != 0) {
            this.f2233u.scrollBy(0, iR);
        }
        this.f2227o = fMax;
    }
}
