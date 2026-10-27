package defpackage;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ViewDragHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class qm3 {
    public static final a x = new a();
    public int a;
    public int b;
    public float[] d;
    public float[] e;
    public float[] f;
    public float[] g;
    public int[] h;
    public int[] i;
    public int[] j;
    public int k;
    public VelocityTracker l;
    public final float m;
    public float n;
    public int o;
    public final int p;
    public int q;
    public final OverScroller r;
    public final c s;
    public View t;
    public boolean u;
    public final ViewGroup v;
    public int c = -1;
    public final b w = new b();

    /* JADX INFO: compiled from: ViewDragHelper.java */
    public class a implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    /* JADX INFO: compiled from: ViewDragHelper.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            qm3.this.r(0);
        }
    }

    public qm3(Context context, ViewGroup viewGroup, c cVar) {
        if (viewGroup == null) {
            throw new NullPointerException("Parent view may not be null");
        }
        if (cVar == null) {
            throw new NullPointerException("Callback may not be null");
        }
        this.v = viewGroup;
        this.s = cVar;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        int i = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.p = i;
        this.o = i;
        this.b = viewConfiguration.getScaledTouchSlop();
        this.m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.r = new OverScroller(context, x);
    }

    public static qm3 i(ViewGroup viewGroup, float f, c cVar) {
        qm3 qm3Var = new qm3(viewGroup.getContext(), viewGroup, cVar);
        qm3Var.b = (int) ((1.0f / f) * qm3Var.b);
        return qm3Var;
    }

    public static boolean l(View view, int i, int i2) {
        return view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom();
    }

    public final void a() {
        b();
        if (this.a == 2) {
            OverScroller overScroller = this.r;
            overScroller.getCurrX();
            overScroller.getCurrY();
            overScroller.abortAnimation();
            this.s.i(this.t, overScroller.getCurrX(), overScroller.getCurrY());
        }
        r(0);
    }

    public final void b() {
        this.c = -1;
        float[] fArr = this.d;
        if (fArr != null) {
            Arrays.fill(fArr, 0.0f);
            Arrays.fill(this.e, 0.0f);
            Arrays.fill(this.f, 0.0f);
            Arrays.fill(this.g, 0.0f);
            Arrays.fill(this.h, 0);
            Arrays.fill(this.i, 0);
            Arrays.fill(this.j, 0);
            this.k = 0;
        }
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l = null;
        }
    }

    public final void c(View view, int i) {
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = this.v;
        if (parent != viewGroup) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + viewGroup + ")");
        }
        this.t = view;
        this.c = i;
        this.s.g(view, i);
        r(1);
    }

    public final boolean d(int i, float f, float f2, int i2) {
        float fAbs = Math.abs(f);
        float fAbs2 = Math.abs(f2);
        if ((this.h[i] & i2) != i2 || (this.q & i2) == 0 || (this.j[i] & i2) == i2 || (this.i[i] & i2) == i2) {
            return false;
        }
        int i3 = this.b;
        if (fAbs <= i3 && fAbs2 <= i3) {
            return false;
        }
        if (fAbs < fAbs2 * 0.5f) {
            this.s.getClass();
        }
        return (this.i[i] & i2) == 0 && fAbs > ((float) this.b);
    }

    public final boolean e(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        c cVar = this.s;
        boolean z = cVar.c(view) > 0;
        boolean z2 = cVar.d() > 0;
        if (!z || !z2) {
            return z ? Math.abs(f) > ((float) this.b) : z2 && Math.abs(f2) > ((float) this.b);
        }
        float f3 = (f2 * f2) + (f * f);
        int i = this.b;
        return f3 > ((float) (i * i));
    }

    public final void f(int i) {
        float[] fArr = this.d;
        if (fArr != null) {
            int i2 = this.k;
            int i3 = 1 << i;
            if ((i2 & i3) != 0) {
                fArr[i] = 0.0f;
                this.e[i] = 0.0f;
                this.f[i] = 0.0f;
                this.g[i] = 0.0f;
                this.h[i] = 0;
                this.i[i] = 0;
                this.j[i] = 0;
                this.k = (~i3) & i2;
            }
        }
    }

    public final int g(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        float width = this.v.getWidth() / 2;
        float fSin = (((float) Math.sin((Math.min(1.0f, Math.abs(i) / r3) - 0.5f) * 0.47123894f)) * width) + width;
        int iAbs = Math.abs(i2);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fSin / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f), 600);
    }

    public final boolean h() {
        if (this.a == 2) {
            OverScroller overScroller = this.r;
            boolean zComputeScrollOffset = overScroller.computeScrollOffset();
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int left = currX - this.t.getLeft();
            int top = currY - this.t.getTop();
            if (left != 0) {
                View view = this.t;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                view.offsetLeftAndRight(left);
            }
            if (top != 0) {
                View view2 = this.t;
                WeakHashMap<View, in3> weakHashMap2 = om3.a;
                view2.offsetTopAndBottom(top);
            }
            if (left != 0 || top != 0) {
                this.s.i(this.t, currX, currY);
            }
            if (zComputeScrollOffset && currX == overScroller.getFinalX() && currY == overScroller.getFinalY()) {
                overScroller.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                this.v.post(this.w);
            }
        }
        return this.a == 2;
    }

    public final View j(int i, int i2) {
        ViewGroup viewGroup = this.v;
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            this.s.getClass();
            View childAt = viewGroup.getChildAt(childCount);
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public final boolean k(int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int left = this.t.getLeft();
        int top = this.t.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        OverScroller overScroller = this.r;
        int i7 = 0;
        if (i5 == 0 && i6 == 0) {
            overScroller.abortAnimation();
            r(0);
            return false;
        }
        View view = this.t;
        int i8 = (int) this.n;
        int i9 = (int) this.m;
        int iAbs = Math.abs(i3);
        if (iAbs < i8) {
            i3 = 0;
        } else if (iAbs > i9) {
            i3 = i3 > 0 ? i9 : -i9;
        }
        int i10 = (int) this.n;
        int iAbs2 = Math.abs(i4);
        if (iAbs2 < i10) {
            i4 = i7;
        } else if (iAbs2 > i9) {
            if (i4 > 0) {
                i4 = i9;
            } else {
                i7 = -i9;
                i4 = i7;
            }
        }
        int iAbs3 = Math.abs(i5);
        int iAbs4 = Math.abs(i6);
        int iAbs5 = Math.abs(i3);
        int iAbs6 = Math.abs(i4);
        int i11 = iAbs5 + iAbs6;
        int i12 = iAbs3 + iAbs4;
        if (i3 != 0) {
            f = iAbs5;
            f2 = i11;
        } else {
            f = iAbs3;
            f2 = i12;
        }
        float f5 = f / f2;
        if (i4 != 0) {
            f3 = iAbs6;
            f4 = i11;
        } else {
            f3 = iAbs4;
            f4 = i12;
        }
        float f6 = f3 / f4;
        c cVar = this.s;
        overScroller.startScroll(left, top, i5, i6, (int) ((g(i6, i4, cVar.d()) * f6) + (g(i5, i3, cVar.c(view)) * f5)));
        r(2);
        return true;
    }

    public final void m(MotionEvent motionEvent) {
        int iFindPointerIndex;
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            b();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        c cVar = this.s;
        if (actionMasked == 0) {
            float x2 = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewJ = j((int) x2, (int) y);
            p(pointerId, x2, y);
            w(viewJ, pointerId);
            if ((this.q & this.h[pointerId]) != 0) {
                cVar.f(pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.a == 1) {
                n();
            }
            b();
            return;
        }
        if (actionMasked == 2) {
            if (this.a == 1) {
                int i2 = this.c;
                if (((this.k & (1 << i2)) != 0 ? 1 : 0) == 0 || (iFindPointerIndex = motionEvent.findPointerIndex(i2)) == -1) {
                    return;
                }
                float x3 = motionEvent.getX(iFindPointerIndex);
                float y2 = motionEvent.getY(iFindPointerIndex);
                float[] fArr = this.f;
                int i3 = this.c;
                int i4 = (int) (x3 - fArr[i3]);
                int i5 = (int) (y2 - this.g[i3]);
                int left = this.t.getLeft() + i4;
                int top = this.t.getTop() + i5;
                int left2 = this.t.getLeft();
                int top2 = this.t.getTop();
                if (i4 != 0) {
                    left = cVar.a(this.t, left);
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    this.t.offsetLeftAndRight(left - left2);
                }
                if (i5 != 0) {
                    top = cVar.b(this.t, top);
                    WeakHashMap<View, in3> weakHashMap2 = om3.a;
                    this.t.offsetTopAndBottom(top - top2);
                }
                if (i4 != 0 || i5 != 0) {
                    cVar.i(this.t, left, top);
                }
            } else {
                int pointerCount = motionEvent.getPointerCount();
                for (int i6 = 0; i6 < pointerCount; i6++) {
                    int pointerId2 = motionEvent.getPointerId(i6);
                    if ((this.k & (1 << pointerId2)) != 0) {
                        float x4 = motionEvent.getX(i6);
                        float y3 = motionEvent.getY(i6);
                        float f = x4 - this.d[pointerId2];
                        float f2 = y3 - this.e[pointerId2];
                        o(pointerId2, f, f2);
                        if (this.a == 1) {
                            break;
                        }
                        View viewJ2 = j((int) x4, (int) y3);
                        if (e(viewJ2, f, f2) && w(viewJ2, pointerId2)) {
                            break;
                        }
                    }
                }
            }
            q(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.a == 1) {
                this.u = true;
                cVar.j(this.t, 0.0f, 0.0f);
                this.u = false;
                if (this.a == 1) {
                    r(0);
                }
            }
            b();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x5 = motionEvent.getX(actionIndex);
            float y4 = motionEvent.getY(actionIndex);
            p(pointerId3, x5, y4);
            if (this.a != 0) {
                if (l(this.t, (int) x5, (int) y4)) {
                    w(this.t, pointerId3);
                    return;
                }
                return;
            } else {
                w(j((int) x5, (int) y4), pointerId3);
                if ((this.q & this.h[pointerId3]) != 0) {
                    cVar.f(pointerId3);
                    return;
                }
                return;
            }
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.a == 1 && pointerId4 == this.c) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i >= pointerCount2) {
                    i = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i);
                if (pointerId5 != this.c) {
                    View viewJ3 = j((int) motionEvent.getX(i), (int) motionEvent.getY(i));
                    View view = this.t;
                    if (viewJ3 == view && w(view, pointerId5)) {
                        i = this.c;
                        break;
                    }
                }
                i++;
            }
            if (i == -1) {
                n();
            }
        }
        f(pointerId4);
    }

    public final void n() {
        VelocityTracker velocityTracker = this.l;
        float f = this.m;
        velocityTracker.computeCurrentVelocity(1000, f);
        float xVelocity = this.l.getXVelocity(this.c);
        float f2 = this.n;
        float fAbs = Math.abs(xVelocity);
        float f3 = 0.0f;
        if (fAbs < f2) {
            xVelocity = 0.0f;
        } else if (fAbs > f) {
            xVelocity = xVelocity > 0.0f ? f : -f;
        }
        float yVelocity = this.l.getYVelocity(this.c);
        float f4 = this.n;
        float fAbs2 = Math.abs(yVelocity);
        if (fAbs2 >= f4) {
            if (fAbs2 > f) {
                if (yVelocity <= 0.0f) {
                    f = -f;
                }
                f3 = f;
            } else {
                f3 = yVelocity;
            }
        }
        this.u = true;
        this.s.j(this.t, xVelocity, f3);
        this.u = false;
        if (this.a == 1) {
            r(0);
        }
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
    /* JADX WARN: Type inference failed for: r2v1, types: [qm3$c] */
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
    public final void o(int i, float f, float f2) {
        boolean zD = d(i, f, f2, 1);
        ?? r0 = zD;
        if (d(i, f2, f, 4)) {
            r0 = (zD ? 1 : 0) | 4;
        }
        ?? r02 = r0;
        if (d(i, f, f2, 2)) {
            r02 = (r0 == true ? 1 : 0) | 2;
        }
        ?? r03 = r02;
        if (d(i, f2, f, 8)) {
            r03 = (r02 == true ? 1 : 0) | 8;
        }
        if (r03 != 0) {
            int[] iArr = this.i;
            iArr[i] = iArr[i] | r03;
            this.s.e(r03, i);
        }
    }

    public final void p(int i, float f, float f2) {
        float[] fArr = this.d;
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
                float[] fArr6 = this.e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.d = fArr2;
            this.e = fArr3;
            this.f = fArr4;
            this.g = fArr5;
            this.h = iArr;
            this.i = iArr2;
            this.j = iArr3;
        }
        float[] fArr9 = this.d;
        this.f[i] = f;
        fArr9[i] = f;
        float[] fArr10 = this.e;
        this.g[i] = f2;
        fArr10[i] = f2;
        int[] iArr7 = this.h;
        int i3 = (int) f;
        int i4 = (int) f2;
        ViewGroup viewGroup = this.v;
        int i5 = i3 < viewGroup.getLeft() + this.o ? 1 : 0;
        if (i4 < viewGroup.getTop() + this.o) {
            i5 |= 4;
        }
        if (i3 > viewGroup.getRight() - this.o) {
            i5 |= 2;
        }
        if (i4 > viewGroup.getBottom() - this.o) {
            i5 |= 8;
        }
        iArr7[i] = i5;
        this.k = (1 << i) | this.k;
    }

    public final void q(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if ((this.k & (1 << pointerId)) != 0) {
                float x2 = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.f[pointerId] = x2;
                this.g[pointerId] = y;
            }
        }
    }

    public final void r(int i) {
        this.v.removeCallbacks(this.w);
        if (this.a != i) {
            this.a = i;
            this.s.h(i);
            if (this.a == 0) {
                this.t = null;
            }
        }
    }

    public final void s(float f) {
        this.n = f;
    }

    public final boolean t(int i, int i2) {
        if (this.u) {
            return k(i, i2, (int) this.l.getXVelocity(this.c), (int) this.l.getYVelocity(this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean u(android.view.MotionEvent r19) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qm3.u(android.view.MotionEvent):boolean");
    }

    public final boolean v(View view, int i, int i2) {
        this.t = view;
        this.c = -1;
        boolean zK = k(i, i2, 0, 0);
        if (!zK && this.a == 0 && this.t != null) {
            this.t = null;
        }
        return zK;
    }

    public final boolean w(View view, int i) {
        if (view == this.t && this.c == i) {
            return true;
        }
        if (view == null || !this.s.k(view, i)) {
            return false;
        }
        this.c = i;
        c(view, i);
        return true;
    }

    /* JADX INFO: compiled from: ViewDragHelper.java */
    public static abstract class c {
        public abstract int a(View view, int i);

        public abstract int b(View view, int i);

        public int c(View view) {
            return 0;
        }

        public int d() {
            return 0;
        }

        public abstract void h(int i);

        public abstract void i(View view, int i, int i2);

        public abstract void j(View view, float f, float f2);

        public abstract boolean k(View view, int i);

        public void f(int i) {
        }

        public void e(int i, int i2) {
        }

        public void g(View view, int i) {
        }
    }
}
