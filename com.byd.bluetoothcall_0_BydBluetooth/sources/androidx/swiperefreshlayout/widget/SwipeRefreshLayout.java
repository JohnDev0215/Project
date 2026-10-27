package androidx.swiperefreshlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
import b.g.k.k;
import b.g.k.n;
import b.g.k.o;
import b.g.k.t;

/* JADX INFO: loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements n, b.g.k.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f1852b = SwipeRefreshLayout.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f1853c = {R.attr.enabled};
    float A;
    protected int B;
    int C;
    int D;
    androidx.swiperefreshlayout.widget.b E;
    private Animation F;
    private Animation G;
    private Animation H;
    private Animation I;
    private Animation J;
    boolean K;
    private int L;
    boolean M;
    private i N;
    private Animation.AnimationListener O;
    private final Animation P;
    private final Animation Q;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f1854d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    j f1855e;
    boolean f;
    private int g;
    private float h;
    private float i;
    private final o j;
    private final k k;
    private final int[] l;
    private final int[] m;
    private boolean n;
    private int o;
    int p;
    private float q;
    private float r;
    private boolean s;
    private int t;
    boolean u;
    private boolean v;
    private final DecelerateInterpolator w;
    androidx.swiperefreshlayout.widget.a x;
    private int y;
    protected int z;

    class a implements Animation.AnimationListener {
        a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            j jVar;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (!swipeRefreshLayout.f) {
                swipeRefreshLayout.q();
                return;
            }
            swipeRefreshLayout.E.setAlpha(255);
            SwipeRefreshLayout.this.E.start();
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            if (swipeRefreshLayout2.K && (jVar = swipeRefreshLayout2.f1855e) != null) {
                jVar.a();
            }
            SwipeRefreshLayout swipeRefreshLayout3 = SwipeRefreshLayout.this;
            swipeRefreshLayout3.p = swipeRefreshLayout3.x.getTop();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    class b extends Animation {
        b() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(f);
        }
    }

    class c extends Animation {
        c() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(1.0f - f);
        }
    }

    class d extends Animation {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1859b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1860c;

        d(int i, int i2) {
            this.f1859b = i;
            this.f1860c = i2;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.E.setAlpha((int) (this.f1859b + ((this.f1860c - r0) * f)));
        }
    }

    class e implements Animation.AnimationListener {
        e() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (swipeRefreshLayout.u) {
                return;
            }
            swipeRefreshLayout.w(null);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    class f extends Animation {
        f() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            int iAbs = !swipeRefreshLayout.M ? swipeRefreshLayout.C - Math.abs(swipeRefreshLayout.B) : swipeRefreshLayout.C;
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            SwipeRefreshLayout.this.setTargetOffsetTopAndBottom((swipeRefreshLayout2.z + ((int) ((iAbs - r1) * f))) - swipeRefreshLayout2.x.getTop());
            SwipeRefreshLayout.this.E.e(1.0f - f);
        }
    }

    class g extends Animation {
        g() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.l(f);
        }
    }

    class h extends Animation {
        h() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            float f2 = swipeRefreshLayout.A;
            swipeRefreshLayout.setAnimationProgress(f2 + ((-f2) * f));
            SwipeRefreshLayout.this.l(f);
        }
    }

    public interface i {
        boolean a(SwipeRefreshLayout swipeRefreshLayout, View view);
    }

    public interface j {
        void a();
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    private void a(int i2, Animation.AnimationListener animationListener) {
        this.z = i2;
        this.P.reset();
        this.P.setDuration(200L);
        this.P.setInterpolator(this.w);
        if (animationListener != null) {
            this.x.b(animationListener);
        }
        this.x.clearAnimation();
        this.x.startAnimation(this.P);
    }

    private void b(int i2, Animation.AnimationListener animationListener) {
        if (this.u) {
            x(i2, animationListener);
            return;
        }
        this.z = i2;
        this.Q.reset();
        this.Q.setDuration(200L);
        this.Q.setInterpolator(this.w);
        if (animationListener != null) {
            this.x.b(animationListener);
        }
        this.x.clearAnimation();
        this.x.startAnimation(this.Q);
    }

    private void d() {
        this.x = new androidx.swiperefreshlayout.widget.a(getContext(), -328966);
        androidx.swiperefreshlayout.widget.b bVar = new androidx.swiperefreshlayout.widget.b(getContext());
        this.E = bVar;
        bVar.l(1);
        this.x.setImageDrawable(this.E);
        this.x.setVisibility(8);
        addView(this.x);
    }

    private void e() {
        if (this.f1854d == null) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (!childAt.equals(this.x)) {
                    this.f1854d = childAt;
                    return;
                }
            }
        }
    }

    private void f(float f2) {
        if (f2 > this.h) {
            r(true, true);
            return;
        }
        this.f = false;
        this.E.j(0.0f, 0.0f);
        b(this.p, this.u ? null : new e());
        this.E.d(false);
    }

    private boolean g(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    private void k(float f2) {
        this.E.d(true);
        float fMin = Math.min(1.0f, Math.abs(f2 / this.h));
        float fMax = (((float) Math.max(((double) fMin) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float fAbs = Math.abs(f2) - this.h;
        int i2 = this.D;
        if (i2 <= 0) {
            i2 = this.M ? this.C - this.B : this.C;
        }
        float f3 = i2;
        double dMax = Math.max(0.0f, Math.min(fAbs, f3 * 2.0f) / f3) / 4.0f;
        float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * 2.0f;
        int i3 = this.B + ((int) ((f3 * fMin) + (f3 * fPow * 2.0f)));
        if (this.x.getVisibility() != 0) {
            this.x.setVisibility(0);
        }
        if (!this.u) {
            this.x.setScaleX(1.0f);
            this.x.setScaleY(1.0f);
        }
        if (this.u) {
            setAnimationProgress(Math.min(1.0f, f2 / this.h));
        }
        if (f2 < this.h) {
            if (this.E.getAlpha() > 76 && !g(this.H)) {
                v();
            }
        } else if (this.E.getAlpha() < 255 && !g(this.I)) {
            u();
        }
        this.E.j(0.0f, Math.min(0.8f, fMax * 0.8f));
        this.E.e(Math.min(1.0f, fMax));
        this.E.g((((fMax * 0.4f) - 0.25f) + (fPow * 2.0f)) * 0.5f);
        setTargetOffsetTopAndBottom(i3 - this.p);
    }

    private void p(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.t) {
            this.t = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    private void r(boolean z, boolean z2) {
        if (this.f != z) {
            this.K = z2;
            e();
            this.f = z;
            if (z) {
                a(this.p, this.O);
            } else {
                w(this.O);
            }
        }
    }

    private Animation s(int i2, int i3) {
        d dVar = new d(i2, i3);
        dVar.setDuration(300L);
        this.x.b(null);
        this.x.clearAnimation();
        this.x.startAnimation(dVar);
        return dVar;
    }

    private void setColorViewAlpha(int i2) {
        this.x.getBackground().setAlpha(i2);
        this.E.setAlpha(i2);
    }

    private void t(float f2) {
        float f3 = this.r;
        float f4 = f2 - f3;
        int i2 = this.g;
        if (f4 <= i2 || this.s) {
            return;
        }
        this.q = f3 + i2;
        this.s = true;
        this.E.setAlpha(76);
    }

    private void u() {
        this.I = s(this.E.getAlpha(), 255);
    }

    private void v() {
        this.H = s(this.E.getAlpha(), 76);
    }

    private void x(int i2, Animation.AnimationListener animationListener) {
        this.z = i2;
        this.A = this.x.getScaleX();
        h hVar = new h();
        this.J = hVar;
        hVar.setDuration(150L);
        if (animationListener != null) {
            this.x.b(animationListener);
        }
        this.x.clearAnimation();
        this.x.startAnimation(this.J);
    }

    private void y(Animation.AnimationListener animationListener) {
        this.x.setVisibility(0);
        this.E.setAlpha(255);
        b bVar = new b();
        this.F = bVar;
        bVar.setDuration(this.o);
        if (animationListener != null) {
            this.x.b(animationListener);
        }
        this.x.clearAnimation();
        this.x.startAnimation(this.F);
    }

    public boolean c() {
        i iVar = this.N;
        if (iVar != null) {
            return iVar.a(this, this.f1854d);
        }
        View view = this.f1854d;
        return view instanceof ListView ? androidx.core.widget.g.a((ListView) view, -1) : view.canScrollVertically(-1);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return this.k.a(f2, f3, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.k.b(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return this.k.c(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.k.f(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        int i4 = this.y;
        return i4 < 0 ? i3 : i3 == i2 + (-1) ? i4 : i3 >= i4 ? i3 + 1 : i3;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.j.a();
    }

    public int getProgressCircleDiameter() {
        return this.L;
    }

    public int getProgressViewEndOffset() {
        return this.C;
    }

    public int getProgressViewStartOffset() {
        return this.B;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.k.j();
    }

    @Override // android.view.View, b.g.k.j
    public boolean isNestedScrollingEnabled() {
        return this.k.l();
    }

    void l(float f2) {
        setTargetOffsetTopAndBottom((this.z + ((int) ((this.B - r0) * f2))) - this.x.getTop());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        q();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0058  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            r4.e()
            int r0 = r5.getActionMasked()
            boolean r1 = r4.v
            r2 = 0
            if (r1 == 0) goto L10
            if (r0 != 0) goto L10
            r4.v = r2
        L10:
            boolean r1 = r4.isEnabled()
            if (r1 == 0) goto L81
            boolean r1 = r4.v
            if (r1 != 0) goto L81
            boolean r1 = r4.c()
            if (r1 != 0) goto L81
            boolean r1 = r4.f
            if (r1 != 0) goto L81
            boolean r1 = r4.n
            if (r1 == 0) goto L29
            goto L81
        L29:
            if (r0 == 0) goto L5d
            r1 = 1
            r3 = -1
            if (r0 == r1) goto L58
            r1 = 2
            if (r0 == r1) goto L3d
            r1 = 3
            if (r0 == r1) goto L58
            r1 = 6
            if (r0 == r1) goto L39
            goto L7e
        L39:
            r4.p(r5)
            goto L7e
        L3d:
            int r0 = r4.t
            if (r0 != r3) goto L49
            java.lang.String r5 = androidx.swiperefreshlayout.widget.SwipeRefreshLayout.f1852b
            java.lang.String r0 = "Got ACTION_MOVE event but don't have an active pointer id."
            android.util.Log.e(r5, r0)
            return r2
        L49:
            int r0 = r5.findPointerIndex(r0)
            if (r0 >= 0) goto L50
            return r2
        L50:
            float r5 = r5.getY(r0)
            r4.t(r5)
            goto L7e
        L58:
            r4.s = r2
            r4.t = r3
            goto L7e
        L5d:
            int r0 = r4.B
            androidx.swiperefreshlayout.widget.a r1 = r4.x
            int r1 = r1.getTop()
            int r0 = r0 - r1
            r4.setTargetOffsetTopAndBottom(r0)
            int r0 = r5.getPointerId(r2)
            r4.t = r0
            r4.s = r2
            int r0 = r5.findPointerIndex(r0)
            if (r0 >= 0) goto L78
            return r2
        L78:
            float r5 = r5.getY(r0)
            r4.r = r5
        L7e:
            boolean r5 = r4.s
            return r5
        L81:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.f1854d == null) {
            e();
        }
        View view = this.f1854d;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.x.getMeasuredWidth();
        int measuredHeight2 = this.x.getMeasuredHeight();
        int i6 = measuredWidth / 2;
        int i7 = measuredWidth2 / 2;
        int i8 = this.p;
        this.x.layout(i6 - i7, i8, i6 + i7, measuredHeight2 + i8);
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f1854d == null) {
            e();
        }
        View view = this.f1854d;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        this.x.measure(View.MeasureSpec.makeMeasureSpec(this.L, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(this.L, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        this.y = -1;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if (getChildAt(i4) == this.x) {
                this.y = i4;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        if (i3 > 0) {
            float f2 = this.i;
            if (f2 > 0.0f) {
                float f3 = i3;
                if (f3 > f2) {
                    iArr[1] = i3 - ((int) f2);
                    this.i = 0.0f;
                } else {
                    this.i = f2 - f3;
                    iArr[1] = i3;
                }
                k(this.i);
            }
        }
        if (this.M && i3 > 0 && this.i == 0.0f && Math.abs(i3 - iArr[1]) > 0) {
            this.x.setVisibility(8);
        }
        int[] iArr2 = this.l;
        if (dispatchNestedPreScroll(i2 - iArr[0], i3 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        dispatchNestedScroll(i2, i3, i4, i5, this.m);
        if (i5 + this.m[1] >= 0 || c()) {
            return;
        }
        float fAbs = this.i + Math.abs(r11);
        this.i = fAbs;
        k(fAbs);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.j.b(view, view2, i2);
        startNestedScroll(i2 & 2);
        this.i = 0.0f;
        this.n = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return (!isEnabled() || this.v || this.f || (i2 & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public void onStopNestedScroll(View view) {
        this.j.d(view);
        this.n = false;
        float f2 = this.i;
        if (f2 > 0.0f) {
            f(f2);
            this.i = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.v && actionMasked == 0) {
            this.v = false;
        }
        if (!isEnabled() || this.v || c() || this.f || this.n) {
            return false;
        }
        if (actionMasked == 0) {
            this.t = motionEvent.getPointerId(0);
            this.s = false;
        } else {
            if (actionMasked == 1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.t);
                if (iFindPointerIndex < 0) {
                    Log.e(f1852b, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.s) {
                    float y = (motionEvent.getY(iFindPointerIndex) - this.q) * 0.5f;
                    this.s = false;
                    f(y);
                }
                this.t = -1;
                return false;
            }
            if (actionMasked == 2) {
                int iFindPointerIndex2 = motionEvent.findPointerIndex(this.t);
                if (iFindPointerIndex2 < 0) {
                    Log.e(f1852b, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y2 = motionEvent.getY(iFindPointerIndex2);
                t(y2);
                if (this.s) {
                    float f2 = (y2 - this.q) * 0.5f;
                    if (f2 <= 0.0f) {
                        return false;
                    }
                    k(f2);
                }
            } else {
                if (actionMasked == 3) {
                    return false;
                }
                if (actionMasked == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    if (actionIndex < 0) {
                        Log.e(f1852b, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return false;
                    }
                    this.t = motionEvent.getPointerId(actionIndex);
                } else if (actionMasked == 6) {
                    p(motionEvent);
                }
            }
        }
        return true;
    }

    void q() {
        this.x.clearAnimation();
        this.E.stop();
        this.x.setVisibility(8);
        setColorViewAlpha(255);
        if (this.u) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.B - this.p);
        }
        this.p = this.x.getTop();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.f1854d instanceof AbsListView)) {
            View view = this.f1854d;
            if (view == null || t.Q(view)) {
                super.requestDisallowInterceptTouchEvent(z);
            }
        }
    }

    void setAnimationProgress(float f2) {
        this.x.setScaleX(f2);
        this.x.setScaleY(f2);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        e();
        this.E.f(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr2[i2] = androidx.core.content.a.b(context, iArr[i2]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i2) {
        this.h = i2;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            return;
        }
        q();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.k.m(z);
    }

    public void setOnChildScrollUpCallback(i iVar) {
        this.N = iVar;
    }

    public void setOnRefreshListener(j jVar) {
        this.f1855e = jVar;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i2) {
        setProgressBackgroundColorSchemeResource(i2);
    }

    public void setProgressBackgroundColorSchemeColor(int i2) {
        this.x.setBackgroundColor(i2);
    }

    public void setProgressBackgroundColorSchemeResource(int i2) {
        setProgressBackgroundColorSchemeColor(androidx.core.content.a.b(getContext(), i2));
    }

    public void setRefreshing(boolean z) {
        if (!z || this.f == z) {
            r(z, false);
            return;
        }
        this.f = z;
        setTargetOffsetTopAndBottom((!this.M ? this.C + this.B : this.C) - this.p);
        this.K = false;
        y(this.O);
    }

    public void setSize(int i2) {
        if (i2 == 0 || i2 == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i2 == 0) {
                this.L = (int) (displayMetrics.density * 56.0f);
            } else {
                this.L = (int) (displayMetrics.density * 40.0f);
            }
            this.x.setImageDrawable(null);
            this.E.l(i2);
            this.x.setImageDrawable(this.E);
        }
    }

    public void setSlingshotDistance(int i2) {
        this.D = i2;
    }

    void setTargetOffsetTopAndBottom(int i2) {
        this.x.bringToFront();
        t.V(this.x, i2);
        this.p = this.x.getTop();
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return this.k.o(i2);
    }

    @Override // android.view.View, b.g.k.j
    public void stopNestedScroll() {
        this.k.q();
    }

    void w(Animation.AnimationListener animationListener) {
        c cVar = new c();
        this.G = cVar;
        cVar.setDuration(150L);
        this.x.b(animationListener);
        this.x.clearAnimation();
        this.x.startAnimation(this.G);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = false;
        this.h = -1.0f;
        this.l = new int[2];
        this.m = new int[2];
        this.t = -1;
        this.y = -1;
        this.O = new a();
        this.P = new f();
        this.Q = new g();
        this.g = ViewConfiguration.get(context).getScaledTouchSlop();
        this.o = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.w = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.L = (int) (displayMetrics.density * 40.0f);
        d();
        setChildrenDrawingOrderEnabled(true);
        int i2 = (int) (displayMetrics.density * 64.0f);
        this.C = i2;
        this.h = i2;
        this.j = new o(this);
        this.k = new k(this);
        setNestedScrollingEnabled(true);
        int i3 = -this.L;
        this.p = i3;
        this.B = i3;
        l(1.0f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1853c);
        setEnabled(typedArrayObtainStyledAttributes.getBoolean(0, true));
        typedArrayObtainStyledAttributes.recycle();
    }
}
