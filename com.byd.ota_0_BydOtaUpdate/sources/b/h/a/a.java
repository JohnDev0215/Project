package b.h.a;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import b.f.m.s;
import java.util.Arrays;

/* JADX INFO: compiled from: ViewDragHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f1851a = new InterpolatorC0034a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1852b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1853c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f1855e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float[] f1856f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float[] f1857g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float[] f1858h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f1859i;
    public int[] j;
    public int[] k;
    public int l;
    public VelocityTracker m;
    public float n;
    public float o;
    public int p;
    public int q;
    public OverScroller r;
    public final c s;
    public View t;
    public boolean u;
    public final ViewGroup v;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1854d = -1;
    public final Runnable w = new b();

    /* JADX INFO: renamed from: b.h.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewDragHelper.java */
    public static class InterpolatorC0034a implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    /* JADX INFO: compiled from: ViewDragHelper.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.J(0);
        }
    }

    /* JADX INFO: compiled from: ViewDragHelper.java */
    public static abstract class c {
        public abstract int a(View view, int i2, int i3);

        public abstract int b(View view, int i2, int i3);

        public int c(int i2) {
            return i2;
        }

        public abstract int d(View view);

        public int e(View view) {
            return 0;
        }

        public abstract void f(int i2, int i3);

        public abstract boolean g(int i2);

        public abstract void h(int i2, int i3);

        public abstract void i(View view, int i2);

        public abstract void j(int i2);

        public abstract void k(View view, int i2, int i3, int i4, int i5);

        public abstract void l(View view, float f2, float f3);

        public abstract boolean m(View view, int i2);
    }

    public a(Context context, ViewGroup viewGroup, c cVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (cVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.v = viewGroup;
        this.s = cVar;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.p = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.f1853c = viewConfiguration.getScaledTouchSlop();
        this.n = viewConfiguration.getScaledMaximumFlingVelocity();
        this.o = viewConfiguration.getScaledMinimumFlingVelocity();
        this.r = new OverScroller(context, f1851a);
    }

    public static a n(ViewGroup viewGroup, float f2, c cVar) {
        a aVarO = o(viewGroup, cVar);
        aVarO.f1853c = (int) (aVarO.f1853c * (1.0f / f2));
        return aVarO;
    }

    public static a o(ViewGroup viewGroup, c cVar) {
        return new a(viewGroup.getContext(), viewGroup, cVar);
    }

    public boolean A(int i2, int i3) {
        return D(this.t, i2, i3);
    }

    public boolean B(int i2) {
        return ((1 << i2) & this.l) != 0;
    }

    public final boolean C(int i2) {
        if (B(i2)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i2 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    public boolean D(View view, int i2, int i3) {
        return view != null && i2 >= view.getLeft() && i2 < view.getRight() && i3 >= view.getTop() && i3 < view.getBottom();
    }

    public void E(MotionEvent motionEvent) {
        int i2;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            a();
        }
        if (this.m == null) {
            this.m = VelocityTracker.obtain();
        }
        this.m.addMovement(motionEvent);
        int i3 = 0;
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewT = t((int) x, (int) y);
            H(x, y, pointerId);
            P(viewT, pointerId);
            int i4 = this.f1859i[pointerId];
            int i5 = this.q;
            if ((i4 & i5) != 0) {
                this.s.h(i4 & i5, pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.f1852b == 1) {
                F();
            }
            a();
            return;
        }
        if (actionMasked == 2) {
            if (this.f1852b == 1) {
                if (C(this.f1854d)) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.f1854d);
                    float x2 = motionEvent.getX(iFindPointerIndex);
                    float y2 = motionEvent.getY(iFindPointerIndex);
                    float[] fArr = this.f1857g;
                    int i6 = this.f1854d;
                    int i7 = (int) (x2 - fArr[i6]);
                    int i8 = (int) (y2 - this.f1858h[i6]);
                    r(this.t.getLeft() + i7, this.t.getTop() + i8, i7, i8);
                    I(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (i3 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(i3);
                if (C(pointerId2)) {
                    float x3 = motionEvent.getX(i3);
                    float y3 = motionEvent.getY(i3);
                    float f2 = x3 - this.f1855e[pointerId2];
                    float f3 = y3 - this.f1856f[pointerId2];
                    G(f2, f3, pointerId2);
                    if (this.f1852b != 1) {
                        View viewT2 = t((int) x3, (int) y3);
                        if (f(viewT2, f2, f3) && P(viewT2, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i3++;
            }
            I(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.f1852b == 1) {
                p(0.0f, 0.0f);
            }
            a();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x4 = motionEvent.getX(actionIndex);
            float y4 = motionEvent.getY(actionIndex);
            H(x4, y4, pointerId3);
            if (this.f1852b != 0) {
                if (A((int) x4, (int) y4)) {
                    P(this.t, pointerId3);
                    return;
                }
                return;
            } else {
                P(t((int) x4, (int) y4), pointerId3);
                int i9 = this.f1859i[pointerId3];
                int i10 = this.q;
                if ((i9 & i10) != 0) {
                    this.s.h(i9 & i10, pointerId3);
                    return;
                }
                return;
            }
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.f1852b == 1 && pointerId4 == this.f1854d) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i3 >= pointerCount2) {
                    i2 = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i3);
                if (pointerId5 != this.f1854d) {
                    View viewT3 = t((int) motionEvent.getX(i3), (int) motionEvent.getY(i3));
                    View view = this.t;
                    if (viewT3 == view && P(view, pointerId5)) {
                        i2 = this.f1854d;
                        break;
                    }
                }
                i3++;
            }
            if (i2 == -1) {
                F();
            }
        }
        j(pointerId4);
    }

    public final void F() {
        this.m.computeCurrentVelocity(1000, this.n);
        p(g(this.m.getXVelocity(this.f1854d), this.o, this.n), g(this.m.getYVelocity(this.f1854d), this.o, this.n));
    }

    public final void G(float f2, float f3, int i2) {
        int i3 = c(f2, f3, i2, 1) ? 1 : 0;
        if (c(f3, f2, i2, 4)) {
            i3 |= 4;
        }
        if (c(f2, f3, i2, 2)) {
            i3 |= 2;
        }
        if (c(f3, f2, i2, 8)) {
            i3 |= 8;
        }
        if (i3 != 0) {
            int[] iArr = this.j;
            iArr[i2] = iArr[i2] | i3;
            this.s.f(i3, i2);
        }
    }

    public final void H(float f2, float f3, int i2) {
        s(i2);
        float[] fArr = this.f1855e;
        this.f1857g[i2] = f2;
        fArr[i2] = f2;
        float[] fArr2 = this.f1856f;
        this.f1858h[i2] = f3;
        fArr2[i2] = f3;
        this.f1859i[i2] = x((int) f2, (int) f3);
        this.l |= 1 << i2;
    }

    public final void I(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i2 = 0; i2 < pointerCount; i2++) {
            int pointerId = motionEvent.getPointerId(i2);
            if (C(pointerId)) {
                float x = motionEvent.getX(i2);
                float y = motionEvent.getY(i2);
                this.f1857g[pointerId] = x;
                this.f1858h[pointerId] = y;
            }
        }
    }

    public void J(int i2) {
        this.v.removeCallbacks(this.w);
        if (this.f1852b != i2) {
            this.f1852b = i2;
            this.s.j(i2);
            if (this.f1852b == 0) {
                this.t = null;
            }
        }
    }

    public void K(int i2) {
        this.q = i2;
    }

    public void L(float f2) {
        this.o = f2;
    }

    public boolean M(int i2, int i3) {
        if (this.u) {
            return u(i2, i3, (int) this.m.getXVelocity(this.f1854d), (int) this.m.getYVelocity(this.f1854d));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean N(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.h.a.a.N(android.view.MotionEvent):boolean");
    }

    public boolean O(View view, int i2, int i3) {
        this.t = view;
        this.f1854d = -1;
        boolean zU = u(i2, i3, 0, 0);
        if (!zU && this.f1852b == 0 && this.t != null) {
            this.t = null;
        }
        return zU;
    }

    public boolean P(View view, int i2) {
        if (view == this.t && this.f1854d == i2) {
            return true;
        }
        if (view == null || !this.s.m(view, i2)) {
            return false;
        }
        this.f1854d = i2;
        b(view, i2);
        return true;
    }

    public void a() {
        this.f1854d = -1;
        i();
        VelocityTracker velocityTracker = this.m;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.m = null;
        }
    }

    public void b(View view, int i2) {
        if (view.getParent() == this.v) {
            this.t = view;
            this.f1854d = i2;
            this.s.i(view, i2);
            J(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.v + ")");
    }

    public final boolean c(float f2, float f3, int i2, int i3) {
        float fAbs = Math.abs(f2);
        float fAbs2 = Math.abs(f3);
        if ((this.f1859i[i2] & i3) != i3 || (this.q & i3) == 0 || (this.k[i2] & i3) == i3 || (this.j[i2] & i3) == i3) {
            return false;
        }
        int i4 = this.f1853c;
        if (fAbs <= i4 && fAbs2 <= i4) {
            return false;
        }
        if (fAbs >= fAbs2 * 0.5f || !this.s.g(i3)) {
            return (this.j[i2] & i3) == 0 && fAbs > ((float) this.f1853c);
        }
        int[] iArr = this.k;
        iArr[i2] = iArr[i2] | i3;
        return false;
    }

    public boolean d(int i2) {
        int length = this.f1855e.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (e(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean e(int i2, int i3) {
        if (!B(i3)) {
            return false;
        }
        boolean z = (i2 & 1) == 1;
        boolean z2 = (i2 & 2) == 2;
        float f2 = this.f1857g[i3] - this.f1855e[i3];
        float f3 = this.f1858h[i3] - this.f1856f[i3];
        if (!z || !z2) {
            return z ? Math.abs(f2) > ((float) this.f1853c) : z2 && Math.abs(f3) > ((float) this.f1853c);
        }
        float f4 = (f2 * f2) + (f3 * f3);
        int i4 = this.f1853c;
        return f4 > ((float) (i4 * i4));
    }

    public final boolean f(View view, float f2, float f3) {
        if (view == null) {
            return false;
        }
        boolean z = this.s.d(view) > 0;
        boolean z2 = this.s.e(view) > 0;
        if (!z || !z2) {
            return z ? Math.abs(f2) > ((float) this.f1853c) : z2 && Math.abs(f3) > ((float) this.f1853c);
        }
        float f4 = (f2 * f2) + (f3 * f3);
        int i2 = this.f1853c;
        return f4 > ((float) (i2 * i2));
    }

    public final float g(float f2, float f3, float f4) {
        float fAbs = Math.abs(f2);
        if (fAbs < f3) {
            return 0.0f;
        }
        return fAbs > f4 ? f2 > 0.0f ? f4 : -f4 : f2;
    }

    public final int h(int i2, int i3, int i4) {
        int iAbs = Math.abs(i2);
        if (iAbs < i3) {
            return 0;
        }
        return iAbs > i4 ? i2 > 0 ? i4 : -i4 : i2;
    }

    public final void i() {
        float[] fArr = this.f1855e;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.f1856f, 0.0f);
        Arrays.fill(this.f1857g, 0.0f);
        Arrays.fill(this.f1858h, 0.0f);
        Arrays.fill(this.f1859i, 0);
        Arrays.fill(this.j, 0);
        Arrays.fill(this.k, 0);
        this.l = 0;
    }

    public final void j(int i2) {
        if (this.f1855e == null || !B(i2)) {
            return;
        }
        this.f1855e[i2] = 0.0f;
        this.f1856f[i2] = 0.0f;
        this.f1857g[i2] = 0.0f;
        this.f1858h[i2] = 0.0f;
        this.f1859i[i2] = 0;
        this.j[i2] = 0;
        this.k[i2] = 0;
        this.l = (~(1 << i2)) & this.l;
    }

    public final int k(int i2, int i3, int i4) {
        if (i2 == 0) {
            return 0;
        }
        int width = this.v.getWidth();
        float f2 = width / 2;
        float fQ = f2 + (q(Math.min(1.0f, Math.abs(i2) / width)) * f2);
        int iAbs = Math.abs(i3);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fQ / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i2) / i4) + 1.0f) * 256.0f), 600);
    }

    public final int l(View view, int i2, int i3, int i4, int i5) {
        float f2;
        float f3;
        float f4;
        float f5;
        int iH = h(i4, (int) this.o, (int) this.n);
        int iH2 = h(i5, (int) this.o, (int) this.n);
        int iAbs = Math.abs(i2);
        int iAbs2 = Math.abs(i3);
        int iAbs3 = Math.abs(iH);
        int iAbs4 = Math.abs(iH2);
        int i6 = iAbs3 + iAbs4;
        int i7 = iAbs + iAbs2;
        if (iH != 0) {
            f2 = iAbs3;
            f3 = i6;
        } else {
            f2 = iAbs;
            f3 = i7;
        }
        float f6 = f2 / f3;
        if (iH2 != 0) {
            f4 = iAbs4;
            f5 = i6;
        } else {
            f4 = iAbs2;
            f5 = i7;
        }
        return (int) ((k(i2, iH, this.s.d(view)) * f6) + (k(i3, iH2, this.s.e(view)) * (f4 / f5)));
    }

    public boolean m(boolean z) {
        if (this.f1852b == 2) {
            boolean zComputeScrollOffset = this.r.computeScrollOffset();
            int currX = this.r.getCurrX();
            int currY = this.r.getCurrY();
            int left = currX - this.t.getLeft();
            int top = currY - this.t.getTop();
            if (left != 0) {
                s.J(this.t, left);
            }
            if (top != 0) {
                s.K(this.t, top);
            }
            if (left != 0 || top != 0) {
                this.s.k(this.t, currX, currY, left, top);
            }
            if (zComputeScrollOffset && currX == this.r.getFinalX() && currY == this.r.getFinalY()) {
                this.r.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                if (z) {
                    this.v.post(this.w);
                } else {
                    J(0);
                }
            }
        }
        return this.f1852b == 2;
    }

    public final void p(float f2, float f3) {
        this.u = true;
        this.s.l(this.t, f2, f3);
        this.u = false;
        if (this.f1852b == 1) {
            J(0);
        }
    }

    public final float q(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    public final void r(int i2, int i3, int i4, int i5) {
        int left = this.t.getLeft();
        int top = this.t.getTop();
        if (i4 != 0) {
            i2 = this.s.a(this.t, i2, i4);
            s.J(this.t, i2 - left);
        }
        int i6 = i2;
        if (i5 != 0) {
            i3 = this.s.b(this.t, i3, i5);
            s.K(this.t, i3 - top);
        }
        int i7 = i3;
        if (i4 == 0 && i5 == 0) {
            return;
        }
        this.s.k(this.t, i6, i7, i6 - left, i7 - top);
    }

    public final void s(int i2) {
        float[] fArr = this.f1855e;
        if (fArr == null || fArr.length <= i2) {
            int i3 = i2 + 1;
            float[] fArr2 = new float[i3];
            float[] fArr3 = new float[i3];
            float[] fArr4 = new float[i3];
            float[] fArr5 = new float[i3];
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f1856f;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f1857g;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.f1858h;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.f1859i;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.j;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.k;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f1855e = fArr2;
            this.f1856f = fArr3;
            this.f1857g = fArr4;
            this.f1858h = fArr5;
            this.f1859i = iArr;
            this.j = iArr2;
            this.k = iArr3;
        }
    }

    public View t(int i2, int i3) {
        for (int childCount = this.v.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.v.getChildAt(this.s.c(childCount));
            if (i2 >= childAt.getLeft() && i2 < childAt.getRight() && i3 >= childAt.getTop() && i3 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public final boolean u(int i2, int i3, int i4, int i5) {
        int left = this.t.getLeft();
        int top = this.t.getTop();
        int i6 = i2 - left;
        int i7 = i3 - top;
        if (i6 == 0 && i7 == 0) {
            this.r.abortAnimation();
            J(0);
            return false;
        }
        this.r.startScroll(left, top, i6, i7, l(this.t, i6, i7, i4, i5));
        J(2);
        return true;
    }

    public View v() {
        return this.t;
    }

    public int w() {
        return this.p;
    }

    public final int x(int i2, int i3) {
        int i4 = i2 < this.v.getLeft() + this.p ? 1 : 0;
        if (i3 < this.v.getTop() + this.p) {
            i4 |= 4;
        }
        if (i2 > this.v.getRight() - this.p) {
            i4 |= 2;
        }
        return i3 > this.v.getBottom() - this.p ? i4 | 8 : i4;
    }

    public int y() {
        return this.f1853c;
    }

    public int z() {
        return this.f1852b;
    }
}
