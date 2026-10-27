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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import b.b.e.j.m;
import b.b.f.k0;
import b.b.f.o;
import b.b.f.p;
import b.g.j.b0;
import b.g.j.l;
import b.g.j.m;
import b.g.j.n;
import b.g.j.t;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements o, n, l, m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f144b = {R$attr.actionBarSize, R.attr.windowContentOverlay};
    public d A;
    public OverScroller B;
    public ViewPropertyAnimator C;
    public final AnimatorListenerAdapter D;
    public final Runnable E;
    public final Runnable F;
    public final b.g.j.o G;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f145c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f146d;
    public ContentFrameLayout e;
    public ActionBarContainer f;
    public p g;
    public Drawable h;
    public boolean i;
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
            actionBarOverlayLayout.C = actionBarOverlayLayout.f.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.D);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = actionBarOverlayLayout.f.animate().translationY(-ActionBarOverlayLayout.this.f.getHeight()).setListener(ActionBarOverlayLayout.this.D);
        }
    }

    public interface d {
        void a(boolean z);

        void b();

        void c();

        void d(int i);

        void e();

        void f();
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(int i, int i2) {
            super(i, i2);
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

    public final boolean B(float f) {
        this.B.fling(0, 0, 0, (int) f, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.B.getFinalY() > this.f.getHeight();
    }

    @Override // b.b.f.o
    public void a(Menu menu, m.a aVar) {
        z();
        this.g.a(menu, aVar);
    }

    @Override // b.b.f.o
    public boolean b() {
        z();
        return this.g.b();
    }

    @Override // b.b.f.o
    public boolean c() {
        z();
        return this.g.c();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // b.b.f.o
    public boolean d() {
        z();
        return this.g.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.h == null || this.i) {
            return;
        }
        int bottom = this.f.getVisibility() == 0 ? (int) (this.f.getBottom() + this.f.getTranslationY() + 0.5f) : 0;
        this.h.setBounds(0, bottom, getWidth(), this.h.getIntrinsicHeight() + bottom);
        this.h.draw(canvas);
    }

    @Override // b.b.f.o
    public boolean e() {
        z();
        return this.g.e();
    }

    @Override // b.b.f.o
    public void f() {
        z();
        this.g.f();
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.fitSystemWindows(rect);
        }
        z();
        boolean zQ = q(this.f, rect, true, true, false, true);
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

    @Override // b.b.f.o
    public boolean g() {
        z();
        return this.g.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f;
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
        return this.g.getTitle();
    }

    @Override // b.g.j.l
    public void h(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // b.g.j.l
    public void i(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // b.g.j.l
    public void j(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    @Override // b.b.f.o
    public void k(int i) {
        z();
        if (i == 2) {
            this.g.t();
        } else if (i == 5) {
            this.g.v();
        } else {
            if (i != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // b.b.f.o
    public void l() {
        z();
        this.g.h();
    }

    @Override // b.g.j.m
    public void m(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        n(view, i, i2, i3, i4, i5);
    }

    @Override // b.g.j.l
    public void n(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // b.g.j.l
    public boolean o(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        z();
        b0 b0VarP = b0.p(windowInsets);
        boolean zQ = q(this.f, new Rect(b0VarP.f(), b0VarP.h(), b0VarP.g(), b0VarP.e()), true, true, false, true);
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
        t.g0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int measuredHeight;
        z();
        measureChildWithMargins(this.f, i, 0, i2, 0);
        e eVar = (e) this.f.getLayoutParams();
        int iMax = Math.max(0, this.f.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f.getMeasuredState());
        boolean z = (t.I(this) & 256) != 0;
        if (z) {
            measuredHeight = this.f145c;
            if (this.k && this.f.getTabContainer() != null) {
                measuredHeight += this.f145c;
            }
        } else {
            measuredHeight = this.f.getVisibility() != 8 ? this.f.getMeasuredHeight() : 0;
        }
        this.r.set(this.p);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 21) {
            this.y = this.w;
        } else {
            this.u.set(this.s);
        }
        if (!this.j && !z) {
            Rect rect = this.r;
            rect.top += measuredHeight;
            rect.bottom += 0;
            if (i3 >= 21) {
                this.y = this.y.k(0, measuredHeight, 0, 0);
            }
        } else if (i3 >= 21) {
            this.y = new b0.a(this.y).c(b.g.c.b.a(this.y.f(), this.y.h() + measuredHeight, this.y.g(), this.y.e() + 0)).a();
        } else {
            Rect rect2 = this.u;
            rect2.top += measuredHeight;
            rect2.bottom += 0;
        }
        q(this.e, this.r, true, true, true, true);
        if (i3 >= 21 && !this.z.equals(this.y)) {
            b0 b0Var = this.y;
            this.z = b0Var;
            t.g(this.e, b0Var);
        } else if (i3 < 21 && !this.v.equals(this.u)) {
            this.v.set(this.u);
            this.e.a(this.u);
        }
        measureChildWithMargins(this.e, i, 0, i2, 0);
        e eVar2 = (e) this.e.getLayoutParams();
        int iMax3 = Math.max(iMax, this.e.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.e.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.e.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.j.n
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.l || !z) {
            return false;
        }
        if (B(f2)) {
            p();
        } else {
            A();
        }
        this.m = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.j.n
    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.j.n
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.j.n
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.n + i2;
        this.n = i5;
        setActionBarHideOffset(i5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.j.n
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.G.b(view, view2, i);
        this.n = getActionBarHideOffset();
        u();
        d dVar = this.A;
        if (dVar != null) {
            dVar.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.j.n
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.f.getVisibility() != 0) {
            return false;
        }
        return this.l;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.j.n
    public void onStopNestedScroll(View view) {
        if (this.l && !this.m) {
            if (this.n <= this.f.getHeight()) {
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
    public void onWindowSystemUiVisibilityChanged(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i);
        }
        z();
        int i2 = this.o ^ i;
        this.o = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & 256) != 0;
        d dVar = this.A;
        if (dVar != null) {
            dVar.a(!z2);
            if (z || !z2) {
                this.A.b();
            } else {
                this.A.e();
            }
        }
        if ((i2 & 256) == 0 || this.A == null) {
            return;
        }
        t.g0(this);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f146d = i;
        d dVar = this.A;
        if (dVar != null) {
            dVar.d(i);
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

    public void setActionBarHideOffset(int i) {
        u();
        this.f.setTranslationY(-Math.max(0, Math.min(i, this.f.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.A = dVar;
        if (getWindowToken() != null) {
            this.A.d(this.f146d);
            int i = this.o;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                t.g0(this);
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

    public void setIcon(int i) {
        z();
        this.g.setIcon(i);
    }

    public void setLogo(int i) {
        z();
        this.g.m(i);
    }

    public void setOverlayMode(boolean z) {
        this.j = z;
        this.i = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    @Override // b.b.f.o
    public void setWindowCallback(Window.Callback callback) {
        z();
        this.g.setWindowCallback(callback);
    }

    @Override // b.b.f.o
    public void setWindowTitle(CharSequence charSequence) {
        z();
        this.g.setWindowTitle(charSequence);
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
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f144b);
        this.f145c = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.h = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.i = context.getApplicationInfo().targetSdkVersion < 19;
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
        if (this.e == null) {
            this.e = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
            this.f = (ActionBarContainer) findViewById(R$id.action_bar_container);
            this.g = t(findViewById(R$id.action_bar));
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f146d = 0;
        this.p = new Rect();
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new Rect();
        this.v = new Rect();
        b0 b0Var = b0.f1097a;
        this.w = b0Var;
        this.x = b0Var;
        this.y = b0Var;
        this.z = b0Var;
        this.D = new a();
        this.E = new b();
        this.F = new c();
        v(context);
        this.G = new b.g.j.o(this);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        z();
        this.g.setIcon(drawable);
    }
}
