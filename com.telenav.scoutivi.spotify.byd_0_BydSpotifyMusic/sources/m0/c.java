package m0;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import i0.u;
import java.util.Arrays;

/* JADX INFO: compiled from: ViewDragHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private static final Interpolator f8043x = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f8044a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f8045b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float[] f8047d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float[] f8048e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float[] f8049f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float[] f8050g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int[] f8051h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int[] f8052i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int[] f8053j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f8054k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private VelocityTracker f8055l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private float f8056m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f8057n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f8058o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final int f8059p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f8060q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private OverScroller f8061r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final AbstractC0122c f8062s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private View f8063t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f8064u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private final ViewGroup f8065v;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f8046c = -1;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private final Runnable f8066w = new b();

    /* JADX INFO: compiled from: ViewDragHelper.java */
    class a implements Interpolator {
        a() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            float f8 = f7 - 1.0f;
            return (f8 * f8 * f8 * f8 * f8) + 1.0f;
        }
    }

    /* JADX INFO: compiled from: ViewDragHelper.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.E(0);
        }
    }

    /* JADX INFO: renamed from: m0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewDragHelper.java */
    public static abstract class AbstractC0122c {
        public abstract int a(View view, int i7, int i8);

        public abstract int b(View view, int i7, int i8);

        public int c(int i7) {
            return i7;
        }

        public int d(View view) {
            return 0;
        }

        public int e(View view) {
            return 0;
        }

        public void f(int i7, int i8) {
        }

        public boolean g(int i7) {
            return false;
        }

        public void h(int i7, int i8) {
        }

        public void i(View view, int i7) {
        }

        public abstract void j(int i7);

        public abstract void k(View view, int i7, int i8, int i9, int i10);

        public abstract void l(View view, float f7, float f8);

        public abstract boolean m(View view, int i7);
    }

    private c(Context context, ViewGroup viewGroup, AbstractC0122c abstractC0122c) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (abstractC0122c == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.f8065v = viewGroup;
        this.f8062s = abstractC0122c;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        int i7 = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.f8059p = i7;
        this.f8058o = i7;
        this.f8045b = viewConfiguration.getScaledTouchSlop();
        this.f8056m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f8057n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f8061r = new OverScroller(context, f8043x);
    }

    private void A() {
        this.f8055l.computeCurrentVelocity(1000, this.f8056m);
        n(e(this.f8055l.getXVelocity(this.f8046c), this.f8057n, this.f8056m), e(this.f8055l.getYVelocity(this.f8046c), this.f8057n, this.f8056m));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v3, types: [m0.c$c] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void B(float f7, float f8, int i7) {
        boolean zC = c(f7, f8, i7, 1);
        ?? r02 = zC;
        if (c(f8, f7, i7, 4)) {
            r02 = (zC ? 1 : 0) | 4;
        }
        ?? r03 = r02;
        if (c(f7, f8, i7, 2)) {
            r03 = (r02 == true ? 1 : 0) | 2;
        }
        ?? r04 = r03;
        if (c(f8, f7, i7, 8)) {
            r04 = (r03 == true ? 1 : 0) | 8;
        }
        if (r04 != 0) {
            int[] iArr = this.f8052i;
            iArr[i7] = iArr[i7] | r04;
            this.f8062s.f(r04, i7);
        }
    }

    private void C(float f7, float f8, int i7) {
        q(i7);
        float[] fArr = this.f8047d;
        this.f8049f[i7] = f7;
        fArr[i7] = f7;
        float[] fArr2 = this.f8048e;
        this.f8050g[i7] = f8;
        fArr2[i7] = f8;
        this.f8051h[i7] = t((int) f7, (int) f8);
        this.f8054k |= 1 << i7;
    }

    private void D(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i7 = 0; i7 < pointerCount; i7++) {
            int pointerId = motionEvent.getPointerId(i7);
            if (x(pointerId)) {
                float x6 = motionEvent.getX(i7);
                float y6 = motionEvent.getY(i7);
                this.f8049f[pointerId] = x6;
                this.f8050g[pointerId] = y6;
            }
        }
    }

    private boolean c(float f7, float f8, int i7, int i8) {
        float fAbs = Math.abs(f7);
        float fAbs2 = Math.abs(f8);
        if ((this.f8051h[i7] & i8) != i8 || (this.f8060q & i8) == 0 || (this.f8053j[i7] & i8) == i8 || (this.f8052i[i7] & i8) == i8) {
            return false;
        }
        int i9 = this.f8045b;
        if (fAbs <= i9 && fAbs2 <= i9) {
            return false;
        }
        if (fAbs >= fAbs2 * 0.5f || !this.f8062s.g(i8)) {
            return (this.f8052i[i7] & i8) == 0 && fAbs > ((float) this.f8045b);
        }
        int[] iArr = this.f8053j;
        iArr[i7] = iArr[i7] | i8;
        return false;
    }

    private boolean d(View view, float f7, float f8) {
        if (view == null) {
            return false;
        }
        boolean z6 = this.f8062s.d(view) > 0;
        boolean z7 = this.f8062s.e(view) > 0;
        if (!z6 || !z7) {
            return z6 ? Math.abs(f7) > ((float) this.f8045b) : z7 && Math.abs(f8) > ((float) this.f8045b);
        }
        float f9 = (f7 * f7) + (f8 * f8);
        int i7 = this.f8045b;
        return f9 > ((float) (i7 * i7));
    }

    private float e(float f7, float f8, float f9) {
        float fAbs = Math.abs(f7);
        if (fAbs < f8) {
            return 0.0f;
        }
        return fAbs > f9 ? f7 > 0.0f ? f9 : -f9 : f7;
    }

    private int f(int i7, int i8, int i9) {
        int iAbs = Math.abs(i7);
        if (iAbs < i8) {
            return 0;
        }
        return iAbs > i9 ? i7 > 0 ? i9 : -i9 : i7;
    }

    private void g() {
        float[] fArr = this.f8047d;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.f8048e, 0.0f);
        Arrays.fill(this.f8049f, 0.0f);
        Arrays.fill(this.f8050g, 0.0f);
        Arrays.fill(this.f8051h, 0);
        Arrays.fill(this.f8052i, 0);
        Arrays.fill(this.f8053j, 0);
        this.f8054k = 0;
    }

    private void h(int i7) {
        if (this.f8047d == null || !w(i7)) {
            return;
        }
        this.f8047d[i7] = 0.0f;
        this.f8048e[i7] = 0.0f;
        this.f8049f[i7] = 0.0f;
        this.f8050g[i7] = 0.0f;
        this.f8051h[i7] = 0;
        this.f8052i[i7] = 0;
        this.f8053j[i7] = 0;
        this.f8054k = (~(1 << i7)) & this.f8054k;
    }

    private int i(int i7, int i8, int i9) {
        if (i7 == 0) {
            return 0;
        }
        int width = this.f8065v.getWidth();
        float f7 = width / 2;
        float fO = f7 + (o(Math.min(1.0f, Math.abs(i7) / width)) * f7);
        int iAbs = Math.abs(i8);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fO / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i7) / i9) + 1.0f) * 256.0f), 600);
    }

    private int j(View view, int i7, int i8, int i9, int i10) {
        float f7;
        float f8;
        float f9;
        float f10;
        int iF = f(i9, (int) this.f8057n, (int) this.f8056m);
        int iF2 = f(i10, (int) this.f8057n, (int) this.f8056m);
        int iAbs = Math.abs(i7);
        int iAbs2 = Math.abs(i8);
        int iAbs3 = Math.abs(iF);
        int iAbs4 = Math.abs(iF2);
        int i11 = iAbs3 + iAbs4;
        int i12 = iAbs + iAbs2;
        if (iF != 0) {
            f7 = iAbs3;
            f8 = i11;
        } else {
            f7 = iAbs;
            f8 = i12;
        }
        float f11 = f7 / f8;
        if (iF2 != 0) {
            f9 = iAbs4;
            f10 = i11;
        } else {
            f9 = iAbs2;
            f10 = i12;
        }
        return (int) ((i(i7, iF, this.f8062s.d(view)) * f11) + (i(i8, iF2, this.f8062s.e(view)) * (f9 / f10)));
    }

    public static c l(ViewGroup viewGroup, float f7, AbstractC0122c abstractC0122c) {
        c cVarM = m(viewGroup, abstractC0122c);
        cVarM.f8045b = (int) (cVarM.f8045b * (1.0f / f7));
        return cVarM;
    }

    public static c m(ViewGroup viewGroup, AbstractC0122c abstractC0122c) {
        return new c(viewGroup.getContext(), viewGroup, abstractC0122c);
    }

    private void n(float f7, float f8) {
        this.f8064u = true;
        this.f8062s.l(this.f8063t, f7, f8);
        this.f8064u = false;
        if (this.f8044a == 1) {
            E(0);
        }
    }

    private float o(float f7) {
        return (float) Math.sin((f7 - 0.5f) * 0.47123894f);
    }

    private void p(int i7, int i8, int i9, int i10) {
        int left = this.f8063t.getLeft();
        int top = this.f8063t.getTop();
        if (i9 != 0) {
            i7 = this.f8062s.a(this.f8063t, i7, i9);
            u.U(this.f8063t, i7 - left);
        }
        int i11 = i7;
        if (i10 != 0) {
            i8 = this.f8062s.b(this.f8063t, i8, i10);
            u.V(this.f8063t, i8 - top);
        }
        int i12 = i8;
        if (i9 == 0 && i10 == 0) {
            return;
        }
        this.f8062s.k(this.f8063t, i11, i12, i11 - left, i12 - top);
    }

    private void q(int i7) {
        float[] fArr = this.f8047d;
        if (fArr == null || fArr.length <= i7) {
            int i8 = i7 + 1;
            float[] fArr2 = new float[i8];
            float[] fArr3 = new float[i8];
            float[] fArr4 = new float[i8];
            float[] fArr5 = new float[i8];
            int[] iArr = new int[i8];
            int[] iArr2 = new int[i8];
            int[] iArr3 = new int[i8];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f8048e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f8049f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.f8050g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.f8051h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.f8052i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.f8053j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f8047d = fArr2;
            this.f8048e = fArr3;
            this.f8049f = fArr4;
            this.f8050g = fArr5;
            this.f8051h = iArr;
            this.f8052i = iArr2;
            this.f8053j = iArr3;
        }
    }

    private boolean s(int i7, int i8, int i9, int i10) {
        int left = this.f8063t.getLeft();
        int top = this.f8063t.getTop();
        int i11 = i7 - left;
        int i12 = i8 - top;
        if (i11 == 0 && i12 == 0) {
            this.f8061r.abortAnimation();
            E(0);
            return false;
        }
        this.f8061r.startScroll(left, top, i11, i12, j(this.f8063t, i11, i12, i9, i10));
        E(2);
        return true;
    }

    private int t(int i7, int i8) {
        int i9 = i7 < this.f8065v.getLeft() + this.f8058o ? 1 : 0;
        if (i8 < this.f8065v.getTop() + this.f8058o) {
            i9 |= 4;
        }
        if (i7 > this.f8065v.getRight() - this.f8058o) {
            i9 |= 2;
        }
        return i8 > this.f8065v.getBottom() - this.f8058o ? i9 | 8 : i9;
    }

    private boolean x(int i7) {
        if (w(i7)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i7 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    void E(int i7) {
        this.f8065v.removeCallbacks(this.f8066w);
        if (this.f8044a != i7) {
            this.f8044a = i7;
            this.f8062s.j(i7);
            if (this.f8044a == 0) {
                this.f8063t = null;
            }
        }
    }

    public boolean F(int i7, int i8) {
        if (this.f8064u) {
            return s(i7, i8, (int) this.f8055l.getXVelocity(this.f8046c), (int) this.f8055l.getYVelocity(this.f8046c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean G(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m0.c.G(android.view.MotionEvent):boolean");
    }

    public boolean H(View view, int i7, int i8) {
        this.f8063t = view;
        this.f8046c = -1;
        boolean zS = s(i7, i8, 0, 0);
        if (!zS && this.f8044a == 0 && this.f8063t != null) {
            this.f8063t = null;
        }
        return zS;
    }

    boolean I(View view, int i7) {
        if (view == this.f8063t && this.f8046c == i7) {
            return true;
        }
        if (view == null || !this.f8062s.m(view, i7)) {
            return false;
        }
        this.f8046c = i7;
        b(view, i7);
        return true;
    }

    public void a() {
        this.f8046c = -1;
        g();
        VelocityTracker velocityTracker = this.f8055l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f8055l = null;
        }
    }

    public void b(View view, int i7) {
        if (view.getParent() == this.f8065v) {
            this.f8063t = view;
            this.f8046c = i7;
            this.f8062s.i(view, i7);
            E(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.f8065v + ")");
    }

    public boolean k(boolean z6) {
        if (this.f8044a == 2) {
            boolean zComputeScrollOffset = this.f8061r.computeScrollOffset();
            int currX = this.f8061r.getCurrX();
            int currY = this.f8061r.getCurrY();
            int left = currX - this.f8063t.getLeft();
            int top = currY - this.f8063t.getTop();
            if (left != 0) {
                u.U(this.f8063t, left);
            }
            if (top != 0) {
                u.V(this.f8063t, top);
            }
            if (left != 0 || top != 0) {
                this.f8062s.k(this.f8063t, currX, currY, left, top);
            }
            if (zComputeScrollOffset && currX == this.f8061r.getFinalX() && currY == this.f8061r.getFinalY()) {
                this.f8061r.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                if (z6) {
                    this.f8065v.post(this.f8066w);
                } else {
                    E(0);
                }
            }
        }
        return this.f8044a == 2;
    }

    public View r(int i7, int i8) {
        for (int childCount = this.f8065v.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.f8065v.getChildAt(this.f8062s.c(childCount));
            if (i7 >= childAt.getLeft() && i7 < childAt.getRight() && i8 >= childAt.getTop() && i8 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public int u() {
        return this.f8045b;
    }

    public boolean v(int i7, int i8) {
        return y(this.f8063t, i7, i8);
    }

    public boolean w(int i7) {
        return ((1 << i7) & this.f8054k) != 0;
    }

    public boolean y(View view, int i7, int i8) {
        return view != null && i7 >= view.getLeft() && i7 < view.getRight() && i8 >= view.getTop() && i8 < view.getBottom();
    }

    public void z(MotionEvent motionEvent) {
        int i7;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            a();
        }
        if (this.f8055l == null) {
            this.f8055l = VelocityTracker.obtain();
        }
        this.f8055l.addMovement(motionEvent);
        int i8 = 0;
        if (actionMasked == 0) {
            float x6 = motionEvent.getX();
            float y6 = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewR = r((int) x6, (int) y6);
            C(x6, y6, pointerId);
            I(viewR, pointerId);
            int i9 = this.f8051h[pointerId];
            int i10 = this.f8060q;
            if ((i9 & i10) != 0) {
                this.f8062s.h(i9 & i10, pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.f8044a == 1) {
                A();
            }
            a();
            return;
        }
        if (actionMasked == 2) {
            if (this.f8044a == 1) {
                if (x(this.f8046c)) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.f8046c);
                    float x7 = motionEvent.getX(iFindPointerIndex);
                    float y7 = motionEvent.getY(iFindPointerIndex);
                    float[] fArr = this.f8049f;
                    int i11 = this.f8046c;
                    int i12 = (int) (x7 - fArr[i11]);
                    int i13 = (int) (y7 - this.f8050g[i11]);
                    p(this.f8063t.getLeft() + i12, this.f8063t.getTop() + i13, i12, i13);
                    D(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (i8 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(i8);
                if (x(pointerId2)) {
                    float x8 = motionEvent.getX(i8);
                    float y8 = motionEvent.getY(i8);
                    float f7 = x8 - this.f8047d[pointerId2];
                    float f8 = y8 - this.f8048e[pointerId2];
                    B(f7, f8, pointerId2);
                    if (this.f8044a != 1) {
                        View viewR2 = r((int) x8, (int) y8);
                        if (d(viewR2, f7, f8) && I(viewR2, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i8++;
            }
            D(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.f8044a == 1) {
                n(0.0f, 0.0f);
            }
            a();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x9 = motionEvent.getX(actionIndex);
            float y9 = motionEvent.getY(actionIndex);
            C(x9, y9, pointerId3);
            if (this.f8044a != 0) {
                if (v((int) x9, (int) y9)) {
                    I(this.f8063t, pointerId3);
                    return;
                }
                return;
            } else {
                I(r((int) x9, (int) y9), pointerId3);
                int i14 = this.f8051h[pointerId3];
                int i15 = this.f8060q;
                if ((i14 & i15) != 0) {
                    this.f8062s.h(i14 & i15, pointerId3);
                    return;
                }
                return;
            }
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.f8044a == 1 && pointerId4 == this.f8046c) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i8 >= pointerCount2) {
                    i7 = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i8);
                if (pointerId5 != this.f8046c) {
                    View viewR3 = r((int) motionEvent.getX(i8), (int) motionEvent.getY(i8));
                    View view = this.f8063t;
                    if (viewR3 == view && I(view, pointerId5)) {
                        i7 = this.f8046c;
                        break;
                    }
                }
                i8++;
            }
            if (i7 == -1) {
                A();
            }
        }
        h(pointerId4);
    }
}
