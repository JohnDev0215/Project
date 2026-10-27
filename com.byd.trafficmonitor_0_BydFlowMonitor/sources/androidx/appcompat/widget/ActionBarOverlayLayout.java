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
import b.b.f;
import b.b.p.j.m;
import b.b.q.k0;
import b.b.q.o;
import b.b.q.p;
import b.h.m.b0;
import b.h.m.l;
import b.h.m.m;
import b.h.m.n;
import b.h.m.t;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements o, n, l, m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f354b = {b.b.a.actionBarSize, R.attr.windowContentOverlay};
    public d A;
    public OverScroller B;
    public ViewPropertyAnimator C;
    public final AnimatorListenerAdapter D;
    public final Runnable E;
    public final Runnable F;
    public final b.h.m.o G;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f355c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f356d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ContentFrameLayout f357e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionBarContainer f358f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public p f359g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f360h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f361i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public int n;
    public int o;
    public final Rect p;
    public final Rect q;
    public final Rect r;
    public final Rect s;
    public final Rect t;
    public final Rect u;
    public final Rect v;
    public b0 w;
    public b0 x;
    public b0 y;
    public b0 z;

    public class a extends AnimatorListenerAdapter {
        public a() {
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

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = actionBarOverlayLayout.f358f.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.D);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = actionBarOverlayLayout.f358f.animate().translationY(-ActionBarOverlayLayout.this.f358f.getHeight()).setListener(ActionBarOverlayLayout.this.D);
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

    public final void A() {
        u();
        this.E.run();
    }

    public final boolean B(float f2) {
        this.B.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.B.getFinalY() > this.f358f.getHeight();
    }

    @Override // b.b.q.o
    public void a(Menu menu, m.a aVar) {
        z();
        this.f359g.a(menu, aVar);
    }

    @Override // b.b.q.o
    public boolean b() {
        z();
        return this.f359g.b();
    }

    @Override // b.b.q.o
    public boolean c() {
        z();
        return this.f359g.c();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // b.b.q.o
    public boolean d() {
        z();
        return this.f359g.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f360h == null || this.f361i) {
            return;
        }
        int bottom = this.f358f.getVisibility() == 0 ? (int) (this.f358f.getBottom() + this.f358f.getTranslationY() + 0.5f) : 0;
        this.f360h.setBounds(0, bottom, getWidth(), this.f360h.getIntrinsicHeight() + bottom);
        this.f360h.draw(canvas);
    }

    @Override // b.b.q.o
    public boolean e() {
        z();
        return this.f359g.e();
    }

    @Override // b.b.q.o
    public void f() {
        z();
        this.f359g.f();
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.fitSystemWindows(rect);
        }
        z();
        boolean zQ = q(this.f358f, rect, true, true, false, true);
        this.s.set(rect);
        k0.a(this, this.s, this.p);
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

    @Override // b.b.q.o
    public boolean g() {
        z();
        return this.f359g.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f358f;
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
        return this.f359g.getTitle();
    }

    @Override // b.h.m.l
    public void h(View view, View view2, int i2, int i3) {
        if (i3 == 0) {
            onNestedScrollAccepted(view, view2, i2);
        }
    }

    @Override // b.h.m.l
    public void i(View view, int i2) {
        if (i2 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // b.h.m.l
    public void j(View view, int i2, int i3, int[] iArr, int i4) {
        if (i4 == 0) {
            onNestedPreScroll(view, i2, i3, iArr);
        }
    }

    @Override // b.b.q.o
    public void k(int i2) {
        z();
        if (i2 == 2) {
            this.f359g.t();
        } else if (i2 == 5) {
            this.f359g.v();
        } else {
            if (i2 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // b.b.q.o
    public void l() {
        z();
        this.f359g.h();
    }

    @Override // b.h.m.m
    public void m(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        n(view, i2, i3, i4, i5, i6);
    }

    @Override // b.h.m.l
    public void n(View view, int i2, int i3, int i4, int i5, int i6) {
        if (i6 == 0) {
            onNestedScroll(view, i2, i3, i4, i5);
        }
    }

    @Override // b.h.m.l
    public boolean o(View view, View view2, int i2, int i3) {
        return i3 == 0 && onStartNestedScroll(view, view2, i2);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        z();
        b0 b0VarP = b0.p(windowInsets);
        boolean zQ = q(this.f358f, new Rect(b0VarP.f(), b0VarP.h(), b0VarP.g(), b0VarP.e()), true, true, false, true);
        t.f(this, b0VarP, this.p);
        Rect rect = this.p;
        b0 b0VarK = b0VarP.k(rect.left, rect.top, rect.right, rect.bottom);
        this.w = b0VarK;
        boolean z = true;
        if (!this.x.equals(b0VarK)) {
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
        return b0VarP.a().c().b().o();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        v(getContext());
        t.h0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
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
    public void onMeasure(int i2, int i3) {
        int measuredHeight;
        z();
        measureChildWithMargins(this.f358f, i2, 0, i3, 0);
        e eVar = (e) this.f358f.getLayoutParams();
        int iMax = Math.max(0, this.f358f.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f358f.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f358f.getMeasuredState());
        boolean z = (t.I(this) & 256) != 0;
        if (z) {
            measuredHeight = this.f355c;
            if (this.k && this.f358f.getTabContainer() != null) {
                measuredHeight += this.f355c;
            }
        } else {
            measuredHeight = this.f358f.getVisibility() != 8 ? this.f358f.getMeasuredHeight() : 0;
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
                this.y = this.y.k(0, measuredHeight, 0, 0);
            }
        } else if (i4 >= 21) {
            this.y = new b0.a(this.y).c(b.h.f.b.a(this.y.f(), this.y.h() + measuredHeight, this.y.g(), this.y.e() + 0)).a();
        } else {
            Rect rect2 = this.u;
            rect2.top += measuredHeight;
            rect2.bottom += 0;
        }
        q(this.f357e, this.r, true, true, true, true);
        if (i4 >= 21 && !this.z.equals(this.y)) {
            b0 b0Var = this.y;
            this.z = b0Var;
            t.g(this.f357e, b0Var);
        } else if (i4 < 21 && !this.v.equals(this.u)) {
            this.v.set(this.u);
            this.f357e.a(this.u);
        }
        measureChildWithMargins(this.f357e, i2, 0, i3, 0);
        e eVar2 = (e) this.f357e.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f357e.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f357e.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f357e.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.h.m.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        if (!this.l || !z) {
            return false;
        }
        if (B(f3)) {
            p();
        } else {
            A();
        }
        this.m = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.h.m.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.h.m.n
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.h.m.n
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        int i6 = this.n + i3;
        this.n = i6;
        setActionBarHideOffset(i6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.h.m.n
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.G.b(view, view2, i2);
        this.n = getActionBarHideOffset();
        u();
        d dVar = this.A;
        if (dVar != null) {
            dVar.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.h.m.n
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        if ((i2 & 2) == 0 || this.f358f.getVisibility() != 0) {
            return false;
        }
        return this.l;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.h.m.n
    public void onStopNestedScroll(View view) {
        if (this.l && !this.m) {
            if (this.n <= this.f358f.getHeight()) {
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
        t.h0(this);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.f356d = i2;
        d dVar = this.A;
        if (dVar != null) {
            dVar.d(i2);
        }
    }

    public final void p() {
        u();
        this.F.run();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean q(android.view.View r3, android.graphics.Rect r4, boolean r5, boolean r6, boolean r7, boolean r8) {
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
        this.f358f.setTranslationY(-Math.max(0, Math.min(i2, this.f358f.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.A = dVar;
        if (getWindowToken() != null) {
            this.A.d(this.f356d);
            int i2 = this.o;
            if (i2 != 0) {
                onWindowSystemUiVisibilityChanged(i2);
                t.h0(this);
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
        this.f359g.setIcon(i2);
    }

    public void setLogo(int i2) {
        z();
        this.f359g.m(i2);
    }

    public void setOverlayMode(boolean z) {
        this.j = z;
        this.f361i = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i2) {
    }

    @Override // b.b.q.o
    public void setWindowCallback(Window.Callback callback) {
        z();
        this.f359g.setWindowCallback(callback);
    }

    @Override // b.b.q.o
    public void setWindowTitle(CharSequence charSequence) {
        z();
        this.f359g.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final p t(View view) {
        if (view instanceof p) {
            return (p) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    public void u() {
        removeCallbacks(this.E);
        removeCallbacks(this.F);
        ViewPropertyAnimator viewPropertyAnimator = this.C;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void v(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f354b);
        this.f355c = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f360h = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f361i = context.getApplicationInfo().targetSdkVersion < 19;
        this.B = new OverScroller(context);
    }

    public boolean w() {
        return this.j;
    }

    public final void x() {
        u();
        postDelayed(this.F, 600L);
    }

    public final void y() {
        u();
        postDelayed(this.E, 600L);
    }

    public void z() {
        if (this.f357e == null) {
            this.f357e = (ContentFrameLayout) findViewById(f.action_bar_activity_content);
            this.f358f = (ActionBarContainer) findViewById(f.action_bar_container);
            this.f359g = t(findViewById(f.action_bar));
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f356d = 0;
        this.p = new Rect();
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new Rect();
        this.v = new Rect();
        b0 b0Var = b0.f2587a;
        this.w = b0Var;
        this.x = b0Var;
        this.y = b0Var;
        this.z = b0Var;
        this.D = new a();
        this.E = new b();
        this.F = new c();
        v(context);
        this.G = new b.h.m.o(this);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        z();
        this.f359g.setIcon(drawable);
    }
}
