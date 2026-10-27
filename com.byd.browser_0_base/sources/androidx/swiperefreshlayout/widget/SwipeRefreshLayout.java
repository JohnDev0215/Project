package androidx.swiperefreshlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ListView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import defpackage.bz;
import defpackage.et;
import defpackage.hx2;
import defpackage.in3;
import defpackage.jx2;
import defpackage.kx2;
import defpackage.om3;
import defpackage.ql1;
import defpackage.rl1;
import defpackage.sl1;
import defpackage.tl1;
import defpackage.ul1;
import defpackage.wv;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements tl1, sl1, ql1 {
    public static final int[] N = {R.attr.enabled};
    public final int A;
    public int B;
    public CircularProgressDrawable C;
    public hx2 D;
    public jx2 E;
    public kx2 F;
    public kx2 G;
    public boolean H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f21J;
    public final a K;
    public final c L;
    public final d M;
    public View e;
    public boolean f;
    public final int g;
    public float h;
    public float i;
    public final ul1 j;
    public final rl1 k;
    public final int[] l;
    public final int[] m;
    public final int[] n;
    public boolean o;
    public final int p;
    public int q;
    public float r;
    public float s;
    public boolean t;
    public int u;
    public final DecelerateInterpolator v;
    public et w;
    public int x;
    public int y;
    public final int z;

    public class c extends Animation {
        public c() {
        }

        @Override // android.view.animation.Animation
        public final void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            swipeRefreshLayout.getClass();
            int iAbs = swipeRefreshLayout.A - Math.abs(swipeRefreshLayout.z);
            swipeRefreshLayout.setTargetOffsetTopAndBottom((swipeRefreshLayout.y + ((int) ((iAbs - r3) * f))) - swipeRefreshLayout.w.getTop());
            CircularProgressDrawable circularProgressDrawable = swipeRefreshLayout.C;
            float f2 = 1.0f - f;
            CircularProgressDrawable.a aVar = circularProgressDrawable.e;
            if (f2 != aVar.p) {
                aVar.p = f2;
            }
            circularProgressDrawable.invalidateSelf();
        }
    }

    public class d extends Animation {
        public d() {
        }

        @Override // android.view.animation.Animation
        public final void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.e(f);
        }
    }

    public interface e {
    }

    public interface f {
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    private void setColorViewAlpha(int i) {
        this.w.getBackground().setAlpha(i);
        this.C.setAlpha(i);
    }

    public final boolean a() {
        View view = this.e;
        return view instanceof ListView ? ((ListView) view).canScrollList(-1) : view.canScrollVertically(-1);
    }

    public final void b() {
        if (this.e == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (!childAt.equals(this.w)) {
                    this.e = childAt;
                    return;
                }
            }
        }
    }

    public final void c(float f2) {
        if (f2 > this.h) {
            g(true, true);
            return;
        }
        this.f = false;
        CircularProgressDrawable circularProgressDrawable = this.C;
        CircularProgressDrawable.a aVar = circularProgressDrawable.e;
        aVar.e = 0.0f;
        aVar.f = 0.0f;
        circularProgressDrawable.invalidateSelf();
        b bVar = new b();
        this.y = this.q;
        d dVar = this.M;
        dVar.reset();
        dVar.setDuration(200L);
        dVar.setInterpolator(this.v);
        et etVar = this.w;
        etVar.e = bVar;
        etVar.clearAnimation();
        this.w.startAnimation(dVar);
        CircularProgressDrawable circularProgressDrawable2 = this.C;
        CircularProgressDrawable.a aVar2 = circularProgressDrawable2.e;
        if (aVar2.n) {
            aVar2.n = false;
        }
        circularProgressDrawable2.invalidateSelf();
    }

    public final void d(float f2) {
        CircularProgressDrawable circularProgressDrawable = this.C;
        CircularProgressDrawable.a aVar = circularProgressDrawable.e;
        if (!aVar.n) {
            aVar.n = true;
        }
        circularProgressDrawable.invalidateSelf();
        float fMin = Math.min(1.0f, Math.abs(f2 / this.h));
        float fMax = (((float) Math.max(((double) fMin) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float fAbs = Math.abs(f2) - this.h;
        int i = this.B;
        if (i <= 0) {
            i = this.A;
        }
        float f3 = i;
        double dMax = Math.max(0.0f, Math.min(fAbs, f3 * 2.0f) / f3) / 4.0f;
        float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * 2.0f;
        int i2 = this.z + ((int) ((f3 * fMin) + (f3 * fPow * 2.0f)));
        if (this.w.getVisibility() != 0) {
            this.w.setVisibility(0);
        }
        this.w.setScaleX(1.0f);
        this.w.setScaleY(1.0f);
        if (f2 < this.h) {
            if (this.C.getAlpha() > 76) {
                kx2 kx2Var = this.F;
                if (!((kx2Var == null || !kx2Var.hasStarted() || kx2Var.hasEnded()) ? false : true)) {
                    kx2 kx2Var2 = new kx2(this, this.C.getAlpha(), 76);
                    kx2Var2.setDuration(300L);
                    et etVar = this.w;
                    etVar.e = null;
                    etVar.clearAnimation();
                    this.w.startAnimation(kx2Var2);
                    this.F = kx2Var2;
                }
            }
        } else if (this.C.getAlpha() < 255) {
            kx2 kx2Var3 = this.G;
            if (!((kx2Var3 == null || !kx2Var3.hasStarted() || kx2Var3.hasEnded()) ? false : true)) {
                kx2 kx2Var4 = new kx2(this, this.C.getAlpha(), 255);
                kx2Var4.setDuration(300L);
                et etVar2 = this.w;
                etVar2.e = null;
                etVar2.clearAnimation();
                this.w.startAnimation(kx2Var4);
                this.G = kx2Var4;
            }
        }
        CircularProgressDrawable circularProgressDrawable2 = this.C;
        float fMin2 = Math.min(0.8f, fMax * 0.8f);
        CircularProgressDrawable.a aVar2 = circularProgressDrawable2.e;
        aVar2.e = 0.0f;
        aVar2.f = fMin2;
        circularProgressDrawable2.invalidateSelf();
        CircularProgressDrawable circularProgressDrawable3 = this.C;
        float fMin3 = Math.min(1.0f, fMax);
        CircularProgressDrawable.a aVar3 = circularProgressDrawable3.e;
        if (fMin3 != aVar3.p) {
            aVar3.p = fMin3;
        }
        circularProgressDrawable3.invalidateSelf();
        CircularProgressDrawable circularProgressDrawable4 = this.C;
        circularProgressDrawable4.e.g = ((fPow * 2.0f) + ((fMax * 0.4f) - 0.25f)) * 0.5f;
        circularProgressDrawable4.invalidateSelf();
        setTargetOffsetTopAndBottom(i2 - this.q);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent == null || keyEvent.getAction() != 1 || keyEvent.getKeyCode() != 285) {
            return super.dispatchKeyEvent(keyEvent);
        }
        h(true, true);
        return true;
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return this.k.a(f2, f3, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f2, float f3) {
        return this.k.b(f2, f3);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.k.c(i, i2, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.k.e(i, i2, i3, i4, iArr, 0, null);
    }

    public final void e(float f2) {
        setTargetOffsetTopAndBottom((this.y + ((int) ((this.z - r0) * f2))) - this.w.getTop());
    }

    public final void f() {
        this.w.clearAnimation();
        this.C.stop();
        this.w.setVisibility(8);
        setColorViewAlpha(255);
        setTargetOffsetTopAndBottom(this.z - this.q);
        this.q = this.w.getTop();
    }

    public final void g(boolean z, boolean z2) {
        if (this.f != z) {
            this.H = z2;
            b();
            this.f = z;
            a aVar = this.K;
            if (!z) {
                jx2 jx2Var = new jx2(this);
                this.E = jx2Var;
                jx2Var.setDuration(150L);
                et etVar = this.w;
                etVar.e = aVar;
                etVar.clearAnimation();
                this.w.startAnimation(this.E);
                return;
            }
            this.y = this.q;
            c cVar = this.L;
            cVar.reset();
            cVar.setDuration(200L);
            cVar.setInterpolator(this.v);
            if (aVar != null) {
                this.w.e = aVar;
            }
            this.w.clearAnimation();
            this.w.startAnimation(cVar);
        }
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        int i3 = this.x;
        return i3 < 0 ? i2 : i2 == i + (-1) ? i3 : i2 >= i3 ? i2 + 1 : i2;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        ul1 ul1Var = this.j;
        return ul1Var.b | ul1Var.a;
    }

    public int getProgressCircleDiameter() {
        return this.I;
    }

    public int getProgressViewEndOffset() {
        return this.A;
    }

    public int getProgressViewStartOffset() {
        return this.z;
    }

    public final void h(boolean z, boolean z2) {
        if (!z || this.f == z) {
            g(z, false);
            return;
        }
        this.f = z;
        setTargetOffsetTopAndBottom((this.A + this.z) - this.q);
        this.H = z2;
        this.w.setVisibility(0);
        this.C.setAlpha(255);
        hx2 hx2Var = new hx2(this);
        this.D = hx2Var;
        hx2Var.setDuration(this.p);
        a aVar = this.K;
        if (aVar != null) {
            this.w.e = aVar;
        }
        this.w.clearAnimation();
        this.w.startAnimation(this.D);
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.k.f(0) != null;
    }

    public final void i(float f2) {
        float f3 = this.s;
        float f4 = f2 - f3;
        int i = this.g;
        if (f4 <= i || this.t) {
            return;
        }
        this.r = f3 + i;
        this.t = true;
        this.C.setAlpha(76);
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.k.d;
    }

    @Override // defpackage.tl1
    public final void j(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i5 != 0) {
            return;
        }
        int i6 = iArr[1];
        int[] iArr2 = this.m;
        if (i5 == 0) {
            this.k.d(i, i2, i3, i4, iArr2, i5, iArr);
        }
        int i7 = i4 - (iArr[1] - i6);
        if ((i7 == 0 ? i4 + this.m[1] : i7) >= 0 || a()) {
            return;
        }
        float fAbs = this.i + Math.abs(r2);
        this.i = fAbs;
        d(fAbs);
        iArr[1] = iArr[1] + i7;
    }

    @Override // defpackage.sl1
    public final void k(View view, int i, int i2, int i3, int i4, int i5) {
        j(view, i, i2, i3, i4, i5, this.n);
    }

    @Override // defpackage.sl1
    public final boolean l(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            return onStartNestedScroll(view, view2, i);
        }
        return false;
    }

    @Override // defpackage.sl1
    public final void m(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // defpackage.sl1
    public final void n(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // defpackage.sl1
    public final void o(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        f();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x005f  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            r5.b()
            int r0 = r6.getActionMasked()
            boolean r1 = r5.isEnabled()
            r2 = 0
            if (r1 == 0) goto L88
            boolean r1 = r5.a()
            if (r1 != 0) goto L88
            boolean r1 = r5.f
            if (r1 != 0) goto L88
            boolean r1 = r5.o
            if (r1 == 0) goto L1e
            goto L88
        L1e:
            if (r0 == 0) goto L64
            r1 = 1
            r3 = -1
            if (r0 == r1) goto L5f
            r4 = 2
            if (r0 == r4) goto L44
            r4 = 3
            if (r0 == r4) goto L5f
            r3 = 6
            if (r0 == r3) goto L2e
            goto L85
        L2e:
            int r0 = r6.getActionIndex()
            int r3 = r6.getPointerId(r0)
            int r4 = r5.u
            if (r3 != r4) goto L85
            if (r0 != 0) goto L3d
            r2 = r1
        L3d:
            int r6 = r6.getPointerId(r2)
            r5.u = r6
            goto L85
        L44:
            int r0 = r5.u
            if (r0 != r3) goto L50
            java.lang.String r5 = "SwipeRefreshLayout"
            java.lang.String r6 = "Got ACTION_MOVE event but don't have an active pointer id."
            android.util.Log.e(r5, r6)
            return r2
        L50:
            int r0 = r6.findPointerIndex(r0)
            if (r0 >= 0) goto L57
            return r2
        L57:
            float r6 = r6.getY(r0)
            r5.i(r6)
            goto L85
        L5f:
            r5.t = r2
            r5.u = r3
            goto L85
        L64:
            et r0 = r5.w
            int r0 = r0.getTop()
            int r1 = r5.z
            int r1 = r1 - r0
            r5.setTargetOffsetTopAndBottom(r1)
            int r0 = r6.getPointerId(r2)
            r5.u = r0
            r5.t = r2
            int r0 = r6.findPointerIndex(r0)
            if (r0 >= 0) goto L7f
            return r2
        L7f:
            float r6 = r6.getY(r0)
            r5.s = r6
        L85:
            boolean r5 = r5.t
            return r5
        L88:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.e == null) {
            b();
        }
        View view = this.e;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.w.getMeasuredWidth();
        int measuredHeight2 = this.w.getMeasuredHeight();
        int i5 = measuredWidth / 2;
        int i6 = measuredWidth2 / 2;
        int i7 = this.q;
        this.w.layout(i5 - i6, i7, i5 + i6, measuredHeight2 + i7);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.e == null) {
            b();
        }
        View view = this.e;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        this.w.measure(View.MeasureSpec.makeMeasureSpec(this.I, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(this.I, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        this.x = -1;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == this.w) {
                this.x = i3;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0) {
            float f2 = this.i;
            if (f2 > 0.0f) {
                float f3 = i2;
                if (f3 > f2) {
                    iArr[1] = (int) f2;
                    this.i = 0.0f;
                } else {
                    this.i = f2 - f3;
                    iArr[1] = i2;
                }
                d(this.i);
            }
        }
        int i3 = i - iArr[0];
        int i4 = i2 - iArr[1];
        int[] iArr2 = this.l;
        if (dispatchNestedPreScroll(i3, i4, iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        j(view, i, i2, i3, i4, 0, this.n);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        this.j.a = i;
        startNestedScroll(i & 2);
        this.i = 0.0f;
        this.o = true;
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setRefreshing(savedState.e);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.f);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        return (!isEnabled() || this.f || (i & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        this.j.a = 0;
        this.o = false;
        float f2 = this.i;
        if (f2 > 0.0f) {
            c(f2);
            this.i = 0.0f;
        } else {
            post(new wv(7, this));
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (!isEnabled() || a() || this.f || this.o) {
            return false;
        }
        if (actionMasked == 0) {
            this.u = motionEvent.getPointerId(0);
            this.t = false;
        } else {
            if (actionMasked == 1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.u);
                if (iFindPointerIndex < 0) {
                    Log.e("SwipeRefreshLayout", "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.t) {
                    float y = (motionEvent.getY(iFindPointerIndex) - this.r) * 0.5f;
                    this.t = false;
                    c(y);
                }
                this.u = -1;
                return false;
            }
            if (actionMasked == 2) {
                int iFindPointerIndex2 = motionEvent.findPointerIndex(this.u);
                if (iFindPointerIndex2 < 0) {
                    Log.e("SwipeRefreshLayout", "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y2 = motionEvent.getY(iFindPointerIndex2);
                i(y2);
                if (this.t) {
                    float f2 = (y2 - this.r) * 0.5f;
                    if (f2 <= 0.0f) {
                        return false;
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                    d(f2);
                }
            } else {
                if (actionMasked == 3) {
                    return false;
                }
                if (actionMasked == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    if (actionIndex < 0) {
                        Log.e("SwipeRefreshLayout", "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return false;
                    }
                    this.u = motionEvent.getPointerId(actionIndex);
                } else if (actionMasked == 6) {
                    int actionIndex2 = motionEvent.getActionIndex();
                    if (motionEvent.getPointerId(actionIndex2) == this.u) {
                        this.u = motionEvent.getPointerId(actionIndex2 == 0 ? 1 : 0);
                    }
                }
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        View view;
        if (this.f21J && (view = this.e) != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (!om3.i.i(view)) {
                return;
            }
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void setAnimationProgress(float f2) {
        this.w.setScaleX(f2);
        this.w.setScaleY(f2);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        b();
        CircularProgressDrawable circularProgressDrawable = this.C;
        CircularProgressDrawable.a aVar = circularProgressDrawable.e;
        aVar.i = iArr;
        aVar.a(0);
        aVar.a(0);
        circularProgressDrawable.invalidateSelf();
    }

    public void setColorSchemeResources(int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            Object obj = bz.a;
            iArr2[i] = context.getColor(i2);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i) {
        this.h = i;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            return;
        }
        f();
    }

    @Deprecated
    public void setLegacyRequestDisallowInterceptTouchEventEnabled(boolean z) {
        this.f21J = z;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        rl1 rl1Var = this.k;
        if (rl1Var.d) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.i.p(rl1Var.c);
        }
        rl1Var.d = z;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeColor(int i) {
        this.w.setBackgroundColor(i);
    }

    public void setProgressBackgroundColorSchemeResource(int i) {
        Context context = getContext();
        Object obj = bz.a;
        setProgressBackgroundColorSchemeColor(context.getColor(i));
    }

    public void setRefreshing(boolean z) {
        h(z, false);
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i == 0) {
                this.I = (int) (displayMetrics.density * 56.0f);
            } else {
                this.I = (int) (displayMetrics.density * 40.0f);
            }
            this.w.setImageDrawable(null);
            this.C.d(i);
            this.w.setImageDrawable(this.C);
        }
    }

    public void setSlingshotDistance(int i) {
        this.B = i;
    }

    public void setTargetOffsetTopAndBottom(int i) {
        this.w.bringToFront();
        et etVar = this.w;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        etVar.offsetTopAndBottom(i);
        this.q = this.w.getTop();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i) {
        return this.k.g(i, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        this.k.h(0);
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final boolean e;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable, boolean z) {
            super(parcelable);
            this.e = z;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.e = parcel.readByte() != 0;
        }
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = false;
        this.h = -1.0f;
        this.l = new int[2];
        this.m = new int[2];
        this.n = new int[2];
        this.u = -1;
        this.x = -1;
        this.K = new a();
        this.L = new c();
        this.M = new d();
        this.g = ViewConfiguration.get(context).getScaledTouchSlop();
        this.p = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.v = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.I = (int) (displayMetrics.density * 40.0f);
        this.w = new et(getContext());
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getContext());
        this.C = circularProgressDrawable;
        circularProgressDrawable.d(1);
        this.w.setImageDrawable(this.C);
        this.w.setVisibility(8);
        addView(this.w);
        setChildrenDrawingOrderEnabled(true);
        int i = (int) (displayMetrics.density * 64.0f);
        this.A = i;
        this.h = i;
        this.j = new ul1();
        this.k = new rl1(this);
        setNestedScrollingEnabled(true);
        int i2 = -this.I;
        this.q = i2;
        this.z = i2;
        e(1.0f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, N);
        setEnabled(typedArrayObtainStyledAttributes.getBoolean(0, true));
        typedArrayObtainStyledAttributes.recycle();
    }

    public class a implements Animation.AnimationListener {
        public a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (!swipeRefreshLayout.f) {
                swipeRefreshLayout.f();
                return;
            }
            swipeRefreshLayout.C.setAlpha(255);
            swipeRefreshLayout.C.start();
            if (swipeRefreshLayout.H) {
                swipeRefreshLayout.getClass();
            }
            swipeRefreshLayout.q = swipeRefreshLayout.w.getTop();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationStart(Animation animation) {
        }
    }

    public class b implements Animation.AnimationListener {
        public b() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            swipeRefreshLayout.getClass();
            jx2 jx2Var = new jx2(swipeRefreshLayout);
            swipeRefreshLayout.E = jx2Var;
            jx2Var.setDuration(150L);
            et etVar = swipeRefreshLayout.w;
            etVar.e = null;
            etVar.clearAnimation();
            swipeRefreshLayout.w.startAnimation(swipeRefreshLayout.E);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationStart(Animation animation) {
        }
    }

    public void setOnChildScrollUpCallback(e eVar) {
    }

    public void setOnRefreshListener(f fVar) {
    }
}
