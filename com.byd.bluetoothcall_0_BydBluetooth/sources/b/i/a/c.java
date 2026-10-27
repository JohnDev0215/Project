package b.i.a;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import b.g.k.t;
import java.util.Arrays;

/* JADX INFO: compiled from: ViewDragHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Interpolator f2397a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2398b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2399c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float[] f2401e;
    private float[] f;
    private float[] g;
    private float[] h;
    private int[] i;
    private int[] j;
    private int[] k;
    private int l;
    private VelocityTracker m;
    private float n;
    private float o;
    private int p;
    private int q;
    private OverScroller r;
    private final AbstractC0059c s;
    private View t;
    private boolean u;
    private final ViewGroup v;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2400d = -1;
    private final Runnable w = new b();

    /* JADX INFO: compiled from: ViewDragHelper.java */
    static class a implements Interpolator {
        a() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    /* JADX INFO: compiled from: ViewDragHelper.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.K(0);
        }
    }

    /* JADX INFO: renamed from: b.i.a.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewDragHelper.java */
    public static abstract class AbstractC0059c {
        public abstract int a(View view, int i, int i2);

        public abstract int b(View view, int i, int i2);

        public int c(int i) {
            return i;
        }

        public int d(View view) {
            return 0;
        }

        public int e(View view) {
            return 0;
        }

        public void f(int i, int i2) {
        }

        public boolean g(int i) {
            return false;
        }

        public void h(int i, int i2) {
        }

        public void i(View view, int i) {
        }

        public abstract void j(int i);

        public abstract void k(View view, int i, int i2, int i3, int i4);

        public abstract void l(View view, float f, float f2);

        public abstract boolean m(View view, int i);
    }

    private c(Context context, ViewGroup viewGroup, AbstractC0059c abstractC0059c) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (abstractC0059c == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.v = viewGroup;
        this.s = abstractC0059c;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.p = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.f2399c = viewConfiguration.getScaledTouchSlop();
        this.n = viewConfiguration.getScaledMaximumFlingVelocity();
        this.o = viewConfiguration.getScaledMinimumFlingVelocity();
        this.r = new OverScroller(context, f2397a);
    }

    private boolean D(int i) {
        if (C(i)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    private void G() {
        this.m.computeCurrentVelocity(1000, this.n);
        q(h(this.m.getXVelocity(this.f2400d), this.o, this.n), h(this.m.getYVelocity(this.f2400d), this.o, this.n));
    }

    private void H(float f, float f2, int i) {
        int i2 = d(f, f2, i, 1) ? 1 : 0;
        if (d(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (d(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (d(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.j;
            iArr[i] = iArr[i] | i2;
            this.s.f(i2, i);
        }
    }

    private void I(float f, float f2, int i) {
        t(i);
        float[] fArr = this.f2401e;
        this.g[i] = f;
        fArr[i] = f;
        float[] fArr2 = this.f;
        this.h[i] = f2;
        fArr2[i] = f2;
        this.i[i] = y((int) f, (int) f2);
        this.l |= 1 << i;
    }

    private void J(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (D(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.g[pointerId] = x;
                this.h[pointerId] = y;
            }
        }
    }

    private boolean d(float f, float f2, int i, int i2) {
        float fAbs = Math.abs(f);
        float fAbs2 = Math.abs(f2);
        if ((this.i[i] & i2) != i2 || (this.q & i2) == 0 || (this.k[i] & i2) == i2 || (this.j[i] & i2) == i2) {
            return false;
        }
        int i3 = this.f2399c;
        if (fAbs <= i3 && fAbs2 <= i3) {
            return false;
        }
        if (fAbs >= fAbs2 * 0.5f || !this.s.g(i2)) {
            return (this.j[i] & i2) == 0 && fAbs > ((float) this.f2399c);
        }
        int[] iArr = this.k;
        iArr[i] = iArr[i] | i2;
        return false;
    }

    private boolean g(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z = this.s.d(view) > 0;
        boolean z2 = this.s.e(view) > 0;
        if (!z || !z2) {
            return z ? Math.abs(f) > ((float) this.f2399c) : z2 && Math.abs(f2) > ((float) this.f2399c);
        }
        float f3 = (f * f) + (f2 * f2);
        int i = this.f2399c;
        return f3 > ((float) (i * i));
    }

    private float h(float f, float f2, float f3) {
        float fAbs = Math.abs(f);
        if (fAbs < f2) {
            return 0.0f;
        }
        return fAbs > f3 ? f > 0.0f ? f3 : -f3 : f;
    }

    private int i(int i, int i2, int i3) {
        int iAbs = Math.abs(i);
        if (iAbs < i2) {
            return 0;
        }
        return iAbs > i3 ? i > 0 ? i3 : -i3 : i;
    }

    private void j() {
        float[] fArr = this.f2401e;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.f, 0.0f);
        Arrays.fill(this.g, 0.0f);
        Arrays.fill(this.h, 0.0f);
        Arrays.fill(this.i, 0);
        Arrays.fill(this.j, 0);
        Arrays.fill(this.k, 0);
        this.l = 0;
    }

    private void k(int i) {
        if (this.f2401e == null || !C(i)) {
            return;
        }
        this.f2401e[i] = 0.0f;
        this.f[i] = 0.0f;
        this.g[i] = 0.0f;
        this.h[i] = 0.0f;
        this.i[i] = 0;
        this.j[i] = 0;
        this.k[i] = 0;
        this.l = (~(1 << i)) & this.l;
    }

    private int l(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.v.getWidth();
        float f = width / 2;
        float fR = f + (r(Math.min(1.0f, Math.abs(i) / width)) * f);
        int iAbs = Math.abs(i2);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fR / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f), 600);
    }

    private int m(View view, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int i5 = i(i3, (int) this.o, (int) this.n);
        int i6 = i(i4, (int) this.o, (int) this.n);
        int iAbs = Math.abs(i);
        int iAbs2 = Math.abs(i2);
        int iAbs3 = Math.abs(i5);
        int iAbs4 = Math.abs(i6);
        int i7 = iAbs3 + iAbs4;
        int i8 = iAbs + iAbs2;
        if (i5 != 0) {
            f = iAbs3;
            f2 = i7;
        } else {
            f = iAbs;
            f2 = i8;
        }
        float f5 = f / f2;
        if (i6 != 0) {
            f3 = iAbs4;
            f4 = i7;
        } else {
            f3 = iAbs2;
            f4 = i8;
        }
        return (int) ((l(i, i5, this.s.d(view)) * f5) + (l(i2, i6, this.s.e(view)) * (f3 / f4)));
    }

    public static c o(ViewGroup viewGroup, float f, AbstractC0059c abstractC0059c) {
        c cVarP = p(viewGroup, abstractC0059c);
        cVarP.f2399c = (int) (cVarP.f2399c * (1.0f / f));
        return cVarP;
    }

    public static c p(ViewGroup viewGroup, AbstractC0059c abstractC0059c) {
        return new c(viewGroup.getContext(), viewGroup, abstractC0059c);
    }

    private void q(float f, float f2) {
        this.u = true;
        this.s.l(this.t, f, f2);
        this.u = false;
        if (this.f2398b == 1) {
            K(0);
        }
    }

    private float r(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    private void s(int i, int i2, int i3, int i4) {
        int left = this.t.getLeft();
        int top = this.t.getTop();
        if (i3 != 0) {
            i = this.s.a(this.t, i, i3);
            t.U(this.t, i - left);
        }
        int i5 = i;
        if (i4 != 0) {
            i2 = this.s.b(this.t, i2, i4);
            t.V(this.t, i2 - top);
        }
        int i6 = i2;
        if (i3 == 0 && i4 == 0) {
            return;
        }
        this.s.k(this.t, i5, i6, i5 - left, i6 - top);
    }

    private void t(int i) {
        float[] fArr = this.f2401e;
        if (fArr == null || fArr.length <= i) {
            int i2 = i + 1;
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            float[] fArr4 = new float[i2];
            float[] fArr5 = new float[i2];
            int[] iArr = new int[i2];
            int[] iArr2 = new int[i2];
            int[] iArr3 = new int[i2];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.g;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.h;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.i;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.j;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.k;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f2401e = fArr2;
            this.f = fArr3;
            this.g = fArr4;
            this.h = fArr5;
            this.i = iArr;
            this.j = iArr2;
            this.k = iArr3;
        }
    }

    private boolean v(int i, int i2, int i3, int i4) {
        int left = this.t.getLeft();
        int top = this.t.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.r.abortAnimation();
            K(0);
            return false;
        }
        this.r.startScroll(left, top, i5, i6, m(this.t, i5, i6, i3, i4));
        K(2);
        return true;
    }

    private int y(int i, int i2) {
        int i3 = i < this.v.getLeft() + this.p ? 1 : 0;
        if (i2 < this.v.getTop() + this.p) {
            i3 |= 4;
        }
        if (i > this.v.getRight() - this.p) {
            i3 |= 2;
        }
        return i2 > this.v.getBottom() - this.p ? i3 | 8 : i3;
    }

    public int A() {
        return this.f2398b;
    }

    public boolean B(int i, int i2) {
        return E(this.t, i, i2);
    }

    public boolean C(int i) {
        return ((1 << i) & this.l) != 0;
    }

    public boolean E(View view, int i, int i2) {
        return view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom();
    }

    public void F(MotionEvent motionEvent) {
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            b();
        }
        if (this.m == null) {
            this.m = VelocityTracker.obtain();
        }
        this.m.addMovement(motionEvent);
        int i2 = 0;
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewU = u((int) x, (int) y);
            I(x, y, pointerId);
            Q(viewU, pointerId);
            int i3 = this.i[pointerId];
            int i4 = this.q;
            if ((i3 & i4) != 0) {
                this.s.h(i3 & i4, pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.f2398b == 1) {
                G();
            }
            b();
            return;
        }
        if (actionMasked == 2) {
            if (this.f2398b == 1) {
                if (D(this.f2400d)) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.f2400d);
                    float x2 = motionEvent.getX(iFindPointerIndex);
                    float y2 = motionEvent.getY(iFindPointerIndex);
                    float[] fArr = this.g;
                    int i5 = this.f2400d;
                    int i6 = (int) (x2 - fArr[i5]);
                    int i7 = (int) (y2 - this.h[i5]);
                    s(this.t.getLeft() + i6, this.t.getTop() + i7, i6, i7);
                    J(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (i2 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(i2);
                if (D(pointerId2)) {
                    float x3 = motionEvent.getX(i2);
                    float y3 = motionEvent.getY(i2);
                    float f = x3 - this.f2401e[pointerId2];
                    float f2 = y3 - this.f[pointerId2];
                    H(f, f2, pointerId2);
                    if (this.f2398b != 1) {
                        View viewU2 = u((int) x3, (int) y3);
                        if (g(viewU2, f, f2) && Q(viewU2, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i2++;
            }
            J(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.f2398b == 1) {
                q(0.0f, 0.0f);
            }
            b();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x4 = motionEvent.getX(actionIndex);
            float y4 = motionEvent.getY(actionIndex);
            I(x4, y4, pointerId3);
            if (this.f2398b != 0) {
                if (B((int) x4, (int) y4)) {
                    Q(this.t, pointerId3);
                    return;
                }
                return;
            } else {
                Q(u((int) x4, (int) y4), pointerId3);
                int i8 = this.i[pointerId3];
                int i9 = this.q;
                if ((i8 & i9) != 0) {
                    this.s.h(i8 & i9, pointerId3);
                    return;
                }
                return;
            }
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.f2398b == 1 && pointerId4 == this.f2400d) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i2 >= pointerCount2) {
                    i = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i2);
                if (pointerId5 != this.f2400d) {
                    View viewU3 = u((int) motionEvent.getX(i2), (int) motionEvent.getY(i2));
                    View view = this.t;
                    if (viewU3 == view && Q(view, pointerId5)) {
                        i = this.f2400d;
                        break;
                    }
                }
                i2++;
            }
            if (i == -1) {
                G();
            }
        }
        k(pointerId4);
    }

    void K(int i) {
        this.v.removeCallbacks(this.w);
        if (this.f2398b != i) {
            this.f2398b = i;
            this.s.j(i);
            if (this.f2398b == 0) {
                this.t = null;
            }
        }
    }

    public void L(int i) {
        this.q = i;
    }

    public void M(float f) {
        this.o = f;
    }

    public boolean N(int i, int i2) {
        if (this.u) {
            return v(i, i2, (int) this.m.getXVelocity(this.f2400d), (int) this.m.getYVelocity(this.f2400d));
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
        throw new UnsupportedOperationException("Method not decompiled: b.i.a.c.O(android.view.MotionEvent):boolean");
    }

    public boolean P(View view, int i, int i2) {
        this.t = view;
        this.f2400d = -1;
        boolean zV = v(i, i2, 0, 0);
        if (!zV && this.f2398b == 0 && this.t != null) {
            this.t = null;
        }
        return zV;
    }

    boolean Q(View view, int i) {
        if (view == this.t && this.f2400d == i) {
            return true;
        }
        if (view == null || !this.s.m(view, i)) {
            return false;
        }
        this.f2400d = i;
        c(view, i);
        return true;
    }

    public void a() {
        b();
        if (this.f2398b == 2) {
            int currX = this.r.getCurrX();
            int currY = this.r.getCurrY();
            this.r.abortAnimation();
            int currX2 = this.r.getCurrX();
            int currY2 = this.r.getCurrY();
            this.s.k(this.t, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        K(0);
    }

    public void b() {
        this.f2400d = -1;
        j();
        VelocityTracker velocityTracker = this.m;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.m = null;
        }
    }

    public void c(View view, int i) {
        if (view.getParent() == this.v) {
            this.t = view;
            this.f2400d = i;
            this.s.i(view, i);
            K(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.v + ")");
    }

    public boolean e(int i) {
        int length = this.f2401e.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (f(i, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean f(int i, int i2) {
        if (!C(i2)) {
            return false;
        }
        boolean z = (i & 1) == 1;
        boolean z2 = (i & 2) == 2;
        float f = this.g[i2] - this.f2401e[i2];
        float f2 = this.h[i2] - this.f[i2];
        if (!z || !z2) {
            return z ? Math.abs(f) > ((float) this.f2399c) : z2 && Math.abs(f2) > ((float) this.f2399c);
        }
        float f3 = (f * f) + (f2 * f2);
        int i3 = this.f2399c;
        return f3 > ((float) (i3 * i3));
    }

    public boolean n(boolean z) {
        if (this.f2398b == 2) {
            boolean zComputeScrollOffset = this.r.computeScrollOffset();
            int currX = this.r.getCurrX();
            int currY = this.r.getCurrY();
            int left = currX - this.t.getLeft();
            int top = currY - this.t.getTop();
            if (left != 0) {
                t.U(this.t, left);
            }
            if (top != 0) {
                t.V(this.t, top);
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
                    K(0);
                }
            }
        }
        return this.f2398b == 2;
    }

    public View u(int i, int i2) {
        for (int childCount = this.v.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.v.getChildAt(this.s.c(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public View w() {
        return this.t;
    }

    public int x() {
        return this.p;
    }

    public int z() {
        return this.f2399c;
    }
}
