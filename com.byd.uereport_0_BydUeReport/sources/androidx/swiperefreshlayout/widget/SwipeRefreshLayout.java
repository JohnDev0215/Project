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
import o0.k;
import o0.n;
import o0.o;
import o0.t;

/* JADX INFO: loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements n, o0.j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f1804d = SwipeRefreshLayout.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f1805e = {R.attr.enabled};
    public int A;
    public int B;
    public float C;
    public int D;
    public int E;
    public int F;
    public j1.b G;
    public Animation H;
    public Animation I;
    public Animation J;
    public Animation K;
    public Animation L;
    public boolean M;
    public int N;
    public boolean O;
    public i P;
    public Animation.AnimationListener Q;
    public final Animation R;
    public final Animation S;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f1806f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j f1807g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1808h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1809i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f1810j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f1811k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o f1812l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k f1813m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int[] f1814n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int[] f1815o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1816p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1817q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1818r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f1819s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f1820t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1821u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1822v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1823w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f1824x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final DecelerateInterpolator f1825y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public j1.a f1826z;

    public class a implements Animation.AnimationListener {
        public a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            j jVar;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (!swipeRefreshLayout.f1808h) {
                swipeRefreshLayout.q();
                return;
            }
            swipeRefreshLayout.G.setAlpha(255);
            SwipeRefreshLayout.this.G.start();
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            if (swipeRefreshLayout2.M && (jVar = swipeRefreshLayout2.f1807g) != null) {
                jVar.a();
            }
            SwipeRefreshLayout swipeRefreshLayout3 = SwipeRefreshLayout.this;
            swipeRefreshLayout3.f1818r = swipeRefreshLayout3.f1826z.getTop();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public class b extends Animation {
        public b() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(f8);
        }
    }

    public class c extends Animation {
        public c() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(1.0f - f8);
        }
    }

    public class d extends Animation {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f1830d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f1831e;

        public d(int i8, int i9) {
            this.f1830d = i8;
            this.f1831e = i9;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            SwipeRefreshLayout.this.G.setAlpha((int) (this.f1830d + ((this.f1831e - r0) * f8)));
        }
    }

    public class e implements Animation.AnimationListener {
        public e() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (swipeRefreshLayout.f1823w) {
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

    public class f extends Animation {
        public f() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            int iAbs = !swipeRefreshLayout.O ? swipeRefreshLayout.E - Math.abs(swipeRefreshLayout.D) : swipeRefreshLayout.E;
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            SwipeRefreshLayout.this.setTargetOffsetTopAndBottom((swipeRefreshLayout2.B + ((int) ((iAbs - r1) * f8))) - swipeRefreshLayout2.f1826z.getTop());
            SwipeRefreshLayout.this.G.e(1.0f - f8);
        }
    }

    public class g extends Animation {
        public g() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            SwipeRefreshLayout.this.l(f8);
        }
    }

    public class h extends Animation {
        public h() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            float f9 = swipeRefreshLayout.C;
            swipeRefreshLayout.setAnimationProgress(f9 + ((-f9) * f8));
            SwipeRefreshLayout.this.l(f8);
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

    private void setColorViewAlpha(int i8) {
        this.f1826z.getBackground().setAlpha(i8);
        this.G.setAlpha(i8);
    }

    public final void a(int i8, Animation.AnimationListener animationListener) {
        this.B = i8;
        this.R.reset();
        this.R.setDuration(200L);
        this.R.setInterpolator(this.f1825y);
        if (animationListener != null) {
            this.f1826z.b(animationListener);
        }
        this.f1826z.clearAnimation();
        this.f1826z.startAnimation(this.R);
    }

    public final void b(int i8, Animation.AnimationListener animationListener) {
        if (this.f1823w) {
            x(i8, animationListener);
            return;
        }
        this.B = i8;
        this.S.reset();
        this.S.setDuration(200L);
        this.S.setInterpolator(this.f1825y);
        if (animationListener != null) {
            this.f1826z.b(animationListener);
        }
        this.f1826z.clearAnimation();
        this.f1826z.startAnimation(this.S);
    }

    public boolean c() {
        i iVar = this.P;
        if (iVar != null) {
            return iVar.a(this, this.f1806f);
        }
        View view = this.f1806f;
        return view instanceof ListView ? r0.g.a((ListView) view, -1) : view.canScrollVertically(-1);
    }

    public final void d() {
        this.f1826z = new j1.a(getContext(), -328966);
        j1.b bVar = new j1.b(getContext());
        this.G = bVar;
        bVar.l(1);
        this.f1826z.setImageDrawable(this.G);
        this.f1826z.setVisibility(8);
        addView(this.f1826z);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f8, float f9, boolean z7) {
        return this.f1813m.a(f8, f9, z7);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f8, float f9) {
        return this.f1813m.b(f8, f9);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i8, int i9, int[] iArr, int[] iArr2) {
        return this.f1813m.c(i8, i9, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i8, int i9, int i10, int i11, int[] iArr) {
        return this.f1813m.f(i8, i9, i10, i11, iArr);
    }

    public final void e() {
        if (this.f1806f == null) {
            for (int i8 = 0; i8 < getChildCount(); i8++) {
                View childAt = getChildAt(i8);
                if (!childAt.equals(this.f1826z)) {
                    this.f1806f = childAt;
                    return;
                }
            }
        }
    }

    public final void f(float f8) {
        if (f8 > this.f1810j) {
            r(true, true);
            return;
        }
        this.f1808h = false;
        this.G.j(0.0f, 0.0f);
        b(this.f1818r, this.f1823w ? null : new e());
        this.G.d(false);
    }

    public final boolean g(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i8, int i9) {
        int i10 = this.A;
        return i10 < 0 ? i9 : i9 == i8 + (-1) ? i10 : i9 >= i10 ? i9 + 1 : i9;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f1812l.a();
    }

    public int getProgressCircleDiameter() {
        return this.N;
    }

    public int getProgressViewEndOffset() {
        return this.E;
    }

    public int getProgressViewStartOffset() {
        return this.D;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.f1813m.k();
    }

    @Override // android.view.View, o0.j
    public boolean isNestedScrollingEnabled() {
        return this.f1813m.m();
    }

    public final void k(float f8) {
        this.G.d(true);
        float fMin = Math.min(1.0f, Math.abs(f8 / this.f1810j));
        float fMax = (((float) Math.max(((double) fMin) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float fAbs = Math.abs(f8) - this.f1810j;
        int i8 = this.F;
        if (i8 <= 0) {
            i8 = this.O ? this.E - this.D : this.E;
        }
        float f9 = i8;
        double dMax = Math.max(0.0f, Math.min(fAbs, f9 * 2.0f) / f9) / 4.0f;
        float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * 2.0f;
        int i9 = this.D + ((int) ((f9 * fMin) + (f9 * fPow * 2.0f)));
        if (this.f1826z.getVisibility() != 0) {
            this.f1826z.setVisibility(0);
        }
        if (!this.f1823w) {
            this.f1826z.setScaleX(1.0f);
            this.f1826z.setScaleY(1.0f);
        }
        if (this.f1823w) {
            setAnimationProgress(Math.min(1.0f, f8 / this.f1810j));
        }
        if (f8 < this.f1810j) {
            if (this.G.getAlpha() > 76 && !g(this.J)) {
                v();
            }
        } else if (this.G.getAlpha() < 255 && !g(this.K)) {
            u();
        }
        this.G.j(0.0f, Math.min(0.8f, fMax * 0.8f));
        this.G.e(Math.min(1.0f, fMax));
        this.G.g((((fMax * 0.4f) - 0.25f) + (fPow * 2.0f)) * 0.5f);
        setTargetOffsetTopAndBottom(i9 - this.f1818r);
    }

    public void l(float f8) {
        setTargetOffsetTopAndBottom((this.B + ((int) ((this.D - r0) * f8))) - this.f1826z.getTop());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
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
            boolean r1 = r4.f1824x
            r2 = 0
            if (r1 == 0) goto L10
            if (r0 != 0) goto L10
            r4.f1824x = r2
        L10:
            boolean r1 = r4.isEnabled()
            if (r1 == 0) goto L81
            boolean r1 = r4.f1824x
            if (r1 != 0) goto L81
            boolean r1 = r4.c()
            if (r1 != 0) goto L81
            boolean r1 = r4.f1808h
            if (r1 != 0) goto L81
            boolean r1 = r4.f1816p
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
            int r0 = r4.f1822v
            if (r0 != r3) goto L49
            java.lang.String r5 = androidx.swiperefreshlayout.widget.SwipeRefreshLayout.f1804d
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
            r4.f1821u = r2
            r4.f1822v = r3
            goto L7e
        L5d:
            int r0 = r4.D
            j1.a r1 = r4.f1826z
            int r1 = r1.getTop()
            int r0 = r0 - r1
            r4.setTargetOffsetTopAndBottom(r0)
            int r0 = r5.getPointerId(r2)
            r4.f1822v = r0
            r4.f1821u = r2
            int r0 = r5.findPointerIndex(r0)
            if (r0 >= 0) goto L78
            return r2
        L78:
            float r5 = r5.getY(r0)
            r4.f1820t = r5
        L7e:
            boolean r5 = r4.f1821u
            return r5
        L81:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.f1806f == null) {
            e();
        }
        View view = this.f1806f;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.f1826z.getMeasuredWidth();
        int measuredHeight2 = this.f1826z.getMeasuredHeight();
        int i12 = measuredWidth / 2;
        int i13 = measuredWidth2 / 2;
        int i14 = this.f1818r;
        this.f1826z.layout(i12 - i13, i14, i12 + i13, measuredHeight2 + i14);
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        if (this.f1806f == null) {
            e();
        }
        View view = this.f1806f;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        this.f1826z.measure(View.MeasureSpec.makeMeasureSpec(this.N, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(this.N, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        this.A = -1;
        for (int i10 = 0; i10 < getChildCount(); i10++) {
            if (getChildAt(i10) == this.f1826z) {
                this.A = i10;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onNestedFling(View view, float f8, float f9, boolean z7) {
        return dispatchNestedFling(f8, f9, z7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onNestedPreFling(View view, float f8, float f9) {
        return dispatchNestedPreFling(f8, f9);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedPreScroll(View view, int i8, int i9, int[] iArr) {
        if (i9 > 0) {
            float f8 = this.f1811k;
            if (f8 > 0.0f) {
                float f9 = i9;
                if (f9 > f8) {
                    iArr[1] = i9 - ((int) f8);
                    this.f1811k = 0.0f;
                } else {
                    this.f1811k = f8 - f9;
                    iArr[1] = i9;
                }
                k(this.f1811k);
            }
        }
        if (this.O && i9 > 0 && this.f1811k == 0.0f && Math.abs(i9 - iArr[1]) > 0) {
            this.f1826z.setVisibility(8);
        }
        int[] iArr2 = this.f1814n;
        if (dispatchNestedPreScroll(i8 - iArr[0], i9 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11) {
        dispatchNestedScroll(i8, i9, i10, i11, this.f1815o);
        if (i11 + this.f1815o[1] >= 0 || c()) {
            return;
        }
        float fAbs = this.f1811k + Math.abs(r11);
        this.f1811k = fAbs;
        k(fAbs);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedScrollAccepted(View view, View view2, int i8) {
        this.f1812l.b(view, view2, i8);
        startNestedScroll(i8 & 2);
        this.f1811k = 0.0f;
        this.f1816p = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onStartNestedScroll(View view, View view2, int i8) {
        return (!isEnabled() || this.f1824x || this.f1808h || (i8 & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onStopNestedScroll(View view) {
        this.f1812l.d(view);
        this.f1816p = false;
        float f8 = this.f1811k;
        if (f8 > 0.0f) {
            f(f8);
            this.f1811k = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.f1824x && actionMasked == 0) {
            this.f1824x = false;
        }
        if (!isEnabled() || this.f1824x || c() || this.f1808h || this.f1816p) {
            return false;
        }
        if (actionMasked == 0) {
            this.f1822v = motionEvent.getPointerId(0);
            this.f1821u = false;
        } else {
            if (actionMasked == 1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f1822v);
                if (iFindPointerIndex < 0) {
                    Log.e(f1804d, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.f1821u) {
                    float y7 = (motionEvent.getY(iFindPointerIndex) - this.f1819s) * 0.5f;
                    this.f1821u = false;
                    f(y7);
                }
                this.f1822v = -1;
                return false;
            }
            if (actionMasked == 2) {
                int iFindPointerIndex2 = motionEvent.findPointerIndex(this.f1822v);
                if (iFindPointerIndex2 < 0) {
                    Log.e(f1804d, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y8 = motionEvent.getY(iFindPointerIndex2);
                t(y8);
                if (this.f1821u) {
                    float f8 = (y8 - this.f1819s) * 0.5f;
                    if (f8 <= 0.0f) {
                        return false;
                    }
                    k(f8);
                }
            } else {
                if (actionMasked == 3) {
                    return false;
                }
                if (actionMasked == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    if (actionIndex < 0) {
                        Log.e(f1804d, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return false;
                    }
                    this.f1822v = motionEvent.getPointerId(actionIndex);
                } else if (actionMasked == 6) {
                    p(motionEvent);
                }
            }
        }
        return true;
    }

    public final void p(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f1822v) {
            this.f1822v = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    public void q() {
        this.f1826z.clearAnimation();
        this.G.stop();
        this.f1826z.setVisibility(8);
        setColorViewAlpha(255);
        if (this.f1823w) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.D - this.f1818r);
        }
        this.f1818r = this.f1826z.getTop();
    }

    public final void r(boolean z7, boolean z8) {
        if (this.f1808h != z7) {
            this.M = z8;
            e();
            this.f1808h = z7;
            if (z7) {
                a(this.f1818r, this.Q);
            } else {
                w(this.Q);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z7) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.f1806f instanceof AbsListView)) {
            View view = this.f1806f;
            if (view == null || t.R(view)) {
                super.requestDisallowInterceptTouchEvent(z7);
            }
        }
    }

    public final Animation s(int i8, int i9) {
        d dVar = new d(i8, i9);
        dVar.setDuration(300L);
        this.f1826z.b(null);
        this.f1826z.clearAnimation();
        this.f1826z.startAnimation(dVar);
        return dVar;
    }

    public void setAnimationProgress(float f8) {
        this.f1826z.setScaleX(f8);
        this.f1826z.setScaleY(f8);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        e();
        this.G.f(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i8 = 0; i8 < iArr.length; i8++) {
            iArr2[i8] = e0.a.b(context, iArr[i8]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i8) {
        this.f1810j = i8;
    }

    @Override // android.view.View
    public void setEnabled(boolean z7) {
        super.setEnabled(z7);
        if (z7) {
            return;
        }
        q();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z7) {
        this.f1813m.n(z7);
    }

    public void setOnChildScrollUpCallback(i iVar) {
        this.P = iVar;
    }

    public void setOnRefreshListener(j jVar) {
        this.f1807g = jVar;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i8) {
        setProgressBackgroundColorSchemeResource(i8);
    }

    public void setProgressBackgroundColorSchemeColor(int i8) {
        this.f1826z.setBackgroundColor(i8);
    }

    public void setProgressBackgroundColorSchemeResource(int i8) {
        setProgressBackgroundColorSchemeColor(e0.a.b(getContext(), i8));
    }

    public void setRefreshing(boolean z7) {
        if (!z7 || this.f1808h == z7) {
            r(z7, false);
            return;
        }
        this.f1808h = z7;
        setTargetOffsetTopAndBottom((!this.O ? this.E + this.D : this.E) - this.f1818r);
        this.M = false;
        y(this.Q);
    }

    public void setSize(int i8) {
        if (i8 == 0 || i8 == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i8 == 0) {
                this.N = (int) (displayMetrics.density * 56.0f);
            } else {
                this.N = (int) (displayMetrics.density * 40.0f);
            }
            this.f1826z.setImageDrawable(null);
            this.G.l(i8);
            this.f1826z.setImageDrawable(this.G);
        }
    }

    public void setSlingshotDistance(int i8) {
        this.F = i8;
    }

    public void setTargetOffsetTopAndBottom(int i8) {
        this.f1826z.bringToFront();
        t.W(this.f1826z, i8);
        this.f1818r = this.f1826z.getTop();
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i8) {
        return this.f1813m.p(i8);
    }

    @Override // android.view.View, o0.j
    public void stopNestedScroll() {
        this.f1813m.r();
    }

    public final void t(float f8) {
        float f9 = this.f1820t;
        float f10 = f8 - f9;
        int i8 = this.f1809i;
        if (f10 <= i8 || this.f1821u) {
            return;
        }
        this.f1819s = f9 + i8;
        this.f1821u = true;
        this.G.setAlpha(76);
    }

    public final void u() {
        this.K = s(this.G.getAlpha(), 255);
    }

    public final void v() {
        this.J = s(this.G.getAlpha(), 76);
    }

    public void w(Animation.AnimationListener animationListener) {
        c cVar = new c();
        this.I = cVar;
        cVar.setDuration(150L);
        this.f1826z.b(animationListener);
        this.f1826z.clearAnimation();
        this.f1826z.startAnimation(this.I);
    }

    public final void x(int i8, Animation.AnimationListener animationListener) {
        this.B = i8;
        this.C = this.f1826z.getScaleX();
        h hVar = new h();
        this.L = hVar;
        hVar.setDuration(150L);
        if (animationListener != null) {
            this.f1826z.b(animationListener);
        }
        this.f1826z.clearAnimation();
        this.f1826z.startAnimation(this.L);
    }

    public final void y(Animation.AnimationListener animationListener) {
        this.f1826z.setVisibility(0);
        this.G.setAlpha(255);
        b bVar = new b();
        this.H = bVar;
        bVar.setDuration(this.f1817q);
        if (animationListener != null) {
            this.f1826z.b(animationListener);
        }
        this.f1826z.clearAnimation();
        this.f1826z.startAnimation(this.H);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1808h = false;
        this.f1810j = -1.0f;
        this.f1814n = new int[2];
        this.f1815o = new int[2];
        this.f1822v = -1;
        this.A = -1;
        this.Q = new a();
        this.R = new f();
        this.S = new g();
        this.f1809i = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f1817q = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.f1825y = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.N = (int) (displayMetrics.density * 40.0f);
        d();
        setChildrenDrawingOrderEnabled(true);
        int i8 = (int) (displayMetrics.density * 64.0f);
        this.E = i8;
        this.f1810j = i8;
        this.f1812l = new o(this);
        this.f1813m = new k(this);
        setNestedScrollingEnabled(true);
        int i9 = -this.N;
        this.f1818r = i9;
        this.D = i9;
        l(1.0f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1805e);
        setEnabled(typedArrayObtainStyledAttributes.getBoolean(0, true));
        typedArrayObtainStyledAttributes.recycle();
    }
}
