package t0;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;
import o0.t;

/* JADX INFO: compiled from: ViewDragHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f7971a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7972b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f7973c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f7975e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float[] f7976f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float[] f7977g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float[] f7978h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f7979i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f7980j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int[] f7981k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f7982l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public VelocityTracker f7983m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f7984n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f7985o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f7986p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f7987q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public OverScroller f7988r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AbstractC0123c f7989s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public View f7990t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f7991u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ViewGroup f7992v;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7974d = -1;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Runnable f7993w = new b();

    /* JADX INFO: compiled from: ViewDragHelper.java */
    public static class a implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f8) {
            float f9 = f8 - 1.0f;
            return (f9 * f9 * f9 * f9 * f9) + 1.0f;
        }
    }

    /* JADX INFO: compiled from: ViewDragHelper.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.K(0);
        }
    }

    /* JADX INFO: renamed from: t0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewDragHelper.java */
    public static abstract class AbstractC0123c {
        public abstract int a(View view, int i8, int i9);

        public abstract int b(View view, int i8, int i9);

        public int c(int i8) {
            return i8;
        }

        public int d(View view) {
            return 0;
        }

        public int e(View view) {
            return 0;
        }

        public void f(int i8, int i9) {
        }

        public boolean g(int i8) {
            return false;
        }

        public void h(int i8, int i9) {
        }

        public void i(View view, int i8) {
        }

        public abstract void j(int i8);

        public abstract void k(View view, int i8, int i9, int i10, int i11);

        public abstract void l(View view, float f8, float f9);

        public abstract boolean m(View view, int i8);
    }

    public c(Context context, ViewGroup viewGroup, AbstractC0123c abstractC0123c) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (abstractC0123c == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.f7992v = viewGroup;
        this.f7989s = abstractC0123c;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f7986p = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.f7973c = viewConfiguration.getScaledTouchSlop();
        this.f7984n = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f7985o = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f7988r = new OverScroller(context, f7971a);
    }

    public static c o(ViewGroup viewGroup, float f8, AbstractC0123c abstractC0123c) {
        c cVarP = p(viewGroup, abstractC0123c);
        cVarP.f7973c = (int) (cVarP.f7973c * (1.0f / f8));
        return cVarP;
    }

    public static c p(ViewGroup viewGroup, AbstractC0123c abstractC0123c) {
        return new c(viewGroup.getContext(), viewGroup, abstractC0123c);
    }

    public int A() {
        return this.f7972b;
    }

    public boolean B(int i8, int i9) {
        return E(this.f7990t, i8, i9);
    }

    public boolean C(int i8) {
        return ((1 << i8) & this.f7982l) != 0;
    }

    public final boolean D(int i8) {
        if (C(i8)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i8 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    public boolean E(View view, int i8, int i9) {
        return view != null && i8 >= view.getLeft() && i8 < view.getRight() && i9 >= view.getTop() && i9 < view.getBottom();
    }

    public void F(MotionEvent motionEvent) {
        int i8;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            b();
        }
        if (this.f7983m == null) {
            this.f7983m = VelocityTracker.obtain();
        }
        this.f7983m.addMovement(motionEvent);
        int i9 = 0;
        if (actionMasked == 0) {
            float x7 = motionEvent.getX();
            float y7 = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewU = u((int) x7, (int) y7);
            I(x7, y7, pointerId);
            Q(viewU, pointerId);
            int i10 = this.f7979i[pointerId];
            int i11 = this.f7987q;
            if ((i10 & i11) != 0) {
                this.f7989s.h(i10 & i11, pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.f7972b == 1) {
                G();
            }
            b();
            return;
        }
        if (actionMasked == 2) {
            if (this.f7972b == 1) {
                if (D(this.f7974d)) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.f7974d);
                    float x8 = motionEvent.getX(iFindPointerIndex);
                    float y8 = motionEvent.getY(iFindPointerIndex);
                    float[] fArr = this.f7977g;
                    int i12 = this.f7974d;
                    int i13 = (int) (x8 - fArr[i12]);
                    int i14 = (int) (y8 - this.f7978h[i12]);
                    s(this.f7990t.getLeft() + i13, this.f7990t.getTop() + i14, i13, i14);
                    J(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (i9 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(i9);
                if (D(pointerId2)) {
                    float x9 = motionEvent.getX(i9);
                    float y9 = motionEvent.getY(i9);
                    float f8 = x9 - this.f7975e[pointerId2];
                    float f9 = y9 - this.f7976f[pointerId2];
                    H(f8, f9, pointerId2);
                    if (this.f7972b != 1) {
                        View viewU2 = u((int) x9, (int) y9);
                        if (g(viewU2, f8, f9) && Q(viewU2, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i9++;
            }
            J(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.f7972b == 1) {
                q(0.0f, 0.0f);
            }
            b();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x10 = motionEvent.getX(actionIndex);
            float y10 = motionEvent.getY(actionIndex);
            I(x10, y10, pointerId3);
            if (this.f7972b != 0) {
                if (B((int) x10, (int) y10)) {
                    Q(this.f7990t, pointerId3);
                    return;
                }
                return;
            } else {
                Q(u((int) x10, (int) y10), pointerId3);
                int i15 = this.f7979i[pointerId3];
                int i16 = this.f7987q;
                if ((i15 & i16) != 0) {
                    this.f7989s.h(i15 & i16, pointerId3);
                    return;
                }
                return;
            }
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.f7972b == 1 && pointerId4 == this.f7974d) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i9 >= pointerCount2) {
                    i8 = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i9);
                if (pointerId5 != this.f7974d) {
                    View viewU3 = u((int) motionEvent.getX(i9), (int) motionEvent.getY(i9));
                    View view = this.f7990t;
                    if (viewU3 == view && Q(view, pointerId5)) {
                        i8 = this.f7974d;
                        break;
                    }
                }
                i9++;
            }
            if (i8 == -1) {
                G();
            }
        }
        k(pointerId4);
    }

    public final void G() {
        this.f7983m.computeCurrentVelocity(1000, this.f7984n);
        q(h(this.f7983m.getXVelocity(this.f7974d), this.f7985o, this.f7984n), h(this.f7983m.getYVelocity(this.f7974d), this.f7985o, this.f7984n));
    }

    public final void H(float f8, float f9, int i8) {
        int i9 = d(f8, f9, i8, 1) ? 1 : 0;
        if (d(f9, f8, i8, 4)) {
            i9 |= 4;
        }
        if (d(f8, f9, i8, 2)) {
            i9 |= 2;
        }
        if (d(f9, f8, i8, 8)) {
            i9 |= 8;
        }
        if (i9 != 0) {
            int[] iArr = this.f7980j;
            iArr[i8] = iArr[i8] | i9;
            this.f7989s.f(i9, i8);
        }
    }

    public final void I(float f8, float f9, int i8) {
        t(i8);
        float[] fArr = this.f7975e;
        this.f7977g[i8] = f8;
        fArr[i8] = f8;
        float[] fArr2 = this.f7976f;
        this.f7978h[i8] = f9;
        fArr2[i8] = f9;
        this.f7979i[i8] = y((int) f8, (int) f9);
        this.f7982l |= 1 << i8;
    }

    public final void J(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i8 = 0; i8 < pointerCount; i8++) {
            int pointerId = motionEvent.getPointerId(i8);
            if (D(pointerId)) {
                float x7 = motionEvent.getX(i8);
                float y7 = motionEvent.getY(i8);
                this.f7977g[pointerId] = x7;
                this.f7978h[pointerId] = y7;
            }
        }
    }

    public void K(int i8) {
        this.f7992v.removeCallbacks(this.f7993w);
        if (this.f7972b != i8) {
            this.f7972b = i8;
            this.f7989s.j(i8);
            if (this.f7972b == 0) {
                this.f7990t = null;
            }
        }
    }

    public void L(int i8) {
        this.f7987q = i8;
    }

    public void M(float f8) {
        this.f7985o = f8;
    }

    public boolean N(int i8, int i9) {
        if (this.f7991u) {
            return v(i8, i9, (int) this.f7983m.getXVelocity(this.f7974d), (int) this.f7983m.getYVelocity(this.f7974d));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean O(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: t0.c.O(android.view.MotionEvent):boolean");
    }

    public boolean P(View view, int i8, int i9) {
        this.f7990t = view;
        this.f7974d = -1;
        boolean zV = v(i8, i9, 0, 0);
        if (!zV && this.f7972b == 0 && this.f7990t != null) {
            this.f7990t = null;
        }
        return zV;
    }

    public boolean Q(View view, int i8) {
        if (view == this.f7990t && this.f7974d == i8) {
            return true;
        }
        if (view == null || !this.f7989s.m(view, i8)) {
            return false;
        }
        this.f7974d = i8;
        c(view, i8);
        return true;
    }

    public void a() {
        b();
        if (this.f7972b == 2) {
            int currX = this.f7988r.getCurrX();
            int currY = this.f7988r.getCurrY();
            this.f7988r.abortAnimation();
            int currX2 = this.f7988r.getCurrX();
            int currY2 = this.f7988r.getCurrY();
            this.f7989s.k(this.f7990t, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        K(0);
    }

    public void b() {
        this.f7974d = -1;
        j();
        VelocityTracker velocityTracker = this.f7983m;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f7983m = null;
        }
    }

    public void c(View view, int i8) {
        if (view.getParent() == this.f7992v) {
            this.f7990t = view;
            this.f7974d = i8;
            this.f7989s.i(view, i8);
            K(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.f7992v + ")");
    }

    public final boolean d(float f8, float f9, int i8, int i9) {
        float fAbs = Math.abs(f8);
        float fAbs2 = Math.abs(f9);
        if ((this.f7979i[i8] & i9) != i9 || (this.f7987q & i9) == 0 || (this.f7981k[i8] & i9) == i9 || (this.f7980j[i8] & i9) == i9) {
            return false;
        }
        int i10 = this.f7973c;
        if (fAbs <= i10 && fAbs2 <= i10) {
            return false;
        }
        if (fAbs >= fAbs2 * 0.5f || !this.f7989s.g(i9)) {
            return (this.f7980j[i8] & i9) == 0 && fAbs > ((float) this.f7973c);
        }
        int[] iArr = this.f7981k;
        iArr[i8] = iArr[i8] | i9;
        return false;
    }

    public boolean e(int i8) {
        int length = this.f7975e.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (f(i8, i9)) {
                return true;
            }
        }
        return false;
    }

    public boolean f(int i8, int i9) {
        if (!C(i9)) {
            return false;
        }
        boolean z7 = (i8 & 1) == 1;
        boolean z8 = (i8 & 2) == 2;
        float f8 = this.f7977g[i9] - this.f7975e[i9];
        float f9 = this.f7978h[i9] - this.f7976f[i9];
        if (!z7 || !z8) {
            return z7 ? Math.abs(f8) > ((float) this.f7973c) : z8 && Math.abs(f9) > ((float) this.f7973c);
        }
        float f10 = (f8 * f8) + (f9 * f9);
        int i10 = this.f7973c;
        return f10 > ((float) (i10 * i10));
    }

    public final boolean g(View view, float f8, float f9) {
        if (view == null) {
            return false;
        }
        boolean z7 = this.f7989s.d(view) > 0;
        boolean z8 = this.f7989s.e(view) > 0;
        if (!z7 || !z8) {
            return z7 ? Math.abs(f8) > ((float) this.f7973c) : z8 && Math.abs(f9) > ((float) this.f7973c);
        }
        float f10 = (f8 * f8) + (f9 * f9);
        int i8 = this.f7973c;
        return f10 > ((float) (i8 * i8));
    }

    public final float h(float f8, float f9, float f10) {
        float fAbs = Math.abs(f8);
        if (fAbs < f9) {
            return 0.0f;
        }
        return fAbs > f10 ? f8 > 0.0f ? f10 : -f10 : f8;
    }

    public final int i(int i8, int i9, int i10) {
        int iAbs = Math.abs(i8);
        if (iAbs < i9) {
            return 0;
        }
        return iAbs > i10 ? i8 > 0 ? i10 : -i10 : i8;
    }

    public final void j() {
        float[] fArr = this.f7975e;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.f7976f, 0.0f);
        Arrays.fill(this.f7977g, 0.0f);
        Arrays.fill(this.f7978h, 0.0f);
        Arrays.fill(this.f7979i, 0);
        Arrays.fill(this.f7980j, 0);
        Arrays.fill(this.f7981k, 0);
        this.f7982l = 0;
    }

    public final void k(int i8) {
        if (this.f7975e == null || !C(i8)) {
            return;
        }
        this.f7975e[i8] = 0.0f;
        this.f7976f[i8] = 0.0f;
        this.f7977g[i8] = 0.0f;
        this.f7978h[i8] = 0.0f;
        this.f7979i[i8] = 0;
        this.f7980j[i8] = 0;
        this.f7981k[i8] = 0;
        this.f7982l = (~(1 << i8)) & this.f7982l;
    }

    public final int l(int i8, int i9, int i10) {
        if (i8 == 0) {
            return 0;
        }
        int width = this.f7992v.getWidth();
        float f8 = width / 2;
        float fR = f8 + (r(Math.min(1.0f, Math.abs(i8) / width)) * f8);
        int iAbs = Math.abs(i9);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fR / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i8) / i10) + 1.0f) * 256.0f), 600);
    }

    public final int m(View view, int i8, int i9, int i10, int i11) {
        float f8;
        float f9;
        float f10;
        float f11;
        int i12 = i(i10, (int) this.f7985o, (int) this.f7984n);
        int i13 = i(i11, (int) this.f7985o, (int) this.f7984n);
        int iAbs = Math.abs(i8);
        int iAbs2 = Math.abs(i9);
        int iAbs3 = Math.abs(i12);
        int iAbs4 = Math.abs(i13);
        int i14 = iAbs3 + iAbs4;
        int i15 = iAbs + iAbs2;
        if (i12 != 0) {
            f8 = iAbs3;
            f9 = i14;
        } else {
            f8 = iAbs;
            f9 = i15;
        }
        float f12 = f8 / f9;
        if (i13 != 0) {
            f10 = iAbs4;
            f11 = i14;
        } else {
            f10 = iAbs2;
            f11 = i15;
        }
        return (int) ((l(i8, i12, this.f7989s.d(view)) * f12) + (l(i9, i13, this.f7989s.e(view)) * (f10 / f11)));
    }

    public boolean n(boolean z7) {
        if (this.f7972b == 2) {
            boolean zComputeScrollOffset = this.f7988r.computeScrollOffset();
            int currX = this.f7988r.getCurrX();
            int currY = this.f7988r.getCurrY();
            int left = currX - this.f7990t.getLeft();
            int top = currY - this.f7990t.getTop();
            if (left != 0) {
                t.V(this.f7990t, left);
            }
            if (top != 0) {
                t.W(this.f7990t, top);
            }
            if (left != 0 || top != 0) {
                this.f7989s.k(this.f7990t, currX, currY, left, top);
            }
            if (zComputeScrollOffset && currX == this.f7988r.getFinalX() && currY == this.f7988r.getFinalY()) {
                this.f7988r.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                if (z7) {
                    this.f7992v.post(this.f7993w);
                } else {
                    K(0);
                }
            }
        }
        return this.f7972b == 2;
    }

    public final void q(float f8, float f9) {
        this.f7991u = true;
        this.f7989s.l(this.f7990t, f8, f9);
        this.f7991u = false;
        if (this.f7972b == 1) {
            K(0);
        }
    }

    public final float r(float f8) {
        return (float) Math.sin((f8 - 0.5f) * 0.47123894f);
    }

    public final void s(int i8, int i9, int i10, int i11) {
        int left = this.f7990t.getLeft();
        int top = this.f7990t.getTop();
        if (i10 != 0) {
            i8 = this.f7989s.a(this.f7990t, i8, i10);
            t.V(this.f7990t, i8 - left);
        }
        int i12 = i8;
        if (i11 != 0) {
            i9 = this.f7989s.b(this.f7990t, i9, i11);
            t.W(this.f7990t, i9 - top);
        }
        int i13 = i9;
        if (i10 == 0 && i11 == 0) {
            return;
        }
        this.f7989s.k(this.f7990t, i12, i13, i12 - left, i13 - top);
    }

    public final void t(int i8) {
        float[] fArr = this.f7975e;
        if (fArr == null || fArr.length <= i8) {
            int i9 = i8 + 1;
            float[] fArr2 = new float[i9];
            float[] fArr3 = new float[i9];
            float[] fArr4 = new float[i9];
            float[] fArr5 = new float[i9];
            int[] iArr = new int[i9];
            int[] iArr2 = new int[i9];
            int[] iArr3 = new int[i9];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f7976f;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f7977g;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.f7978h;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.f7979i;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.f7980j;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.f7981k;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f7975e = fArr2;
            this.f7976f = fArr3;
            this.f7977g = fArr4;
            this.f7978h = fArr5;
            this.f7979i = iArr;
            this.f7980j = iArr2;
            this.f7981k = iArr3;
        }
    }

    public View u(int i8, int i9) {
        for (int childCount = this.f7992v.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.f7992v.getChildAt(this.f7989s.c(childCount));
            if (i8 >= childAt.getLeft() && i8 < childAt.getRight() && i9 >= childAt.getTop() && i9 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public final boolean v(int i8, int i9, int i10, int i11) {
        int left = this.f7990t.getLeft();
        int top = this.f7990t.getTop();
        int i12 = i8 - left;
        int i13 = i9 - top;
        if (i12 == 0 && i13 == 0) {
            this.f7988r.abortAnimation();
            K(0);
            return false;
        }
        this.f7988r.startScroll(left, top, i12, i13, m(this.f7990t, i12, i13, i10, i11));
        K(2);
        return true;
    }

    public View w() {
        return this.f7990t;
    }

    public int x() {
        return this.f7986p;
    }

    public final int y(int i8, int i9) {
        int i10 = i8 < this.f7992v.getLeft() + this.f7986p ? 1 : 0;
        if (i9 < this.f7992v.getTop() + this.f7986p) {
            i10 |= 4;
        }
        if (i8 > this.f7992v.getRight() - this.f7986p) {
            i10 |= 2;
        }
        return i9 > this.f7992v.getBottom() - this.f7986p ? i10 | 8 : i10;
    }

    public int z() {
        return this.f7973c;
    }
}
