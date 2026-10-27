package p0;

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
import l0.t;

/* JADX INFO: compiled from: ViewDragHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f4487a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4488b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4489c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f4491e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float[] f4492f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float[] f4493g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float[] f4494h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f4495i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f4496j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int[] f4497k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4498l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public VelocityTracker f4499m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f4500n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f4501o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4502p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4503q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public OverScroller f4504r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AbstractC0049c f4505s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public View f4506t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f4507u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ViewGroup f4508v;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4490d = -1;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Runnable f4509w = new b();

    /* JADX INFO: compiled from: ViewDragHelper.java */
    public static class a implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f3) {
            float f4 = f3 - 1.0f;
            return (f4 * f4 * f4 * f4 * f4) + 1.0f;
        }
    }

    /* JADX INFO: compiled from: ViewDragHelper.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.J(0);
        }
    }

    /* JADX INFO: renamed from: p0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewDragHelper.java */
    public static abstract class AbstractC0049c {
        public abstract int a(View view, int i3, int i4);

        public abstract int b(View view, int i3, int i4);

        public int c(int i3) {
            return i3;
        }

        public int d(View view) {
            return 0;
        }

        public int e(View view) {
            return 0;
        }

        public void f(int i3, int i4) {
        }

        public boolean g(int i3) {
            return false;
        }

        public void h(int i3, int i4) {
        }

        public void i(View view, int i3) {
        }

        public abstract void j(int i3);

        public abstract void k(View view, int i3, int i4, int i5, int i6);

        public abstract void l(View view, float f3, float f4);

        public abstract boolean m(View view, int i3);
    }

    public c(Context context, ViewGroup viewGroup, AbstractC0049c abstractC0049c) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (abstractC0049c == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.f4508v = viewGroup;
        this.f4505s = abstractC0049c;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f4502p = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.f4489c = viewConfiguration.getScaledTouchSlop();
        this.f4500n = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f4501o = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f4504r = new OverScroller(context, f4487a);
    }

    public static c n(ViewGroup viewGroup, float f3, AbstractC0049c abstractC0049c) {
        c cVarO = o(viewGroup, abstractC0049c);
        cVarO.f4489c = (int) (cVarO.f4489c * (1.0f / f3));
        return cVarO;
    }

    public static c o(ViewGroup viewGroup, AbstractC0049c abstractC0049c) {
        return new c(viewGroup.getContext(), viewGroup, abstractC0049c);
    }

    public boolean A(int i3, int i4) {
        return D(this.f4506t, i3, i4);
    }

    public boolean B(int i3) {
        return ((1 << i3) & this.f4498l) != 0;
    }

    public final boolean C(int i3) {
        if (B(i3)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i3 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    public boolean D(View view, int i3, int i4) {
        return view != null && i3 >= view.getLeft() && i3 < view.getRight() && i4 >= view.getTop() && i4 < view.getBottom();
    }

    public void E(MotionEvent motionEvent) {
        int i3;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            a();
        }
        if (this.f4499m == null) {
            this.f4499m = VelocityTracker.obtain();
        }
        this.f4499m.addMovement(motionEvent);
        int i4 = 0;
        if (actionMasked == 0) {
            float x3 = motionEvent.getX();
            float y3 = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewT = t((int) x3, (int) y3);
            H(x3, y3, pointerId);
            P(viewT, pointerId);
            int i5 = this.f4495i[pointerId];
            int i6 = this.f4503q;
            if ((i5 & i6) != 0) {
                this.f4505s.h(i5 & i6, pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.f4488b == 1) {
                F();
            }
            a();
            return;
        }
        if (actionMasked == 2) {
            if (this.f4488b == 1) {
                if (C(this.f4490d)) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.f4490d);
                    float x4 = motionEvent.getX(iFindPointerIndex);
                    float y4 = motionEvent.getY(iFindPointerIndex);
                    float[] fArr = this.f4493g;
                    int i7 = this.f4490d;
                    int i8 = (int) (x4 - fArr[i7]);
                    int i9 = (int) (y4 - this.f4494h[i7]);
                    r(this.f4506t.getLeft() + i8, this.f4506t.getTop() + i9, i8, i9);
                    I(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (i4 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(i4);
                if (C(pointerId2)) {
                    float x5 = motionEvent.getX(i4);
                    float y5 = motionEvent.getY(i4);
                    float f3 = x5 - this.f4491e[pointerId2];
                    float f4 = y5 - this.f4492f[pointerId2];
                    G(f3, f4, pointerId2);
                    if (this.f4488b != 1) {
                        View viewT2 = t((int) x5, (int) y5);
                        if (f(viewT2, f3, f4) && P(viewT2, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i4++;
            }
            I(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.f4488b == 1) {
                p(0.0f, 0.0f);
            }
            a();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x6 = motionEvent.getX(actionIndex);
            float y6 = motionEvent.getY(actionIndex);
            H(x6, y6, pointerId3);
            if (this.f4488b != 0) {
                if (A((int) x6, (int) y6)) {
                    P(this.f4506t, pointerId3);
                    return;
                }
                return;
            } else {
                P(t((int) x6, (int) y6), pointerId3);
                int i10 = this.f4495i[pointerId3];
                int i11 = this.f4503q;
                if ((i10 & i11) != 0) {
                    this.f4505s.h(i10 & i11, pointerId3);
                    return;
                }
                return;
            }
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.f4488b == 1 && pointerId4 == this.f4490d) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i4 >= pointerCount2) {
                    i3 = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i4);
                if (pointerId5 != this.f4490d) {
                    View viewT3 = t((int) motionEvent.getX(i4), (int) motionEvent.getY(i4));
                    View view = this.f4506t;
                    if (viewT3 == view && P(view, pointerId5)) {
                        i3 = this.f4490d;
                        break;
                    }
                }
                i4++;
            }
            if (i3 == -1) {
                F();
            }
        }
        j(pointerId4);
    }

    public final void F() {
        this.f4499m.computeCurrentVelocity(1000, this.f4500n);
        p(g(this.f4499m.getXVelocity(this.f4490d), this.f4501o, this.f4500n), g(this.f4499m.getYVelocity(this.f4490d), this.f4501o, this.f4500n));
    }

    public final void G(float f3, float f4, int i3) {
        int i4 = c(f3, f4, i3, 1) ? 1 : 0;
        if (c(f4, f3, i3, 4)) {
            i4 |= 4;
        }
        if (c(f3, f4, i3, 2)) {
            i4 |= 2;
        }
        if (c(f4, f3, i3, 8)) {
            i4 |= 8;
        }
        if (i4 != 0) {
            int[] iArr = this.f4496j;
            iArr[i3] = iArr[i3] | i4;
            this.f4505s.f(i4, i3);
        }
    }

    public final void H(float f3, float f4, int i3) {
        s(i3);
        float[] fArr = this.f4491e;
        this.f4493g[i3] = f3;
        fArr[i3] = f3;
        float[] fArr2 = this.f4492f;
        this.f4494h[i3] = f4;
        fArr2[i3] = f4;
        this.f4495i[i3] = x((int) f3, (int) f4);
        this.f4498l |= 1 << i3;
    }

    public final void I(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i3 = 0; i3 < pointerCount; i3++) {
            int pointerId = motionEvent.getPointerId(i3);
            if (C(pointerId)) {
                float x3 = motionEvent.getX(i3);
                float y3 = motionEvent.getY(i3);
                this.f4493g[pointerId] = x3;
                this.f4494h[pointerId] = y3;
            }
        }
    }

    public void J(int i3) {
        this.f4508v.removeCallbacks(this.f4509w);
        if (this.f4488b != i3) {
            this.f4488b = i3;
            this.f4505s.j(i3);
            if (this.f4488b == 0) {
                this.f4506t = null;
            }
        }
    }

    public void K(int i3) {
        this.f4503q = i3;
    }

    public void L(float f3) {
        this.f4501o = f3;
    }

    public boolean M(int i3, int i4) {
        if (this.f4507u) {
            return u(i3, i4, (int) this.f4499m.getXVelocity(this.f4490d), (int) this.f4499m.getYVelocity(this.f4490d));
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
        throw new UnsupportedOperationException("Method not decompiled: p0.c.N(android.view.MotionEvent):boolean");
    }

    public boolean O(View view, int i3, int i4) {
        this.f4506t = view;
        this.f4490d = -1;
        boolean zU = u(i3, i4, 0, 0);
        if (!zU && this.f4488b == 0 && this.f4506t != null) {
            this.f4506t = null;
        }
        return zU;
    }

    public boolean P(View view, int i3) {
        if (view == this.f4506t && this.f4490d == i3) {
            return true;
        }
        if (view == null || !this.f4505s.m(view, i3)) {
            return false;
        }
        this.f4490d = i3;
        b(view, i3);
        return true;
    }

    public void a() {
        this.f4490d = -1;
        i();
        VelocityTracker velocityTracker = this.f4499m;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f4499m = null;
        }
    }

    public void b(View view, int i3) {
        if (view.getParent() == this.f4508v) {
            this.f4506t = view;
            this.f4490d = i3;
            this.f4505s.i(view, i3);
            J(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.f4508v + ")");
    }

    public final boolean c(float f3, float f4, int i3, int i4) {
        float fAbs = Math.abs(f3);
        float fAbs2 = Math.abs(f4);
        if ((this.f4495i[i3] & i4) != i4 || (this.f4503q & i4) == 0 || (this.f4497k[i3] & i4) == i4 || (this.f4496j[i3] & i4) == i4) {
            return false;
        }
        int i5 = this.f4489c;
        if (fAbs <= i5 && fAbs2 <= i5) {
            return false;
        }
        if (fAbs >= fAbs2 * 0.5f || !this.f4505s.g(i4)) {
            return (this.f4496j[i3] & i4) == 0 && fAbs > ((float) this.f4489c);
        }
        int[] iArr = this.f4497k;
        iArr[i3] = iArr[i3] | i4;
        return false;
    }

    public boolean d(int i3) {
        int length = this.f4491e.length;
        for (int i4 = 0; i4 < length; i4++) {
            if (e(i3, i4)) {
                return true;
            }
        }
        return false;
    }

    public boolean e(int i3, int i4) {
        if (!B(i4)) {
            return false;
        }
        boolean z3 = (i3 & 1) == 1;
        boolean z4 = (i3 & 2) == 2;
        float f3 = this.f4493g[i4] - this.f4491e[i4];
        float f4 = this.f4494h[i4] - this.f4492f[i4];
        if (!z3 || !z4) {
            return z3 ? Math.abs(f3) > ((float) this.f4489c) : z4 && Math.abs(f4) > ((float) this.f4489c);
        }
        float f5 = (f3 * f3) + (f4 * f4);
        int i5 = this.f4489c;
        return f5 > ((float) (i5 * i5));
    }

    public final boolean f(View view, float f3, float f4) {
        if (view == null) {
            return false;
        }
        boolean z3 = this.f4505s.d(view) > 0;
        boolean z4 = this.f4505s.e(view) > 0;
        if (!z3 || !z4) {
            return z3 ? Math.abs(f3) > ((float) this.f4489c) : z4 && Math.abs(f4) > ((float) this.f4489c);
        }
        float f5 = (f3 * f3) + (f4 * f4);
        int i3 = this.f4489c;
        return f5 > ((float) (i3 * i3));
    }

    public final float g(float f3, float f4, float f5) {
        float fAbs = Math.abs(f3);
        if (fAbs < f4) {
            return 0.0f;
        }
        return fAbs > f5 ? f3 > 0.0f ? f5 : -f5 : f3;
    }

    public final int h(int i3, int i4, int i5) {
        int iAbs = Math.abs(i3);
        if (iAbs < i4) {
            return 0;
        }
        return iAbs > i5 ? i3 > 0 ? i5 : -i5 : i3;
    }

    public final void i() {
        float[] fArr = this.f4491e;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.f4492f, 0.0f);
        Arrays.fill(this.f4493g, 0.0f);
        Arrays.fill(this.f4494h, 0.0f);
        Arrays.fill(this.f4495i, 0);
        Arrays.fill(this.f4496j, 0);
        Arrays.fill(this.f4497k, 0);
        this.f4498l = 0;
    }

    public final void j(int i3) {
        if (this.f4491e == null || !B(i3)) {
            return;
        }
        this.f4491e[i3] = 0.0f;
        this.f4492f[i3] = 0.0f;
        this.f4493g[i3] = 0.0f;
        this.f4494h[i3] = 0.0f;
        this.f4495i[i3] = 0;
        this.f4496j[i3] = 0;
        this.f4497k[i3] = 0;
        this.f4498l = (~(1 << i3)) & this.f4498l;
    }

    public final int k(int i3, int i4, int i5) {
        if (i3 == 0) {
            return 0;
        }
        int width = this.f4508v.getWidth();
        float f3 = width / 2;
        float fQ = f3 + (q(Math.min(1.0f, Math.abs(i3) / width)) * f3);
        int iAbs = Math.abs(i4);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fQ / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i3) / i5) + 1.0f) * 256.0f), 600);
    }

    public final int l(View view, int i3, int i4, int i5, int i6) {
        float f3;
        float f4;
        float f5;
        float f6;
        int iH = h(i5, (int) this.f4501o, (int) this.f4500n);
        int iH2 = h(i6, (int) this.f4501o, (int) this.f4500n);
        int iAbs = Math.abs(i3);
        int iAbs2 = Math.abs(i4);
        int iAbs3 = Math.abs(iH);
        int iAbs4 = Math.abs(iH2);
        int i7 = iAbs3 + iAbs4;
        int i8 = iAbs + iAbs2;
        if (iH != 0) {
            f3 = iAbs3;
            f4 = i7;
        } else {
            f3 = iAbs;
            f4 = i8;
        }
        float f7 = f3 / f4;
        if (iH2 != 0) {
            f5 = iAbs4;
            f6 = i7;
        } else {
            f5 = iAbs2;
            f6 = i8;
        }
        return (int) ((k(i3, iH, this.f4505s.d(view)) * f7) + (k(i4, iH2, this.f4505s.e(view)) * (f5 / f6)));
    }

    public boolean m(boolean z3) {
        if (this.f4488b == 2) {
            boolean zComputeScrollOffset = this.f4504r.computeScrollOffset();
            int currX = this.f4504r.getCurrX();
            int currY = this.f4504r.getCurrY();
            int left = currX - this.f4506t.getLeft();
            int top = currY - this.f4506t.getTop();
            if (left != 0) {
                t.U(this.f4506t, left);
            }
            if (top != 0) {
                t.V(this.f4506t, top);
            }
            if (left != 0 || top != 0) {
                this.f4505s.k(this.f4506t, currX, currY, left, top);
            }
            if (zComputeScrollOffset && currX == this.f4504r.getFinalX() && currY == this.f4504r.getFinalY()) {
                this.f4504r.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                if (z3) {
                    this.f4508v.post(this.f4509w);
                } else {
                    J(0);
                }
            }
        }
        return this.f4488b == 2;
    }

    public final void p(float f3, float f4) {
        this.f4507u = true;
        this.f4505s.l(this.f4506t, f3, f4);
        this.f4507u = false;
        if (this.f4488b == 1) {
            J(0);
        }
    }

    public final float q(float f3) {
        return (float) Math.sin((f3 - 0.5f) * 0.47123894f);
    }

    public final void r(int i3, int i4, int i5, int i6) {
        int left = this.f4506t.getLeft();
        int top = this.f4506t.getTop();
        if (i5 != 0) {
            i3 = this.f4505s.a(this.f4506t, i3, i5);
            t.U(this.f4506t, i3 - left);
        }
        int i7 = i3;
        if (i6 != 0) {
            i4 = this.f4505s.b(this.f4506t, i4, i6);
            t.V(this.f4506t, i4 - top);
        }
        int i8 = i4;
        if (i5 == 0 && i6 == 0) {
            return;
        }
        this.f4505s.k(this.f4506t, i7, i8, i7 - left, i8 - top);
    }

    public final void s(int i3) {
        float[] fArr = this.f4491e;
        if (fArr == null || fArr.length <= i3) {
            int i4 = i3 + 1;
            float[] fArr2 = new float[i4];
            float[] fArr3 = new float[i4];
            float[] fArr4 = new float[i4];
            float[] fArr5 = new float[i4];
            int[] iArr = new int[i4];
            int[] iArr2 = new int[i4];
            int[] iArr3 = new int[i4];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f4492f;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f4493g;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.f4494h;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.f4495i;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.f4496j;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.f4497k;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f4491e = fArr2;
            this.f4492f = fArr3;
            this.f4493g = fArr4;
            this.f4494h = fArr5;
            this.f4495i = iArr;
            this.f4496j = iArr2;
            this.f4497k = iArr3;
        }
    }

    public View t(int i3, int i4) {
        for (int childCount = this.f4508v.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.f4508v.getChildAt(this.f4505s.c(childCount));
            if (i3 >= childAt.getLeft() && i3 < childAt.getRight() && i4 >= childAt.getTop() && i4 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public final boolean u(int i3, int i4, int i5, int i6) {
        int left = this.f4506t.getLeft();
        int top = this.f4506t.getTop();
        int i7 = i3 - left;
        int i8 = i4 - top;
        if (i7 == 0 && i8 == 0) {
            this.f4504r.abortAnimation();
            J(0);
            return false;
        }
        this.f4504r.startScroll(left, top, i7, i8, l(this.f4506t, i7, i8, i5, i6));
        J(2);
        return true;
    }

    public View v() {
        return this.f4506t;
    }

    public int w() {
        return this.f4502p;
    }

    public final int x(int i3, int i4) {
        int i5 = i3 < this.f4508v.getLeft() + this.f4502p ? 1 : 0;
        if (i4 < this.f4508v.getTop() + this.f4502p) {
            i5 |= 4;
        }
        if (i3 > this.f4508v.getRight() - this.f4502p) {
            i5 |= 2;
        }
        return i4 > this.f4508v.getBottom() - this.f4502p ? i5 | 8 : i5;
    }

    public int y() {
        return this.f4489c;
    }

    public int z() {
        return this.f4488b;
    }
}
