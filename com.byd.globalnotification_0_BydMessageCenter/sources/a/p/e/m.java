package a.p.e;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class m extends RecyclerView.l implements RecyclerView.q {
    public static final int[] D = {R.attr.state_pressed};
    public static final int[] E = new int[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1205a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1206b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final StateListDrawable f1207c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Drawable f1208d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1209e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1210f;
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
    public final Runnable B = new a();
    public final RecyclerView.r C = new b();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            m mVar = m.this;
            int i = mVar.A;
            if (i == 1) {
                mVar.z.cancel();
            } else if (i != 2) {
                return;
            }
            mVar.A = 3;
            ValueAnimator valueAnimator = mVar.z;
            valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
            mVar.z.setDuration(500);
            mVar.z.start();
        }
    }

    public class b extends RecyclerView.r {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public void b(RecyclerView recyclerView, int i, int i2) {
            m mVar = m.this;
            int iComputeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
            int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
            int iComputeVerticalScrollRange = mVar.s.computeVerticalScrollRange();
            int i3 = mVar.r;
            mVar.t = iComputeVerticalScrollRange - i3 > 0 && i3 >= mVar.f1205a;
            int iComputeHorizontalScrollRange = mVar.s.computeHorizontalScrollRange();
            int i4 = mVar.q;
            boolean z = iComputeHorizontalScrollRange - i4 > 0 && i4 >= mVar.f1205a;
            mVar.u = z;
            if (!mVar.t && !z) {
                if (mVar.v != 0) {
                    mVar.m(0);
                    return;
                }
                return;
            }
            if (mVar.t) {
                float f2 = i3;
                mVar.l = (int) ((((f2 / 2.0f) + iComputeVerticalScrollOffset) * f2) / iComputeVerticalScrollRange);
                mVar.k = Math.min(i3, (i3 * i3) / iComputeVerticalScrollRange);
            }
            if (mVar.u) {
                float f3 = iComputeHorizontalScrollOffset;
                float f4 = i4;
                mVar.o = (int) ((((f4 / 2.0f) + f3) * f4) / iComputeHorizontalScrollRange);
                mVar.n = Math.min(i4, (i4 * i4) / iComputeHorizontalScrollRange);
            }
            int i5 = mVar.v;
            if (i5 == 0 || i5 == 1) {
                mVar.m(1);
            }
        }
    }

    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1213a = false;

        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1213a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f1213a) {
                this.f1213a = false;
                return;
            }
            float fFloatValue = ((Float) m.this.z.getAnimatedValue()).floatValue();
            m mVar = m.this;
            if (fFloatValue == 0.0f) {
                mVar.A = 0;
                mVar.m(0);
            } else {
                mVar.A = 2;
                mVar.s.invalidate();
            }
        }
    }

    public class d implements ValueAnimator.AnimatorUpdateListener {
        public d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            m.this.f1207c.setAlpha(iFloatValue);
            m.this.f1208d.setAlpha(iFloatValue);
            m.this.s.invalidate();
        }
    }

    public m(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        this.f1207c = stateListDrawable;
        this.f1208d = drawable;
        this.g = stateListDrawable2;
        this.h = drawable2;
        this.f1209e = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.f1210f = Math.max(i, drawable.getIntrinsicWidth());
        this.i = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.j = Math.max(i, drawable2.getIntrinsicWidth());
        this.f1205a = i2;
        this.f1206b = i3;
        this.f1207c.setAlpha(255);
        this.f1208d.setAlpha(255);
        this.z.addListener(new c());
        this.z.addUpdateListener(new d());
        RecyclerView recyclerView2 = this.s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            recyclerView2.f0(this);
            RecyclerView recyclerView3 = this.s;
            recyclerView3.p.remove(this);
            if (recyclerView3.q == this) {
                recyclerView3.q = null;
            }
            RecyclerView recyclerView4 = this.s;
            RecyclerView.r rVar = this.C;
            List<RecyclerView.r> list = recyclerView4.g0;
            if (list != null) {
                list.remove(rVar);
            }
            i();
        }
        this.s = recyclerView;
        recyclerView.g(this);
        this.s.p.add(this);
        this.s.h(this.C);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public void b(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public boolean c(RecyclerView recyclerView, MotionEvent motionEvent) {
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
                m(2);
                return true;
            }
        } else if (i == 2) {
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public void d(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.v == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean zK = k(motionEvent.getX(), motionEvent.getY());
            boolean zJ = j(motionEvent.getX(), motionEvent.getY());
            if (zK || zJ) {
                if (zJ) {
                    this.w = 1;
                    this.p = (int) motionEvent.getX();
                } else if (zK) {
                    this.w = 2;
                    this.m = (int) motionEvent.getY();
                }
                m(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.v == 2) {
            this.m = 0.0f;
            this.p = 0.0f;
            m(1);
            this.w = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.v == 2) {
            n();
            if (this.w == 1) {
                float x = motionEvent.getX();
                int[] iArr = this.y;
                int i = this.f1206b;
                iArr[0] = i;
                iArr[1] = this.q - i;
                float fMax = Math.max(iArr[0], Math.min(iArr[1], x));
                if (Math.abs(this.o - fMax) >= 2.0f) {
                    int iL = l(this.p, fMax, iArr, this.s.computeHorizontalScrollRange(), this.s.computeHorizontalScrollOffset(), this.q);
                    if (iL != 0) {
                        this.s.scrollBy(iL, 0);
                    }
                    this.p = fMax;
                }
            }
            if (this.w == 2) {
                float y = motionEvent.getY();
                int[] iArr2 = this.x;
                int i2 = this.f1206b;
                iArr2[0] = i2;
                iArr2[1] = this.r - i2;
                float fMax2 = Math.max(iArr2[0], Math.min(iArr2[1], y));
                if (Math.abs(this.l - fMax2) < 2.0f) {
                    return;
                }
                int iL2 = l(this.m, fMax2, iArr2, this.s.computeVerticalScrollRange(), this.s.computeVerticalScrollOffset(), this.r);
                if (iL2 != 0) {
                    this.s.scrollBy(0, iL2);
                }
                this.m = fMax2;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void h(Canvas canvas, RecyclerView recyclerView, RecyclerView.y yVar) {
        if (this.q != this.s.getWidth() || this.r != this.s.getHeight()) {
            this.q = this.s.getWidth();
            this.r = this.s.getHeight();
            m(0);
            return;
        }
        if (this.A != 0) {
            if (this.t) {
                int i = this.q;
                int i2 = this.f1209e;
                int i3 = i - i2;
                int i4 = this.l;
                int i5 = this.k;
                int i6 = i4 - (i5 / 2);
                this.f1207c.setBounds(0, 0, i2, i5);
                this.f1208d.setBounds(0, 0, this.f1210f, this.r);
                if (a.h.k.q.p(this.s) == 1) {
                    this.f1208d.draw(canvas);
                    canvas.translate(this.f1209e, i6);
                    canvas.scale(-1.0f, 1.0f);
                    this.f1207c.draw(canvas);
                    canvas.scale(1.0f, 1.0f);
                    i3 = this.f1209e;
                } else {
                    canvas.translate(i3, 0.0f);
                    this.f1208d.draw(canvas);
                    canvas.translate(0.0f, i6);
                    this.f1207c.draw(canvas);
                }
                canvas.translate(-i3, -i6);
            }
            if (this.u) {
                int i7 = this.r;
                int i8 = this.i;
                int i9 = this.o;
                int i10 = this.n;
                this.g.setBounds(0, 0, i10, i8);
                this.h.setBounds(0, 0, this.q, this.j);
                canvas.translate(0.0f, i7 - i8);
                this.h.draw(canvas);
                canvas.translate(i9 - (i10 / 2), 0.0f);
                this.g.draw(canvas);
                canvas.translate(-r2, -r7);
            }
        }
    }

    public final void i() {
        this.s.removeCallbacks(this.B);
    }

    public boolean j(float f2, float f3) {
        if (f3 >= this.r - this.i) {
            int i = this.o;
            int i2 = this.n;
            if (f2 >= i - (i2 / 2) && f2 <= (i2 / 2) + i) {
                return true;
            }
        }
        return false;
    }

    public boolean k(float f2, float f3) {
        if (a.h.k.q.p(this.s) == 1) {
            if (f2 > this.f1209e / 2) {
                return false;
            }
        } else if (f2 < this.q - this.f1209e) {
            return false;
        }
        int i = this.l;
        int i2 = this.k / 2;
        return f3 >= ((float) (i - i2)) && f3 <= ((float) (i2 + i));
    }

    public final int l(float f2, float f3, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 == 0) {
            return 0;
        }
        int i5 = i - i3;
        int i6 = (int) (((f3 - f2) / i4) * i5);
        int i7 = i2 + i6;
        if (i7 >= i5 || i7 < 0) {
            return 0;
        }
        return i6;
    }

    public void m(int i) {
        int i2;
        if (i == 2 && this.v != 2) {
            this.f1207c.setState(D);
            i();
        }
        if (i == 0) {
            this.s.invalidate();
        } else {
            n();
        }
        if (this.v != 2 || i == 2) {
            i2 = i == 1 ? 1500 : 1200;
            this.v = i;
        }
        this.f1207c.setState(E);
        i();
        this.s.postDelayed(this.B, i2);
        this.v = i;
    }

    public void n() {
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
