package androidx.appcompat.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.view.menu.m;
import b.g.k.b0;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements o, b.g.k.n, b.g.k.l, b.g.k.m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f372b = {b.a.a.actionBarSize, R.attr.windowContentOverlay};
    private d A;
    private OverScroller B;
    ViewPropertyAnimator C;
    final AnimatorListenerAdapter D;
    private final Runnable E;
    private final Runnable F;
    private final b.g.k.o G;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f373c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f374d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ContentFrameLayout f375e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    ActionBarContainer f376f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private p f377g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Drawable f378h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f379i;
    private boolean j;
    private boolean k;
    private boolean l;
    boolean m;
    private int n;
    private int o;
    private final Rect p;
    private final Rect q;
    private final Rect r;
    private final Rect s;
    private final Rect t;
    private final Rect u;
    private final Rect v;
    private b.g.k.b0 w;
    private b.g.k.b0 x;
    private b.g.k.b0 y;
    private b.g.k.b0 z;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = null;
            actionBarOverlayLayout.m = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = null;
            actionBarOverlayLayout.m = false;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = actionBarOverlayLayout.f376f.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.D);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = actionBarOverlayLayout.f376f.animate().translationY(-ActionBarOverlayLayout.this.f376f.getHeight()).setListener(ActionBarOverlayLayout.this.D);
        }
    }

    public interface d {
        void a(boolean z);

        void b();

        void c();

        void d(int i2);

        void e();

        void f();
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(int i2, int i3) {
            super(i2, i3);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    private void A() {
        u();
        this.E.run();
    }

    private boolean B(float f2) {
        this.B.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.B.getFinalY() > this.f376f.getHeight();
    }

    private void a() {
        u();
        this.F.run();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean q(android.view.View r3, android.graphics.Rect r4, boolean r5, boolean r6, boolean r7, boolean r8) {
        /*
            r2 = this;
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            androidx.appcompat.widget.ActionBarOverlayLayout$e r3 = (androidx.appcompat.widget.ActionBarOverlayLayout.e) r3
            r0 = 1
            if (r5 == 0) goto L13
            int r5 = r3.leftMargin
            int r1 = r4.left
            if (r5 == r1) goto L13
            r3.leftMargin = r1
            r5 = r0
            goto L14
        L13:
            r5 = 0
        L14:
            if (r6 == 0) goto L1f
            int r6 = r3.topMargin
            int r1 = r4.top
            if (r6 == r1) goto L1f
            r3.topMargin = r1
            r5 = r0
        L1f:
            if (r8 == 0) goto L2a
            int r6 = r3.rightMargin
            int r8 = r4.right
            if (r6 == r8) goto L2a
            r3.rightMargin = r8
            r5 = r0
        L2a:
            if (r7 == 0) goto L35
            int r6 = r3.bottomMargin
            int r4 = r4.bottom
            if (r6 == r4) goto L35
            r3.bottomMargin = r4
            goto L36
        L35:
            r0 = r5
        L36:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.q(android.view.View, android.graphics.Rect, boolean, boolean, boolean, boolean):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private p t(View view) {
        if (view instanceof p) {
            return (p) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private void v(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f372b);
        this.f373c = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f378h = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f379i = context.getApplicationInfo().targetSdkVersion < 19;
        this.B = new OverScroller(context);
    }

    private void x() {
        u();
        postDelayed(this.F, 600L);
    }

    private void y() {
        u();
        postDelayed(this.E, 600L);
    }

    @Override // androidx.appcompat.widget.o
    public void b(Menu menu, m.a aVar) {
        z();
        this.f377g.b(menu, aVar);
    }

    @Override // androidx.appcompat.widget.o
    public boolean c() {
        z();
        return this.f377g.c();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // androidx.appcompat.widget.o
    public boolean d() {
        z();
        return this.f377g.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f378h == null || this.f379i) {
            return;
        }
        int bottom = this.f376f.getVisibility() == 0 ? (int) (this.f376f.getBottom() + this.f376f.getTranslationY() + 0.5f) : 0;
        this.f378h.setBounds(0, bottom, getWidth(), this.f378h.getIntrinsicHeight() + bottom);
        this.f378h.draw(canvas);
    }

    @Override // androidx.appcompat.widget.o
    public boolean e() {
        z();
        return this.f377g.e();
    }

    @Override // androidx.appcompat.widget.o
    public boolean f() {
        z();
        return this.f377g.f();
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.fitSystemWindows(rect);
        }
        z();
        boolean zQ = q(this.f376f, rect, true, true, false, true);
        this.s.set(rect);
        l0.a(this, this.s, this.p);
        if (!this.t.equals(this.s)) {
            this.t.set(this.s);
            zQ = true;
        }
        if (!this.q.equals(this.p)) {
            this.q.set(this.p);
            zQ = true;
        }
        if (zQ) {
            requestLayout();
        }
        return true;
    }

    @Override // androidx.appcompat.widget.o
    public void g() {
        z();
        this.f377g.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f376f;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.G.a();
    }

    public CharSequence getTitle() {
        z();
        return this.f377g.getTitle();
    }

    @Override // androidx.appcompat.widget.o
    public boolean h() {
        z();
        return this.f377g.h();
    }

    @Override // b.g.k.l
    public void i(View view, View view2, int i2, int i3) {
        if (i3 == 0) {
            onNestedScrollAccepted(view, view2, i2);
        }
    }

    @Override // b.g.k.l
    public void j(View view, int i2) {
        if (i2 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // b.g.k.l
    public void k(View view, int i2, int i3, int[] iArr, int i4) {
        if (i4 == 0) {
            onNestedPreScroll(view, i2, i3, iArr);
        }
    }

    @Override // androidx.appcompat.widget.o
    public void l(int i2) {
        z();
        if (i2 == 2) {
            this.f377g.t();
        } else if (i2 == 5) {
            this.f377g.v();
        } else {
            if (i2 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // androidx.appcompat.widget.o
    public void m() {
        z();
        this.f377g.i();
    }

    @Override // b.g.k.m
    public void n(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        o(view, i2, i3, i4, i5, i6);
    }

    @Override // b.g.k.l
    public void o(View view, int i2, int i3, int i4, int i5, int i6) {
        if (i6 == 0) {
            onNestedScroll(view, i2, i3, i4, i5);
        }
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        z();
        b.g.k.b0 b0VarQ = b.g.k.b0.q(windowInsets);
        boolean zQ = q(this.f376f, new Rect(b0VarQ.g(), b0VarQ.i(), b0VarQ.h(), b0VarQ.f()), true, true, false, true);
        b.g.k.t.f(this, b0VarQ, this.p);
        Rect rect = this.p;
        b.g.k.b0 b0VarL = b0VarQ.l(rect.left, rect.top, rect.right, rect.bottom);
        this.w = b0VarL;
        boolean z = true;
        if (!this.x.equals(b0VarL)) {
            this.x = this.w;
            zQ = true;
        }
        if (this.q.equals(this.p)) {
            z = zQ;
        } else {
            this.q.set(this.p);
        }
        if (z) {
            requestLayout();
        }
        return b0VarQ.a().c().b().p();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        v(getContext());
        b.g.k.t.g0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i7 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i8 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int measuredHeight;
        z();
        measureChildWithMargins(this.f376f, i2, 0, i3, 0);
        e eVar = (e) this.f376f.getLayoutParams();
        int iMax = Math.max(0, this.f376f.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f376f.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f376f.getMeasuredState());
        boolean z = (b.g.k.t.I(this) & 256) != 0;
        if (z) {
            measuredHeight = this.f373c;
            if (this.k && this.f376f.getTabContainer() != null) {
                measuredHeight += this.f373c;
            }
        } else {
            measuredHeight = this.f376f.getVisibility() != 8 ? this.f376f.getMeasuredHeight() : 0;
        }
        this.r.set(this.p);
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 21) {
            this.y = this.w;
        } else {
            this.u.set(this.s);
        }
        if (!this.j && !z) {
            Rect rect = this.r;
            rect.top += measuredHeight;
            rect.bottom += 0;
            if (i4 >= 21) {
                this.y = this.y.l(0, measuredHeight, 0, 0);
            }
        } else if (i4 >= 21) {
            this.y = new b0.a(this.y).c(b.g.d.b.a(this.y.g(), this.y.i() + measuredHeight, this.y.h(), this.y.f() + 0)).a();
        } else {
            Rect rect2 = this.u;
            rect2.top += measuredHeight;
            rect2.bottom += 0;
        }
        q(this.f375e, this.r, true, true, true, true);
        if (i4 >= 21 && !this.z.equals(this.y)) {
            b.g.k.b0 b0Var = this.y;
            this.z = b0Var;
            b.g.k.t.g(this.f375e, b0Var);
        } else if (i4 < 21 && !this.v.equals(this.u)) {
            this.v.set(this.u);
            this.f375e.a(this.u);
        }
        measureChildWithMargins(this.f375e, i2, 0, i3, 0);
        e eVar2 = (e) this.f375e.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f375e.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f375e.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f375e.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        if (!this.l || !z) {
            return false;
        }
        if (B(f3)) {
            a();
        } else {
            A();
        }
        this.m = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        int i6 = this.n + i3;
        this.n = i6;
        setActionBarHideOffset(i6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.G.b(view, view2, i2);
        this.n = getActionBarHideOffset();
        u();
        d dVar = this.A;
        if (dVar != null) {
            dVar.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        if ((i2 & 2) == 0 || this.f376f.getVisibility() != 0) {
            return false;
        }
        return this.l;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public void onStopNestedScroll(View view) {
        if (this.l && !this.m) {
            if (this.n <= this.f376f.getHeight()) {
                y();
            } else {
                x();
            }
        }
        d dVar = this.A;
        if (dVar != null) {
            dVar.f();
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i2);
        }
        z();
        int i3 = this.o ^ i2;
        this.o = i2;
        boolean z = (i2 & 4) == 0;
        boolean z2 = (i2 & 256) != 0;
        d dVar = this.A;
        if (dVar != null) {
            dVar.a(!z2);
            if (z || !z2) {
                this.A.b();
            } else {
                this.A.e();
            }
        }
        if ((i3 & 256) == 0 || this.A == null) {
            return;
        }
        b.g.k.t.g0(this);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.f374d = i2;
        d dVar = this.A;
        if (dVar != null) {
            dVar.d(i2);
        }
    }

    @Override // b.g.k.l
    public boolean p(View view, View view2, int i2, int i3) {
        return i3 == 0 && onStartNestedScroll(view, view2, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public e generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public void setActionBarHideOffset(int i2) {
        u();
        this.f376f.setTranslationY(-Math.max(0, Math.min(i2, this.f376f.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.A = dVar;
        if (getWindowToken() != null) {
            this.A.d(this.f374d);
            int i2 = this.o;
            if (i2 != 0) {
                onWindowSystemUiVisibilityChanged(i2);
                b.g.k.t.g0(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.k = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.l) {
            this.l = z;
            if (z) {
                return;
            }
            u();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i2) {
        z();
        this.f377g.setIcon(i2);
    }

    public void setLogo(int i2) {
        z();
        this.f377g.n(i2);
    }

    public void setOverlayMode(boolean z) {
        this.j = z;
        this.f379i = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i2) {
    }

    @Override // androidx.appcompat.widget.o
    public void setWindowCallback(Window.Callback callback) {
        z();
        this.f377g.setWindowCallback(callback);
    }

    @Override // androidx.appcompat.widget.o
    public void setWindowTitle(CharSequence charSequence) {
        z();
        this.f377g.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    void u() {
        removeCallbacks(this.E);
        removeCallbacks(this.F);
        ViewPropertyAnimator viewPropertyAnimator = this.C;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public boolean w() {
        return this.j;
    }

    void z() {
        if (this.f375e == null) {
            this.f375e = (ContentFrameLayout) findViewById(b.a.f.action_bar_activity_content);
            this.f376f = (ActionBarContainer) findViewById(b.a.f.action_bar_container);
            this.f377g = t(findViewById(b.a.f.action_bar));
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f374d = 0;
        this.p = new Rect();
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new Rect();
        this.v = new Rect();
        b.g.k.b0 b0Var = b.g.k.b0.f2671a;
        this.w = b0Var;
        this.x = b0Var;
        this.y = b0Var;
        this.z = b0Var;
        this.D = new a();
        this.E = new b();
        this.F = new c();
        v(context);
        this.G = new b.g.k.o(this);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        z();
        this.f377g.setIcon(drawable);
    }
}
