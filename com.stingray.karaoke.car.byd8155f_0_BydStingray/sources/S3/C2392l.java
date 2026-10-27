package s3;

import android.R;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: renamed from: s3.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2392l {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final int[] f22295C = {R.attr.state_pressed};

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final int[] f22296D = new int[0];

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f22297A;
    public final RunnableC2389i B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f22298a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22299b;
    public final StateListDrawable c;
    public final Drawable d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f22300e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f22301f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final StateListDrawable f22302g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Drawable f22303h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22304i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f22305j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f22306k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f22307m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22308n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f22309p;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final RecyclerView f22312s;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final ValueAnimator f22319z;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f22310q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f22311r = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f22313t = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f22314u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f22315v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f22316w = 0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int[] f22317x = new int[2];

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int[] f22318y = new int[2];

    public C2392l(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i7, int i10, int i11) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f22319z = valueAnimatorOfFloat;
        this.f22297A = 0;
        RunnableC2389i runnableC2389i = new RunnableC2389i(this, 0);
        this.B = runnableC2389i;
        C2390j c2390j = new C2390j(this);
        this.c = stateListDrawable;
        this.d = drawable;
        this.f22302g = stateListDrawable2;
        this.f22303h = drawable2;
        this.f22300e = Math.max(i7, stateListDrawable.getIntrinsicWidth());
        this.f22301f = Math.max(i7, drawable.getIntrinsicWidth());
        this.f22304i = Math.max(i7, stateListDrawable2.getIntrinsicWidth());
        this.f22305j = Math.max(i7, drawable2.getIntrinsicWidth());
        this.f22298a = i10;
        this.f22299b = i11;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new C2391k(this));
        valueAnimatorOfFloat.addUpdateListener(new P4.b(this, 3));
        RecyclerView recyclerView2 = this.f22312s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            AbstractC2367F abstractC2367F = recyclerView2.f12157x;
            if (abstractC2367F != null) {
                abstractC2367F.c("Cannot remove item decoration during a scroll  or layout");
            }
            ArrayList arrayList = recyclerView2.f12161z;
            arrayList.remove(this);
            if (arrayList.isEmpty()) {
                recyclerView2.setWillNotDraw(recyclerView2.getOverScrollMode() == 2);
            }
            recyclerView2.L();
            recyclerView2.requestLayout();
            RecyclerView recyclerView3 = this.f22312s;
            recyclerView3.f12097A.remove(this);
            if (recyclerView3.B == this) {
                recyclerView3.B = null;
            }
            ArrayList arrayList2 = this.f22312s.f12144q0;
            if (arrayList2 != null) {
                arrayList2.remove(c2390j);
            }
            this.f22312s.removeCallbacks(runnableC2389i);
        }
        this.f22312s = recyclerView;
        if (recyclerView != null) {
            AbstractC2367F abstractC2367F2 = recyclerView.f12157x;
            if (abstractC2367F2 != null) {
                abstractC2367F2.c("Cannot add item decoration during a scroll  or layout");
            }
            ArrayList arrayList3 = recyclerView.f12161z;
            if (arrayList3.isEmpty()) {
                recyclerView.setWillNotDraw(false);
            }
            arrayList3.add(this);
            recyclerView.L();
            recyclerView.requestLayout();
            this.f22312s.f12097A.add(this);
            RecyclerView recyclerView4 = this.f22312s;
            if (recyclerView4.f12144q0 == null) {
                recyclerView4.f12144q0 = new ArrayList();
            }
            recyclerView4.f12144q0.add(c2390j);
        }
    }

    public static int d(float f10, float f11, int[] iArr, int i7, int i10, int i11) {
        int i12 = iArr[1] - iArr[0];
        if (i12 == 0) {
            return 0;
        }
        int i13 = i7 - i11;
        int i14 = (int) (((f11 - f10) / i12) * i13);
        int i15 = i10 + i14;
        if (i15 >= i13 || i15 < 0) {
            return 0;
        }
        return i14;
    }

    public final boolean a(float f10, float f11) {
        if (f11 >= this.f22311r - this.f22304i) {
            int i7 = this.o;
            int i10 = this.f22308n;
            if (f10 >= i7 - (i10 / 2) && f10 <= (i10 / 2) + i7) {
                return true;
            }
        }
        return false;
    }

    public final boolean b(float f10, float f11) {
        RecyclerView recyclerView = this.f22312s;
        int[] iArr = F1.H.f2303a;
        boolean z6 = recyclerView.getLayoutDirection() == 1;
        int i7 = this.f22300e;
        if (z6) {
            if (f10 > i7) {
                return false;
            }
        } else if (f10 < this.f22310q - i7) {
            return false;
        }
        int i10 = this.l;
        int i11 = this.f22306k / 2;
        return f11 >= ((float) (i10 - i11)) && f11 <= ((float) (i11 + i10));
    }

    public void c(RecyclerView recyclerView) {
    }

    public final void e(int i7) {
        RunnableC2389i runnableC2389i = this.B;
        StateListDrawable stateListDrawable = this.c;
        if (i7 == 2 && this.f22315v != 2) {
            stateListDrawable.setState(f22295C);
            this.f22312s.removeCallbacks(runnableC2389i);
        }
        if (i7 == 0) {
            this.f22312s.invalidate();
        } else {
            f();
        }
        if (this.f22315v == 2 && i7 != 2) {
            stateListDrawable.setState(f22296D);
            this.f22312s.removeCallbacks(runnableC2389i);
            this.f22312s.postDelayed(runnableC2389i, 1200);
        } else if (i7 == 1) {
            this.f22312s.removeCallbacks(runnableC2389i);
            this.f22312s.postDelayed(runnableC2389i, 1500);
        }
        this.f22315v = i7;
    }

    public final void f() {
        int i7 = this.f22297A;
        ValueAnimator valueAnimator = this.f22319z;
        if (i7 != 0) {
            if (i7 != 3) {
                return;
            } else {
                valueAnimator.cancel();
            }
        }
        this.f22297A = 1;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        valueAnimator.setDuration(500L);
        valueAnimator.setStartDelay(0L);
        valueAnimator.start();
    }
}
