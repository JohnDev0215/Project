package defpackage;

import android.R;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: q5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1363q5 extends S5 implements Y5 {
    public static final int[] D = {R.attr.state_pressed};
    public static final int[] E = new int[0];
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
    public int q = 0;
    public int r = 0;
    public boolean t = false;
    public boolean u = false;
    public int v = 0;
    public int w = 0;
    public final int[] x = new int[2];
    public final int[] y = new int[2];
    public final ValueAnimator z = ValueAnimator.ofFloat(0.0f, 1.0f);
    public int A = 0;
    public final Runnable B = new RunnableC1143m5(this);
    public final Z5 C = new C1198n5(this);

    public C1363q5(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
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
        this.c.setAlpha(255);
        this.d.setAlpha(255);
        this.z.addListener(new C1253o5(this));
        this.z.addUpdateListener(new C1308p5(this));
        RecyclerView recyclerView2 = this.s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            W5 w5 = recyclerView2.B;
            if (w5 != null) {
                w5.a("Cannot remove item decoration during a scroll  or layout");
            }
            recyclerView2.C.remove(this);
            if (recyclerView2.C.isEmpty()) {
                recyclerView2.setWillNotDraw(recyclerView2.getOverScrollMode() == 2);
            }
            recyclerView2.v();
            recyclerView2.requestLayout();
            RecyclerView recyclerView3 = this.s;
            recyclerView3.D.remove(this);
            if (recyclerView3.E == this) {
                recyclerView3.E = null;
            }
            RecyclerView recyclerView4 = this.s;
            Z5 z5 = this.C;
            List list = recyclerView4.s0;
            if (list != null) {
                list.remove(z5);
            }
            a();
        }
        this.s = recyclerView;
        W5 w52 = recyclerView.B;
        if (w52 != null) {
            w52.a("Cannot add item decoration during a scroll  or layout");
        }
        if (recyclerView.C.isEmpty()) {
            recyclerView.setWillNotDraw(false);
        }
        recyclerView.C.add(this);
        recyclerView.v();
        recyclerView.requestLayout();
        this.s.D.add(this);
        RecyclerView recyclerView5 = this.s;
        Z5 z52 = this.C;
        if (recyclerView5.s0 == null) {
            recyclerView5.s0 = new ArrayList();
        }
        recyclerView5.s0.add(z52);
    }

    public final void a() {
        this.s.removeCallbacks(this.B);
    }

    public boolean b(float f, float f2) {
        if (f2 >= this.r - this.i) {
            int i = this.o;
            int i2 = this.n;
            if (f >= i - (i2 / 2) && f <= (i2 / 2) + i) {
                return true;
            }
        }
        return false;
    }

    public boolean c(float f, float f2) {
        if (AbstractC1251o3.k(this.s) == 1) {
            if (f > this.e / 2) {
                return false;
            }
        } else if (f < this.q - this.e) {
            return false;
        }
        int i = this.l;
        int i2 = this.k / 2;
        return f2 >= ((float) (i - i2)) && f2 <= ((float) (i2 + i));
    }

    public boolean d(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i = this.v;
        if (i == 1) {
            boolean zC = c(motionEvent.getX(), motionEvent.getY());
            boolean zB = b(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() == 0 && (zC || zB)) {
                if (zB) {
                    this.w = 1;
                    this.p = (int) motionEvent.getX();
                } else if (zC) {
                    this.w = 2;
                    this.m = (int) motionEvent.getY();
                }
                f(2);
                return true;
            }
        } else if (i == 2) {
            return true;
        }
        return false;
    }

    public final int e(float f, float f2, int[] iArr, int i, int i2, int i3) {
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

    public void f(int i) {
        if (i == 2 && this.v != 2) {
            this.c.setState(D);
            a();
        }
        if (i == 0) {
            this.s.invalidate();
        } else {
            g();
        }
        if (this.v == 2 && i != 2) {
            this.c.setState(E);
            a();
            this.s.postDelayed(this.B, 1200);
        } else if (i == 1) {
            a();
            this.s.postDelayed(this.B, 1500);
        }
        this.v = i;
    }

    public void g() {
        int i = this.A;
        if (i != 0) {
            if (i != 3) {
                return;
            } else {
                this.z.cancel();
            }
        }
        this.A = 1;
        ValueAnimator valueAnimator = this.z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.z.setDuration(500L);
        this.z.setStartDelay(0L);
        this.z.start();
    }
}
