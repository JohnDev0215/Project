package w0;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import l0.t;

/* JADX INFO: compiled from: FastScroller.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends RecyclerView.n implements RecyclerView.s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5585a = {R.attr.state_pressed};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f5586b = new int[0];
    public final ValueAnimator B;
    public int C;
    public final Runnable D;
    public final RecyclerView.t E;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5587c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5588d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final StateListDrawable f5589e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Drawable f5590f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f5591g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f5592h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final StateListDrawable f5593i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Drawable f5594j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f5595k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f5596l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5597m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5598n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f5599o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5600p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5601q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5602r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public RecyclerView f5605u;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f5603s = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f5604t = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f5606v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f5607w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f5608x = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f5609y = 0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int[] f5610z = new int[2];
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
        public void b(RecyclerView recyclerView, int i3, int i4) {
            d.this.B(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5613a = false;

        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5613a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f5613a) {
                this.f5613a = false;
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

    /* JADX INFO: renamed from: w0.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FastScroller.java */
    public class C0071d implements ValueAnimator.AnimatorUpdateListener {
        public C0071d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            d.this.f5589e.setAlpha(iFloatValue);
            d.this.f5590f.setAlpha(iFloatValue);
            d.this.v();
        }
    }

    public d(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i3, int i4, int i5) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.B = valueAnimatorOfFloat;
        this.C = 0;
        this.D = new a();
        this.E = new b();
        this.f5589e = stateListDrawable;
        this.f5590f = drawable;
        this.f5593i = stateListDrawable2;
        this.f5594j = drawable2;
        this.f5591g = Math.max(i3, stateListDrawable.getIntrinsicWidth());
        this.f5592h = Math.max(i3, drawable.getIntrinsicWidth());
        this.f5595k = Math.max(i3, stateListDrawable2.getIntrinsicWidth());
        this.f5596l = Math.max(i3, drawable2.getIntrinsicWidth());
        this.f5587c = i4;
        this.f5588d = i5;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new c());
        valueAnimatorOfFloat.addUpdateListener(new C0071d());
        j(recyclerView);
    }

    public void A() {
        int i3 = this.C;
        if (i3 != 0) {
            if (i3 != 3) {
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

    public void B(int i3, int i4) {
        int iComputeVerticalScrollRange = this.f5605u.computeVerticalScrollRange();
        int i5 = this.f5604t;
        this.f5606v = iComputeVerticalScrollRange - i5 > 0 && i5 >= this.f5587c;
        int iComputeHorizontalScrollRange = this.f5605u.computeHorizontalScrollRange();
        int i6 = this.f5603s;
        boolean z3 = iComputeHorizontalScrollRange - i6 > 0 && i6 >= this.f5587c;
        this.f5607w = z3;
        boolean z4 = this.f5606v;
        if (!z4 && !z3) {
            if (this.f5608x != 0) {
                y(0);
                return;
            }
            return;
        }
        if (z4) {
            float f3 = i5;
            this.f5598n = (int) ((f3 * (i4 + (f3 / 2.0f))) / iComputeVerticalScrollRange);
            this.f5597m = Math.min(i5, (i5 * i5) / iComputeVerticalScrollRange);
        }
        if (this.f5607w) {
            float f4 = i6;
            this.f5601q = (int) ((f4 * (i3 + (f4 / 2.0f))) / iComputeHorizontalScrollRange);
            this.f5600p = Math.min(i6, (i6 * i6) / iComputeHorizontalScrollRange);
        }
        int i7 = this.f5608x;
        if (i7 == 0 || i7 == 1) {
            y(1);
        }
    }

    public final void C(float f3) {
        int[] iArrP = p();
        float fMax = Math.max(iArrP[0], Math.min(iArrP[1], f3));
        if (Math.abs(this.f5598n - fMax) < 2.0f) {
            return;
        }
        int iX = x(this.f5599o, fMax, iArrP, this.f5605u.computeVerticalScrollRange(), this.f5605u.computeVerticalScrollOffset(), this.f5604t);
        if (iX != 0) {
            this.f5605u.scrollBy(0, iX);
        }
        this.f5599o = fMax;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i3 = this.f5608x;
        if (i3 == 1) {
            boolean zU = u(motionEvent.getX(), motionEvent.getY());
            boolean zT = t(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!zU && !zT) {
                return false;
            }
            if (zT) {
                this.f5609y = 1;
                this.f5602r = (int) motionEvent.getX();
            } else if (zU) {
                this.f5609y = 2;
                this.f5599o = (int) motionEvent.getY();
            }
            y(2);
        } else if (i3 != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void b(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.f5608x == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean zU = u(motionEvent.getX(), motionEvent.getY());
            boolean zT = t(motionEvent.getX(), motionEvent.getY());
            if (zU || zT) {
                if (zT) {
                    this.f5609y = 1;
                    this.f5602r = (int) motionEvent.getX();
                } else if (zU) {
                    this.f5609y = 2;
                    this.f5599o = (int) motionEvent.getY();
                }
                y(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.f5608x == 2) {
            this.f5599o = 0.0f;
            this.f5602r = 0.0f;
            y(1);
            this.f5609y = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.f5608x == 2) {
            A();
            if (this.f5609y == 1) {
                r(motionEvent.getX());
            }
            if (this.f5609y == 2) {
                C(motionEvent.getY());
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void c(boolean z3) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void i(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        if (this.f5603s != this.f5605u.getWidth() || this.f5604t != this.f5605u.getHeight()) {
            this.f5603s = this.f5605u.getWidth();
            this.f5604t = this.f5605u.getHeight();
            y(0);
        } else if (this.C != 0) {
            if (this.f5606v) {
                n(canvas);
            }
            if (this.f5607w) {
                m(canvas);
            }
        }
    }

    public void j(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f5605u;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            l();
        }
        this.f5605u = recyclerView;
        if (recyclerView != null) {
            z();
        }
    }

    public final void k() {
        this.f5605u.removeCallbacks(this.D);
    }

    public final void l() {
        this.f5605u.W0(this);
        this.f5605u.X0(this);
        this.f5605u.Y0(this.E);
        k();
    }

    public final void m(Canvas canvas) {
        int i3 = this.f5604t;
        int i4 = this.f5595k;
        int i5 = this.f5601q;
        int i6 = this.f5600p;
        this.f5593i.setBounds(0, 0, i6, i4);
        this.f5594j.setBounds(0, 0, this.f5603s, this.f5596l);
        canvas.translate(0.0f, i3 - i4);
        this.f5594j.draw(canvas);
        canvas.translate(i5 - (i6 / 2), 0.0f);
        this.f5593i.draw(canvas);
        canvas.translate(-r2, -r0);
    }

    public final void n(Canvas canvas) {
        int i3 = this.f5603s;
        int i4 = this.f5591g;
        int i5 = i3 - i4;
        int i6 = this.f5598n;
        int i7 = this.f5597m;
        int i8 = i6 - (i7 / 2);
        this.f5589e.setBounds(0, 0, i4, i7);
        this.f5590f.setBounds(0, 0, this.f5592h, this.f5604t);
        if (!s()) {
            canvas.translate(i5, 0.0f);
            this.f5590f.draw(canvas);
            canvas.translate(0.0f, i8);
            this.f5589e.draw(canvas);
            canvas.translate(-i5, -i8);
            return;
        }
        this.f5590f.draw(canvas);
        canvas.translate(this.f5591g, i8);
        canvas.scale(-1.0f, 1.0f);
        this.f5589e.draw(canvas);
        canvas.scale(1.0f, 1.0f);
        canvas.translate(-this.f5591g, -i8);
    }

    public final int[] o() {
        int[] iArr = this.A;
        int i3 = this.f5588d;
        iArr[0] = i3;
        iArr[1] = this.f5603s - i3;
        return iArr;
    }

    public final int[] p() {
        int[] iArr = this.f5610z;
        int i3 = this.f5588d;
        iArr[0] = i3;
        iArr[1] = this.f5604t - i3;
        return iArr;
    }

    public void q(int i3) {
        int i4 = this.C;
        if (i4 == 1) {
            this.B.cancel();
        } else if (i4 != 2) {
            return;
        }
        this.C = 3;
        ValueAnimator valueAnimator = this.B;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.B.setDuration(i3);
        this.B.start();
    }

    public final void r(float f3) {
        int[] iArrO = o();
        float fMax = Math.max(iArrO[0], Math.min(iArrO[1], f3));
        if (Math.abs(this.f5601q - fMax) < 2.0f) {
            return;
        }
        int iX = x(this.f5602r, fMax, iArrO, this.f5605u.computeHorizontalScrollRange(), this.f5605u.computeHorizontalScrollOffset(), this.f5603s);
        if (iX != 0) {
            this.f5605u.scrollBy(iX, 0);
        }
        this.f5602r = fMax;
    }

    public final boolean s() {
        return t.z(this.f5605u) == 1;
    }

    public boolean t(float f3, float f4) {
        if (f4 >= this.f5604t - this.f5595k) {
            int i3 = this.f5601q;
            int i4 = this.f5600p;
            if (f3 >= i3 - (i4 / 2) && f3 <= i3 + (i4 / 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean u(float f3, float f4) {
        if (!s() ? f3 >= this.f5603s - this.f5591g : f3 <= this.f5591g / 2) {
            int i3 = this.f5598n;
            int i4 = this.f5597m;
            if (f4 >= i3 - (i4 / 2) && f4 <= i3 + (i4 / 2)) {
                return true;
            }
        }
        return false;
    }

    public void v() {
        this.f5605u.invalidate();
    }

    public final void w(int i3) {
        k();
        this.f5605u.postDelayed(this.D, i3);
    }

    public final int x(float f3, float f4, int[] iArr, int i3, int i4, int i5) {
        int i6 = iArr[1] - iArr[0];
        if (i6 == 0) {
            return 0;
        }
        int i7 = i3 - i5;
        int i8 = (int) (((f4 - f3) / i6) * i7);
        int i9 = i4 + i8;
        if (i9 >= i7 || i9 < 0) {
            return 0;
        }
        return i8;
    }

    public void y(int i3) {
        if (i3 == 2 && this.f5608x != 2) {
            this.f5589e.setState(f5585a);
            k();
        }
        if (i3 == 0) {
            v();
        } else {
            A();
        }
        if (this.f5608x == 2 && i3 != 2) {
            this.f5589e.setState(f5586b);
            w(1200);
        } else if (i3 == 1) {
            w(1500);
        }
        this.f5608x = i3;
    }

    public final void z() {
        this.f5605u.h(this);
        this.f5605u.k(this);
        this.f5605u.l(this.E);
    }
}
