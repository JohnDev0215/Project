package androidx.recyclerview.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: FastScroller.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends RecyclerView.l implements RecyclerView.p {
    public static final int[] C = {R.attr.state_pressed};
    public static final int[] D = new int[0];
    public int A;
    public final a B;
    public final int a;
    public final int b;
    public final StateListDrawable c;
    public final Drawable d;
    public final int e;
    public final int f;
    public final StateListDrawable g;
    public final Drawable h;
    public final int i;
    public final int j;
    public int k;
    public int l;
    public float m;
    public int n;
    public int o;
    public float p;
    public RecyclerView s;
    public final ValueAnimator z;
    public int q = 0;
    public int r = 0;
    public boolean t = false;
    public boolean u = false;
    public int v = 0;
    public int w = 0;
    public final int[] x = new int[2];
    public final int[] y = new int[2];

    /* JADX INFO: compiled from: FastScroller.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            j jVar = j.this;
            int i = jVar.A;
            ValueAnimator valueAnimator = jVar.z;
            if (i == 1) {
                valueAnimator.cancel();
            } else if (i != 2) {
                return;
            }
            jVar.A = 3;
            valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
            valueAnimator.setDuration(500);
            valueAnimator.start();
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    public class b extends RecyclerView.q {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public final void b(RecyclerView recyclerView, int i, int i2) {
            int iComputeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
            int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
            j jVar = j.this;
            int iComputeVerticalScrollRange = jVar.s.computeVerticalScrollRange();
            int i3 = jVar.r;
            int i4 = iComputeVerticalScrollRange - i3;
            int i5 = jVar.a;
            jVar.t = i4 > 0 && i3 >= i5;
            int iComputeHorizontalScrollRange = jVar.s.computeHorizontalScrollRange();
            int i6 = jVar.q;
            boolean z = iComputeHorizontalScrollRange - i6 > 0 && i6 >= i5;
            jVar.u = z;
            boolean z2 = jVar.t;
            if (!z2 && !z) {
                if (jVar.v != 0) {
                    jVar.l(0);
                    return;
                }
                return;
            }
            if (z2) {
                float f = i3;
                jVar.l = (int) ((((f / 2.0f) + iComputeVerticalScrollOffset) * f) / iComputeVerticalScrollRange);
                jVar.k = Math.min(i3, (i3 * i3) / iComputeVerticalScrollRange);
            }
            if (jVar.u) {
                float f2 = iComputeHorizontalScrollOffset;
                float f3 = i6;
                jVar.o = (int) ((((f3 / 2.0f) + f2) * f3) / iComputeHorizontalScrollRange);
                jVar.n = Math.min(i6, (i6 * i6) / iComputeHorizontalScrollRange);
            }
            int i7 = jVar.v;
            if (i7 == 0 || i7 == 1) {
                jVar.l(1);
            }
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    public class c extends AnimatorListenerAdapter {
        public boolean e = false;

        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.e = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.e) {
                this.e = false;
                return;
            }
            j jVar = j.this;
            if (((Float) jVar.z.getAnimatedValue()).floatValue() == 0.0f) {
                jVar.A = 0;
                jVar.l(0);
            } else {
                jVar.A = 2;
                jVar.s.invalidate();
            }
        }
    }

    /* JADX INFO: compiled from: FastScroller.java */
    public class d implements ValueAnimator.AnimatorUpdateListener {
        public d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            j jVar = j.this;
            jVar.c.setAlpha(iFloatValue);
            jVar.d.setAlpha(iFloatValue);
            jVar.s.invalidate();
        }
    }

    public j(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.z = valueAnimatorOfFloat;
        this.A = 0;
        a aVar = new a();
        this.B = aVar;
        b bVar = new b();
        this.c = stateListDrawable;
        this.d = drawable;
        this.g = stateListDrawable2;
        this.h = drawable2;
        this.e = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.f = Math.max(i, drawable.getIntrinsicWidth());
        this.i = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.j = Math.max(i, drawable2.getIntrinsicWidth());
        this.a = i2;
        this.b = i3;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new c());
        valueAnimatorOfFloat.addUpdateListener(new d());
        RecyclerView recyclerView2 = this.s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            recyclerView2.c0(this);
            this.s.removeOnItemTouchListener(this);
            this.s.removeOnScrollListener(bVar);
            this.s.removeCallbacks(aVar);
        }
        this.s = recyclerView;
        if (recyclerView != null) {
            recyclerView.g(this);
            this.s.addOnItemTouchListener(this);
            this.s.addOnScrollListener(bVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0105  */
    @Override // androidx.recyclerview.widget.RecyclerView.p
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instruction units count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.j.a(android.view.MotionEvent):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public final boolean b(MotionEvent motionEvent) {
        int i = this.v;
        if (i == 1) {
            boolean zK = k(motionEvent.getX(), motionEvent.getY());
            boolean zJ = j(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() == 0 && (zK || zJ)) {
                if (zJ) {
                    this.w = 1;
                    this.p = (int) motionEvent.getX();
                } else if (zK) {
                    this.w = 2;
                    this.m = (int) motionEvent.getY();
                }
                l(2);
                return true;
            }
        } else if (i == 2) {
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public final void i(Canvas canvas, RecyclerView recyclerView) {
        if (this.q != this.s.getWidth() || this.r != this.s.getHeight()) {
            this.q = this.s.getWidth();
            this.r = this.s.getHeight();
            l(0);
            return;
        }
        if (this.A != 0) {
            if (this.t) {
                int i = this.q;
                int i2 = this.e;
                int i3 = i - i2;
                int i4 = this.l;
                int i5 = this.k;
                int i6 = i4 - (i5 / 2);
                StateListDrawable stateListDrawable = this.c;
                stateListDrawable.setBounds(0, 0, i2, i5);
                int i7 = this.r;
                int i8 = this.f;
                Drawable drawable = this.d;
                drawable.setBounds(0, 0, i8, i7);
                RecyclerView recyclerView2 = this.s;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                if (om3.e.c(recyclerView2) == 1) {
                    drawable.draw(canvas);
                    canvas.translate(i2, i6);
                    canvas.scale(-1.0f, 1.0f);
                    stateListDrawable.draw(canvas);
                    canvas.scale(-1.0f, 1.0f);
                    canvas.translate(-i2, -i6);
                } else {
                    canvas.translate(i3, 0.0f);
                    drawable.draw(canvas);
                    canvas.translate(0.0f, i6);
                    stateListDrawable.draw(canvas);
                    canvas.translate(-i3, -i6);
                }
            }
            if (this.u) {
                int i9 = this.r;
                int i10 = this.i;
                int i11 = i9 - i10;
                int i12 = this.o;
                int i13 = this.n;
                int i14 = i12 - (i13 / 2);
                StateListDrawable stateListDrawable2 = this.g;
                stateListDrawable2.setBounds(0, 0, i13, i10);
                int i15 = this.q;
                int i16 = this.j;
                Drawable drawable2 = this.h;
                drawable2.setBounds(0, 0, i15, i16);
                canvas.translate(0.0f, i11);
                drawable2.draw(canvas);
                canvas.translate(i14, 0.0f);
                stateListDrawable2.draw(canvas);
                canvas.translate(-i14, -i11);
            }
        }
    }

    public final boolean j(float f, float f2) {
        if (f2 >= this.r - this.i) {
            int i = this.o;
            int i2 = this.n;
            if (f >= i - (i2 / 2) && f <= (i2 / 2) + i) {
                return true;
            }
        }
        return false;
    }

    public final boolean k(float f, float f2) {
        RecyclerView recyclerView = this.s;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        boolean z = om3.e.c(recyclerView) == 1;
        int i = this.e;
        if (z) {
            if (f > i) {
                return false;
            }
        } else if (f < this.q - i) {
            return false;
        }
        int i2 = this.l;
        int i3 = this.k / 2;
        return f2 >= ((float) (i2 - i3)) && f2 <= ((float) (i3 + i2));
    }

    public final void l(int i) {
        a aVar = this.B;
        StateListDrawable stateListDrawable = this.c;
        if (i == 2 && this.v != 2) {
            stateListDrawable.setState(C);
            this.s.removeCallbacks(aVar);
        }
        if (i == 0) {
            this.s.invalidate();
        } else {
            m();
        }
        if (this.v == 2 && i != 2) {
            stateListDrawable.setState(D);
            this.s.removeCallbacks(aVar);
            this.s.postDelayed(aVar, 1200);
        } else if (i == 1) {
            this.s.removeCallbacks(aVar);
            this.s.postDelayed(aVar, 1500);
        }
        this.v = i;
    }

    public final void m() {
        int i = this.A;
        ValueAnimator valueAnimator = this.z;
        if (i != 0) {
            if (i != 3) {
                return;
            } else {
                valueAnimator.cancel();
            }
        }
        this.A = 1;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        valueAnimator.setDuration(500L);
        valueAnimator.setStartDelay(0L);
        valueAnimator.start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public final void e(boolean z) {
    }
}
