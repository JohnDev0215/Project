package androidx.recyclerview.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.customview.view.AbsSavedState;
import defpackage.AbstractC0299Qm;
import defpackage.AbstractC0678dn;
import defpackage.AbstractC0697e6;
import defpackage.AbstractC1251o3;
import defpackage.AbstractC1306p3;
import defpackage.AbstractC1528t6;
import defpackage.C0474a6;
import defpackage.C0530b6;
import defpackage.C0585c5;
import defpackage.C0586c6;
import defpackage.C0641d5;
import defpackage.C0752f5;
import defpackage.C0809g6;
import defpackage.C0862h3;
import defpackage.C1087l5;
import defpackage.C1363q5;
import defpackage.C1472s5;
import defpackage.C1638v6;
import defpackage.I5;
import defpackage.InterfaceC0696e5;
import defpackage.InterfaceC1583u6;
import defpackage.J5;
import defpackage.K5;
import defpackage.L5;
import defpackage.M5;
import defpackage.N5;
import defpackage.P5;
import defpackage.Q5;
import defpackage.R5;
import defpackage.RunnableC0642d6;
import defpackage.RunnableC1582u5;
import defpackage.S5;
import defpackage.W5;
import defpackage.X5;
import defpackage.Y5;
import defpackage.Z5;
import defpackage.Z7;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup {
    public static final int[] H0 = {R.attr.nestedScrollingEnabled};
    public static final int[] I0 = {R.attr.clipToPadding};
    public static final boolean J0;
    public static final boolean K0;
    public static final boolean L0;
    public static final boolean M0;
    public static final Class[] N0;
    public static final Interpolator O0;
    public final RectF A;
    public final int[] A0;
    public W5 B;
    public final int[] B0;
    public final ArrayList C;
    public final int[] C0;
    public final ArrayList D;
    public final int[] D0;
    public Y5 E;
    public final List E0;
    public boolean F;
    public Runnable F0;
    public boolean G;
    public final InterfaceC1583u6 G0;
    public boolean H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f19J;
    public boolean K;
    public int L;
    public boolean M;
    public final AccessibilityManager N;
    public boolean O;
    public boolean P;
    public int Q;
    public int R;
    public N5 S;
    public EdgeEffect T;
    public EdgeEffect U;
    public EdgeEffect V;
    public EdgeEffect W;
    public Q5 a0;
    public int b0;
    public int c0;
    public VelocityTracker d0;
    public int e0;
    public int f0;
    public int g0;
    public int h0;
    public int i0;
    public final int j0;
    public final int k0;
    public float l0;
    public float m0;
    public boolean n0;
    public final RunnableC0642d6 o0;
    public RunnableC1582u5 p0;
    public C1472s5 q0;
    public final C0586c6 r0;
    public final C0474a6 s;
    public List s0;
    public SavedState t;
    public boolean t0;
    public C0585c5 u;
    public boolean u0;
    public C0752f5 v;
    public P5 v0;
    public final C1638v6 w;
    public boolean w0;
    public boolean x;
    public C0809g6 x0;
    public final Rect y;
    public final int[] y0;
    public final Rect z;
    public C0862h3 z0;

    static {
        J0 = Build.VERSION.SDK_INT >= 23;
        K0 = true;
        L0 = false;
        M0 = false;
        Class cls = Integer.TYPE;
        N0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        O0 = new J5();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static void c() {
        throw null;
    }

    public static void p(View view) {
        if (view == null) {
            return;
        }
    }

    public static void q(View view, Rect rect) {
        X5 x5 = (X5) view.getLayoutParams();
        Rect rect2 = x5.a;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) x5).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) x5).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) x5).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) x5).bottomMargin);
    }

    public final void A(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.c0) {
            int i = actionIndex == 0 ? 1 : 0;
            this.c0 = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.g0 = x;
            this.e0 = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.h0 = y;
            this.f0 = y;
        }
    }

    public void B() {
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void C() {
        /*
            r5 = this;
            Q5 r0 = r5.a0
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L10
            W5 r0 = r5.B
            boolean r0 = r0.c0()
            if (r0 == 0) goto L10
            r0 = 1
            goto L11
        L10:
            r0 = 0
        L11:
            if (r0 == 0) goto L19
            c5 r0 = r5.u
            r0.j()
            goto L1e
        L19:
            c5 r0 = r5.u
            r0.c()
        L1e:
            boolean r0 = r5.t0
            if (r0 != 0) goto L29
            boolean r0 = r5.u0
            if (r0 == 0) goto L27
            goto L29
        L27:
            r0 = 0
            goto L2a
        L29:
            r0 = 1
        L2a:
            c6 r3 = r5.r0
            boolean r4 = r5.H
            if (r4 == 0) goto L3e
            Q5 r4 = r5.a0
            if (r4 == 0) goto L3e
            if (r0 != 0) goto L3c
            W5 r4 = r5.B
            boolean r4 = r4.g
            if (r4 == 0) goto L3e
        L3c:
            r4 = 1
            goto L3f
        L3e:
            r4 = 0
        L3f:
            r3.j = r4
            c6 r3 = r5.r0
            boolean r4 = r3.j
            if (r4 == 0) goto L5b
            if (r0 == 0) goto L5b
            Q5 r0 = r5.a0
            if (r0 == 0) goto L57
            W5 r0 = r5.B
            boolean r0 = r0.c0()
            if (r0 == 0) goto L57
            r0 = 1
            goto L58
        L57:
            r0 = 0
        L58:
            if (r0 == 0) goto L5b
            goto L5c
        L5b:
            r1 = 0
        L5c:
            r3.k = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.C():void");
    }

    public final void D(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.y.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof X5) {
            X5 x5 = (X5) layoutParams;
            if (!x5.b) {
                Rect rect = x5.a;
                Rect rect2 = this.y;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.y);
            offsetRectIntoDescendantCoords(view, this.y);
        }
        this.B.Z(this, view, this.y, !this.H, view2 == null);
    }

    public final void E() {
        VelocityTracker velocityTracker = this.d0;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean zIsFinished = false;
        M(0);
        EdgeEffect edgeEffect = this.T;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.T.isFinished();
        }
        EdgeEffect edgeEffect2 = this.U;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.U.isFinished();
        }
        EdgeEffect edgeEffect3 = this.V;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.V.isFinished();
        }
        EdgeEffect edgeEffect4 = this.W;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.W.isFinished();
        }
        if (zIsFinished) {
            AbstractC1251o3.y(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean F(int r11, int r12, android.view.MotionEvent r13) {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.F(int, int, android.view.MotionEvent):boolean");
    }

    public void G(W5 w5) {
        if (w5 == this.B) {
            return;
        }
        H(0);
        RunnableC0642d6 runnableC0642d6 = this.o0;
        runnableC0642d6.y.removeCallbacks(runnableC0642d6);
        runnableC0642d6.u.abortAnimation();
        if (this.B != null) {
            Q5 q5 = this.a0;
            if (q5 != null) {
                q5.c();
            }
            this.B.X(this.s);
            this.B.Y(this.s);
            this.s.a();
            if (this.F) {
                W5 w52 = this.B;
                C0474a6 c0474a6 = this.s;
                w52.h = false;
                w52.G(this, c0474a6);
            }
            this.B.b0(null);
            this.B = null;
        } else {
            this.s.a();
        }
        C0752f5 c0752f5 = this.v;
        C0641d5 c0641d5 = c0752f5.b;
        c0641d5.a = 0L;
        C0641d5 c0641d52 = c0641d5.b;
        if (c0641d52 != null) {
            c0641d52.d();
        }
        int size = c0752f5.c.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            InterfaceC0696e5 interfaceC0696e5 = c0752f5.a;
            View view = (View) c0752f5.c.get(size);
            Objects.requireNonNull((L5) interfaceC0696e5);
            p(view);
            c0752f5.c.remove(size);
        }
        L5 l5 = (L5) c0752f5.a;
        int iB = l5.b();
        for (int i = 0; i < iB; i++) {
            View viewA = l5.a(i);
            RecyclerView recyclerView = l5.a;
            Objects.requireNonNull(recyclerView);
            p(viewA);
            recyclerView.x();
            viewA.clearAnimation();
        }
        l5.a.removeAllViews();
        this.B = w5;
        if (w5 != null) {
            if (w5.b != null) {
                throw new IllegalArgumentException("LayoutManager " + w5 + " is already attached to a RecyclerView:" + w5.b.m());
            }
            w5.b0(this);
            if (this.F) {
                W5 w53 = this.B;
                w53.h = true;
                w53.E();
            }
        }
        this.s.e();
        requestLayout();
    }

    public void H(int i) {
        if (i == this.b0) {
            return;
        }
        this.b0 = i;
        if (i != 2) {
            RunnableC0642d6 runnableC0642d6 = this.o0;
            runnableC0642d6.y.removeCallbacks(runnableC0642d6);
            runnableC0642d6.u.abortAnimation();
        }
        W5 w5 = this.B;
        if (w5 != null) {
            w5.U(i);
        }
        B();
        List list = this.s0;
        if (list == null) {
            return;
        }
        int size = list.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            } else {
                Objects.requireNonNull((Z5) this.s0.get(size));
            }
        }
    }

    public void I(int i, int i2) {
        int iRound;
        W5 w5 = this.B;
        if (w5 == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        int i3 = !w5.b() ? 0 : i;
        int i4 = !this.B.c() ? 0 : i2;
        if (i3 == 0 && i4 == 0) {
            return;
        }
        RunnableC0642d6 runnableC0642d6 = this.o0;
        Objects.requireNonNull(runnableC0642d6);
        int iAbs = Math.abs(i3);
        int iAbs2 = Math.abs(i4);
        boolean z = iAbs > iAbs2;
        int iSqrt = (int) Math.sqrt(0);
        int iSqrt2 = (int) Math.sqrt((i4 * i4) + (i3 * i3));
        RecyclerView recyclerView = runnableC0642d6.y;
        int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
        int i5 = width / 2;
        float f = width;
        float f2 = i5;
        float fSin = (((float) Math.sin((Math.min(1.0f, (iSqrt2 * 1.0f) / f) - 0.5f) * 0.47123894f)) * f2) + f2;
        if (iSqrt > 0) {
            iRound = Math.round(Math.abs(fSin / iSqrt) * 1000.0f) * 4;
        } else {
            if (!z) {
                iAbs = iAbs2;
            }
            iRound = (int) (((iAbs / f) + 1.0f) * 300.0f);
        }
        int iMin = Math.min(iRound, 2000);
        Interpolator interpolator = O0;
        if (runnableC0642d6.v != interpolator) {
            runnableC0642d6.v = interpolator;
            runnableC0642d6.u = new OverScroller(runnableC0642d6.y.getContext(), interpolator);
        }
        runnableC0642d6.y.H(2);
        runnableC0642d6.t = 0;
        runnableC0642d6.s = 0;
        runnableC0642d6.u.startScroll(0, 0, i3, i4, iMin);
        if (Build.VERSION.SDK_INT < 23) {
            runnableC0642d6.u.computeScrollOffset();
        }
        runnableC0642d6.a();
    }

    public void J() {
        int i = this.I + 1;
        this.I = i;
        if (i == 1) {
            this.f19J = false;
        }
    }

    public boolean K(int i, int i2) {
        return s().g(i, i2);
    }

    public void L(boolean z) {
        if (this.I < 1) {
            this.I = 1;
        }
        if (!z) {
            this.f19J = false;
        }
        if (this.I == 1) {
            if (z && this.f19J) {
                W5 w5 = this.B;
            }
            this.f19J = false;
        }
        this.I--;
    }

    public void M(int i) {
        s().h(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList arrayList, int i, int i2) {
        W5 w5 = this.B;
        if (w5 == null || !w5.D()) {
            super.addFocusables(arrayList, i, i2);
        }
    }

    public final void b() {
        E();
        H(0);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof X5) && this.B.d((X5) layoutParams);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        W5 w5 = this.B;
        if (w5 != null && w5.b()) {
            return this.B.f(this.r0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        W5 w5 = this.B;
        if (w5 != null && w5.b()) {
            return this.B.g(this.r0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        W5 w5 = this.B;
        if (w5 != null && w5.b()) {
            return this.B.h(this.r0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        W5 w5 = this.B;
        if (w5 != null && w5.c()) {
            return this.B.i(this.r0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        W5 w5 = this.B;
        if (w5 != null && w5.c()) {
            return this.B.j(this.r0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        W5 w5 = this.B;
        if (w5 != null && w5.c()) {
            return this.B.k(this.r0);
        }
        return 0;
    }

    public void d(int i, int i2) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.T;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            zIsFinished = false;
        } else {
            this.T.onRelease();
            zIsFinished = this.T.isFinished();
        }
        EdgeEffect edgeEffect2 = this.V;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.V.onRelease();
            zIsFinished |= this.V.isFinished();
        }
        EdgeEffect edgeEffect3 = this.U;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.U.onRelease();
            zIsFinished |= this.U.isFinished();
        }
        EdgeEffect edgeEffect4 = this.W;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.W.onRelease();
            zIsFinished |= this.W.isFinished();
        }
        if (zIsFinished) {
            AbstractC1251o3.y(this);
        }
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return s().a(f, f2, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f, float f2) {
        return s().b(f, f2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return s().c(i, i2, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return s().d(i, i2, i3, i4, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        int size = this.C.size();
        boolean z2 = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            C1363q5 c1363q5 = (C1363q5) ((S5) this.C.get(i));
            if (c1363q5.q != c1363q5.s.getWidth() || c1363q5.r != c1363q5.s.getHeight()) {
                c1363q5.q = c1363q5.s.getWidth();
                c1363q5.r = c1363q5.s.getHeight();
                c1363q5.f(0);
            } else if (c1363q5.A != 0) {
                if (c1363q5.t) {
                    int i2 = c1363q5.q;
                    int i3 = c1363q5.e;
                    int i4 = i2 - i3;
                    int i5 = c1363q5.l;
                    int i6 = c1363q5.k;
                    int i7 = i5 - (i6 / 2);
                    c1363q5.c.setBounds(0, 0, i3, i6);
                    c1363q5.d.setBounds(0, 0, c1363q5.f, c1363q5.r);
                    if (AbstractC1251o3.k(c1363q5.s) == 1) {
                        c1363q5.d.draw(canvas);
                        canvas.translate(c1363q5.e, i7);
                        canvas.scale(-1.0f, 1.0f);
                        c1363q5.c.draw(canvas);
                        canvas.scale(1.0f, 1.0f);
                        canvas.translate(-c1363q5.e, -i7);
                    } else {
                        canvas.translate(i4, 0.0f);
                        c1363q5.d.draw(canvas);
                        canvas.translate(0.0f, i7);
                        c1363q5.c.draw(canvas);
                        canvas.translate(-i4, -i7);
                    }
                }
                if (c1363q5.u) {
                    int i8 = c1363q5.r;
                    int i9 = c1363q5.i;
                    int i10 = c1363q5.o;
                    int i11 = c1363q5.n;
                    c1363q5.g.setBounds(0, 0, i11, i9);
                    c1363q5.h.setBounds(0, 0, c1363q5.q, c1363q5.j);
                    canvas.translate(0.0f, i8 - i9);
                    c1363q5.h.draw(canvas);
                    canvas.translate(i10 - (i11 / 2), 0.0f);
                    c1363q5.g.draw(canvas);
                    canvas.translate(-r7, -r4);
                }
            }
            i++;
        }
        EdgeEffect edgeEffect = this.T;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.x ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.T;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.U;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.x) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.U;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.V;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.x ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.V;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.W;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.x) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.W;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(iSave4);
        }
        if ((z || this.a0 == null || this.C.size() <= 0 || !this.a0.d()) ? z : true) {
            AbstractC1251o3.y(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public void e() {
        if (!this.H) {
            Trace.beginSection("RV FullInvalidate");
            g();
            Trace.endSection();
        } else if (this.u.g()) {
            Objects.requireNonNull(this.u);
            if (this.u.g()) {
                Trace.beginSection("RV FullInvalidate");
                g();
                Trace.endSection();
            }
        }
    }

    public void f(int i, int i2) {
        setMeasuredDimension(W5.e(i, getPaddingRight() + getPaddingLeft(), AbstractC1251o3.m(this)), W5.e(i2, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x00f5  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View focusSearch(android.view.View r10, int r11) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public void g() {
        Log.e("RecyclerView", "No adapter attached; skipping layout");
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        W5 w5 = this.B;
        if (w5 != null) {
            return w5.l();
        }
        StringBuilder sbF = Z7.f("RecyclerView has no LayoutManager");
        sbF.append(m());
        throw new IllegalStateException(sbF.toString());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        W5 w5 = this.B;
        if (w5 != null) {
            return w5.m(getContext(), attributeSet);
        }
        StringBuilder sbF = Z7.f("RecyclerView has no LayoutManager");
        sbF.append(m());
        throw new IllegalStateException(sbF.toString());
    }

    @Override // android.view.View
    public int getBaseline() {
        W5 w5 = this.B;
        if (w5 == null) {
            return super.getBaseline();
        }
        Objects.requireNonNull(w5);
        return -1;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i, int i2) {
        return super.getChildDrawingOrder(i, i2);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.x;
    }

    public boolean h(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return s().d(i, i2, i3, i4, iArr, i5);
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return s().f(0);
    }

    public void i() {
        if (this.W != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.S.a(this);
        this.W = edgeEffectA;
        if (this.x) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.F;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return s().d;
    }

    public void j() {
        if (this.T != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.S.a(this);
        this.T = edgeEffectA;
        if (this.x) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void k() {
        if (this.V != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.S.a(this);
        this.V = edgeEffectA;
        if (this.x) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void l() {
        if (this.U != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.S.a(this);
        this.U = edgeEffectA;
        if (this.x) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public String m() {
        StringBuilder sbF = Z7.f(" ");
        sbF.append(super.toString());
        sbF.append(", adapter:");
        sbF.append((Object) null);
        sbF.append(", layout:");
        sbF.append(this.B);
        sbF.append(", context:");
        sbF.append(getContext());
        return sbF.toString();
    }

    public View n(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    public void o(int i, boolean z) {
        int iD = this.v.d();
        for (int i2 = 0; i2 < iD; i2++) {
            p(this.v.c(i2));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.Q = 0;
        this.F = true;
        this.H = this.H && !isLayoutRequested();
        W5 w5 = this.B;
        if (w5 != null) {
            w5.h = true;
            w5.E();
        }
        this.w0 = false;
        if (K0) {
            RunnableC1582u5 runnableC1582u5 = (RunnableC1582u5) RunnableC1582u5.w.get();
            this.p0 = runnableC1582u5;
            if (runnableC1582u5 == null) {
                this.p0 = new RunnableC1582u5();
                Display displayG = AbstractC1251o3.g(this);
                float f = 60.0f;
                if (!isInEditMode() && displayG != null) {
                    float refreshRate = displayG.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f = refreshRate;
                    }
                }
                RunnableC1582u5 runnableC1582u52 = this.p0;
                runnableC1582u52.u = (long) (1.0E9f / f);
                RunnableC1582u5.w.set(runnableC1582u52);
            }
            this.p0.s.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        RunnableC1582u5 runnableC1582u5;
        super.onDetachedFromWindow();
        Q5 q5 = this.a0;
        if (q5 != null) {
            q5.c();
        }
        H(0);
        RunnableC0642d6 runnableC0642d6 = this.o0;
        runnableC0642d6.y.removeCallbacks(runnableC0642d6);
        runnableC0642d6.u.abortAnimation();
        W5 w5 = this.B;
        this.F = false;
        if (w5 != null) {
            C0474a6 c0474a6 = this.s;
            w5.h = false;
            w5.G(this, c0474a6);
        }
        this.E0.clear();
        removeCallbacks(this.F0);
        Objects.requireNonNull(this.w);
        while (AbstractC1528t6.a.b() != null) {
        }
        if (!K0 || (runnableC1582u5 = this.p0) == null) {
            return;
        }
        runnableC1582u5.s.remove(this);
        this.p0 = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            Objects.requireNonNull((S5) this.C.get(i));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0065  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            W5 r0 = r5.B
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r6.getAction()
            r2 = 8
            if (r0 != r2) goto L72
            int r0 = r6.getSource()
            r0 = r0 & 2
            r2 = 0
            if (r0 == 0) goto L37
            W5 r0 = r5.B
            boolean r0 = r0.c()
            if (r0 == 0) goto L27
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L28
        L27:
            r0 = 0
        L28:
            W5 r3 = r5.B
            boolean r3 = r3.b()
            if (r3 == 0) goto L5c
            r3 = 10
            float r3 = r6.getAxisValue(r3)
            goto L5d
        L37:
            int r0 = r6.getSource()
            r3 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r3
            if (r0 == 0) goto L5b
            r0 = 26
            float r0 = r6.getAxisValue(r0)
            W5 r3 = r5.B
            boolean r3 = r3.c()
            if (r3 == 0) goto L50
            float r0 = -r0
            goto L5c
        L50:
            W5 r3 = r5.B
            boolean r3 = r3.b()
            if (r3 == 0) goto L5b
            r3 = r0
            r0 = 0
            goto L5d
        L5b:
            r0 = 0
        L5c:
            r3 = 0
        L5d:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L65
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L72
        L65:
            float r2 = r5.l0
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.m0
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.F(r2, r0, r6)
        L72:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.E = null;
        }
        int size = this.D.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                z = false;
                break;
            }
            C1363q5 c1363q5 = (C1363q5) ((Y5) this.D.get(i));
            if (c1363q5.d(this, motionEvent) && action != 3) {
                this.E = c1363q5;
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            b();
            return true;
        }
        W5 w5 = this.B;
        if (w5 == null) {
            return false;
        }
        boolean zB = w5.b();
        boolean zC = this.B.c();
        if (this.d0 == null) {
            this.d0 = VelocityTracker.obtain();
        }
        this.d0.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.K) {
                this.K = false;
            }
            this.c0 = motionEvent.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.g0 = x;
            this.e0 = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.h0 = y;
            this.f0 = y;
            if (this.b0 == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                H(1);
            }
            int[] iArr = this.C0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = zB ? 1 : 0;
            if (zC) {
                i2 |= 2;
            }
            K(i2, 0);
        } else if (actionMasked == 1) {
            this.d0.clear();
            M(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.c0);
            if (iFindPointerIndex < 0) {
                StringBuilder sbF = Z7.f("Error processing scroll; pointer index for id ");
                sbF.append(this.c0);
                sbF.append(" not found. Did any MotionEvents get skipped?");
                Log.e("RecyclerView", sbF.toString());
                return false;
            }
            int x2 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.b0 != 1) {
                int i3 = x2 - this.e0;
                int i4 = y2 - this.f0;
                if (!zB || Math.abs(i3) <= this.i0) {
                    z2 = false;
                } else {
                    this.g0 = x2;
                    z2 = true;
                }
                if (zC && Math.abs(i4) > this.i0) {
                    this.h0 = y2;
                    z2 = true;
                }
                if (z2) {
                    H(1);
                }
            }
        } else if (actionMasked == 3) {
            b();
        } else if (actionMasked == 5) {
            this.c0 = motionEvent.getPointerId(actionIndex);
            int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.g0 = x3;
            this.e0 = x3;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.h0 = y3;
            this.f0 = y3;
        } else if (actionMasked == 6) {
            A(motionEvent);
        }
        return this.b0 == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Trace.beginSection("RV OnLayout");
        g();
        Trace.endSection();
        this.H = true;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        W5 w5 = this.B;
        if (w5 == null) {
            f(i, i2);
            return;
        }
        if (w5.B()) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.B.P(i, i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                return;
            } else {
                return;
            }
        }
        if (this.M) {
            J();
            y();
            C();
            z(true);
            C0586c6 c0586c6 = this.r0;
            if (c0586c6.k) {
                c0586c6.g = true;
            } else {
                this.u.c();
                this.r0.g = false;
            }
            this.M = false;
            L(false);
        } else if (this.r0.k) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        this.r0.e = 0;
        J();
        this.B.P(i, i2);
        L(false);
        this.r0.g = false;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (u()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.t = savedState;
        super.onRestoreInstanceState(savedState.s);
        W5 w5 = this.B;
        if (w5 == null || (parcelable2 = this.t.u) == null) {
            return;
        }
        w5.S(parcelable2);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.t;
        if (savedState2 != null) {
            savedState.u = savedState2.u;
        } else {
            W5 w5 = this.B;
            if (w5 != null) {
                savedState.u = w5.T();
            } else {
                savedState.u = null;
            }
        }
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        this.W = null;
        this.U = null;
        this.V = null;
        this.T = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x012b  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r19) {
        /*
            Method dump skipped, instruction units count: 882
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public long r() {
        if (K0) {
            return System.nanoTime();
        }
        return 0L;
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z) {
        p(view);
        view.clearAnimation();
        p(view);
        x();
        super.removeDetachedView(view, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.B.R(this, view, view2) && view2 != null) {
            D(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.B.Z(this, view, rect, z, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.D.size();
        for (int i = 0; i < size; i++) {
            Objects.requireNonNull((C1363q5) ((Y5) this.D.get(i)));
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.I == 0) {
            super.requestLayout();
        } else {
            this.f19J = true;
        }
    }

    public final C0862h3 s() {
        if (this.z0 == null) {
            this.z0 = new C0862h3(this);
        }
        return this.z0;
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        W5 w5 = this.B;
        if (w5 == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        boolean zB = w5.b();
        boolean zC = this.B.c();
        if (zB || zC) {
            if (!zB) {
                i = 0;
            }
            if (!zC) {
                i2 = 0;
            }
            F(i, i2, null);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (u()) {
            int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
            this.L |= contentChangeTypes != 0 ? contentChangeTypes : 0;
            i = 1;
        }
        if (i != 0) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.x) {
            this.W = null;
            this.U = null;
            this.V = null;
            this.T = null;
        }
        this.x = z;
        super.setClipToPadding(z);
        if (this.H) {
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        C0862h3 c0862h3S = s();
        if (c0862h3S.d) {
            AbstractC1251o3.J(c0862h3S.c);
        }
        c0862h3S.d = z;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return s().g(i, 0);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        s().h(0);
    }

    public boolean t() {
        return !this.H || this.O || this.u.g();
    }

    public boolean u() {
        return this.Q > 0;
    }

    public void v() {
        int iD = this.v.d();
        for (int i = 0; i < iD; i++) {
            ((X5) this.v.c(i).getLayoutParams()).b = true;
        }
        C0474a6 c0474a6 = this.s;
        if (c0474a6.c.size() <= 0) {
            return;
        }
        throw null;
    }

    public void w(int i, int i2, boolean z) {
        int iD = this.v.d();
        for (int i3 = 0; i3 < iD; i3++) {
            p(this.v.c(i3));
        }
        C0474a6 c0474a6 = this.s;
        int size = c0474a6.c.size();
        do {
            size--;
            if (size < 0) {
                requestLayout();
                return;
            }
        } while (((AbstractC0697e6) c0474a6.c.get(size)) == null);
        throw null;
    }

    public void x() {
    }

    public void y() {
        this.Q++;
    }

    public void z(boolean z) {
        int i = this.Q - 1;
        this.Q = i;
        if (i < 1) {
            this.Q = 0;
            if (z) {
                int i2 = this.L;
                this.L = 0;
                if (i2 != 0) {
                    AccessibilityManager accessibilityManager = this.N;
                    if (accessibilityManager != null && accessibilityManager.isEnabled()) {
                        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                        accessibilityEventObtain.setEventType(2048);
                        accessibilityEventObtain.setContentChangeTypes(i2);
                        sendAccessibilityEventUnchecked(accessibilityEventObtain);
                    }
                }
                int size = this.E0.size() - 1;
                if (size < 0) {
                    this.E0.clear();
                } else {
                    throw null;
                }
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArray;
        char c;
        char c2;
        Constructor constructor;
        Object[] objArr;
        super(context, attributeSet, i);
        int i2 = Build.VERSION.SDK_INT;
        this.s = new C0474a6(this);
        this.w = new C1638v6();
        this.y = new Rect();
        this.z = new Rect();
        this.A = new RectF();
        this.C = new ArrayList();
        this.D = new ArrayList();
        this.I = 0;
        this.O = false;
        this.P = false;
        this.Q = 0;
        this.R = 0;
        this.S = new N5();
        this.a0 = new C1087l5();
        this.b0 = 0;
        this.c0 = -1;
        this.l0 = Float.MIN_VALUE;
        this.m0 = Float.MIN_VALUE;
        boolean z = true;
        this.n0 = true;
        this.o0 = new RunnableC0642d6(this);
        this.q0 = K0 ? new C1472s5() : null;
        this.r0 = new C0586c6();
        this.t0 = false;
        this.u0 = false;
        this.v0 = new R5(this);
        this.w0 = false;
        this.y0 = new int[2];
        this.A0 = new int[2];
        this.B0 = new int[2];
        this.C0 = new int[2];
        this.D0 = new int[2];
        this.E0 = new ArrayList();
        this.F0 = new I5(this);
        this.G0 = new K5(this);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, I0, i, 0);
            this.x = typedArrayObtainStyledAttributes.getBoolean(0, true);
            typedArrayObtainStyledAttributes.recycle();
        } else {
            this.x = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.i0 = viewConfiguration.getScaledTouchSlop();
        this.l0 = AbstractC1306p3.b(viewConfiguration, context);
        this.m0 = i2 >= 26 ? viewConfiguration.getScaledVerticalScrollFactor() : AbstractC1306p3.a(viewConfiguration, context);
        this.j0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.k0 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.a0.a = this.v0;
        this.u = new C0585c5(new M5(this));
        this.v = new C0752f5(new L5(this));
        if (AbstractC1251o3.j(this) == 0 && i2 >= 26) {
            setImportantForAutofill(8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.N = (AccessibilityManager) getContext().getSystemService("accessibility");
        C0809g6 c0809g6 = new C0809g6(this);
        this.x0 = c0809g6;
        setAccessibilityDelegate(c0809g6.a);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.o0, i, 0);
            String string = typedArrayObtainStyledAttributes2.getString(5);
            if (typedArrayObtainStyledAttributes2.getInt(10, -1) == -1) {
                setDescendantFocusability(com.android.webview.R.bool.abc_action_bar_embed_tabs);
            }
            boolean z2 = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            this.G = z2;
            if (z2) {
                StateListDrawable stateListDrawable = (StateListDrawable) typedArrayObtainStyledAttributes2.getDrawable(3);
                Drawable drawable = typedArrayObtainStyledAttributes2.getDrawable(4);
                StateListDrawable stateListDrawable2 = (StateListDrawable) typedArrayObtainStyledAttributes2.getDrawable(1);
                Drawable drawable2 = typedArrayObtainStyledAttributes2.getDrawable(2);
                if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
                    StringBuilder sbF = Z7.f("Trying to set fast scroller without both required drawables.");
                    sbF.append(m());
                    throw new IllegalArgumentException(sbF.toString());
                }
                Resources resources = getContext().getResources();
                c = 3;
                c2 = 2;
                typedArray = typedArrayObtainStyledAttributes2;
                new C1363q5(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(AbstractC0299Qm.F2), resources.getDimensionPixelSize(AbstractC0299Qm.H2), resources.getDimensionPixelOffset(AbstractC0299Qm.G2));
            } else {
                typedArray = typedArrayObtainStyledAttributes2;
                c = 3;
                c2 = 2;
            }
            typedArray.recycle();
            if (string != null) {
                String strTrim = string.trim();
                if (!strTrim.isEmpty()) {
                    if (strTrim.charAt(0) == '.') {
                        strTrim = context.getPackageName() + strTrim;
                    } else if (!strTrim.contains(".")) {
                        strTrim = RecyclerView.class.getPackage().getName() + '.' + strTrim;
                    }
                    try {
                        Class<? extends U> clsAsSubclass = (isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).loadClass(strTrim).asSubclass(W5.class);
                        try {
                            constructor = clsAsSubclass.getConstructor(N0);
                            objArr = new Object[4];
                            objArr[0] = context;
                            objArr[1] = attributeSet;
                            objArr[c2] = Integer.valueOf(i);
                            objArr[c] = 0;
                        } catch (NoSuchMethodException e) {
                            try {
                                constructor = clsAsSubclass.getConstructor(new Class[0]);
                                objArr = null;
                            } catch (NoSuchMethodException e2) {
                                e2.initCause(e);
                                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strTrim, e2);
                            }
                        }
                        constructor.setAccessible(true);
                        G((W5) constructor.newInstance(objArr));
                    } catch (ClassCastException e3) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strTrim, e3);
                    } catch (ClassNotFoundException e4) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strTrim, e4);
                    } catch (IllegalAccessException e5) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strTrim, e5);
                    } catch (InstantiationException e6) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strTrim, e6);
                    } catch (InvocationTargetException e7) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strTrim, e7);
                    }
                }
            }
            TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, H0, i, 0);
            z = typedArrayObtainStyledAttributes3.getBoolean(0, true);
            typedArrayObtainStyledAttributes3.recycle();
        } else {
            setDescendantFocusability(com.android.webview.R.bool.abc_action_bar_embed_tabs);
        }
        setNestedScrollingEnabled(z);
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new C0530b6();
        public Parcelable u;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.u = parcel.readParcelable(classLoader == null ? W5.class.getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.s, i);
            parcel.writeParcelable(this.u, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        W5 w5 = this.B;
        if (w5 != null) {
            return w5.n(layoutParams);
        }
        StringBuilder sbF = Z7.f("RecyclerView has no LayoutManager");
        sbF.append(m());
        throw new IllegalStateException(sbF.toString());
    }
}
